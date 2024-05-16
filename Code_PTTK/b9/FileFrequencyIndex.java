import java.util.HashMap;
import java.io.File;

//run : "ex1.txt","ex2.txt","ex3.txt"

public class FileFrequencyIndex { 

    
    public FileFrequencyIndex() { }
    
    public static void main(String[] args) { 
        // key = word, value = file + so lan xuat hien
        StdOut.println("Dao Minh Quan");
        ST<String, ST<File, Integer>> index = new ST<>();
        
        // create inverted index of all files
        StdOut.println("Indexing files");
        for (String filename : args) {
            StdOut.println("  " + filename);
            File file = new File(filename);
            In in = new In(file);
            
            while (!in.isEmpty()) {
                String word = in.readString();
                if(!index.contains(word)){
                    index.put(word, new ST<>()); // them word vao map
                }
                ST<File, Integer> temp = index.get(word); // lay ra ST chua nhung file chua word
                Integer count = temp.get(file);// lay ra value(so lan trong file) 
                if (count == null) {
                    temp.put(file, 1);
                } else {
                    temp.put(file, count + 1);
                }
            }
        }
        
        
        for (String word : index.keys()) {
            StdOut.println(word);
            ST<File, Integer> fileCount = index.get(word);
            for (File file : fileCount.keys()) {
                Integer count = fileCount.get(file);
                StdOut.printf("\t%s - %d lan\n", file.getName(), count);
            }
        }
        
    }
}
