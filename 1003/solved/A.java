import java.util.*;
import java.io.*;
public class A {
    static class Treap{
        int x;
        double y;
        Treap L, R;
    }
    static Treap merge(Treap a, Treap b) {
        if (a == null) return b;
        if(b == null) return a;
        if (a.y < b.y) {
            a.R = merge(a.R, b);
            return a;
        } else {
            b.L = merge(a, b.L);
            return b;
        }
    }

    static Treap[] split(Treap t, int x) {
        if (t == null) return new Treap[2];
        if (t.x <= x) {
            Treap[] p = split(t.R, x);
            t.R = p[0];
            p[0] = t;
            return p;
        } else {
            Treap[] p = split(t.L, x);
            t.L = p[1];
            p[1] = t;
            return p;
        }
    }

    static Integer Left(Treap t) {
        if (t == null) return null;
        if (t.L == null) return t.x;
        return Left(t.L);
    }
    static Integer Right(Treap t) {
        if (t == null) return null;
        if (t.R == null) return t.x;
        return Right(t.R);
    }

    static Treap insert(Treap t, int x) {
        Treap m = new Treap();
        m.x = x;
        m.y = Math.random();
        Treap[] p = split(t, x);
        t = merge(p[0], m);
        return merge(t, p[1]);
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
        String line = in.readLine();
        Treap t = null;
        while(line != null) {
            String[] s = line.split(" ");
            String ins = s[0];
            int x = toInt(s[1]);
            if(ins.equals("insert")) {
                t = insert(t, x);
            } else if(ins.equals("delete")) {
                t = remove(t, x);
            } else if(ins.equals("exists")) {
                System.out.println(exists(t, x));
            } else if(ins.equals("next")) {
                Integer r = next(t, x);
                if(r == null) System.out.println("none");
                else System.out.println(r);
            } else if(ins.equals("prev")) {
                Integer r = prev(t, x);
                if(r == null) System.out.println("none");
                else System.out.println(r);
            
            }
            line = in.readLine();
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
        (new A()).solve(in);
    }
}
