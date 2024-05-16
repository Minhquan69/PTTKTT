import java.util.Collections;
import java.util.List;
import java.util.Comparator;
/**
 * Write a description of class JobImp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JobImp 
{
    // instance variables - replace the example below with your own
    private boolean task[];
    private int maxProfit;
    private List<ItemJob> jobs;
    private Bag<ItemJob> bag;
    
    public JobImp(List<ItemJob> jobs){
        bag = new Bag<ItemJob>();
        this.jobs = jobs;
        // sort jobs in increasing order of their finish times
        //Collections.sort(jobs, (x, y) -> x.finish - y.finish);
        Collections.sort(jobs);
        // get number of jobs
        int n = jobs.size();
    
        // construct an lookup table where the i'th index stores the maximum profit
        // for first i jobs
        int[] maxProfit = new int[n];
        task = new boolean[n];
        // maximum profit gained by including the first job
        maxProfit[0] = jobs.get(0).profit;
        task[0] = true;
        // fill maxProfit[] table in bottom-up manner from the second index
        for (int i = 1; i < n; i++)
        {
            // find the index of last non-conflicting job with current job

            // Tim index la cong viec sau cung khong xung dot voi cong viec hien thoi i .....  
            int index = findLastNonConflictingJob(jobs, i);
            // include the current job with its non-conflicting jobs
            int incl = jobs.get(i).profit;
            if (index != -1){
                incl += maxProfit[index];
            }

            // store the maximum profit by including or excluding current job
           // maxProfit[i] = Math.max(incl, maxProfit[i - 1]);
            
             if (incl > maxProfit[i - 1]) {
                maxProfit[i] = incl;
                task[i] = true;
            } else {
                maxProfit[i] = maxProfit[i - 1];
                task[i] = false;
                }
        }

    // add selected jobs to the bag and mark them as selected in the task arra
        
        int i = jobs.size()-1;
         while( i >= 0)   {
            if (task[i]) {
                bag.add(jobs.get(i));
                i = findLastNonConflictingJob(jobs,i);
            }else
                i--;
        }  
        this.maxProfit = maxProfit[n-1];
    }
    
    
    private int findLastNonConflictingJob(List<ItemJob> jobs, int n)
    {
        // search space
        int low = 0;
        int high = n;

        // iterate till search space is not exhausted
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (jobs.get(mid).finish <= jobs.get(n).start) {
                if (jobs.get(mid + 1).finish <= jobs.get(n).start) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            }
            else {
                high = mid - 1;
            }
        }

        // return negative index if no non-conflicting job is found
        return -1;
    }
    
    public int maxProfit(){
        return maxProfit;
    }
    
    public Bag<ItemJob> bag(){
        return bag;
    }
    
}
