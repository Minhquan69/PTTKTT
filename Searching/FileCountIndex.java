import java.io.File;
import java.util.HashMap;

public class FileCountIndex { 

    // Do not instantiate.
    private FileCountIndex() { 
    }

    public static void main(String[] args) {  
        System.out.println("Do Xuan Trang");
        HashMap<String, HashMap<File, Integer>> hm = new HashMap<String, HashMap<File, Integer>>();

        // create inverted index of all files
        StdOut.println("Indexing files");
        for (String filename : args) {
            StdOut.println("  " + filename);
            File file = new File(filename);
            In in = new In(file);
            
            while (!in.isEmpty()) {
                String word = in.readString();
                if (!hm.containsKey(word)) 
                    hm.put(word, new HashMap<File, Integer>());
                    
                HashMap<File, Integer> hm1 = hm.get(word);
                if (!hm1.containsKey(file))
                    hm1.put(file, 1);
                else
                    hm1.put(file, hm1.get(file) + 1);
            }
        }

            String query = "age";
            StdOut.println(query);
        if (hm.containsKey(query)) {
            HashMap<File, Integer> hm1  = hm.get(query);
            for (File file : hm1.keySet()) {
                StdOut.println("  " + file.getName() + "     " + hm1.get(file));      
            }
        }
    }
}
