package Backtracking;

public class Main
{
   public static void main(String[] args) {
        Bag<Solution> s = new Bag<>();
        Solution solution = new Solution();
        s.add(solution);
        for (Solution sol : s) {
            sol.solveNQ();
        }
    }
}

