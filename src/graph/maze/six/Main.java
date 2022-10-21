package graph.maze.six;

/**
 * Link : https://www.techiedelight.com/find-shortest-path-source-destination-matrix-satisfies-given-constraints/
 *
 * Find the shortest path from source to destination in a matrix that satisfies given constraints
 *
 */
import java.util.*;

// A queue node used in BFS
class Node
{
    // (x, y) represents coordinates of a cell in the matrix
    int x, y;

    // maintain a parent node for printing the final path
    Node parent;

    Node(int x, int y, Node parent)
    {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}

class Main
{
    // Below arrays detail all four possible movements from a cell
    private static int[] row = { -1, 0, 0, 1 };
    private static int[] col = { 0, -1, 1, 0 };

    // The function returns false if (x, y) is not a valid position
    private static boolean isValid(int x, int y, int N) {
        return (x >= 0 && x < N) && (y >= 0 && y < N);
    }

    // Utility function to find path from source to destination
    private static void findPath(Node node, List<String> path)
    {
        if (node != null) {
            findPath(node.parent, path);
            path.add(node.toString());
        }
    }

    // Find the shortest route in a matrix from source cell (x, y) to
    // destination cell (N-1, N-1)
    public static List<String> findPath(int[][] matrix, int x, int y)
    {
        // list to store shortest path
        List<String> path = new ArrayList<>();

        // base case
        if (matrix == null || matrix.length == 0) {
            return path;
        }

        // `N × N` matrix
        int N = matrix.length;

        // create a queue and enqueue the first node
        Queue<Node> q = new ArrayDeque<>();
        Node src = new Node(x, y, null);
        q.add(src);

        // set to check if the matrix cell is visited before or not
        Set<String> visited = new HashSet<>();

        String key = src.x + "," + src.y;
        visited.add(key);

        // loop till queue is empty
        while (!q.isEmpty())
        {
            // dequeue front node and process it
            Node curr = q.poll();
            int i = curr.x, j = curr.y;

            // return if the destination is found
            if (i == N - 1 && j == N - 1) {
                findPath(curr, path);
                return path;
            }

            // value of the current cell
            int n = matrix[i][j];

            // check all four possible movements from the current cell
            // and recur for each valid movement
            for (int k = 0; k < row.length; k++)
            {
                // get next position coordinates using the value of the current cell
                x = i + row[k] * n;
                y = j + col[k] * n;

                // check if it is possible to go to the next position
                // from the current position
                if (isValid(x, y, N))
                {
                    // construct the next cell node
                    Node next = new Node(x, y, curr);

                    key = next.x + "," + next.y;

                    // if it isn't visited yet
                    if (!visited.contains(key))
                    {
                        // enqueue it and mark it as visited
                        q.add(next);
                        visited.add(key);
                    }
                }
            }
        }

        // we reach here if the path is not possible
        return path;
    }

    public static void main(String[] args)
    {
        int[][] matrix =
                {
                        { 4, 4, 6, 5, 5, 1, 1, 1, 7, 4 },
                        { 3, 6, 2, 4, 6, 5, 7, 2, 6, 6 },
                        { 1, 3, 6, 1, 1, 1, 7, 1, 4, 5 },
                        { 7, 5, 6, 3, 1, 3, 3, 1, 1, 7 },
                        { 3, 4, 6, 4, 7, 2, 6, 5, 4, 4 },
                        { 3, 2, 5, 1, 2, 5, 1, 2, 3, 4 },
                        { 4, 2, 2, 2, 5, 2, 3, 7, 7, 3 },
                        { 7, 2, 4, 3, 5, 2, 2, 3, 6, 3 },
                        { 5, 1, 4, 2, 6, 4, 6, 7, 3, 7 },
                        { 1, 4, 1, 7, 5, 3, 6, 5, 3, 4 }
                };

        // Find a route in the matrix from source cell (0, 0) to
        // destination cell (N-1, N-1)
        List<String> path = findPath(matrix, 0, 0);

        if (path != null && path.size() > 0) {
            System.out.print("The shortest path is " + path);
        } else {
            System.out.println("Destination is not found");
        }
    }
}