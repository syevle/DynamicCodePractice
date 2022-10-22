package maze.three;

/**
 * Link : https://www.techiedelight.com/find-shortest-path-in-maze/
 * Find the shortest path in a maze
 *
 *
 */
class Main
{
    // Check if it is possible to go to (x, y) from the current position. The
    // function returns false if the cell is invalid, has value 0, or already visited
    private static boolean isSafe(int[][] mat, boolean[][] visited, int x, int y) {
        return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
                mat[x][y] == 1 && !visited[x][y];
    }

    // Find the shortest possible route in a matrix `mat` from source cell (i, j)
    // to destination cell (x, y).
    // `min_dist` stores the length of the longest path from source to a destination
    // found so far, and `dist` maintains the length of the path from a source cell
    // to the current cell (i, j).
    public static int findShortestPath(int[][] mat, boolean[][] visited,
                                       int i, int j, int x, int y, int min_dist, int dist)
    {
        // if the destination is found, update `min_dist`
        if (i == x && j == y) {
            return Integer.min(dist, min_dist);
        }

        // set (i, j) cell as visited
        visited[i][j] = true;

        // go to the bottom cell
        if (isSafe(mat, visited, i + 1, j))
        {
            min_dist = findShortestPath(mat, visited, i + 1, j, x, y,
                    min_dist, dist + 1);
        }

        // go to the right cell
        if (isSafe(mat, visited, i, j + 1))
        {
            min_dist = findShortestPath(mat, visited, i, j + 1, x, y,
                    min_dist, dist + 1);
        }

        // go to the top cell
        if (isSafe(mat, visited, i - 1, j))
        {
            min_dist = findShortestPath(mat, visited, i - 1, j, x, y,
                    min_dist, dist + 1);
        }

        // go to the left cell
        if (isSafe(mat, visited, i, j - 1))
        {
            min_dist = findShortestPath(mat, visited, i, j - 1, x, y,
                    min_dist, dist + 1);
        }

        // backtrack: remove (i, j) from the visited matrix
        visited[i][j] = false;

        return min_dist;
    }

    // Wrapper over findShortestPath() function
    public static int findShortestPathLength(int[][] mat, int i, int j, int x, int y)
    {
        // base case: invalid input
        if (mat == null || mat.length == 0 || mat[i][j] == 0 || mat[x][y] == 0) {
            return -1;
        }

        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        int min_dist;

        // construct an `M × N` matrix to keep track of visited cells
        boolean[][] visited = new boolean[M][N];

        min_dist = findShortestPath(mat, visited, i, j, x, y, Integer.MAX_VALUE, 0);
        if (min_dist != Integer.MAX_VALUE) {
            return min_dist;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int mat[][] =
                {
                        { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                        { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                        { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
                        { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
                        { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                        { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                        { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                        { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
                };

        int min_dist = findShortestPathLength(mat, 0, 0, 7, 5);

        if (min_dist != -1) {
            System.out.println("The shortest path from source to destination " +
                    "has length " + min_dist);
        } else {
            System.out.println("Destination cannot be reached from source");
        }
    }
}
