package Backtracking;

class test {
    private static int N = 8;
    private int[][] board;
    private int k;

    public test() {
        board = new int[N][N];
        k = 1;
    }

    public void solveNQ() {
        solveNQUtil(0);
    }

    public void solveNQUtil(int col) {
        if (col == N) {
            printSolution();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1;
                solveNQUtil(col + 1);
                board[i][col] = 0;
            }
        }
    }

    public boolean isSafe(int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        for (int i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    public void printSolution() {
        System.out.println(k + "- Do Xuan Trang");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        k++;
    }
}
