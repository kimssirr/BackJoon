import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[index+1];
        int[] result = new int[index+1];

        for(int i=1; i<=index; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for(int i=1; i<=index; i++) {
            while(!stack.isEmpty() && array[stack.peek()] <= array[i]) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek();
            }

            stack.push(i);

        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= index; i++) {
            bw.write(result[i] + " ");
        }

        br.close();
        bw.flush();
        bw.close();

    }
}
