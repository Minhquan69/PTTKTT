import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
 
// Data structure to store a ItemJob
class item_job implements Comparable<item_job>{
    int start, finish, profit;
    String name;
    public int compareTo (item_job y){
        return Double.compare(this.finish, y.finish);
    }

    item_job(int start, int finish, int profit, String name) {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
        this.name = name;
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
    public void setName(String name) { 
        this.name = name;
    }

    public String getName() { 
        return this.name;
    }
    public String toString(){
        return name + " " + start + " " + finish + " " + profit;
    }
    
}
