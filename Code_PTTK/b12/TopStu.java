import java.io.*;

public class TopStu
{
    private TopStu() { }

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream(new File("student.txt")));
        //int m = Integer.parseInt(args[0]); 
        int m =5;
        MinPQ<student> pq = new MinPQ<student>(m+1,new student.dateoder());
        MinPQ<student> pq1 = new MinPQ<student>(m+1);
        MinPQ<student> pqname = new MinPQ<student>(m+1,new student.nameoder());
        MinPQ<student> pqho = new MinPQ<student>(m+1,new student.hooder());
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            student student = new student(line);
            pq.insert(student); 
            pq1.insert(student); 
            pqname.insert(student);
            pqho.insert(student);
            if (pq.size() > m) 
                pq.delMin();
            if(pq1.size()>m)
                pq1.delMin();
            if(pqname.size()>m)
                pqname.delMin();
            if(pqho.size()>m)
                pqho.delMin();
        }   

        // print entries on PQ in reverse order
        StdOut.println("top age");
        Stack<student> stack = new Stack<student>();
        for (student student : pq)
            stack.push(student);
        for (student student : stack)
            StdOut.println(student);
            
        StdOut.println("top tbc");
        Stack<student> stack1 = new Stack<student>();
        for (student student : pq1)
            stack1.push(student);
        for (student student : stack1)
            StdOut.println(student);
        
        StdOut.println("top name");
        Stack<student> stackname = new Stack<student>();
        for (student student : pqname)
            stackname.push(student);
        for (student student : stackname)
            StdOut.println(student);  
        
        StdOut.println("top ho");
        Stack<student> stackho = new Stack<student>();
        for (student student : pqho)
            stackho.push(student);
        for (student student : stackho)
            StdOut.println(student);  
    } 
}
