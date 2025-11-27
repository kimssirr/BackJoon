import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static int w,h;
    static boolean[][] visited;

    static boolean isVaild(int x, int y, boolean F) {
        if(F == true) {
            if(x > w || x < 1 || y > h || y < 1) {
                return false;
            }

            if(map[x][y] == '*' || map[x][y] == '#') {
                return false;
            }

            return true;
        }else {
            if(x == 0 || x == w+1 || y == 0 || y == h+1) {
                return true;
            }

            if(visited[x][y] == true) {
                return false;
            }

            if(map[x][y] == '*' || map[x][y] == '#') {
                return false;
            }

            return true;
        }
    }

    static class Point {
        int x,y,n;
        boolean isFire;
        public Point(int x,int y, int n, boolean isFire) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.isFire = isFire;
        }
    }

    static int bfs(int startX, int startY, ArrayDeque<Point> q) {
        q.add(new Point(startX, startY, 0, false));

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.isFire == false) {
                if(now.x == 0 || now.x == w+1 || now.y == 0 || now.y == h+1) {
                    return now.n;
                }
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(isVaild(nx, ny, now.isFire)) {
                    if(now.isFire == true) {
                        map[nx][ny] = '*';
                        q.add(new Point(nx, ny, now.n + 1, true));

                    } else {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, now.n + 1, false));
                    }

                }
            }

        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int idx = Integer.parseInt(br.readLine());

        for(int i =0; i<idx; i++) {
            ArrayDeque<Point> q = new ArrayDeque<>();
            int nx=0;
            int ny=0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map= new char[w+2][h+2];
            visited = new boolean [w+2][h+2];

            for(int j= 0; j<h; j++) {
                StringBuilder sb = new StringBuilder(br.readLine());

                for(int k = 0; k<w; k++) {
                    map[k+1][j+1] = sb.charAt(k);
                    if(sb.charAt(k) == '@') {
                        nx = k+1;
                        ny = j+1;
                    }
                    if(sb.charAt(k) == '*') {
                        q.add(new Point(k+1,j+1,0,true));
                    }
                }
            }

            int result = bfs(nx,ny,q);

            if(result == 0) {
                bw.write("IMPOSSIBLE\n");
            } else {
                bw.write(String.valueOf(result+"\n"));
            }

        }

        bw.flush();
        br.close();
        bw.close();



    }

}