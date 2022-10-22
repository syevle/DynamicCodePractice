package maze.eight;

/**
 * Link : https://www.techiedelight.com/find-shortest-distance-every-cell-landmine-maze/
 *
 * Find the shortest distance of every cell from a landmine inside a maze
 */
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

// A Queue Node
class Node
{
    int x;              // stores x–coordinate of a matrix cell
    int y;              // stores y–coordinate of a matrix cell
    int distance;       // stores the distance of (x, y) from mine

    Node(int x, int y, int distance)
    {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Main
{
    // check if specified row and column are valid matrix index
    private static boolean isValid(int i, int j, int M, int N) {
        return (i >= 0 && i < M) && (j >= 0 && j < N);
    }

    // check if the current cell is an open area, and its
    // distance from the mine is not yet calculated
    private static boolean isSafe(int i, int j, char[][] mat, int[][] result) {
        return mat[i][j] == 'O' && result[i][j] == -1;
    }

    // Replace all O's in a matrix with their shortest distance
    // from the nearest mine
    public static int[][] updateShortestDistance(char[][] mat)
    {
        // base case
        if (mat == null || mat.length == 0) {
            return null;
        }

        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        int[][] result = new int[M][N];

        // initialize an empty queue
        Queue<Node> q = new ArrayDeque<>();

        // find all mines location and add them to the queue
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                // if the current cell represents a mine
                if (mat[i][j] == 'M')
                {
                    q.add(new Node(i, j, 0));

                    // update mine distance as 0
                    result[i][j] = 0;
                }
                // otherwise, initialize the mine distance by -1
                else {
                    result[i][j] = -1;
                }
            }
        }

        // arrays to get indices of four adjacent cells of a given cell
        int[] row = { 0, -1, 0, 1 };
        int[] col = { -1, 0, 1, 0 };

        // do for each node in the queue
        while (!q.isEmpty())
        {
            // process front cell in the queue
            int x = q.peek().x;
            int y = q.peek().y;
            int distance = q.peek().distance;

            // dequeue front cell
            q.poll();

            // update the four adjacent cells of the front node in the queue
            for (int i = 0; i < row.length; i++)
            {
                // enqueue adjacent cell if it is valid, unvisited,
                // and has a path through it
                if (isValid(x + row[i], y + col[i], M, N) &&
                        isSafe(x + row[i], y + col[i], mat, result))
                {
                    result[x + row[i]][y + col[i]] = distance + 1;
                    q.add(new Node(x + row[i], y + col[i], distance + 1));
                }
            }
        }

        return result;
    }

    public static void main(String[] args)
    {
        char[][] mat =
                {
                        {'O', 'M', 'O', 'O', 'X'},
                        {'O', 'X', 'X', 'O', 'M'},
                        {'O', 'O', 'O', 'O', 'O'},
                        {'O', 'X', 'X', 'X', 'O'},
                        {'O', 'O', 'M', 'O', 'O'},
                        {'O', 'X', 'X', 'M', 'O'}
                };

        int[][] result = updateShortestDistance(mat);

        // print results
        for (var r: result) {
            System.out.println(Arrays.toString(r));
        }
    }
}
