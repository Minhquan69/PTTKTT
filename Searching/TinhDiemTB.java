import java.util.*;
import java.io.*;
//"toan.csv","hoa.csv","ly.csv"
/**
 * Write a description of class Bangdiem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TinhDiemTB
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream(new File("sinhvien.csv")));
        ST<String, Student> st = new ST<String, Student>();
        ST<String, Double> bangdiem = new ST<String, Double>();
        
        for(int i = 0; !StdIn.isEmpty(); i++){
            String line = StdIn.readLine();
            String[] tokens = line.split(",");
            String masv = tokens[0];
            Student sinhvien = new Student(tokens);
            st.put(masv,sinhvien);
        }
        
        for(int i = 0; i< args.length; i++){
            In in = new In(args[i]);
            while(!in.isEmpty()){
                String line = in.readLine();
                String[] mon = line.split(",");
        //        st.get(mon[0]).bangdiem().put(args[i], Double.parseDouble(mon[1]));
                //StdOut.println(st.get(mon[0]).bangdiem().get(args[i]));
               
            }
        }
        
        
        for(String s : st.keys()){
            StdOut.println(st.get(s).getHoDem() +  " " + st.get(s).getTen() + " masv = " + s);
            StdOut.println(st.get(s).Tinh_diemtb());
            //StdOut.println(st.get(s).bangdiem().get("toan.csv"));
            //StdOut.println(st.get(s).bangdiem().get("ly.csv"));
            //StdOut.println(st.get(s).bangdiem().get("hoa.csv"));
        }
    }
}
