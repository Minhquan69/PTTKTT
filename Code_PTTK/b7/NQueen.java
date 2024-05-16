
/**
 * Write a description of class NQueen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NQueen
{
    public static void main(String args[]){
        int N = Integer.parseInt("8");
        AllSolNQueen nQueen = new AllSolNQueen(N);
        System.out.println("Total of result: " + nQueen.getResults());
        
        nQueen.printAllSolutions();
    }
}