import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0,0,-1,1};
    static int n;
    static boolean[][] visited;
    static char[][] map;

    static class Point {
        int x, y;
        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isValid(int nx, int ny, int x, int y) {
        if(x < 0 || x>=n || y<0 || y>=n) {
            return false;
        }

        if(visited[x][y]) {
            return false;
        }

        if(!(map[x][y] == map[nx][ny])) {
            return false;
        }

        return true;
    }

    static void BFS(int startX, int startY) {
        ArrayDeque<Point> queue = new ArrayDeque<>();

        queue.add(new Point(startX, startY));
        visited[startX][startY] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(isValid(now.x, now.y, nx,ny)) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

    }

    static boolean isValid2(int nx, int ny, int x, int y) {
        if(x < 0 || x>=n || y<0 || y>=n) {
            return false;
        }

        if(visited[x][y]) {
            return false;
        }

        if(map[x][y] == 'R' || map[x][y] == 'G') {
            if(map[nx][ny] == 'R' || map[nx][ny] == 'G') {
                return true;
            }
        } else {
            if((map[x][y] == map[nx][ny])) {
                return true;
            }
        }


        return false;
    }


    static void BFS2(int startX, int startY) {
        ArrayDeque<Point> queue = new ArrayDeque<>();

        queue.add(new Point(startX, startY));
        visited[startX][startY] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(isValid2(now.x, now.y, nx,ny)) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        visited= new boolean[n][n];
        map = new char[n][n];

        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = str.charAt(j);
            }

        }
        int count = 0;
        int count2 = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j]) {
                    BFS(i,j);
                    count++;
                }

            }
        }

        visited= new boolean[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j]) {
                    BFS2(i,j);
                    count2++;
                }
            }
        }



        bw.write(String.valueOf(count)+" "+String.valueOf(count2));

        br.close();
        bw.flush();
        bw.close();
    }



}