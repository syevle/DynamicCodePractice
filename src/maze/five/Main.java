package maze.five;

/**
 * Link : https://www.techiedelight.com/find-path-source-destination-matrix-satisfies-given-constraints/
 *
 * Find the path from source to destination in a matrix that satisfies given constraints
 */
import java.util.ArrayList;
import java.util.List;

// Objects of this class stores cell coordinates of the matrix
class Node
{
    int first, second;

    public Node(int first, int second)
    {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object ob)
    {
        Node node = (Node) ob;
        return (first == node.first && second == node.second);
    }

    @Override
    public int hashCode() {
        return 31 * first + second;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }
}

class Main
{
    // Below arrays detail all four possible movements from a cell
    private static final int[] row = { -1, 0, 0, 1 };
    private static final int[] col = { 0, -1, 1, 0 };

    // Function to check if it is possible to go to position `pt`
    // from the current position. The function returns false if `pt` is
    // not a valid position, or it is already visited
    private static boolean isValid(List<Node> path, Node pt, int N)
    {
        return pt.first >= 0 && pt.first < N &&
                pt.second >= 0 && pt.second < N &&
                !path.contains(pt);
    }

    // Find a route in a matrix `mat` from source cell (0, 0) to
    // destination cell (N-1, N-1)
    public static boolean findPath(int mat[][], List<Node> path, Node curr)
    {
        // base case
        if (mat == null || mat.length == 0) {
            return false;
        }

        // `N × N` matrix
        int N = mat.length;

        // include current vertex in the path
        path.add(curr);

        // if the destination is found, return true
        if (curr.first == N - 1 && curr.second == N - 1) {
            return true;
        }

        // get the value of the current cell
        int n = mat[curr.first][curr.second];

        // check all four possible movements from the current cell
        // and recur for each valid movement
        for (int i = 0; i < row.length; i++)
        {
            // get the next position using the value of the current cell
            int x = curr.first + row[i] * n;
            int y = curr.second + col[i] * n;

            Node next = new Node(x, y);

            // check if it is possible to go to a position (x, y)
            // from the current position
            if (isValid(path, next, N) && findPath(mat, path, next)) {
                return true;
            }
        }

        // backtrack: exclude the current cell from the path
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args)
    {
        int mat[][] =
                {
                        { 7, 1, 3, 5, 3, 6, 1, 1, 7, 5 },
                        { 2, 3, 6, 1, 1, 6, 6, 6, 1, 2 },
                        { 6, 1, 7, 2, 1, 4, 7, 6, 6, 2 },
                        { 6, 6, 7, 1, 3, 3, 5, 1, 3, 4 },
                        { 5, 5, 6, 1, 5, 4, 6, 1, 7, 4 },
                        { 3, 5, 5, 2, 7, 5, 3, 4, 3, 6 },
                        { 4, 1, 4, 3, 6, 4, 5, 3, 2, 6 },
                        { 4, 4, 1, 7, 4, 3, 3, 1, 4, 2 },
                        { 4, 4, 5, 1, 5, 2, 3, 5, 3, 5 },
                        { 3, 6, 3, 5, 2, 2, 6, 4, 2, 1 }
                };

        List<Node> path = new ArrayList<>();
        Node source = new Node(0, 0);

        // Find a route in a matrix `mat` from source cell (0, 0) to
        // destination cell (N-1, N-1)
        if (findPath(mat, path, source)) {
            System.out.println(path);
        }
    }
}