package backtracking.p30;


/**
 * Easy
 * Link : https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/
 *
 * Print all paths from the root to leaf nodes of a binary tree
 *
 * The problem seems a bit difficult to solve without recursion.
 * There is one workaround where we store the path from the root-to-leaf in a string as we traverse the tree iteratively
 * and print the path whenever we encounter any leaf node. This is demonstrated below in C++, Java, and Python:
 */
import java.util.ArrayDeque;
import java.util.Deque;

// A class to store a binary tree node
class Node
{
    int data;
    Node left = null, right = null;

    Node(int data) {
        this.data = data;
    }
}

// A Pair class
class Pair<U, V>
{
    public final U first;       // first field of a pair
    public final V second;      // second field of a pair

    // Constructs a new Pair with specified values
    private Pair(U first, V second)
    {
        this.first = first;
        this.second = second;
    }

    // Factory method for creating a Typed Pair immutable instance
    public static <U, V> Pair <U, V> of(U a, V b)
    {
        // calls private constructor
        return new Pair<>(a, b);
    }
}

class Main
{
    public static void printRootToleafPathIterative(Node root)
    {
        // base case
        if (root == null) {
            return;
        }

        // create an empty stack to store a pair of tree nodes and
        // its path from the root node
        Deque<Pair<Node, String>> stack = new ArrayDeque<>();

        // push the root node
        stack.add(Pair.of(root, ""));

        // loop till stack is empty
        while (!stack.isEmpty())
        {
            // pop a node from the stack and push the data into the output stack
            Pair<Node, String> pair = stack.pollLast();

            // fetch current node
            Node curr = pair.first;
            String path = pair.second;

            // add the current node to the existing path
            String separator = (path.equals("")) ? "\n" : " —> ";
            path += (separator + curr.data);

            // print the path if the node is a leaf
            if (curr.left == null && curr.right == null) {
                System.out.print(path);
            }

            // push the left and right child of the popped node into the stack
            if (curr.right != null) {
                stack.add(Pair.of(curr.right, path));
            }

            if (curr.left != null) {
                stack.add(Pair.of(curr.left, path));
            }
        }
    }

    public static void main(String[] args)
    {
        /* Construct the following tree
                  1
                /   \
               /     \
              2       3
             / \     / \
            4   5   6   7
                   /     \
                  8       9
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(8);
        root.right.right.right = new Node(9);

        printRootToleafPathIterative(root);
    }
}


/**  ==================== OUT PUT ==============================================
 * 1 —> 2 —> 4
 * 1 —> 2 —> 5
 * 1 —> 3 —> 6 —> 8
 * 1 —> 3 —> 7 —> 9
 */