import java.util.*;
import java.io.*;
public class A {
    class Point implements Comparable<Point> {
        int x, y;
        public Point(int x, int y) {
            this.x = x; this.y=y;
        }
        public int compareTo(Point p) {
            return x - p.x;
        }
    }
    private static class BIT {
        long[] data;
        public BIT(int size) {
            data = new long[size+1];
        }
        public void update(int i, int delta) {
            while(i< data.length) {
                data[i] += delta;
                i += i&-i;
            }
        }
        public long sum(int i) {
            long sum = 0;
            while(i>0) {
                sum += data[i];
                i -= i&-i;
            }
            return sum;
        }
    }
    class Query implements Comparable<Query> {
        int lo, hi, x, mul;
        public Query(int lo, int hi, int x, int mul) {
            this.lo = lo; this.hi = hi; this.x = x; this.mul = mul;
        }
        public int compareTo(Query q) {
            return x - q.x;
        }
    }
    void solve(BufferedReader in) throws Exception {
        int[] xx = toInts(in.readLine());
        int n = xx[0], m = xx[1];
        Point[] pts = new Point[n];
        for(int i = 0; i<n; i++) {
            xx = toInts(in.readLine());
            pts[i] = new Point(xx[0], xx[1]);
        }
        Arrays.sort(pts);
        Query[] qs = new Query[2*m];
        for(int i = 0; i<m; i++) {
            xx = toInts(in.readLine());
            int l = xx[0], r = xx[1], b = xx[2], t = xx[3];
            qs[2*i] = new Query(b, t, l-1, -1);
            qs[2*i+1] = new Query(b, t, r, 1);
        }
        Arrays.sort(qs);
        int px = 0;
        BIT bit = new BIT(100003);
        long sum = 0;
        for(Query q: qs) {
            while(px < pts.length && pts[px].x <= q.x) {
                bit.update(pts[px++].y + 1, 1);
            }
            sum += q.mul*(bit.sum(q.hi+1)-bit.sum(q.lo));
        }
        System.out.println(sum);

    }
    static int toInt(String s) {return Integer.parseInt(s);}
    static int[] toInts(String s) {
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
        A a = (new A());
        int T = toInt(in.readLine());
        for(int i = 0; i<T; i++) {
            a.solve(in);
        }
    }
}
