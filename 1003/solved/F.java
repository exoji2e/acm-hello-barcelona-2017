import java.util.*;
import java.io.*;
public class F {
    static Random r = new Random();
    static class Treap{
        int v, sz;
        Treap L, R;
    }
    static int sz(Treap t) {
        if(t == null) return 0;
        return t.sz;
    }

    static Treap merge(Treap a, Treap b) {
        if (a == null) return b;
        if(b == null) return a;
        int n = sz(a), m = sz(b);
        if (r.nextDouble()*(n+m) < n) {
            Treap t = new Treap();
            t.v = a.v;
            t.L = a.L;
            t.R = merge(a.R, b);
            t.sz = 1 + sz(t.R) + sz(t.L);
            return t;
        } else {
            Treap t = new Treap();
            t.v = b.v;
            t.R = b.R;
            t.L = merge(a, b.L);
            t.sz = 1 + sz(t.R) + sz(t.L);
            return t;
        }
    }

    static Treap[] Left(Treap t) {
        if (t == null) return null;
        if (t.L == null) {
            Treap x = new Treap();
            x.v = t.v;
            return new Treap[]{t.R, x};
        }
        Treap n = new Treap();
        n.R = t.R;
        n.v = t.v;
        Treap[] p = Left(t.L);
        n.L = p[0];
        n.sz = 1 + sz(n.R) + sz(n.L);
        p[0] = n;
        return p;
    }

    static Treap push(Treap t, int x) {
        Treap m = new Treap();
        m.v = x;
        m.sz = 1;
        return merge(t, m);
    }

    void solve(BufferedReader in) throws Exception {
        int n = toInt(in.readLine());
        Treap[] trps = new Treap[n+1];
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=n; i++) {
            int[] l = toInts(in.readLine());
            if(l[0] == 1) {
                trps[i] = push(trps[l[1]], l[2]);
            } else {
                Treap[] p = Left(trps[l[1]]);
                sb.append(p[1].v).append('\n');
                trps[i] = p[0];
            }
        }
        System.out.print(sb);
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
