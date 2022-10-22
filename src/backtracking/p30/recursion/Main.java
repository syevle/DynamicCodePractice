package backtracking.p30.recursion;


/**
 * Easy
 * Link : https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/
 *
 * Print all paths from the root to leaf nodes of a binary tree
 *
 * The idea is to traverse the tree in a preorder fashion and store every encountered node in
 * the current path from the root-to-leaf in a vector.
 * If we encounter a leaf node, print all nodes present in the vector.
 *
 * The time complexity of the above solution is O(n), where n is the total number of nodes in the binary tree.
 * The program requires O(h) extra space for the call stack, where h is the height of the tree.
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

class Main
{
    // Function to check if a given node is a leaf node or not
    public static boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

    // Recursive function to find paths from the root node to every leaf node
    public static void printRootToLeafPaths(Node node, Deque<Integer> path)
    {
        // base case
        if (node == null) {
            return;
        }

        // include the current node to the path
        path.addLast(node.data);

        // if a leaf node is found, print the path
        if (isLeaf(node)) {
            System.out.println(path);
        }

        // recur for the left and right subtree
        printRootToLeafPaths(node.left, path);
        printRootToLeafPaths(node.right, path);

        // backtrack: remove the current node after the left, and right subtree are done
        path.removeLast();
    }

    // The main function to print paths from the root node to every leaf node
    public static void printRootToLeafPaths(Node node)
    {
        // list to store root-to-leaf path
        Deque<Integer> path = new ArrayDeque<>();
        printRootToLeafPaths(node, path);
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

        // print all root-to-leaf paths
        printRootToLeafPaths(root);
    }
}


/**
 * [1, 2, 4]
 * [1, 2, 5]
 * [1, 3, 6, 8]
 * [1, 3, 7, 9]
 */