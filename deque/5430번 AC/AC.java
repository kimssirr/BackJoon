import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());


        //logic
        boolean flag;
        boolean isError;

        for(int i=0; i<len; i++) {
            ArrayDeque<String> deque = new ArrayDeque<>();
            flag = false; isError=false;

            //line1
            String str = br.readLine();

            //line2
            int idx = Integer.parseInt(br.readLine());

            //line3
            String[] array = br.readLine().replaceAll("[\\[\\]\\s]", "").split(",");

            //main
            for(String a : array) {
                if(!a.isEmpty()) {
                    deque.add(a);
                }
            }

            StringBuilder sb = new StringBuilder(str);
            for(int j=0; j<sb.length(); j++) {
                char n = sb.charAt(j);

                if(n == 'R') {
                    flag = !flag;
                } else if (n == 'D') {

                    if(deque.size() == 0) {
                        bw.write("error\n");
                        isError = true;
                        break;
                    } else {
                        if(flag == true) {
                            deque.pollLast();
                        } else {
                            deque.pollFirst();
                        }
                    }

                }
            }
            if(isError) {
                continue;
            }

            // print
            if(deque.size() != 0) {
                StringBuilder result = new StringBuilder("[");
                if(flag) {
                    result.append(deque.pollLast());
                    while(!deque.isEmpty()) {
                        result.append(",");
                        result.append(deque.pollLast());
                    }
                } else {
                    result.append(deque.pollFirst());
                    while(!deque.isEmpty()) {
                        result.append(",");
                        result.append(deque.pollFirst());
                    }

                }

                result.append("]"+"\n");
                bw.write(result.toString());

            } else {
                bw.write(deque+"\n");
            }

        }

        br.close();
        bw.flush();
        bw.close();
    }
}