import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
    static int[] dist;
    static int MAX = 100000;

    static int bfs() {
        ArrayDeque<Integer> q = new ArrayDeque();
        q.add(N);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        while(!q.isEmpty()) {
            int now = q.poll();
            if(now == K) {
                return dist[now];
            }
            int next = 0;
            if(now > K) {
                next = now-1;
                if(next >= 0 && next <= MAX) {
                    if(dist[next] > dist[now] +1) {
                        dist[next] = dist[now] +1;
                        q.addLast(next);
                    }

                }



            } else {
                next = now-1;
                if(next >= 0 && next <= MAX) {
                    if(dist[next] > dist[now] +1) {
                        dist[next] = dist[now] +1;
                        q.addLast(next);
                    }
                }


                next = now +1;
                if(next >= 0 && next <= MAX) {
                    if(dist[next] > dist[now] +1) {
                        dist[next] = dist[now] +1;
                        q.addLast(next);
                    }
                }


                next = now*2;
                if(next >= 0 && next <= MAX) {
                    if(dist[next] > dist[now] ) {
                        dist[next] = dist[now] ;
                        q.addFirst(next);
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
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        dist = new int[MAX + 1];

        bw.write(String.valueOf(bfs()));

        bw.flush();
        bw.close();
        br.close();

    }
}