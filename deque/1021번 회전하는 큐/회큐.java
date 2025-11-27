import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        //1 line

        int index = Integer.parseInt(st1.nextToken());
        ArrayDeque<Integer> dequeCircle = new ArrayDeque<>();

        for(int i=index; i>0; i--) {
            dequeCircle.addFirst(i);
        }

        int len = Integer.parseInt(st1.nextToken());

        //2 line

        StringTokenizer st2 = new StringTokenizer(br.readLine());


        // logic
        int result = 0;

        while(st2.hasMoreTokens()) {
            int target = Integer.parseInt(st2.nextToken());
            int idx=0;
            for(int a: dequeCircle) {
                if(a == target) {
                    break;
                }
                idx++;
            }

            int half = dequeCircle.size() / 2;
            if(idx <= half) {
                for(int i=0; i<idx; i++) {
                    dequeCircle.addLast(dequeCircle.pollFirst());
                    result++;
                }


            } else {
                for(int i=0; i<dequeCircle.size() - idx; i++) {
                    dequeCircle.addFirst(dequeCircle.pollLast());
                    result++;
                }
            }

            dequeCircle.pollFirst();

        }




        bw.write(String.valueOf(result));


        br.close();
        bw.flush();
        bw.close();
    }
}