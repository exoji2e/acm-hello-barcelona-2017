import java.util.*;
import java.io.*;
public class G {
    static Random r = new Random(42);
    static class Treap{
        int v, sz;
        Treap L, R;
    }
    static int sz(Treap t) {
        if(t == null) return 0;
        return t.sz;
    }
    static void update(Treap t) {
        if(t == null) return;
        t.sz = sz(t.L) + sz(t.R) + 1;
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

    static Treap[] split(Treap t, int x) {
        if (t == null) return new Treap[2];
        if (t.sz < x) return new Treap[]{t, null};
        Treap n = new Treap();
        n.R = t.R;
        n.v = t.v;
        n.L = t.L;

        if (sz(t.L) >= x) {
            Treap[] p = split(t.L, x);
            n.L = p[1];
            p[1] = n;
            update(p[1]);
            return p;
        } else if (sz(t.L) + 1 == x){
            Treap r = t.R;
            n.R = null;
            Treap[] p = new Treap[]{n, r};
            update(p[0]);
            return p;
        } else {
            Treap[] p = split(t.R, x - sz(t.L)-1);
            n.R = p[0];
            p[0] = n;
            update(p[0]);
            return p;
        }
    }
    static int search(Treap t, int w) {
        Treap[] p = split(t, w);
        Treap[] q = split(p[0], w-1);
        return q[1].v;
    }

    static Treap mod(Treap t, int w, int x) {
        Treap m = new Treap();
        m.v = x;
        m.sz = 1;
        Treap[] p = split(t, w);
        Treap[] q = split(p[0], w-1);
        return merge(merge(q[0],m),p[1]);
    }

    void solve(BufferedReader in) throws Exception {
        int n = toInt(in.readLine());
        int[] vi = toInts(in.readLine());
        Treap t = null;
        for(int i = 0; i<n; i++) {
            t = mod(t, i+1, vi[i]);
        }
        int q = toInt(in.readLine());
        Treap[] trps = new Treap[q+2];
        trps[1] = t;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        for(int i = 2; i<=q+1; i++) {
            String[] l = (in.readLine()).split(" ");
            int a = toInt(l[1]), b = toInt(l[2]);
            if(l[0].equals("create")) {
                int c = toInt(l[3]);
                trps[++cnt] = mod(trps[a], b, c);
            } else {
                sb.append(search(trps[a], b)).append('\n');
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
        (new G()).solve(in);
    }
}
