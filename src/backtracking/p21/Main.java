package backtracking.p21;

/**
 *
 * Find all n-digit numbers with equal sum of digits at even and odd indices
 * Link : https://www.techiedelight.com/find-n-digit-numbers-with-equal-sum-even-odd-digits/
 *
 */
class Main
{
    // Function to find all n–digit numbers with an equal sum of digits at even
    // and odd index in a bottom-up manner
    public static void findNdigitNums(String result, int n, int diff)
    {
        // if the number is less than n–digit
        if (n > 0)
        {
            char ch = '0';

            // special case: number cannot start from 0
            if (result.equals("")) {
                ch = '1';
            }

            // consider every valid digit and put it in the current
            // index and recur for the next index
            while (ch <= '9')
            {
                int absdiff;

                // update difference between odd and even digits
                if ((n & 1) != 0)
                {
                    // add value to `diff` if the digit is odd
                    absdiff = diff + (ch - '0');
                }
                else {
                    // subtract a value from `diff` if even
                    absdiff = diff - (ch - '0');
                }

                findNdigitNums(result + ch, n - 1, absdiff);
                ch++;
            }
        }

        // if the number becomes n–digit with an equal sum of even and odd
        // digits, print it
        else if (n == 0 && Math.abs(diff) == 0) {
            System.out.println(result);
        }
    }

    public static void main(String[] args)
    {
        int n = 3;        // n–digit
        String result = "";

        findNdigitNums(result, n, 0);
    }
}

/**
 *
 * 110 121 132 143 154 165 176 187 198 220 231 242 253 264 275 286 297 330 341 352 363 374 385 396 440 451 462 473 484 495 550 561 572 583 594 660 671 682 693 770 781 792 880 891 990
 */