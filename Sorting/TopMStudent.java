import java.io.*;
/******************************************************************************
 *  Compilation:  javac TopMStudent.java
 *  Execution:    java TopMStudent m < input.txt
 *  Dependencies: MinPQ.java Transaction.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tinyBatch.txt
 * 
 *  Given an integer m from the command line and an input stream where
 *  each line contains a String and a long value, this MinPQ client
 *  prints the m lines whose numbers are the highest.
 * 
 *  % java TopMStudent 5 < tinyBatch.txt 
 *  Thompson    2/27/2000  4747.08
 *  vonNeumann  2/12/1994  4732.35
 *  vonNeumann  1/11/1999  4409.74
 *  Hoare       8/18/1992  4381.21
 *  vonNeumann  3/26/2002  4121.85
 *
 ******************************************************************************/

 

/**
 *  The {@code TopMStudent} class provides a client that reads a sequence of
 *  transactions from standard input and prints the <em>m</em> largest ones
 *  to standard output. This implementation uses a {@link MinPQ} of size
 *  at most <em>m</em> + 1 to identify the <em>M</em> largest transactions
 *  and a {@link Stack} to output them in the proper order.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class TopMStudent {   

    // This class should not be instantiated.
    
    private TopMStudent() { }

    /**
     *  Reads a sequence of transactions from standard input; takes a
     *  command-line integer m; prints to standard output the m largest
     *  transactions in descending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException{
        System.out.println("Do Xuan Trang");
        System.setIn(new FileInputStream(new File("SortSinhVien.TXT")));
        int m = Integer.parseInt(args[0]); 
        MinPQ<SinhVien> pq = new MinPQ<SinhVien>(m+1);

        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ. 
            String line = StdIn.readLine();
            SinhVien sv = new SinhVien(line);
            pq.insert(sv); 

            // remove minimum if m+1 entries on the PQ
            if (pq.size() > m) 
                pq.delMin();
        }   // top m entries are on the PQ

        // print entries on PQ in reverse order
        Stack<SinhVien> stack = new Stack<SinhVien>();
        for (SinhVien sv : pq)
            stack.push(sv);
        for (SinhVien sv : stack)
            StdOut.println(sv);
    } 
} 


/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
