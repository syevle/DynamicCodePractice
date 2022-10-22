package backtracking.p9;

/**
 * Magnet Puzzle
 *
 *
 * Link : https://www.techiedelight.com/magnet-puzzle/
 */


import java.util.Arrays;

class Main
{
    // `M × N` matrix
    private static final int M = 5;
    private static final int N = 6;

    // Utility function to print solution
    private static void printSolution(char[][] board) {
        for (var r: board) {
            System.out.println(Arrays.toString(r));
        }
    }

    // Utility function to count the total number of characters `ch` in
    // current column `j`
    private static int countInColumns(char[][] board, char ch, int j)
    {
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (board[i][j] == ch) {
                count++;
            }
        }
        return count;
    }

    // Utility function to count the total number of characters `ch` in
    // current row `i`
    private static int countInRow(char[][] board, char ch, int i)
    {
        int count = 0;
        for (int j = 0; j < N; j++) {
            if (board[i][j] == ch) {
                count++;
            }
        }
        return count;
    }

    // Function to check if it is safe to put `ch` at `board[row][col]`
    private static boolean isSafe(char[][] board, int row, int col, char ch,
                                  int[] top, int[] left, int[] bottom, int[] right)
    {
        // check for adjacent cells
        if ((row - 1 >= 0 && board[row - 1][col] == ch) ||
                (col + 1 < N && board[row][col + 1] == ch) ||
                (row + 1 < M && board[row + 1][col] == ch) ||
                (col - 1 >= 0 && board[row][col - 1] == ch)) {
            return false;
        }

        // count character `ch` in the current row
        int rowCount = countInRow(board, ch, row);

        // count character `ch` in the current column
        int colCount = countInColumns(board, ch, col);

        // if the given character is `+`, check `top[]` and `left[]`
        if (ch == '+')
        {
            // check top
            if (top[col] != -1 && colCount >= top[col]) {
                return false;
            }

            // check left
            if (left[row] != -1 && rowCount >= left[row]) {
                return false;
            }
        }

        // if the given character is `-`, check `bottom[]` and `right[]`
        if (ch == '-')
        {
            // check bottom
            if (bottom[col] != -1 && colCount >= bottom[col]) {
                return false;
            }

            // check left
            if (right[row] != -1 && rowCount >= right[row]) {
                return false;
            }
        }

        return true;
    }

    // Function to validate the configuration of an output board
    private static boolean validateConfiguration(char[][] board, int[] top, int[] left,
                                                 int[] bottom, int[] right)
    {
        // check top
        for (int i = 0; i < N; i++)
        {
            if (top[i] != -1 && countInColumns(board, '+', i) != top[i]) {
                return false;
            }
        }

        // check left
        for (int j = 0; j < M; j++)
        {
            if (left[j] != -1 && countInRow(board, '+', j) != left[j]) {
                return false;
            }
        }

        // check bottom
        for (int i = 0; i < N; i++)
        {
            if (bottom[i] != -1 && countInColumns(board, '-', i) != bottom[i]) {
                return false;
            }
        }

        // check right
        for (int j = 0; j < M; j++)
        {
            if (right[j] != -1 && countInRow(board, '-', j) != right[j]) {
                return false;
            }
        }

        return true;
    }

    // The main function to solve the Bipolar Magnets puzzle
    private static boolean solveMagnetPuzzle(char[][] board, int row, int col,
                                             int[] top, int[] left, int[] bottom,
                                             int[] right, char[][] rules)
    {
        // if the last cell is reached
        if (row >= M - 1 && col >= N - 1) {
            return validateConfiguration(board, top, left, bottom, right);
        }

        // if the last column of the current row is already processed,
        // go to the next row, the first column
        if (col >= N)
        {
            col = 0;
            row = row + 1;
        }

        // if the current cell contains `R` or `B` (end of horizontal
        // or vertical slot), recur for the next cell
        if (rules[row][col] == 'R' || rules[row][col] == 'B')
        {
            if (solveMagnetPuzzle(board, row, col + 1, top,
                    left, bottom, right, rules)) {
                return true;
            }
        }

        // if horizontal slot contains `L` and `R`
        if (rules[row][col] == 'L' && rules[row][col + 1] == 'R')
        {
            // put (`+`, `-`) pair and recur
            if (isSafe(board, row, col, '+', top, left, bottom, right) &&
                    isSafe(board, row, col + 1, '-', top, left, bottom, right))
            {
                board[row][col] = '+';
                board[row][col + 1] = '-';

                if (solveMagnetPuzzle(board, row, col + 2,
                        top, left, bottom, right, rules)) {
                    return true;
                }

                // if it doesn't lead to a solution, backtrack
                board[row][col] = 'X';
                board[row][col + 1] = 'X';
            }

            // put (`-`, `+`) pair and recur
            if (isSafe(board, row, col, '-', top, left, bottom, right) &&
                    isSafe(board, row, col + 1, '+', top, left, bottom, right))
            {
                board[row][col] = '-';
                board[row][col + 1] = '+';

                if (solveMagnetPuzzle(board, row, col + 2,
                        top, left, bottom, right, rules)) {
                    return true;
                }

                // if it doesn't lead to a solution, backtrack
                board[row][col] = 'X';
                board[row][col + 1] = 'X';
            }
        }

        // if vertical slot contains `T` and `B`
        if (rules[row][col] == 'T' && rules[row + 1][col] == 'B')
        {
            // put (`+`, `-`) pair and recur
            if (isSafe(board, row, col, '+', top, left, bottom, right) &&
                    isSafe(board, row + 1, col, '-', top, left, bottom, right))
            {
                board[row][col] = '+';
                board[row + 1][col] = '-';

                if (solveMagnetPuzzle(board, row, col + 1,
                        top, left, bottom, right, rules)) {
                    return true;
                }

                // if it doesn't lead to a solution, backtrack
                board[row][col] = 'X';
                board[row + 1][col] = 'X';
            }

            // put (`-`, `+`) pair and recur
            if (isSafe(board, row, col, '-', top, left, bottom, right) &&
                    isSafe(board, row + 1, col, '+', top, left, bottom, right))
            {
                board[row][col] = '-';
                board[row + 1][col] = '+';

                if (solveMagnetPuzzle(board, row, col + 1,
                        top, left, bottom, right, rules)) {
                    return true;
                }

                // if it doesn't lead to a solution, backtrack
                board[row][col] = 'X';
                board[row + 1][col] = 'X';
            }
        }

        // ignore the current cell and recur
        if (solveMagnetPuzzle(board, row, col + 1,
                top, left, bottom, right, rules)) {
            return true;
        }

        // if no solution is possible, return false
        return false;
    }

    public static void solveMagnetPuzzle(int[] top, int[] left, int[] bottom,
                                         int[] right, char[][] rules)
    {
        // to store the result
        char[][] board = new char[M][N];

        // initialize all cells by `X`
        for (int i = 0; i < M; i++) {
            Arrays.fill(board[i], 'X');
        }

        // start from `(0, 0)` cell
        if (!solveMagnetPuzzle(board, 0, 0, top, left, bottom, right, rules))
        {
            System.out.println("Solution does not exist");
            return;
        }

        // print result if the given configuration is solvable
        printSolution(board);
    }

    public static void main(String[] args)
    {
        // indicates the count of `+` or `-` along the top (+), bottom (-),
        // left (+), and right (-) edges, respectively.
        // value of -1 indicate any number of `+` or `-` signs
        final int[] top = { 1, -1, -1, 2, 1, -1 };
        final int[] bottom = { 2, -1, -1, 2, -1, 3 };
        final int[] left = { 2, 3, -1, -1, -1 };
        final int[] right = { -1, -1, -1, 1, -1 };

        // rules matrix
        char[][] rules =
                {
                        { 'L', 'R', 'L', 'R', 'T', 'T' },
                        { 'L', 'R', 'L', 'R', 'B', 'B' },
                        { 'T', 'T', 'T', 'T', 'L', 'R' },
                        { 'B', 'B', 'B', 'B', 'T', 'T' },
                        { 'L', 'R', 'L', 'R', 'B', 'B' }
                };

        solveMagnetPuzzle(top, left, bottom, right, rules);
    }
}

/**
 *
 * [+, -, +, -, X, -]
 * [-, +, -, +, X, +]
 * [X, X, +, -, +, -]
 * [X, X, -, +, X, +]
 * [-, +, X, X, X, -]
 */