import java.util.*;
import java.io.*;
public class C {
    static Random r = new Random(42);
    static class Treap{
        int sz;
        int x;
        double y;
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
        if (a.y < b.y) {
            a.R = merge(a.R, b);
            update(a);
            return a;
        } else {
            b.L = merge(a, b.L);
            update(b);
            return b;
        }
    }

    static Treap[] split(Treap t, int x) {
        if (t == null) return new Treap[2];
        if (t.sz <= x) return new Treap[]{t, null};
        if (sz(t.L) >= x) {
            Treap[] p = split(t.L, x);
            t.L = p[1];
            p[1] = t;
            update(p[0]);
            update(p[1]);
            return p;
        } else if (sz(t.L) + 1 == x){
            Treap r = t.R;
            t.R = null;
            Treap[] p = new Treap[]{t, r};
            update(p[0]);
            update(p[1]);
            return p;
        } else {
            Treap[] p = split(t.R, x - sz(t.L)-1);
            t.R = p[0];
            p[0] = t;
            update(p[0]);
            update(p[1]);
            return p;
        }
    }

    static Treap insert(Treap t, int x) {
        Treap m = new Treap();
        m.x = x;
        m.y = r.nextDouble();
        m.sz = 1;
        return merge(t, m);
    }

    static Treap mvfront(Treap t, int l, int r) {
        Treap[] p = split(t, l-1);
        Treap[] q = split(p[1], r-l+1);

        return merge(q[0], merge(p[0], q[1]));
    }
    static void print(Treap t) {
        StringBuilder sb = new StringBuilder();
        print(t, sb);
        System.out.println(sb);
    }
    static void print(Treap t, StringBuilder sb) {
        if(t == null) return;
        print(t.L, sb);
        sb.append(t.x).append(" ");
        print(t.R, sb);
    }


    void solve(BufferedReader in) throws Exception {
        int[] xx = toInts(in.readLine());
        int n = xx[0], m = xx[1];
        Treap t = null;
        for(int i = 1; i<=n; i++) t = insert(t, i);
        for(int i = 0; i< m; i++) {
            xx = toInts(in.readLine());
            t = mvfront(t, xx[0], xx[1]);
        }
        print(t);
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
        (new C()).solve(in);
    }
}
