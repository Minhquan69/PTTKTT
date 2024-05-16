import java.util.*;

/**
 * Write a description of class KnapsackApplication here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KnapsackApplication
{
    // instance variables - replace the example below with your own
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main(String[] args){
        int N = Integer.parseInt("6");   // number of items
        int W = Integer.parseInt("2000");
        Item[] items = new Item[N+1];
        
        // Initialize items
        for (int n = 1; n <= N; n++) {
            int profit = StdRandom.uniform(1000);
            int weight = 1+StdRandom.uniform(W);
            items[n] = new Item("item" + n,weight,profit);
        }
        
        // Print list items
        StdOut.println("List items: ");
        for(int i = 1; i <= N; i++){
            StdOut.println("Name: " + items[i].getName() + " weight: " + items[i].getWeight() + " Value: " + items[i].getValue());
        }
        
        // Using knapsack algorithms
        KnapsackImplementation api = new KnapsackImplementation(items,W,N);
        
        // get result
        StdOut.println("total value: " + api.getTotalValue());
        Bag<Item> a = api.getItems();
        for(Item item:a){
            StdOut.println("Name: " + item.getName() + " weight: " + item.getWeight() + " Value: " + item.getValue());
        }
        StdOut.println("real weight: " + api.getRealWeight());
        StdOut.println("max weight: " + api.getMaxWeight());
    }
}