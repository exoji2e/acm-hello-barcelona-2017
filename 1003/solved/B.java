import java.util.*;
import java.util.*;
import java.io.*;
public class B {
    static class Treap{
        int sz;
        int v;
        double y;
        int min;
        Treap L, R;
    }
    static int sz(Treap t) {
        if(t == null) return 0;
        return t.sz;
    }
    static int min(Treap t) {
        if(t == null) return Integer.MAX_VALUE;
        return t.min;
    }
    static void update(Treap t) {
        if(t == null) return;
        t.sz = sz(t.L) + sz(t.R) + 1;
        t.min = Math.min(min(t.L), Math.min(min(t.R), t.v));
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
        if (t.sz < x) return new Treap[]{t, null};
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

    static Integer Left(Treap t) {
        if (t == null) return null;
        if (t.L == null) return t.v;
        return Left(t.L);
    }
    static Integer Right(Treap t) {
        if (t == null) return null;
        if (t.R == null) return t.v;
        return Right(t.R);
    }

    static Treap insert(Treap t, int w, int x) {
        Treap m = new Treap();
        m.v = x;
        m.y = Math.random();
        m.sz = 1;
        m.min = x;
        Treap[] p = split(t, w);
        t = merge(p[0], m);
        return merge(t, p[1]);
    }
    static int min(Treap t, int l, int r) {
        Treap[] p = split(t, l-1);
        Treap[] q = split(p[1], r-l+1);
        int ret = min(q[0]);
        p[1] = merge(q[0], q[1]);
        t = merge(p[0], p[1]);
        return ret;

    }

    static Treap remove(Treap t, int x) {
        Treap[] p = split(t, x);
        Treap[] q = split(p[0], x-1);
        return merge(q[0], p[1]);
    }

    static boolean exists(Treap t, int x) {
        Treap[] p = split(t, x);
        Treap[] q = split(p[0], x-1);
        boolean ret = q[1] != null;
        p[0] = merge(q[0], q[1]);
        t = merge(p[0], p[1]);
        return ret;
    }

    static Integer next(Treap t, int x) {
        Treap[] p = split(t, x);
        Integer ret = Left(p[1]);
        t = merge(p[0], p[1]);
        return ret;
    }
    static Integer prev(Treap t, int x) {
        Treap[] p = split(t, x-1);
        Integer ret = Right(p[0]);
        t = merge(p[0], p[1]);
        return ret;
    }
    void solve(BufferedReader in) throws Exception {
        int q = toInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        Treap t = null;
        for(int i = 0; i<q; i++) {
            String[] s = in.readLine().split(" ");
            int a = toInt(s[1]), b = toInt(s[2]);
            //System.out.println(a + " " + b);
            if(s[0].equals("+")) {
                t = insert(t, a, b);
                //System.out.println(t.sz);
            } else {
                int min = Math.min(a, b);
                int max = Math.max(a, b);
                sb.append(min(t, min, max)).append('\n');
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
        (new B()).solve(in);
    }
}
