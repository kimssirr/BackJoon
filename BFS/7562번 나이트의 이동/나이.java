import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {1, -1, 2, -2, 2, -2, 1, -1};
    static int[][] map;
    static int len;
    static boolean[][] visited;

    static boolean isVaild(int x, int y) {
        if(x >= len || x < 0 || y >= len || y < 0) {
            return false;
        }

        if(visited[x][y] == true) {
            return false;
        }

        return true;
    }

    static class Point {
        int x,y,n;
        public Point(int x,int y,int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }

    static int bfs(int startX, int startY, int endX, int endY) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(startX, startY, 0));

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.x == endX && now.y == endY) {
                return now.n;
            }

            for(int i=0; i<8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(isVaild(nx, ny)) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, now.n + 1));
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
            len = Integer.parseInt(br.readLine());
            map= new int[len][len];
            visited = new boolean [len][len];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st2.nextToken());
            int endY = Integer.parseInt(st2.nextToken());

            int result = bfs(startX, startY, endX, endY);


            bw.write(String.valueOf(result)+"\n");

        }

        bw.flush();
        br.close();
        bw.close();



    }

}