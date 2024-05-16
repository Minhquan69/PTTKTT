import java.io.*;

public class DanhSachSvCSV {
    public static void main(String[] arg) throws IOException {
        System.setIn(new FileInputStream(new File("sinhvien.csv")));
        ST<String, Student> st = new ST<String,Student>();
        ST<String, Queue<Student>> ts = new ST<String,Queue<Student>>();
        for(int i=0; !StdIn.isEmpty();i++){
            String line = StdIn.readLine();
            String[] tokens = line.split(",");
            String keyMaSV = tokens[0];
            String keyQueQuan = tokens[5];
            Student sinhvien = new Student(tokens);
            st.put(keyMaSV,sinhvien);
            if(ts.get(keyQueQuan) == null){
                Queue<Student> sv = new Queue<Student>();
                sv.enqueue(sinhvien);
                ts.put(keyQueQuan, sv);
            }
            else {
                ts.get(keyQueQuan).enqueue(sinhvien);
            }
        }
        String query = "Thai Binh";
        
        for(String s: st.keys()){
            if(s.equals(query)){
                System.out.println(st.get(s).toString());
            }
        }
        
        for(String s: ts.keys()){
            if(s.equals(query)){
                while(!ts.get(s).isEmpty()){
                   StdOut.println(ts.get(s).peek().toString()); 
                    ts.get(s).dequeue();
                }
                
            }
        }
    }
}


