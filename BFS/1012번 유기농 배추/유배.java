import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int n,m;
    static boolean[][] visited;
    static int[][] map;

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int startX, int startY) {
        ArrayDeque<Point> q = new ArrayDeque<>();

        q.add(new Point(startX, startY));
        visited[startX][startY] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();


            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(isValid(nx, ny)) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }


    }

    static boolean isValid(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y>= m) {
            return false;
        }

        if(visited[x][y]) {
            return false;
        }

        if(map[x][y] == 0) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for(int i = 0; i<tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            visited = new boolean[n][m];
            map = new int[n][m];
            int result = 0;

            for(int j=0; j<c; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st2.nextToken())][Integer.parseInt(st2.nextToken())] = 1;
            }

            for(int a=0; a<n; a++) {
                for(int b=0; b<m; b++) {
                    if(map[a][b] == 1 && !visited[a][b]) {
                        bfs(a,b);
                        result++;
                    }

                }
            }

            bw.write(String.valueOf(result+"\n"));
        }




        bw.flush();
        bw.close();
        br.close();

    }



}