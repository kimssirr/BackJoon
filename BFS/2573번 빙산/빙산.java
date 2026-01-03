import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    static int mapX;
    static int mapY;

    static int count = 0;
    static int len = 0;
    static int year = 0;

    static class Point {
        int x;
        int y;
        int min;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int min) {
            this.x = x;
            this.y = y;
            this.min = min;
        }
    }

    static boolean inRange(int x, int y) {
        return !(x >= mapX || x < 0 || y >= mapY || y < 0);
    }

    static boolean isTrue(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y]) return false;
        if (map[x][y] == 0) return false;
        return true;
    }

    static boolean bfs(int x, int y) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        count = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isTrue(nx, ny)) {
                    visited[nx][ny] = true;
                    count++;
                    q.add(new Point(nx, ny));
                }
            }
        }

        return count == len;
    }

    // return value
    //  -1: zero
    //   0: one
    //   1: two >
    static int check() {
        len = 0;
        int sx = -1, sy = -1;


        for (int i = 0; i < mapX; i++) {
            for (int j = 0; j < mapY; j++) {
                if (map[i][j] != 0) {
                    len++;
                    if (sx == -1) {
                        sx = i;
                        sy = j;
                    }
                }
            }
        }

        if (len == 0) return -1;

        visited = new boolean[mapX][mapY];

        boolean isOne = bfs(sx, sy);

        if (!isOne) return 1;
        return 0;
    }

    static void minus() {
        ArrayDeque<Point> q = new ArrayDeque<>();

        for (int i = 0; i < mapX; i++) {
            for (int j = 0; j < mapY; j++) {
                if (map[i][j] != 0) {
                    int tem = 0;
                    for (int w = 0; w < 4; w++) {
                        int nx = i + dx[w];
                        int ny = j + dy[w];

                        if (!inRange(nx, ny)) continue;

                        if (map[nx][ny] == 0) {
                            tem++;
                        }
                    }
                    q.add(new Point(i, j, tem));
                }
            }
        }

        while (!q.isEmpty()) {
            Point now = q.poll();
            map[now.x][now.y] = Math.max(0, map[now.x][now.y] - now.min);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        mapX = Integer.parseInt(st.nextToken());
        mapY = Integer.parseInt(st.nextToken());

        map = new int[mapX][mapY];

        for (int i = 0; i < mapX; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapY; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        while (true) {
            int status = check();

            if (status == -1) {
                bw.write("0");
                break;
            } else if (status == 1) {
                bw.write(String.valueOf(year));
                break;
            } else {
                minus();
                year++;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}