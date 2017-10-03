import java.util.*;
import java.io.*;
public class D {
    void solve(BufferedReader in) throws Exception {
        String p = in.readLine();
        int x = 0, y = 0;
        int minx = x, maxx= x, miny = y, maxy = y;
        for(char c : p.toCharArray()) {
            if (c == 'L')x--;
            if(c=='R')x++;
            if(c=='U')y++;
            if(c=='D')y--;
            minx = Math.min(x, minx);
            maxx = Math.max(x, maxx);
            miny = Math.min(y, miny);
            maxy = Math.max(y, maxy);
        }
        int sx = (maxx-minx + 3)*2, sy = (maxy-miny + 3)*2;
        boolean[][] g = new boolean[sx][sy];
        x = -minx*2 + 2; y = -miny*2 + 2;
        g[x][y] = true;
        for(char c : p.toCharArray()) {
            if (c == 'L')x--;
            if(c=='R')x++;
            if(c=='U')y++;
            if(c=='D')y--;
            g[x][y] = true;
            if (c == 'L')x--;
            if(c=='R')x++;
            if(c=='U')y++;
            if(c=='D')y--;
            g[x][y] = true;
        }
        boolean[][] marked = new boolean[sx][sy];
        int c = 0;
        for(int i = 0; i<sx; i++) {
            for(int j = 0;j<sy; j++) {
                if(!marked[i][j] && !g[i][j]) {
                    c++;
                    LinkedList<Integer>bfs = new LinkedList<>();
                    bfs.addLast(i*sy + j);
                    int[] dx = new int[]{-1, 1, 0, 0};
                    int[] dy = new int[]{0, 0, -1, 1};
                    while(!bfs.isEmpty()) {
                        int nxt = bfs.removeFirst();
                        int x0 = nxt/sy, y0 = nxt%sy;
                        if(marked[x0][y0] || g[x0][y0]) continue; //already been here.
                        marked[x0][y0] = true;
                        for(int v = 0; v<4; v++) {
                            x = x0 + dx[v];
                            y = y0 + dy[v];
                            if(x>=0 && x<sx && y>=0 && y<sy) { 
                                bfs.addLast(x*sy + y);
                            }
                        }
                    }

                }
            }
        }
        System.out.println(c);
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
