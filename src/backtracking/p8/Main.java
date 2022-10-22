package backtracking.p8;

/**
 * K–Partition Problem | Printing all partitions
 *
 *
 * Link : https://www.techiedelight.com/k-partition-problem-print-all-subsets/
 */

import java.util.Arrays;
import java.util.stream.IntStream;

class Main
{
    // Function to check if all subsets are filled or not
    private static boolean checkSum(int[] sumLeft, int k)
    {
        boolean r = true;
        for (int i = 0; i < k; i++)
        {
            if (sumLeft[i] != 0) {
                r = false;
            }
        }

        return r;
    }

    // Helper function for solving `k` partition problem.
    // It returns true if there exist `k` subsets with the given sum
    private static boolean subsetSum(int[] S, int n, int[] sumLeft, int[] A, int k)
    {
        // return true if a subset is found
        if (checkSum(sumLeft, k)) {
            return true;
        }

        // base case: no items left
        if (n < 0) {
            return false;
        }

        boolean result = false;

        // consider current item `S[n]` and explore all possibilities
        // using backtracking
        for (int i = 0; i < k; i++)
        {
            if (!result && (sumLeft[i] - S[n]) >= 0)
            {
                // mark the current element subset
                A[n] = i + 1;

                // add the current item to the i'th subset
                sumLeft[i] = sumLeft[i] - S[n];

                // recur for remaining items
                result = subsetSum(S, n - 1, sumLeft, A, k);

                // backtrack: remove the current item from the i'th subset
                sumLeft[i] = sumLeft[i] + S[n];
            }
        }

        // return true if we get a solution
        return result;
    }

    // Function for solving k–partition problem. It prints the subsets if
    // set `S[0…n-1]` can be divided into `k` subsets with equal sum
    public static void partition(int[] S, int k)
    {
        // get the total number of items in `S`
        int n = S.length;

        // base case
        if (n < k)
        {
            System.out.println("k-partition of set S is not possible");
            return;
        }

        // get the sum of all elements in the set
        int sum = IntStream.of(S).sum();
        int[] A = new int[n];

        // create an array of size `k` for each subset and initialize it
        // by their expected sum, i.e., `sum/k`
        int[] sumLeft = new int[k];
        Arrays.fill(sumLeft, sum/k);

        // return true if the sum is divisible by `k` and set `S` can
        // be divided into `k` subsets with equal sum
        boolean result = (sum % k) == 0 && subsetSum(S, n - 1, sumLeft, A, k);

        if (!result)
        {
            System.out.println("k-partition of set S is not possible");
            return;
        }

        // print all k–partitions
        for (int i = 0; i < k; i++)
        {
            System.out.print("Partition " + i + " is ");
            for (int j = 0; j < n; j++)
            {
                if (A[j] == i + 1) {
                    System.out.print(S[j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        // Input: a set of integers
        int[] S = { 7, 3, 5, 12, 2, 1, 5, 3, 8, 4, 6, 4 };
        int k = 5;

        partition(S, k);
    }
}

/**
 *
 * Partition 0 is 2 6 4
 * Partition 1 is 8 4
 * Partition 2 is 3 1 5 3
 * Partition 3 is 12
 * Partition 4 is 7 5
 */