import java.util.*;
import java.io.*;
public class D {
    static class Treap{
        int sz;
        int v;
        int added = 0;
        double y;
        Treap L, R;
    }
    static int sz(Treap t) {
        if(t == null) return 0;
        return t.sz;
    }
    static void update(Treap t) {
        if(t == null) return;
        t.sz = sz(t.R) + sz(t.L) + 1;
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
            Treap l = t.L;
            t.L = null;
            Treap[] p = new Treap[]{l, t};
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
    static int[] dump(Treap t){
        int[] d = new int[sz(t)];
        dump(t, d, 0);
        return d;
    }
    static void dump(Treap t, int [] d, int w){
        if(t == null) return;
        if(t.L != null) dump(t.L, d, w);
        w += sz(t.L);
        d[w++] = t.v;
        if(t.R != null) dump(t.R, d, w);
    }

    static Treap insert(Treap t, int w, int x) {
        Treap m = new Treap();
        m.v = x;
        m.y = Math.random();
        m.sz = 1;
        Treap[] p = split(t, w);
        t = merge(p[0], m);
        return merge(t, p[1]);
    }
    void p(TreeMap<Integer, Treap> map, int n, int m) {
        int[] out = new int[n+m];
        int max = 0;
        for(Map.Entry<Integer, Treap> e: map.entrySet()) {
            Treap t = e.getValue();
            int s = e.getKey();
            int[] d = dump(t);
            for(int i = 0;i<sz(t); i++) out[i+s] = d[i];
            max = s + sz(t);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max-1).append('\n');
        for(int i = 1; i<max; i++) {
            sb.append(out[i]).append(' ');
        }
        System.out.println(sb);
    }
    void solve(BufferedReader in) throws Exception {
        TreeMap<Integer, Treap> map = new TreeMap<>();
        int[] xx = toInts(in.readLine());
        int n = xx[0], m = xx[1];
        int[] a = toInts(in.readLine());
        for(int i = 0; i<a.length; i++) {
            int v = i+1;
            int k = a[i];
            Map.Entry<Integer, Treap> e1 = map.floorEntry(k);
            
            int s = k;
            Treap t = null;
            if(e1 != null) {
                Treap tt = e1.getValue();
                int ss = e1.getKey();
                if(sz(tt) + ss >= k) {
                    t = tt;
                    s = ss;
                    map.remove(s);
                }
            }
            t = insert(t, (k - s) + 1, v);

            Map.Entry<Integer, Treap> e2 = map.ceilingEntry(s + sz(t));
            if(e2 != null && e2.getKey() == s + sz(t)) {
                map.remove(e2.getKey());
                t = merge(t, e2.getValue());
            }
            map.put(s, t);
        }
        p(map, n, m);
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
