import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = Integer.parseInt(br.readLine());

        int[] array = new int[index+1];
        long result = 0;

        for(int i=1; i<=index; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();

        for(int i=1; i<=index; i++) {
            while(!stack.isEmpty() && array[stack.peek()] <= array[i]) {
                stack.pop();
            }

            result += stack.size();

            stack.push(i);

        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));

        br.close();
        bw.flush();
        bw.close();

    }
}