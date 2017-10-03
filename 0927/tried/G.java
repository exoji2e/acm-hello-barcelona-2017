import java.util.*;
import java.io.*;
public class G {
    int gcd(int a, int b) {
        return (a%b == 0)? b: gcd(b, a%b);
    }
    void solve(BufferedReader in) throws Exception {
        int T = toInt(in.readLine());
    for (int t = 0; t<T; t++){
        int[] xx = toInts(in.readLine());
        int n = xx[0], m = xx[1];

        int sum = 0;
        for(int p1 = 0; p1<n*m; p1++) {
            int p10 = p1%n;
            int p11 = p1/n;
            for (int p2 = 0; p2<p1; p2++) {
                int p20 = p2%n;
                int p21 = p2/n;

                int dx = p10 - p20;
                int dy = p11 - p21;
                if (dx == 0){
                    sum += n*m - m;
                    continue;
                }
                if (dy == 0) {
                    sum += n*m - n;
                    continue;
                }
                int g = gcd(Math.abs(dx), Math.abs(dy));
                int rml = p11/dy + 1;
                int rmr = (n-1 - p11)/dy;
                if (dx < 0){
                    rml = Math.min(rml, -(m-1 - p10)/dx + 1);
                    rmr = Math.min(rmr, -(p10)/dx);
                }else{
                    rmr = Math.min(rmr, (m-1 - p10)/dx);
                    rml = Math.min(rml, (p10)/dx + 1);
                }
                sum += n*m - rmr - rml;
            }
        }
        System.out.println(sum/3);
        
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
        (new G()).solve(in);
    }
}
