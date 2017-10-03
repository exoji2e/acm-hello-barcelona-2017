import java.util.*;
import java.io.*;
import java.math.*;
public class K {
    BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger m = a.mod(b);
        if(m.equals(BigInteger.ZERO)) return b;
        else return gcd(b, m);
    }
    void solve(BufferedReader in) throws Exception {
        int[] xx = toInts(in.readLine());
        int L = xx[0], N = xx[1];

d = dfs(1, N, [0]*L, N)
n = N**L
g = gcd(d, n)
print(str(d//g) + '/' + str(n//g))
        
    }
    BigInteger dfs(int l, int n, int w, BigInteger weight){
        if (l == L) {
            s = set()
            for i in range(len(w)):
                for j in range(i+1,len(w)+1):
                    s.add(str(w[i:j]))
            return len(s)*weight
        }
        s = 0
        for i in range(min(l, n)):
            w[l] = i
            s += dfs(l+1, n, w, weight)
        if n > l:
            w[l] = l
            s+= dfs(l+1, n, w, weight*(n-l))
        return s
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
        (new K()).solve(in);
    }
}
