import java.util.*;
import java.io.*;
public class K {
    void solve(BufferedReader in) throws Exception {
        int[] sol = new int[100000];
        boolean[] solved = new boolean[100000];
        for(int i = 99999; i>=0; i--) {
            if(!sorted(i)) continue;
            sol[i]=solution(i);
            solved[i] = true;
        }

        for(int i = 0; i<100000; i++){
            if(!solved[i]){
                int[] temp = ds(i);
                Arrays.sort(temp);
                sol[i]=sol[10000*temp[4]+1000*temp[3]+100*temp[2]+10*temp[1]+temp[0]];
            }
        }

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t<T; t++) sb.append(p(sol[Integer.parseInt(in.readLine())])).append('\n');

        System.out.print(sb);
    }

    String p(int i) {
        String s = ("00000" + i);
        return s.substring(s.length() - 5, s.length());


    }
    boolean sorted(int j) {
        int[] ds = ds(j);
        for(int i = 0; i<4; i++)
            if(ds[i] < ds[i+1]) return false;
        return true;
    }
    int[] ds(int i) {
        int[] arr = new int[5];
        int d = 1;
        for(int x = 4; x>=0; x--) {
            arr[x] = (i/d)%10;
            d*=10;
        }
        return arr;

    }
    int solution(int curr){
        boolean[] visited = new boolean[100000];
        int ctr = 0;
        while(!visited[curr]){
            visited[curr]=true;
            curr = next(curr);
            ctr++;
        }
        int ctrC = 0;
        visited = new boolean[100000];
        while(!visited[curr]){
            visited[curr] = true;
            curr = next(curr);
            ctrC++;
        }
        int nbrInCtr = (999999-ctr+ctrC)%ctrC;
        for(int i = 0; i<nbrInCtr; i++) curr = next(curr);
        return curr;
    }

    int next(int i) {
        int[] arr = new int[5];
        int d = 1;
        for(int x = 0; x<5; x++) {
            arr[x] = (i/d)%10;
            d*=10;
        }
        Arrays.sort(arr);
        int X = 0, Y=0;
        d = 1;
        for(int x = 0; x<5; x++) {
            X += arr[x]*d;
            Y += arr[4-x]*d;
            d *=10;
        }
        return X - Y;
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
