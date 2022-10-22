package backtracking.p32;

/**
 *
 * Generate the power set of a given set
 * Link : https://www.techiedelight.com/generate-power-set-given-set/
 *
 */
import java.util.ArrayDeque;
import java.util.Deque;

class Main
{
    // Function to generate power set of a given set `S`
    public static void findPowerSet(int[] S, Deque<Integer> set, int n)
    {
        // if we have considered all elements
        if (n == 0)
        {
            System.out.println(set);
            return;
        }

        // consider the n'th element
        set.addLast(S[n - 1]);
        findPowerSet(S, set, n - 1);

        set.removeLast();                    // backtrack

        // or don't consider the n'th element
        findPowerSet(S, set, n - 1);
    }

    public static void main(String[] args)
    {
        int[] S = { 1, 2, 3 };

        Deque<Integer> set = new ArrayDeque<>();
        findPowerSet(S, set, S.length);
    }
}
/**
 *
 * Output:
 *
 * [3, 2, 1]
 * [3, 2]
 * [3, 1]
 * [3]
 * [2, 1]
 * [2]
 * [1]
 * []
 */