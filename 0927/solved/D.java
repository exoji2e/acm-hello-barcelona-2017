import java.util.*;
import java.io.*;
public class D {
    private class Node implements Comparable<Node>{
        HashMap<Node, Integer> edgs = new HashMap<>();
        int w;
        int id;
        int layer;
        public Node(int id, int layer) {
            w = Integer.MAX_VALUE;
            this.id = id;
            this.layer = layer;
        }
        public int compareTo(Node n) {
            if(w != n.w) return w - n.w;
            if(id != n.id) return id - n.id;
            return layer - n.layer;
        }
        //Asumes all nodes have weight MAXINT.
        public int djikstra(TreeSet<Node> set, int n) {
            while(!set.isEmpty()) {
                Node curr = set.pollFirst();
                if(curr.layer == n) return curr.w;
                for(Node other: curr.edgs.keySet()) {
                    int c = curr.edgs.get(other);
                    if(other.w > c + curr.w) {
                        set.remove(other);
                        other.w = c + curr.w;
                        set.add(other);
                    }
                }
            }
            return -1;
        }
    }
    void solve(BufferedReader in) throws Exception {
        int[] xx = toInts(in.readLine());
        int n = xx[0], m = xx[1], e = xx[2];
        int[] R = toInts(in.readLine());
        Node[][] nodes = new Node[m][n+1];
        for(int i = 0; i<m; i++) for(int j = 0; j<=n; j++) nodes[i][j] = new Node(i, j);
        for(int i = 0; i<e; i++) {
            xx = toInts(in.readLine());
            for(int j = 0; j<=n; j++) {
                nodes[xx[0]][j].edgs.put(nodes[xx[1]][j], xx[2]);
            }
        }
        for(int i = 0; i<m; i++) {
            nodes[i][R[i]].edgs.put(nodes[i][R[i]+1], 0);
        }
        
        TreeSet<Node> set = new TreeSet<>();
        for(int i = 0; i<m; i++) {
            nodes[i][0].w = 0;
            set.add(nodes[i][0]);
        }

        System.out.println(nodes[0][0].djikstra(set, n));

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
        (new D()).solve(in);
    }
}
