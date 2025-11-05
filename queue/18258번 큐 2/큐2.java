import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = Integer.parseInt(br.readLine());
        ArrayDeque<String> deque = new ArrayDeque<>();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        for(int i=0; i<index; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String n = st.nextToken();

            switch (n) {
                case "push":
                    String x = st.nextToken();
                    deque.addLast(x);
                    break;
                case "pop":
                    if(deque.peek() != null) {
                        bw.write(deque.pollFirst());
                    } else {
                        bw.write("-1");
                    }
                    bw.newLine();
                    break;
                case "size":
                    bw.write(String.valueOf(deque.size()));
                    bw.newLine();
                    break;
                case "empty":
                    if(deque.peek() != null) {
                        bw.write("0");
                    } else {
                        bw.write("1");
                    }
                    bw.newLine();
                    break;
                case "front":
                    if(deque.peek() != null) {
                        bw.write(deque.peekFirst());
                    } else {
                        bw.write("-1");
                    }
                    bw.newLine();
                    break;
                case "back":
                    if(deque.peek() != null) {
                        bw.write(deque.peekLast());
                    } else {
                        bw.write("-1");
                    }
                    bw.newLine();
                    break;
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}