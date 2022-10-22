package backtracking.p19;

/**
 *
 * Find all possible combinations by replacing given digits with characters of the corresponding list
 * Link : https://www.techiedelight.com/possible-combinations-replacing-given-digits-corresponding-list/
 *
 */
import java.util.*;

class Main
{
    // Top-down recursive function to find all possible combinations by
    // replacing the key's digits with the corresponding characters in a list
    public static void findCombinations(List<List<Character>> lists,
                                        int[] keys, Set<String> combinations, String result,
                                        int index, Map<Integer, Character> map)
    {
        // print the result if every digit of the key is processed
        if (index == -1) {
            combinations.add(result);
            return;
        }

        // stores the current digit
        int digit = keys[index];

        // get the size of the list corresponding to the current digit
        int len = lists.get(digit).size();

        // if the digit is seen for the first time
        if (!map.containsKey(digit))
        {
            // one by one, replace it with each character in the
            // corresponding list and recur for the next digit
            for (int i = 0; i < len; i++)
            {
                // store character that maps to the current digit in a map
                map.put(digit, lists.get(digit).get(i));

                // recur for the next digit
                findCombinations(lists, keys, combinations, lists.get(digit).get(i) +
                        result, index - 1, map);

                // backtrack
                map.remove(digit);
            }

            return;
        }

        // if the digit is seen before, replace it with the same character
        // used in the previous occurrence
        findCombinations(lists, keys, combinations, map.get(digit) + result,
                index - 1, map);
    }

    public static Set<String> findCombinations(List<List<Character>> lists, int[] keys)
    {
        // HashSet to store all combinations
        Set<String> combinations = new HashSet<>();

        // invalid input
        if (lists == null || lists.size() == 0 || keys == null || keys.length == 0) {
            return combinations;
        }

        // create an empty map to store the mapping of digits
        Map<Integer, Character> map = new HashMap<>();

        // find all combinations
        findCombinations(lists, keys, combinations, "", keys.length - 1, map);

        return combinations;
    }

    public static void main(String[] args)
    {
        // `N` lists of characters
        List<List<Character>> list = Arrays.asList(
                Arrays.asList( 'A', 'B', 'C', 'D' ),
                Arrays.asList( 'E', 'F', 'G', 'H', 'I', 'J', 'K' ),
                Arrays.asList( 'L', 'M', 'N', 'O', 'P', 'Q' ),
                Arrays.asList( 'R', 'S', 'T' ),
                Arrays.asList( 'U', 'V', 'W', 'X', 'Y', 'Z' )
        );

        // input number in the form of an array
        int[] keys = {0, 2, 0};

        // find and print all combinations
        System.out.println(findCombinations(list, keys));
    }
}
/**
 *
 * [AQA, APA, BQB, AOA, ANA, BOB, CQC, AMA, BPB, ALA, BMB, COC, DQD, BNB, CPC, CMC, DOD, BLB, CNC, DPD, DMD, CLC, DND, DLD]
 */