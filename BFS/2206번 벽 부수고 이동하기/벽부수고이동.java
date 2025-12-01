import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static int n,m;
    static boolean[][][] visited;

    static class Point {
        int x,y,len,broken;
        public Point(int x,int y, int len, int broken) {
            this.x = x;
            this.y = y;
            this.len = len;
            this.broken= broken;
        }
    }

    static int bfs(int startX, int startY) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(startX, startY, 1, 0));

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.x == n-1 && now.y == m-1) {
                return now.len;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) { // map 크기 유효성 검사
                    continue;
                }

                if(map[nx][ny] == '1') { // 벽이면
                    if(now.broken == 0 && !visited[nx][ny][1]){ // 벽 기회 남았고, 방문X
                        visited[nx][ny][1] = true;
                        q.add(new Point(nx,ny,now.len+1,1));
                    }
                } else { // 벽 아니면
                    if(!visited[nx][ny][now.broken]){
                        visited[nx][ny][now.broken] = true;
                        q.add(new Point(nx,ny,now.len+1,now.broken));
                    }
                }



            }

        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map= new char[n][m];
        visited = new boolean [n][m][2];
        // 벽 뚫었을 때 방문, 뚫지않았을 때 방문을 따로 관리함. 벽 뚫었을 때의 경로가 아예 달라지기 때문에, 하나로 한다면 미래의 경로가 visited true에 막힐 수가 있음.

        for(int j= 0; j<n; j++) {
            StringBuilder sb = new StringBuilder(br.readLine());

            for(int k = 0; k<m; k++) {
                map[j][k] = sb.charAt(k);
            }
        }

        int result = bfs(0,0);

        if(result == 0) {
            bw.write("-1\n");
        } else {
            bw.write(String.valueOf(result+"\n"));
        }


        bw.flush();
        br.close();
        bw.close();



    }

}