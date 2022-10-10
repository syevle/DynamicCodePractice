package tree;
// A class to store a binary tree node
class Node {
    int data;
    Node left = null,right = null;
    public Node(int data){
        this.data = data;
    }
}

public class ChildrenSum {
    // Function to check if a given binary tree holds children-sum property
    public static int hasChildrenSumProperty(Node root){
        // base case: empty tree
        if (root == null) {
            return 0;
        }

        // base case: leaf node
        if (root.left == null && root.right == null) {
            return root.data;
        }
        int left = hasChildrenSumProperty(root.left);
        int right = hasChildrenSumProperty(root.right);

        // if the root's value is equal to the sum of values at its left and
        // right subtree
        if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE &&
                root.data == left + right) {
            return root.data;
        }

        return Integer.MIN_VALUE;

    }

    public static void main(String[] args)
    {
        /* Construct the following binary tree
                  25
                /    \
               /      \
              /        \
             12        13
            /  \      /  \
           /    \    /    \
          7      5  6      7
        */

        Node root = new Node(25);
        root.left = new Node(12);
        root.right = new Node(13);
        root.left.left = new Node(7);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        if (hasChildrenSumProperty(root) != Integer.MIN_VALUE) {
            System.out.println("Binary tree holds children-sum property");
        }
        else {
            System.out.println("Binary tree does not hold children-sum property");
        }
    }
}
