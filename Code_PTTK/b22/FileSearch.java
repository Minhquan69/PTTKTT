/******************************************************************************
 *  Compilation:  javac FileSearch.java
 *  Execution:    java FileSearch file1.txt file2.txt file3.txt ...
 *  Dependencies: ST.java SET.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/ex1.txt
 *                https://algs4.cs.princeton.edu/35applications/ex2.txt
 *                https://algs4.cs.princeton.edu/35applications/ex3.txt
 *                https://algs4.cs.princeton.edu/35applications/ex4.txt
 *
 *  % java FileSearch ex*.txt
 *  age
 *   ex3.txt
 *   ex4.txt 
 * best
 *   ex1.txt 
 * was
 *   ex1.txt
 *   ex2.txt
 *   ex3.txt
 *   ex4.txt 
 *
 *  % java FileSearch *.txt
 *
 *  % java FileSearch *.java
 *
 ******************************************************************************/

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  The {@code FileSearch} class provides a client for indexing a set of files,
 *  specified as command-line arguments. It takes queries from standard input
 *  and prints each file that contains the given query.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class FileSearch { 

    private FileSearch() { }

    public static void main(String[] args) {
        StdOut.println("Truong Tuan Anh chay chuong trinh:");
        HashMap<String, HashMap<File, Integer>> wordCounts = new HashMap<>();       // luu ten file voi so lan xuat hien voi tu tuong ung
        StdOut.println("Indexing files");
        
        for (String filename : args) {      // duyet qua tat ca cac file
            StdOut.println("  " + filename);
            File file = new File(filename);
            In in = new In(file);
            HashMap<String, Integer> counts = new HashMap<>();      // luu ten file va so lan xuat hien cua tu trong file
            while (!in.isEmpty()) {         // lap den khi file rong
                String word = in.readString();          // doc tung tu trong file
                if (counts.containsKey(word)) {         // neu hashmap da chua word
                    counts.put(word, counts.get(word) + 1);         // tang word len 1 roi cap nhap vao hashmap
                } else {
                    counts.put(word, 1);            // them tu vao hashmap voi so lan xuat hien = 1
                }
            }
            for (String word : counts.keySet()) {           // duyet qua cac tu trong counts
                int count = counts.get(word);           // so lan xuat hien cua tu
                if (!wordCounts.containsKey(word)) {            //neu wordCounts khong chua tu word do
                    wordCounts.put(word, new HashMap<File, Integer>());         // khoi tao hashmap de luu ten file va so lan xuat hien cua tu do
                }
                wordCounts.get(word).put(file, count);          // luu ten file va so lan xuat hien voi tu tuong ung
            }
        }

        // read queries from standard input, one per line
        // while (!StdIn.isEmpty()) {
        // String query = "age";
           
        String word = "it";
        StdOut.println(word);
        HashMap<File, Integer> fileCounts = wordCounts.get(word);
        for (File file : fileCounts.keySet()) {
            int count = fileCounts.get(file);
            System.out.println("- " + file.getName() + ": " + count + " times.");
        }
            
        String word1 = "age";
        StdOut.println(word1);
        HashMap<File, Integer> fileCounts1 = wordCounts.get(word1);
        for (File file : fileCounts1.keySet()) {
            int count = fileCounts1.get(file);
            System.out.println("- " + file.getName() + ": " + count + " times.");
        }
            
        String word2 = "times";
        StdOut.println(word2);
        HashMap<File, Integer> fileCounts2 = wordCounts.get(word2);
        for (File file : fileCounts2.keySet()) {
            int count = fileCounts2.get(file);
            System.out.println("- " + file.getName() + ": " + count + " times.");
        }

        int totalWordCount = 0;     // khai bao totalWordCount = 0
        ArrayList<File> files = new ArrayList<>();      // khoi tao mot mang files
        String[] words = {"age", "it", "times"};        // cac tu can tim
        
        for (File file : wordCounts.get(words[0]).keySet()) {       // duyet qua cac file chua tu dau tien
            boolean containsAll = true;     // khoi tao gia tri ban dau = true, dai dien cho viec tep hien tai chua tat ca cac tu trong words
            
            for (String wordf : words) {        // voi moi tu trong word
                HashMap<File, Integer> fileCount = wordCounts.get(wordf);       // day ten file va so lan xuat hien vao fileCount voi tu tuong ung
                if (fileCount == null || !fileCount.containsKey(file)) {        // neu fileCount rong hoac file dang xet khong thuoc fileCount
                    containsAll = false;        // gan false
                    break;      // thoat khoi vong lap
                } 
            }
            
            if (containsAll) {      // neu file chua tat ca cac tu can tim
                files.add(file);       // them file vao files
            }
        }
        
        Collections.sort(files, (file1, file2) ->   {       // sap xep file
            int count1 = 0;
            int count2 = 0;
        
            for (String find : words) {
                HashMap<File, Integer> fileCount1 = wordCounts.get(find);
                count1 += fileCount1.getOrDefault(file1, 0);
                count2 += fileCount1.getOrDefault(file2, 0);
            }
            return Integer.compare(count2, count1);
        });
        
        // xuat cac file chua tat ca cac tu ra man hinh
        System.out.println("ARRANGE FILE");
        
        for (File file : files) {
            System.out.println("- " + file.getName());
        }

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

