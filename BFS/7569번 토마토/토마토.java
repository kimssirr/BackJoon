import java.io.*;
import java.util.*;

public class Main {
    static int n,m,h;
    static int[] dx = {1, -1 , 0 , 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1 , -1};

    static int[][][] map;

    static class Point {
        int x,y,z;
        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static boolean isValid(int z, int y, int x) {
        if(z < 0 || y < 0 || x < 0 || z >= h || y >= m || x >= n) {
            return false;
        }

        if(map[z][y][x] == -1) {
            return false;
        }

        if(map[z][y][x] != 0) {
            return false;
        }

        return true;
    }

    static void bfs(ArrayDeque<Point> q) {

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int i=0; i<6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nz = now.z + dz[i];

                if(isValid(nz, ny, nx)) {
                    map[nz][ny][nx] = map[now.z][now.y][now.x] + 1;
                    q.add(new Point(nx, ny, nz));
                }
            }
        }


    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][m][n];

        ArrayDeque<Point> q = new ArrayDeque<>();


        int zero = 0;

        for(int z=0; z<h; z++) {
            for(int y=0; y<m; y++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for(int x=0; x<n; x++) {
                    map[z][y][x] = Integer.parseInt(st2.nextToken());
                    if(map[z][y][x] == 1) {
                        q.add(new Point(x,y,z));
                    }
                    if(map[z][y][x] == 0) {
                        zero ++;
                    }
                }
            }
        }

        if(zero == 0) {
            bw.write("0");
            bw.flush();
            return;
        }

        bfs(q);

        int max = 0;
        for(int z=0; z<h; z++) {
            for(int y=0; y<m; y++) {
                for(int x=0; x<n; x++) {
                    if(map[z][y][x] == 0) {
                        bw.write("-1");
                        bw.flush();
                        return;
                    }
                    max = Math.max(max, map[z][y][x]);
                }
            }
        }

        bw.write(String.valueOf(max-1));

        br.close();
        bw.flush();
        bw.close();
    }



}