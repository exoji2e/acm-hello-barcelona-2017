import java.util.*;
import java.io.*;
public class J {
    void solve(BufferedReader in) throws Exception {
        int N = toInt(in.readLine().trim());
        boolean[] np = new boolean[31623];
        LinkedList<Integer> primes = new LinkedList<>();
        for(int p = 2; p<np.length; p++) {
            if(np[p]) continue;
            for(int k = p*p; k<np.length; k+=p) np[p] = true;
            primes.add(p);
        }
        int[] ai = toInts(in.readLine());
        int c = 0;
        int ones = 0;
        for(int a: ai) {
            if(a == 1) ones++;
            if (a == Integer.highestOneBit(a)) continue;
            boolean prime = true;
            for(int p : primes) {
                if(p*p > a) break;
                if(a%p == 0) {
                    prime = false;
                    break;
                }
            }
            if(!prime) c++;
        }
        if(c == 0)
            c = N-ones;

        if(c%2 == 1) System.out.println("Alice");
        else System.out.println("Bob");
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
        (new J()).solve(in);
    }
}
