
/**
 * Store the n'th solution of N queen problems
 *
 * @Nguyen Van Giap (your name)
 * @version (a version number or a date)
 */
public class SolutionN
{
   private int[][] board; // current board of the solution
   
   public SolutionN(int[][] board){
       int N = board.length;
       this.board = new int[N][N];
       for(int i = 0; i < N; i++){
           for(int j = 0; j < N; j++){
               this.board[i][j] = board[i][j];
           }
       }
   }
   
   public int[][] getBoard(){
       return this.board;
   }
   
   public void setBoard(int[][] board){
       this.board = board;
   }
   
   public void printBoard(){
       for (int i = 0; i < board.length; i++)  
       {  
            for (int j = 0; j < board.length; j++)  
                System.out.printf(" %d ", board[i][j]);  
            System.out.printf("\n");  
       }  
       System.out.printf("\n");
   }
}