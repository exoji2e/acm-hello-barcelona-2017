import java.util.*;
import java.io.*;
public class B {
    void solve(BufferedReader in) throws Exception {
        int n = toInt(in.readLine());
        int[][] pts = new int[n][2];
        for(int i = 0; i<n; i++) pts[i] = toInts(in.readLine());
        int[] d = new int[n];
        for(int i = 0; i<n; i++) d[i] = dist(pts[0], pts[i]);
        int sum = 0;
        for(int t = 1; t<n; t++) {
             int min = Integer.MAX_VALUE;
             int minid = -1;
             for(int i = 0; i<n; i++) if(d[i] != 0 && d[i] < min) {
                 min = d[i];
                 minid = i;
             }
             sum += min;
             for(int i = 0; i<n; i++) d[i] = Math.min(d[i], dist(pts[minid], pts[i]));
        }
        System.out.println(sum*2);
    }
    int dist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
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
        (new B()).solve(in);
    }
}
