import java.util.*;
import java.io.*;
public class F {
    class Node implements Comparable<Node> {
        int[] x, y;
        long area2;
        int id;
        int w;
        LinkedList<Node> edgs = new LinkedList<>();
        public Node(int id, int[] pts) {
            this.id = id;
            x = new int[pts[0]];
            y = new int[pts[0]];
            for(int i = 0; i<pts[0]; i++) {
                x[i] = pts[i*2 + 1];
                y[i] = pts[i*2 + 2];
            }
            long sum = 0;
            for(int i = 0; i<pts[0]-1; i++) sum += x[i]*y[i+1] - x[i+1]*y[i];
            sum += x[pts[0] - 1]*y[0] - x[0]*y[pts[0] - 1];
            area2 = Math.abs(sum);
            w = 400;
        }
        public int compareTo(Node n) {
            if(w != n.w) return w - n.w;
            return id - n.id;
        }
        public ArrayList<Integer> djikstra(int C) {
            this.w = 0;
            TreeSet<Node> set = new TreeSet<>();
            ArrayList<Integer> fire = new ArrayList<>();
            set.add(this);
            while(!set.isEmpty()) {
                Node curr = set.pollFirst();
                if(curr.w > C) break;
                fire.add(curr.id);
                for(Node other: curr.edgs) {
                    long u = 10*other.area2, l = curr.area2;
                    long fd = Math.floorDiv(u, l);
                    long add = (Math.floorMod(u,l) > 0)? 1: 0;
                    long d = fd + add + curr.w;
                    if(other.w > d) {
                        set.remove(other);
                        other.w = (int)d;
                        set.add(other);
                    }
                }
            }
            return fire;
        }
    }
    void solve(BufferedReader in) throws Exception {
        int N = toInt(in.readLine());
        Node[] nodes = new Node[N];
        for(int i = 0; i<N; i++) {
            nodes[i] = new Node(i, toInts(in.readLine()));
        }
        for(Node n: nodes) for(Node m: nodes) {
            if(n == m) break;
            boolean found = false;
            for(int i = 0; i<n.x.length && !found; i++) 
                for(int j = 0; j<m.x.length; j++) {
                    int ii = i + 1;
                    if(ii == n.x.length) ii = 0;
                    int jj = j + 1;
                    if(jj == m.x.length) jj = 0;
                    if(close(n.x[i], n.y[i], n.x[ii], n.y[ii], m.x[j], m.y[j])) found = true;
                    if(close(m.x[j], m.y[j], m.x[jj], m.y[jj], n.x[i], n.y[i])) found = true;
                }
            if(found) {
                n.edgs.add(m);
                m.edgs.add(n);
            }
        }
        int P = toInt(in.readLine());
        int C = toInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> fire = nodes[P].djikstra(C);
        Collections.sort(fire);
        for(int i: fire)
            sb.append(i).append(' ');
        System.out.println(sb);


    }
    boolean close(int x1, int y1, int x2, int y2, int x3, int y3) {
        //ax + by + c = 0
        //(y-y1)(x2-x1) = (y2 - y1)(x-x1)
        int b = x2 - x1;
        int a = y1 - y2;
        int c = x1*y2 - y1*x2;
        double x = (b*(b*x3 - a*y3) - a*c)/(a*a + b*b + 0.0);
        if((x - x1)*(x-x2) < 0) {
            return Math.abs(a*x3 + b*y3 + c)/Math.hypot(a, b) < 30.0;
        }
        else {
            int dx1 = x1 - x3;
            int dy1 = y1 - y3;
            int dx2 = x2 - x3;
            int dy2 = y2 - y3;
            return dx1*dx1 + dy1*dy1 < 900 || dx2*dx2 + dy2*dy2 < 900;
        }
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
        (new F()).solve(in);
    }
}
