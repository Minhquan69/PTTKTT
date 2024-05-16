package Backtracking;

import edu.princeton.cs.algs4.StdOut;

class Solution {
    private static int N = 8;
    private int board[][];
    private int k;
    private int dem=0;
    
    // Ham tao khong doi
	public Solution() {
    	board = new int[N][N];
		k=1;
	}
	// Bat dau giai quyet bai toan tu cot 0
	public void solveNQ() {
		solveNQUtil(0);
	}
    
	public void solveNQUtil(int col) {
		// neu dat duoc tat ca quan hau thi in ra 
		if (col == N) {
            dem++; 
            if (dem <= 5) {
                printSolution();
            }
            return; 
        }
		// xem xet cot nay va thu dat quan hau theo 1 cach tuan tu
		for(int i=0;i<N;i++) {
			if(isSafe(i, col)) {
				/* Dat quan hau vao vi tri board[i][col] */
				board[i][col]=1;
				// De quy de dat tiep cac quan hau cot tiep theo
				solveNQUtil(col+1);
				// Neu khong an toan thi tra ve 0
				board[i][col]=0;
			}
		}
	}
	
	private void printSolution() {
		System.out.println(k+"- Do Xuan Trang");
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				StdOut.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		k++;
	}

	public boolean isSafe(int row, int col) {
		// Kiem tra hang nay phia ben trai 
		for(int i =0;i<col;i++) {
			if(board[row][i]==1) {
				return false;
			}
		}
		// Kiem tra duong cheo phia tren ben phai
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }
		// kiem tra duong cheo phia duoi ben trai
		for (int i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }
		return true;
	}
    
}
