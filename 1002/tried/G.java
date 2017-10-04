import java.util.*;
import java.io.*;
import java.math.*;
public class G {
    static class Point implements Comparable<Point> {
        static Point xmin;
        int x, y, id;
        public Point(int x, int y, int id) {
            this.x = x;this.y = y; this.id = id;
        }
        public int compareTo(Point p) {
            int c = cross(this, xmin, p);
            if(c!=0) return c;
            double d = dist(this,xmin) - dist(p,xmin);
            return (int) Math.signum(d);
        }
    }
    static double dist(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
    static int cross(Point a, Point b, Point c) {
        int dx1 = b.x - a.x;
        int dy1 = b.y - a.y;
        int dx2 = c.x - b.x;
        int dy2 = c.y - b.y;
        return dx1*dy2 - dx2*dy1;
    }
    Point[] convexHull(Point[] S) {
        int N = S.length;
        // find a point on the convex hull.
        Point xmin = S[0];
        int id = 0;
        for(int i = 0; i<N; i++) {
            Point p = S[i];
            if(xmin.x > p.x || 
                    xmin.x == p.x && xmin.y > p.y) {
                xmin = p;
                id = i;
                    }
        }
        S[id] = S[N-1];
        S[N-1] = xmin;
        Point.xmin = xmin;
        // Sort on angle to xmin.
        Arrays.sort(S, 0, N-1);
        Point[] H = new Point[N+1];
        H[0] = S[N-2];
        H[1] = xmin;
        for(int i = 0; i<N-1; i++)
            H[i+2] = S[i];
        int M = 1;
        // swipe over the points
        for(int i = 2; i<=N; i++) {
            while(cross(H[M-1],H[M],H[i]) <= 0) {
                if(M>1)
                    M--;
                else if (i == N)
                    break;
                else
                    i += 1;
            }
            M+=1;
            Point tmp = H[M];
            H[M] = H[i];
            H[i] = tmp;
        }
        Point[] Hull = new Point[M];
        for(int i = 0; i<M; i++) 
            Hull[i] = H[i];
        return Hull;
    }
    void solve(BufferedReader in) throws Exception {
        int n = toInt(in.readLine());
        Point[] pts = new Point[n];
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i<n; i++) {
            int[] xx = toInts(in.readLine());
            String s = xx[0] + " " + xx[1];
            if(!set.contains(s))
                pts[set.size()] = new Point(xx[0], xx[1], i+1);
            set.add(s);
        }
        Point[] S = new Point[set.size()];
        for(int i = 0; i<set.size(); i++) {
            S[i] = pts[i];
        }
        Point[] H = convexHull(S);
        StringBuilder sb = new StringBuilder();
        sb.append(H.length).append('\n');
        BigDecimal perim = new BigDecimal(0.0);
        BigInteger area = BigInteger.ZERO;
        for(int i = 0; i<H.length; i++) {
            Point P = H[i], Q = H[(i+1)%H.length];
            sb.append(P.id).append(' ');
            perim = perim.add(new BigDecimal(dist(P, Q)));
            BigInteger Px = BigInteger.valueOf(P.x);
            BigInteger Py = BigInteger.valueOf(P.y);
            BigInteger Qx = BigInteger.valueOf(Q.x);
            BigInteger Qy = BigInteger.valueOf(Q.y);
            area = area.add(Px.multiply(Qy)).subtract(Py.multiply(Qx));
        }
        BigInteger two = BigInteger.valueOf(2L);
        area = area.abs();
        sb.append('\n').append(perim.toString()).append('\n');
        sb.append(area.divide(two).toString());
        if(!area.mod(two).equals(BigInteger.ZERO)) {
            sb.append(".5");
        }
        System.out.println(sb);

        

    }
    int toInt(String s) {return Integer.parseInt(s);}
    int[] toInts(String s) {
        String[] a = s.split(" ");
        int[] o = new int[a.length];
        for(int i = 0; i<a.length; i++) o[i] = toInt(a[i]);
        return o;
    }
    void e(Object o) {
        System.err.println(o);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        (new G()).solve(in);
    }
}
