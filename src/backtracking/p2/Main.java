package backtracking.p2;

/**
 * Print all possible Knight’s tours on a chessboard
 * Link : https://www.techiedelight.com/print-possible-knights-tours-chessboard/
 *
 * The time complexity of the above backtracking solution is exponential,
 * which is impractical on larger boards. For larger N values,
 * it is well beyond modern computers’ capacity (or networks of computers) to perform operations on such a large set.
 */
import java.util.Arrays;

class Main
{
    // `N × N` chessboard
    public static final int N = 5;

    // Below arrays detail all eight possible movements for a knight.
    // Don't change the sequence of the below arrays
    public static final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2, 2 };
    public static final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };

    // Check if `(x, y)` is valid chessboard coordinates.
    // Note that a knight cannot go out of the chessboard
    private static boolean isValid(int x, int y)
    {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }

        return true;
    }

    private static void print(int[][] visited)
    {
        for (var r: visited) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println();
    }

    // Recursive function to perform the knight's tour using backtracking
    public static void knightTour(int[][] visited, int x, int y, int pos)
    {
        // mark the current square as visited
        visited[x][y] = pos;

        // if all squares are visited, print the solution
        if (pos >= N*N)
        {
            print(visited);
            // backtrack before returning
            visited[x][y] = 0;
            return;
        }

        // check for all eight possible movements for a knight
        // and recur for each valid movement
        for (int k = 0; k < 8; k++)
        {
            // get the new position of the knight from the current
            // position on the chessboard
            int newX = x + row[k];
            int newY = y + col[k];

            // if the new position is valid and not visited yet
            if (isValid(newX, newY) && visited[newX][newY] == 0) {
                knightTour(visited, newX, newY, pos + 1);
            }
        }

        // backtrack from the current square and remove it from the current path
        visited[x][y] = 0;
    }

    public static void main(String[] args)
    {
        // `visited[][]` serves two purposes:
        // 1. It keeps track of squares involved in the knight's tour.
        // 2. It stores the order in which the squares are visited.
        int[][] visited = new int[N][N];
        int pos = 1;

        // start knight tour from corner square `(0, 0)`
        knightTour(visited, 0, 0, pos);
    }
}
/*
[1, 6, 15, 10, 21]
[14, 9, 20, 5, 16]
[19, 2, 7, 22, 11]
[8, 13, 24, 17, 4]
[25, 18, 3, 12, 23]

[1, 6, 11, 18, 21]
[12, 17, 20, 5, 10]
[7, 2, 15, 22, 19]
[16, 13, 24, 9, 4]
[25, 8, 3, 14, 23]

[1, 6, 11, 16, 21]
[12, 15, 20, 5, 10]
[7, 2, 13, 22, 17]
[14, 19, 24, 9, 4]
[25, 8, 3, 18, 23]

[1, 6, 17, 12, 21]
[16, 11, 20, 5, 18]
[7, 2, 9, 22, 13]
[10, 15, 24, 19, 4]
[25, 8, 3, 14, 23]

… and 300 other knight’s tours
 */