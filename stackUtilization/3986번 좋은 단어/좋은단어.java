import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        int result = 0;
        ArrayDeque<String> deque = new ArrayDeque<>();

        for(int i=0; i<len; i++) {
            deque = new ArrayDeque<>();

            StringBuilder sb = new StringBuilder(br.readLine());

            for(int j=0; j<sb.length(); j++) {
                String n = String.valueOf(sb.charAt(j));

                if(!deque.isEmpty()) {
                    if(deque.peekLast().equals(n)) {
                        deque.pollLast();
                    } else {
                        deque.addLast(n);
                    }
                } else {
                    deque.addLast(n);
                }



            }

            if(deque.isEmpty()) {
                result += 1;
            }


        }



        bw.write(String.valueOf(result));


        br.close();
        bw.flush();
        bw.close();
    }
}