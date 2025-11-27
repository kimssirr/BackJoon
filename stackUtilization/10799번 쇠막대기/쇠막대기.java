import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder(br.readLine());
        int result = 0;
        ArrayDeque<String> deque = new ArrayDeque<>();

        for(int i=0; i<sb.length(); i++ ) {

            if(sb.charAt(i) == '(') {
                deque.add(String.valueOf(sb.charAt(i)));

            } else if (sb.charAt(i) == ')') {
                if(sb.charAt(i-1) == '(') {
                    deque.pollLast();
                    result += deque.size();
                } else {
                    result += 1;
                    deque.pollLast();
                }

            }
        }



        bw.write(String.valueOf(result));


        br.close();
        bw.flush();
        bw.close();
    }
}