import java.util.*;
import java.io.*;
public class H {
    void solve(BufferedReader in) throws Exception {
        int T = toInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t<T; t++){
            int N = toInt(in.readLine());
            String x = in.readLine(), y = in.readLine(), z = in.readLine();
            int k = 0, m = 0, n=0, o=0, f =0;
            for (int i = 0; i<N; i++) {
                char xx = x.charAt(i), yy = y.charAt(i), zz = z.charAt(i);
                if (xx == yy && xx == zz)
                    o += 1;
                else if( xx == yy)
                    k += 1;
                else if(xx == zz)
                    n += 1;
                else if(yy == zz)
                    m += 1;
                else
                    f += 1;
            }
            int d = Math.min(k, Math.min(m, n));
            int res = o + 2*d;
            int used = o + 3*d;
            k -= d;
            n -= d;
            m -= d;
            int MAX = Math.max(k, Math.max(m, n));
            if (MAX*2 < N - used) {
                res += MAX;
                used += MAX*2;
                if (k == MAX)
                    k = 0;
                else if( n == MAX)
                    n = 0;
                else
                    m = 0;
                int rm = Math.max(k,Math.max(n,m));
                f -= MAX - rm;
                if (f/2 < rm)
                    res += f/2;
                else
                    res += (f + rm)/3;
            }else {
                res += (N - used)/2;
                used = N;
            }
            sb.append(res).append('\n');
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
        (new H()).solve(in);
    }
}
