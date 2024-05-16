 

import java.util.*;

public class student implements Comparable<student> {
    private final String id;
    private final String ho;
    private final String name;
    private final Date when;
    private final double tbc;

    public student(String id, String ho, String name, Date when, double tbc) {
        if (Double.isNaN(tbc) || Double.isInfinite(tbc))
            throw new IllegalArgumentException("");
        this.id = id;
        this.ho = ho;
        this.name = name; 
        this.when = when;
        this.tbc = tbc;
    }

    public student(String student){
        String []arr=student.split("\\s+");
        this.id=arr[0];
        this.ho=arr[1];
        this.name=arr[2];
        this.when=new Date(arr[3]);
        this.tbc=Double.parseDouble(arr[4]);
        if (Double.isNaN(tbc) || Double.isInfinite(tbc)) throw new IllegalArgumentException("nguyen anh");
    }
    @Override
    public String toString(){
        return String.format("%-10s %10s %10s %10s %8.2f",id,ho,name,when,tbc);
    }

    public int compareTo(student that){
        return Double.compare(this.tbc, that.tbc);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        student that = (student) other;
        return (this.tbc == that.tbc) && (this.id.equals(that.id))&& (this.when.equals(that.when))&&(this.name.equals(that.name));
    }

    public int hascode(){
        int hash=1;
        hash=13*hash+id.hashCode();
        hash=13*hash+name.hashCode();
        hash=13*hash+ ((Double)tbc).hashCode();
        return hash;
    }

    public static class idorder implements Comparator<student>{
        @Override
        public int compare(student a,student b){
            return a.id.compareTo(b.id);
        }
    }

    public static class hooder implements Comparator<student>{
        @Override
        public int compare(student a,student b){
            return b.ho.compareTo(a.ho);
        }
    }

    public static class nameoder implements Comparator<student>{
        @Override
        public int compare(student a,student b){
            return b.name.compareTo(a.name);
        }
    }

    public static class dateoder implements Comparator<student>{
        @Override
        public int compare(student a,student b){
            return a.when.compareTo(b.when);
        }
    }
    public static class tbcOrder implements Comparator<student> {
        @Override
        public int compare(student a,student b) {
            return Double.compare(a.tbc, b.tbc);
        }
    }

    public static void main(String[] args){
        student[] a=new student[4];
        a[0] = new student("123456   Do         Turing      6/17/1990   6.08");
        a[1] = new student("234567   Nam        Tarjan      3/26/2002   6.85");
        a[2] = new student("345678   Trung      Knuth       6/14/1999   8.34");
        a[3] = new student("456789   Quy        Dijkstra    8/22/2007   8.40");

        System.out.println("Unsorted");
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
        System.out.println();

        System.out.println("Sort by CompareTo");
        Arrays.sort(a);
        for(student arr:a)
            System.out.println(arr);
        System.out.println();

        System.out.println("Sort by id");
        Arrays.sort(a,new student.idorder());
        for(student arr:a)
            System.out.println(arr);
        System.out.println();

        System.out.println("Sort by name");
        Arrays.sort(a,new student.nameoder());
        for(student arr:a)
            System.out.println(arr);
        System.out.println();

        System.out.println("Sort by date");
        Arrays.sort(a, new student.dateoder());
        for(student arr:a)
            System.out.println(arr);
        System.out.println();

        System.out.println("Sort by tbc");
        Arrays.sort(a, new student.tbcOrder());
        for(student arr:a)
            System.out.println(arr);
        System.out.println();
    }


}
