import java.util.*;
import java.io.*;
public class E {
    static class PStack {
        int sum;
        PStack nxt;
    }
    static int sum(PStack s) {
        if(s == null) return 0;
        return s.sum;
    }
    static PStack push(PStack s, int x) {
        PStack p = new PStack();
        p.sum = sum(s) + x;
        p.nxt = s;
        return p;
    }
    static PStack pop(PStack s) {
        return s.nxt;
    }

    void solve(BufferedReader in) throws Exception {
        int q = toInt(in.readLine());
        PStack[] stacks = new PStack[q+1];
        for(int i = 1; i<=q; i++) {
            int[] ins = toInts(in.readLine());
            int t = ins[0], v = ins[1];
            stacks[i] = (v == 0)? pop(stacks[t]): push(stacks[t], v);
        }
        long sum = 0;
        for(PStack s: stacks) sum += sum(s);
        System.out.println(sum);
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
        (new E()).solve(in);
    }
}
