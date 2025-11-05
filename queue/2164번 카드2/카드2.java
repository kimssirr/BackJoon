import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());

        for(int i=n; i>0; i--) {
            deque.addLast(i);
        }

        while(deque.size() != 1) {
            deque.pollLast();
            if(deque.size() != 1) {
                deque.addFirst(deque.pollLast());
            }
        }

        bw.write(String.valueOf(deque.peek()));


        br.close();
        bw.flush();
        bw.close();
    }
}