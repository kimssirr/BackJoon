import java.io.*;
import java.util.*;

public class Main {
    static int K,W,H;
    static String[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] hx = {-2,-2,-1,-1,1,1,2,2};
    static int[] hy = {1,-1,2,-2,2,-2,1,-1};
    static int[][][] dist;

    static class Point {
        int x,y,h;
        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    static boolean isTrue(int x, int y, int h) {
        if(x < 0 || y < 0 || x >= W || y >= H) {
            return false;
        }

        if(visited[x][y][h]) {
            return false;
        }

        if(map[x][y].equals("1")) {
            return false;
        }

        return true;
    }

    static int bfs() {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(0,0,K));
        visited[0][0][K] = true;
        dist[0][0][K] = 0;

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.x == W-1 && now.y == H-1) {
                return dist[now.x][now.y][now.h];
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nh = now.h;

                if(isTrue(nx, ny, nh)) {
                    q.add(new Point(nx, ny, nh));
                    visited[nx][ny][nh] = true;
                    dist[nx][ny][nh] = dist[now.x][now.y][now.h] +1;
                }
            }

            if(now.h > 0) {
                for(int i=0; i<8; i++) {
                    int nx = now.x + hx[i];
                    int ny = now.y + hy[i];
                    int nh = now.h - 1;

                    if(isTrue(nx, ny, nh)) {
                        q.add(new Point(nx, ny, nh));
                        visited[nx][ny][nh] = true;
                        dist[nx][ny][nh] = dist[now.x][now.y][now.h] +1;
                    }
                }
            }


        }

        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new String[W][H];
        visited = new boolean[W][H][K+1];
        dist = new int[W][H][K+1];

        for(int i=0; i<H; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                map[j][i] = st2.nextToken();
            }
        }

        bw.write(String.valueOf(bfs()));

        bw.flush();
        bw.close();
        br.close();

    }
}