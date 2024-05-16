import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

// Data structure to store a ItemJob
class ItemJob implements Comparable<ItemJob>{
    int start, finish, profit;
    
    public int compareTo (ItemJob y){
        return Double.compare(this.finish, y.finish);
    }

    ItemJob(int start, int finish, int profit) {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
    }
    public void setStart(int start){
        this.start = start;
    }
    
    public int start(){
        return this.start;
    }
    
    public void setFinish(int start){
        this.finish = finish;
    }
    
    public int finish(){
        return this.finish;
    }
    public void setProfit(int profit){
        this.profit = profit;
    }
    
    public int profit(){
        return this.profit;
    }
    
    public String toString(){
        return start + " " + finish + " " + profit;
    }
    
}
