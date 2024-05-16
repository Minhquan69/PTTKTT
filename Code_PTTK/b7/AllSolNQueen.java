import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class AllSolNQueen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AllSolNQueen
{
    private int N; //Number of queen
    private List<SolutionN> solutions;
    /**
     * Constructor for objects of class AllSolNQueen
     */
    public AllSolNQueen(int N)
    {
        this.N = N;
        this.solutions = new ArrayList<>();
        SolveNQ();
    }

    private void SolveNQ(){
        int[][] board = new int[N][N];
        if(solveNQUtil(board,0) == false){
            System.out.println("Complete!");
            return;
        }
        return;
    }

    private boolean isSafe(int board[][], int row, int col){
        int i,j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)  
            if (board[row][i] == 1)  
                return false;  

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)  
            if (board[i][j] == 1)  
                return false;  

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)  
            if (board[i][j] == 1)  
                return false;  

        return true;
    }

    private boolean solveNQUtil(int board[][], int col){
        /* base case: If all queens are placed  
        then return true */
        if (col == N)  
        {
            this.solutions.add(new SolutionN(board));
            return true;
        }  

        /* Consider this column and try placing  
        this queen in all rows one by one */
        boolean res = false;  
        for (int i = 0; i < N; i++)  
        {  
            /* Check if queen can be placed on  
            board[i][col] */
            if ( isSafe(board, i, col) )  
            {  
                /* Place this queen in board[i][col] */
                board[i][col] = 1;  

                // Make result true if any placement  
                // is possible  
                res = solveNQUtil(board,col+1); 
                /* If placing queen in board[i][col]  
                doesn't lead to a solution, then  
                remove queen from board[i][col] */
                board[i][col] = 0; // BACKTRACK  
            }  
        }  

        /* If queen can not be place in any row in  
        this column col then return false */
        return res;  
    }

    /**
     * Print the n'th solution
     *
     * @param   n
     * @return  void
     */
    public void printNSolution(int n)
    {
        solutions.get(n).printBoard();
    }
    
    public void printAllSolutions(){
        for(SolutionN sol : solutions){
            sol.printBoard();
        }
    }

    /**
     * Total of solutions
     *
     * @param
     * @return    the total of solutions
     */
    public int getResults()
    {
        return this.solutions.size();
    }

    public void printSolution(int board[][])  
    {  
        for (int i = 0; i < N; i++)  
        {  
            for (int j = 0; j < N; j++)  
                System.out.printf(" %d ", board[i][j]);  
            System.out.printf("\n");  
        }  
    System.out.printf("\n");  
    } 
}