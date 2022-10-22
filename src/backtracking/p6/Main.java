package backtracking.p6;

/**
 * Find all combinations of elements satisfying given constraints
 *
 * Link : https://www.techiedelight.com/find-combinations-of-elements-satisfies-given-constraints/
 */
import java.util.Arrays;

class Main
{
    // Find all combinations that satisfy the given constraints
    public static void findAllCombinations(int[] arr, int x, int n)
    {
        // if all elements are filled, print the solution
        if (x > n)
        {
            System.out.println(Arrays.toString(arr));
            return;
        }

        // try all possible combinations for element `x`
        for (int i = 0; i < 2*n; i++)
        {
            // if position `i` and `i+x+1` are not occupied in the input
            if (arr[i] == -1 && (i + x + 1) < 2*n && arr[i + x + 1] == -1)
            {
                // place `x` at position `i` and `i+x+1`
                arr[i] = x;
                arr[i + x + 1] = x;

                // recur for the next element
                findAllCombinations(arr, x + 1, n);

                // backtrack (remove `x` from position `i` and `i+x+1`)
                arr[i] = -1;
                arr[i + x + 1] = -1;
            }
        }
    }

    public static void main(String[] args)
    {
        // given number
        int n = 7;

        // create an input array double the size of a given number with
        // all its elements initialized by -1
        int[] arr = new int[2*n];
        Arrays.fill(arr, -1);

        // start from element 1
        int x = 1;
        findAllCombinations(arr, x, n);
    }
}

/**
 * [1, 7, 1, 2, 5, 6, 2, 3, 4, 7, 5, 3, 6, 4]
 * [1, 7, 1, 2, 6, 4, 2, 5, 3, 7, 4, 6, 3, 5]
 * [1, 6, 1, 7, 2, 4, 5, 2, 6, 3, 4, 7, 5, 3]
 * [1, 5, 1, 6, 7, 2, 4, 5, 2, 3, 6, 4, 7, 3]
 * [1, 4, 1, 5, 6, 7, 4, 2, 3, 5, 2, 6, 3, 7]
 * [1, 4, 1, 6, 7, 3, 4, 5, 2, 3, 6, 2, 7, 5]
 * [1, 6, 1, 3, 5, 7, 4, 3, 6, 2, 5, 4, 2, 7]
 * [1, 5, 1, 7, 3, 4, 6, 5, 3, 2, 4, 7, 2, 6]
 * [1, 5, 1, 6, 3, 7, 4, 5, 3, 2, 6, 4, 2, 7]
 * [1, 5, 1, 4, 6, 7, 3, 5, 4, 2, 3, 6, 2, 7]
 * [5, 1, 7, 1, 6, 2, 5, 4, 2, 3, 7, 6, 4, 3]
 * [4, 1, 7, 1, 6, 4, 2, 5, 3, 2, 7, 6, 3, 5]
 * [4, 1, 6, 1, 7, 4, 3, 5, 2, 6, 3, 2, 7, 5]
 * [7, 1, 3, 1, 6, 4, 3, 5, 7, 2, 4, 6, 2, 5]
 * [7, 1, 4, 1, 6, 3, 5, 4, 7, 3, 2, 6, 5, 2]
 * [6, 1, 5, 1, 7, 3, 4, 6, 5, 3, 2, 4, 7, 2]
 * [4, 6, 1, 7, 1, 4, 5, 2, 6, 3, 2, 7, 5, 3]
 * [7, 3, 1, 6, 1, 3, 4, 5, 7, 2, 6, 4, 2, 5]
 * [4, 6, 1, 7, 1, 4, 3, 5, 6, 2, 3, 7, 2, 5]
 * [5, 6, 1, 7, 1, 3, 5, 4, 6, 3, 2, 7, 4, 2]
 * [7, 4, 1, 5, 1, 6, 4, 3, 7, 5, 2, 3, 6, 2]
 * [5, 7, 1, 4, 1, 6, 5, 3, 4, 7, 2, 3, 6, 2]
 * [3, 6, 7, 1, 3, 1, 4, 5, 6, 2, 7, 4, 2, 5]
 * [5, 7, 4, 1, 6, 1, 5, 4, 3, 7, 2, 6, 3, 2]
 * [2, 6, 7, 2, 1, 5, 1, 4, 6, 3, 7, 5, 4, 3]
 * [4, 5, 6, 7, 1, 4, 1, 5, 3, 6, 2, 7, 3, 2]
 * [2, 3, 7, 2, 6, 3, 5, 1, 4, 1, 7, 6, 5, 4]
 * [3, 4, 5, 7, 3, 6, 4, 1, 5, 1, 2, 7, 6, 2]
 * [2, 3, 6, 2, 7, 3, 4, 5, 1, 6, 1, 4, 7, 5]
 * [5, 2, 4, 7, 2, 6, 5, 4, 1, 3, 1, 7, 6, 3]
 * [2, 6, 3, 2, 7, 4, 3, 5, 6, 1, 4, 1, 7, 5]
 * [2, 6, 3, 2, 5, 7, 3, 4, 6, 1, 5, 1, 4, 7]
 * [2, 4, 7, 2, 3, 6, 4, 5, 3, 1, 7, 1, 6, 5]
 * [5, 2, 7, 3, 2, 6, 5, 3, 4, 1, 7, 1, 6, 4]
 * [5, 2, 4, 6, 2, 7, 5, 4, 3, 1, 6, 1, 3, 7]
 * [3, 5, 7, 2, 3, 6, 2, 5, 4, 1, 7, 1, 6, 4]
 * [2, 7, 4, 2, 3, 5, 6, 4, 3, 7, 1, 5, 1, 6]
 * [2, 5, 6, 2, 3, 7, 4, 5, 3, 6, 1, 4, 1, 7]
 * [5, 2, 6, 4, 2, 7, 5, 3, 4, 6, 1, 3, 1, 7]
 * [5, 7, 2, 3, 6, 2, 5, 3, 4, 7, 1, 6, 1, 4]
 * [5, 3, 6, 7, 2, 3, 5, 2, 4, 6, 1, 7, 1, 4]
 * [3, 4, 6, 7, 3, 2, 4, 5, 2, 6, 1, 7, 1, 5]
 * [7, 2, 6, 3, 2, 4, 5, 3, 7, 6, 4, 1, 5, 1]
 * [7, 2, 4, 6, 2, 3, 5, 4, 7, 3, 6, 1, 5, 1]
 * [6, 2, 7, 4, 2, 3, 5, 6, 4, 3, 7, 1, 5, 1]
 * [7, 2, 4, 5, 2, 6, 3, 4, 7, 5, 3, 1, 6, 1]
 * [5, 7, 2, 6, 3, 2, 5, 4, 3, 7, 6, 1, 4, 1]
 * [7, 3, 6, 2, 5, 3, 2, 4, 7, 6, 5, 1, 4, 1]
 * [3, 7, 4, 6, 3, 2, 5, 4, 2, 7, 6, 1, 5, 1]
 * [3, 5, 7, 4, 3, 6, 2, 5, 4, 2, 7, 1, 6, 1]
 * [5, 3, 6, 4, 7, 3, 5, 2, 4, 6, 2, 1, 7, 1]
 * [4, 6, 3, 5, 7, 4, 3, 2, 6, 5, 2, 1, 7, 1]
 */
