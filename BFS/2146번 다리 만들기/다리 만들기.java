import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static ArrayDeque<Point> startQ = new ArrayDeque<>();
    static int num=2;

    static class Point {
        int x, y, value;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y,int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    static boolean isTrue(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }

        if(map[x][y] == 0) {
            return false;
        }

        if(visited[x][y] == true) {
            return false;
        }

        return true;

    }

    static void mapRegen(int x, int y, int a) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(x,y));
        visited[x][y] = true;
        map[x][y] = a;
        int temp;

        while(!q.isEmpty()) {
            Point now = q.poll();
            temp=0;

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(map[nx][ny] == 0) {
                    temp ++;
                }


                if(isTrue(nx, ny)) {
                    map[nx][ny] = a;
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }

            if(temp>0) {
                startQ.add(new Point(now.x, now.y,a));
            }
        }

        num++;


    }

    static int BFS() {
        int result = Integer.MAX_VALUE;
        while(!startQ.isEmpty()) {
            Point now = startQ.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(map[nx][ny] == 0) {
                    map[nx][ny] = now.value;
                    dist[nx][ny] = dist[now.x][now.y] +1;
                    startQ.add(new Point(nx,ny,now.value));

                } else if (map[nx][ny] != now.value) {
                    if(result > dist[nx][ny] + dist[now.x][now.y]) {
                        result = dist[nx][ny] + dist[now.x][now.y];
                    }
                }

            }

        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        dist = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j] && map[i][j] != 0) {
                    mapRegen(i, j, num);
                }
            }
        }

        bw.write(String.valueOf(BFS()));

        bw.flush();
        bw.close();
        br.close();

    }
}