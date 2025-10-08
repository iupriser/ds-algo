package greedy.easy;

public class ValidParenthesis {
    /**
     * try all possible combinations, recursion
     * TC: O(3^N), SC:O(N)
     */
    boolean checkValidStringBrute(String s) {
        return isValid(s, 0, 0);
    }

    // Recursive function to check if the parenthesis string is valid
    boolean isValid(String s, int ind, int cntOpen) {
        if (cntOpen < 0) return false;
        if (s.length() == ind) return cntOpen == 0;
        int c = s.charAt(ind);
        if (c == '(') return isValid(s, ind + 1, cntOpen + 1);
        else if (c == ')') return isValid(s, ind + 1, cntOpen - 1);
        else {
            return isValid(s, ind + 1, cntOpen + 1)
                    || isValid(s, ind + 1, cntOpen - 1)
                    || isValid(s, ind + 1, cntOpen);
        }
    }

    /**
     * Memoization to optimize
     * two variables /two states
     * The state of our recursion is defined by the parameters that change with each call.
     * In your function isValid(String s, int ind, int cntOpen), the string s is constant, but ind and cntOpen change.
     * <p>
     * ind: The current index we are examining in the string.
     * <p>
     * cntOpen: The current balance of open parentheses.
     * <p>
     * Therefore, our unique state can be represented by the pair (ind, cntOpen). This will be the key for our cache.
     */
    // memo[index][cntOpen]
    //0 = not computed, 1= true, -1= false
    private int[][] memo;

    // TC: O(N^2), SC: O(N^2)
    public boolean checkValidStringMemoization(String s) {
        int n = s.length();
        memo = new int[n + 1][n + 1];
        return isValidMemoization(s, 0, 0);
    }

    /**
     * Memoization (Top-Down): You start with the goal (e.g., isValid(0, 0)) and use recursion.
     * If you encounter a subproblem you've seen, you use the cached answer.
     */
    private boolean isValidMemoization(String s, int ind, int cntOpen) {
        // Base case: An invalid state where open count is negative
        if (cntOpen < 0) return false;
        // STEP 1: Check the cache first!
        // If this state has been computed, return the stored result.
        if (memo[ind][cntOpen] != 0) {
            return memo[ind][cntOpen] == 1;
        }
        // Base case: We've reached the end of the string
        if (s.length() == ind) {
            boolean result = (cntOpen == 0);
            // STEP 2: Store the result before returning
            memo[ind][cntOpen] = result ? 1 : -1;
            return result;
        }
        // --- Original recursive logic ---
        boolean ans;
        int c = s.charAt(ind);
        if (c == '(') ans = isValidMemoization(s, ind + 1, cntOpen + 1);
        else if (c == ')') ans = isValidMemoization(s, ind + 1, cntOpen - 1);
        else {
            boolean asOpen = isValidMemoization(s, ind + 1, cntOpen + 1);
            boolean asClose = isValidMemoization(s, ind + 1, cntOpen - 1);
            boolean asEmpty = isValidMemoization(s, ind + 1, cntOpen);
            ans = asOpen || asClose || asEmpty;
        }
        // STEP 3: Store the final computed answer for this state in the cache
        memo[ind][cntOpen] = ans ? 1 : -1;
        return ans;
    }

    /**
     * Tabulation == bottom up approach of DP, for better SC
     * Tabulation solves the problem by filling a DP table starting from the smallest,
     * simplest subproblems and building up to the final solution.
     * Tabulation (Bottom-Up): You start with the base case (the end of the string)
     * and iteratively compute the answers for larger subproblems until you reach the original goal (isValid(0, 0)).
     * TC: O(N^2), SC: O(N^2)
     */
    private boolean isValidTabulation(String s) {
        int n = s.length();
        // dp[i][j]: is s[i:] valid with j open parentheses?
        boolean[][] dp = new boolean[n + 1][n + 1];

        // Step 2: Base Case
        dp[n][0] = true;

        // Step 3: Iterate backward from the end of the string
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= n; j++) {
                boolean isValid = false;
                char c = s.charAt(i);

                if (c == '(') {
                    if (j + 1 <= n) {
                        isValid = dp[i + 1][j + 1];
                    }
                } else if (c == ')') {
                    if (j - 1 >= 0) {
                        isValid = dp[i + 1][j - 1];
                    }
                } else { // It's a '*'
                    // Case 1: '*' is '('
                    if (j + 1 <= n) {
                        isValid = isValid || dp[i + 1][j + 1];
                    }
                    // Case 2: '*' is ')'
                    if (j - 1 >= 0) {
                        isValid = isValid || dp[i + 1][j - 1];
                    }
                    // Case 3: '*' is empty
                    isValid = isValid || dp[i + 1][j];
                }
                dp[i][j] = isValid;
            }
        }

        // Final answer
        return dp[0][0];
    }

    /**
     * Space-Optimized Tabulation
     * TC: O(N^2), SC: O(N)
     * if you look closely at the logic, to compute the values for dp[i],
     * we only need the values from dp[i+1]. We don't need the entire table.
     * This means we can optimize the space from O(N^2) to O(N) by only keeping track of the previous row.
     */
    public boolean checkValidStringSpaceOptimized(String s) {
        int n = s.length();
        // Only need the previous row's results
        boolean[] dp = new boolean[n + 1];

        // Base Case
        dp[0] = true;

        // Iterate backward from the end of the string
        for (int i = n - 1; i >= 0; i--) {
            boolean[] currentRow = new boolean[n + 1];
            for (int j = 0; j <= n; j++) {
                boolean isValid = false;
                char c = s.charAt(i);

                if (c == '(') {
                    if (j + 1 <= n) isValid = dp[j + 1];
                } else if (c == ')') {
                    if (j - 1 >= 0) isValid = dp[j - 1];
                } else { // It's a '*'
                    isValid = dp[j]; // as empty
                    if (j + 1 <= n) isValid = isValid || dp[j + 1]; // as '('
                    if (j - 1 >= 0) isValid = isValid || dp[j - 1]; // as ')'
                }
                currentRow[j] = isValid;
            }
            // The current row becomes the previous row for the next iteration
            dp = currentRow;
        }

        return dp[0];
    }

    // TC;O(N), SC:O(1)
    public boolean checkValidStringOptimized(String s) {
        int leftMin = 0, leftMax = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftMin++;
                leftMax++;
            } else if (c == ')') {
                leftMin--;
                leftMax--;

            } else {
                leftMin--;
                leftMax++;
            }

            if (leftMin < 0) leftMin = 0;
            if (leftMax < 0) return false; // str = (, min = -1, max = -1
        }


        return leftMin == 0;
    }

    public static void main(String[] args) {
        String str = "(*))";
        ValidParenthesis obj = new ValidParenthesis();
        boolean checkValidString = obj.checkValidStringBrute(str);
        System.out.println("string is valid ? ----> " + checkValidString);
        System.out.println("-------------------------------------");
        checkValidString = obj.checkValidStringMemoization(str);
        System.out.println("string is valid ? ----> " + checkValidString);
        System.out.println("-------------------------------------");
        checkValidString = obj.checkValidStringOptimized(str);
        System.out.println("string is valid ? ----> " + checkValidString);
    }
}
