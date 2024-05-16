
 

import java.util.Arrays;
import java.util.Comparator;


public class Student implements Comparable<Student> {
    private final String  ten;     
    private final String    masv;     
    private final int  tuoi;   


 
    public Student(String ten, String masv, int tuoi) {
        this.ten = ten;
        this.masv = masv;
        this.tuoi = tuoi;
    }
    public Student(String transaction) {
        String[] a = transaction.split("\\s+");
        ten    = a[0];
        masv   = new String(a[1]);
        tuoi = Integer.parseInt(a[2]);
   
    }


    public String ten() {
        return ten;
    }

    public String masv() {
        return masv;
    }

    public double tuoi() {
        return tuoi;
    }
    @Override
    public String toString() {
        return String.format("%-10s %10s %d", ten, masv, tuoi);
    }

    public int compareTo(Student that) {
        return Integer.compare(this.tuoi, that.tuoi);
    }    

    /**
     * Compares this transaction to the specified object.
     *
     * @param  other the other transaction
     * @return true if this transaction is equal to {@code other}; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Student that = (Student) other;
        return (this.ten == that.ten) && (this.masv.equals(that.masv))
                                            && (this.masv.equals(that.tuoi));
    }


    /**
     * Returns a hash code for this transaction.
     *
     * @return a hash code for this transaction
     */
    public int hashCode() {
        int hash = 1;
        hash = 31*hash + ten.hashCode();
        hash = 31*hash + masv.hashCode();
        hash = 31*hash + ((Integer) tuoi).hashCode();
        return hash;
        // return Objects.hash(who, when, amount);
    }
    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[2] = new Transaction("Knuth    6/14/1999  288.34");
        a[3] = new Transaction("Dijkstra 8/22/2007 2678.40");

        StdOut.println("Danh sach sinh vien ban dau");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
        
        StdOut.println("Sort by CompareTo");
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
        
                StdOut.println("Sort by date");
        Arrays.sort(a, new Transaction.WhenOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Transaction.WhoOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Transaction.HowMuchOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
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