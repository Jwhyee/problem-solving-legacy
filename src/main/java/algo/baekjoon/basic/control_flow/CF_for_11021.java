package algo.baekjoon.basic.control_flow;

import java.io.*;
import java.util.StringTokenizer;

public class CF_for_11021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int a, b;

        for (int i = 1; i <= num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write("Case #" + i + ": "+ (a+b) + "\n");
        }
        bw.flush();

        br.close();
        bw.close();


    }
}
