package Backtracking;


/* Java program to solve N Queen   
Problem using backtracking */
  
class AllSolNQueen  
{ 
  
static int N = 8;  
static int k = 1; 
  
// In ket qua
static void printSolution(int board[][])  
{  
    System.out.printf("%d-\n", k++);  
    for (int i = 0; i < N; i++)  
    {  
        for (int j = 0; j < N; j++)  
            System.out.printf(" %d ", board[i][j]);  
        System.out.printf("\n");  
    }  
    System.out.printf("\n");  
}  
  
// Kiem tra xem co the dat 1 quan hau len vi tri board[row][col] khong. 
// Luu y rang ham nay duoc goi khi "col" quan hau da duoc dat trong cac cot
// tu 0 -> col-1. Vi vay chung ta chi can kiem tra phia trai de tim cac quan hau 
// co the tan cong 
static boolean isSafe(int board[][], int row, int col)  
{  
    int i, j;  
  
    /* Kiem tra hang nay phia ben trai */
    for (i = 0; i < col; i++)  
        if (board[row][i] == 1)  
            return false;  
  
    /* Kiem tra duong cheo phia tren ben phai */
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)  
        if (board[i][j] == 1)  
            return false;  
  
    /* Kiem tra duong cheo phia duoi ben trai*/
    for (i = row, j = col; j >= 0 && i < N; i++, j--)  
        if (board[i][j] == 1)  
            return false;  
  
    return true;  
}  
  
/* Ham de quy de giai quyet bai toan N quan hau*/
static boolean solveNQUtil(int board[][], int col)  
{  
    /* Truong hop co ban: Neu dat tat ca quan hau thi tra ve true */
    if (col == N)  
    {  
        printSolution(board);  
        return true;  
    }  
  
    /* Xem xet cot nay va thu dat quan hau vao tat ca cac hang cot 1 cach tuan tu */
    boolean res = false;  
    for (int i = 0; i < N; i++)  
    {  
        /* Kiem tra xem co the dat quan hau vao vi tri board[i][col] khong  ?*/
        if ( isSafe(board, i, col) )  
        {  
            /* Dat quan hau vao vi tri board[i][col] */
            board[i][col] = 1;  
  
            res = solveNQUtil(board, col+1) || res; 
        
       // Tinh menh de res o day xem co loi giai khong de quay lui ..... 
         
            /* Neu viec dat quan hau vao vi tri board[i][col] 
             * khong dan den 1 giai phap chinh xac thi loai bo quan hau tu vi tri board[i][col] */
            board[i][col] = 0; //=> Quay lui (BACKTRACK)  
        }  
    }  
  
    /* Neu khong the dat quan hau vao bat ki hang nao trong cot nay col
     * => tra ve failse */
    return res;  
}  
  
/* Ham nay giai quyet bai toan N quan hau bang thuat toan Backtracking. 
 * No chu yeu su dung ham solveNQUtil() de giai quyet bai toan. No tra ve false 
 * neu khong the dat quan hau, nguoc lai tra ve true va in ra cach dat quan hau duoi dang so 1 */
static void solveNQ()  
{  
    int board[][] = new int[N][N];  
  
    if (solveNQUtil(board, 0) == false)  
    {  
        System.out.printf("Khong ton tai giai phap nao !");  
        return ;  
    }  
  
    return ;  
}  
  
// Driver code  
public static void main(String[] args) 
{ 
    solveNQ(); 
    System.out.println("Do Xuan Trang");
} 
} 
  
// This code has been contributed 
// by 29AjayKumar 




