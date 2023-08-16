package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_6593 {
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] dz = {0, -1, 1};
    static int L, R, C;
    static Queue<Node> nodeQueue = new LinkedList<>();
    static Node exitNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        L = Integer.parseInt(arr[0]);
        R = Integer.parseInt(arr[1]);
        C = Integer.parseInt(arr[2]);
        map = new int[L][R][C];
        visited = new boolean[L][R][C];

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                char[] charArr = br.readLine().toCharArray();
                for (int k = 0; k < C; k++) {
                    if (charArr[k] == '.') {
                        map[i][j][k] = 0;
                    } else if (charArr[k] == 'S') {
                        map[i][j][k] = 0;
                        nodeQueue.offer(new Node(i, k, j, 0));
                    } else if (charArr[k] == '#') {
                        map[i][j][k] = -1;
                    } else if (charArr[k] == 'E') {
                        map[i][j][k] = 0;
                        exitNode = new Node(i, k, j, 0);
                    }
                }
            }
            if (i != L - 1) {
                br.readLine();
            }
        }

        int result = bfs();

        matrix();

        if (result != -1) {
            System.out.println("Escaped in " + result + " minute(s).");
        } else {
            System.out.println("Trapped!");
        }

    }

    private static void matrix() {
        for (int i = 0; i < map.length; i++) {
            System.out.println("Matrix " + (i + 1) + ":");
            printMatrix(map[i]);
            System.out.println();
        }
    }

    public static void printMatrix(int[][] map) {
        Arrays.stream(map)
                .map(Arrays::toString)
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    private static int bfs() {
        while (!nodeQueue.isEmpty()) {
            Node cur = nodeQueue.poll();
            visited[cur.z][cur.y][cur.x] = true;
            if (cur.z == exitNode.z && cur.y == exitNode.y && cur.x == exitNode.x) {
                return cur.cost;
            }

            for (int i = 0; i < 3; i++) {
                int nz = cur.z + dz[i];

                if (nz >= 0 && nz < L) {
                    System.out.println("---------------");
                    System.out.println("nz = " + nz);

                    for (int j = 0; j < 4; j++) {
                        int nx = cur.x + dx[j];
                        int ny = cur.y + dy[j];

                        if (nx >= 0 && nx < C && ny >= 0 && ny < R) {
                            if (map[nz][ny][nx] == 0 && !visited[nz][ny][nx]) {
                                System.out.println("ny = " + ny);
                                System.out.println("nx = " + nx);
                                if (nz != cur.z) {
                                    map[nz][ny][nx] = cur.cost + 2;
                                    nodeQueue.offer(new Node(nz, nx, ny, cur.cost + 2));
                                } else {
                                    map[nz][ny][nx] = cur.cost + 1;
                                    nodeQueue.offer(new Node(nz, nx, ny, cur.cost + 1));
                                }
                            }
                        }

                    }
                }
            }

        }
        return -1;
    }


    static class Node {
        int z, x, y, cost;

        public Node(int z, int x, int y, int cost) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}