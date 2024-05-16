import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class JobApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JobApp
{
    public static void main(String[] args)
    {
        List<ItemJob> jobs = Arrays.asList(     new ItemJob( 0, 6, 60 ),
                                            new ItemJob( 1, 4, 30 ),
                                            new ItemJob( 3, 5, 10 ),
                                            new ItemJob( 5, 7, 30 ),
                                            new ItemJob( 5, 9, 50 ),
                                            new ItemJob( 7, 8, 10 ));


        JobImp job = new JobImp(jobs);
        StdOut.println("The maximum profit is " + job.maxProfit());
        
        for(ItemJob s : job.bag())
            StdOut.println(s);
    }
}
