package graph.maze.seven;

/**
 * Link : https://www.techiedelight.com/find-longest-possible-route-matrix/
 *
 * Find the longest possible route in a matrix
 */


class Main
{
    // Check if it is possible to go to position (x, y) from
    // the current position. The function returns false if the cell
    // is invalid, has a value 0, or it is already visited.
    private static boolean isSafe(int[][] mat, boolean[][] visited, int x, int y) {
        return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
                mat[x][y] == 1 && !visited[x][y];
    }

    // Find the longest possible route in a matrix `mat` from the source cell
    // (i, j) to destination cell (x, y).
    // `max_dist` —> keep track of the length of the longest path from source to
    // destination.
    // `dist` —> length of the path from the source cell to the current cell (i, j).
    public static int findLongestPath(int[][] mat, boolean[][] visited, int i, int j,
                                      int x, int y, int max_dist, int dist)
    {
        // if the destination is not possible from the current cell
        if (mat[i][j] == 0) {
            return 0;
        }

        // if the destination is found, update `max_dist`
        if (i == x && j == y) {
            return Integer.max(dist, max_dist);
        }

        // set (i, j) cell as visited
        visited[i][j] = true;

        // go to the bottom cell
        if (isSafe(mat, visited, i + 1, j))
        {
            max_dist = findLongestPath(mat, visited, i + 1, j, x, y,
                    max_dist, dist + 1);
        }

        // go to the right cell
        if (isSafe(mat, visited, i, j + 1))
        {
            max_dist = findLongestPath(mat, visited, i, j + 1, x, y,
                    max_dist, dist + 1);
        }

        // go to the top cell
        if (isSafe(mat, visited, i - 1, j))
        {
            max_dist = findLongestPath(mat, visited, i - 1, j, x, y,
                    max_dist, dist + 1);
        }

        // go to the left cell
        if (isSafe(mat, visited, i, j - 1))
        {
            max_dist = findLongestPath(mat, visited, i, j - 1, x, y,
                    max_dist, dist + 1);
        }

        // backtrack: remove (i, j) from the visited matrix
        visited[i][j] = false;

        return max_dist;
    }

    // Wrapper over findLongestPath() function
    public static int findLongestPathLength(int[][] mat, int i, int j, int x, int y)
    {
        // base case: invalid input
        if (mat == null || mat.length == 0 || mat[i][j] == 0 || mat[x][y] == 0) {
            return -1;
        }

        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        // construct an `M × N` matrix to keep track of visited cells
        boolean[][] visited= new boolean[M][N];

        // (i, j) are the source cell, and (x, y) are the destination
        // cell coordinates
        return findLongestPath(mat, visited, i, j, x, y, 0, 0);
    }

    public static void main(String[] args)
    {
        // input matrix
        int mat[][] =
                {
                        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                        { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 },
                        { 1, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                        { 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
                        { 1, 1, 0, 0, 1, 0, 0, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }
                };

        // (0, 0) are the source cell, and (5, 7) are the destination
        // cell coordinates
        int max_dist = findLongestPathLength(mat, 0, 0, 5, 7);
        System.out.println("The maximum length path is " + max_dist);
    }
}