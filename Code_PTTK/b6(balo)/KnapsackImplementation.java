
import java.util.*;

/**
 * Write a description of class KnapsackImplementation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KnapsackImplementation
{
    private int maxWeight; // max weight of the bag
    private int realWeight; // real weight
    private Bag<Item> items; // Bag of items
    private int totalValue; // total value 

    /**
     * Constructor for objects of class KnapsackImplementation
     */
    public KnapsackImplementation(Item[] items,int maxWeight,int N)
    {
        this.items = new Bag<Item>();
        this.maxWeight = maxWeight;
        int[][] opt = new int[N+1][maxWeight+1];
        boolean[][] sol = new boolean[N+1][maxWeight+1];
        for(int i = 1; i <= N; i++){
            for(int w = 1; w <= maxWeight; w++){
                
                int option1 = opt[i-1][w];

                // take item i
                int option2 = Integer.MIN_VALUE;
                if (items[i].getWeight() <= w) option2 = items[i].getValue() + opt[i-1][w-items[i].getWeight()];

                // select better of two options
                opt[i][w] = Math.max(option1, option2);
                sol[i][w] = (option2 > option1);
            }
        }
        this.totalValue = opt[N][maxWeight];
        boolean[] take = new boolean[N+1];
        for (int n = N, w = maxWeight; n > 0; n--) {
            if (sol[n][w]) {
                take[n] = true;
                w = w - items[n].getWeight();
            }
            else{
                take[n] = false;
            }
        }
        for(int i = 1; i <= N; i++){
            if(take[i]){
                this.items.add(items[i]);
                this.realWeight += items[i].getWeight();
            }
        }
    }
    public int getRealWeight(){
        return this.realWeight;
    }

    public int getTotalValue(){
        return this.totalValue;
    }
    public Bag<Item> getItems(){
        return this.items;
    }
    public int getMaxWeight(){
        return this.maxWeight;
    }
}
