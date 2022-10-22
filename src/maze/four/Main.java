package maze.four;

/**
 *
 * Find the shortest safe route in a field with sensors present
 * Link : https://www.techiedelight.com/find-shortest-safe-route-field-sensors-present/
 */

import java.util.ArrayDeque;
import java.util.Queue;

// A queue node used in BFS
class Node
{
    // (x, y) represents a position inside the field
    // `dist` represents its minimum distance from the source
    int x, y, dist;

    Node(int x, int y, int dist)
    {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Main
{
    // Below arrays detail all four possible movements from a cell,
    // i.e., (top, right, bottom, left)
    private static final int[] row = { -1, 0, 0, 1 };
    private static final int[] col = { 0, -1, 1, 0 };

    // Function to check if it is safe to go to position (x, y)
    // from the current position. The function returns false if (x, y)
    // is unsafe or already visited.
    private static boolean isSafe(int[][] field, boolean visited[][], int x, int y) {
        return (field[x][y] == 1 && !visited[x][y]);
    }

    // Check if (x, y) is valid field coordinates.
    // Note that we cannot go out of the field.
    private static boolean isValid(int x, int y, int M, int N) {
        return (x < M && y < N && x >= 0 && y >= 0);
    }

    // Find the minimum number of steps required to reach the last column
    // from the first column using BFS
    private static int BFS(int[][] field)
    {
        // `M × N` matrix
        int M = field.length;
        int N = field[0].length;

        // stores if a cell is visited or not
        boolean[][] visited = new boolean[M][N];

        // create an empty queue
        Queue<Node> q = new ArrayDeque<>();

        // process every cell of the first column
        for (int r = 0; r < M; r++)
        {
            // if the cell is safe, mark it as visited and
            // enqueue it by assigning it distance as 0
            if (field[r][0] == 1)
            {
                q.add(new Node(r, 0, 0));
                visited[r][0] = true;
            }
        }

        // loop till queue is empty
        while (!q.isEmpty())
        {
            // dequeue front node and process it
            int i = q.peek().x;
            int j = q.peek().y;
            int dist = q.peek().dist;
            q.poll();

            // if the destination is found, return minimum distance
            if (j == N - 1) {
                return dist;
            }

            // check for all four possible movements from the current cell
            // and enqueue each valid movement
            for (int k = 0; k < row.length; k++)
            {
                // skip if the location is invalid or visited, or unsafe
                if (isValid(i + row[k], j + col[k], M, N) &&
                        isSafe(field, visited, i + row[k], j + col[k]))
                {
                    // mark it as visited and enqueue it with +1 distance
                    visited[i + row[k]][j + col[k]] = true;
                    q.add(new Node(i + row[k], j + col[k], dist + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    // Find the shortest path from the first column to the last column in a given field
    public static int findShortestDistance(int[][] mat)
    {
        // base case
        if (mat == null || mat.length == 0) {
            return 0;
        }

        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        // `r[]` and `c[]` detail all eight possible movements from a cell
        // (top, right, bottom, left, and four diagonal moves)
        int[] r = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] c = { -1, 0, 1, -1, 1, -1, 0, 1 };

        // mark adjacent cells of sensors as unsafe
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                for (int k = 0; k < r.length; k++) {
                    if (mat[i][j] == 0 && isValid(i + r[k], j + c[k], M, N)
                            && mat[i + r[k]][j + c[k]] == 1) {
                        mat[i + r[k]][j + c[k]] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        // update the mat
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (mat[i][j] == Integer.MAX_VALUE) {
                    mat[i][j] = 0;
                }
            }
        }

        // call BFS and return the shortest distance found by it
        return BFS(mat);
    }

    public static void main(String[] args)
    {
        int[][] field =
                {
                        { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
                };

        int dist = findShortestDistance(field);

        if (dist != Integer.MAX_VALUE) {
            System.out.print("The shortest safe path has a length of " + dist);
        }
        else {
            System.out.print("No route is safe to reach destination");
        }
    }
}