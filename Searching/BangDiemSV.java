import java.io.*;
import java.util.Map;
import java.util.HashMap;
// "toan.csv","ly.csv","hoa.csv"
public class BangDiemSV
{
    public static void main(String[] args) throws IOException{
        System.out.println("Do Xuan Trang");
        System.setIn(new FileInputStream(new File("sinhvien.csv")));
        ST<String, Student> st = new ST<String, Student>();
        
        for(int i = 0; !StdIn.isEmpty(); i++){
            String line = StdIn.readLine();
            String[] tokens = line.split(",");
            String masv = tokens[0];
            Student sinhvien = new Student(tokens);
            st.put(masv,sinhvien);
        }
        
        for(int i = 0; i< args.length; i++){
            In in = new In(args[i]);
            String[] tenMon = args[i].split(".csv");
            System.out.println(args[i]);
            String line = in.readLine();
            String[] line_SoTin = line.split(",");
            Mon monHoc = new Mon(tenMon[0], Integer.parseInt(line_SoTin[1]));
            while(!in.isEmpty()) {
                line = in.readLine();
                String[] maVaDiem = line.split(",");
                st.get(maVaDiem[0]).getBangdiem().put(monHoc, Double.parseDouble(maVaDiem[1]));
            }
        }
        
        for(String key : st.keys()){
            Student student = st.get(key);
            StdOut.println(student.toString2());
            StdOut.println("Bang diem: ");
            HashMap<Mon, Double> bd = student.getBangdiem();
            for(Mon mon : bd.keySet()){
                StdOut.println(mon.getTen() + ":   " + bd.get(mon) + " | So tin: "+mon.getSoTinChi());
            }
            StdOut.println("=> Diem trung binh: " + st.get(key).Tinh_diemtb());
            StdOut.println();
        }
            
    }  
}

