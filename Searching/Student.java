import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a VietNameseDate)
 */
public class Student implements Comparable<Student>
{
    private String maSV;
    private String hoDem;
    private String ten;
    private VietnamDate ngaySinh;
    private double diemTB;
    private String queQuan;
    private String sDT;
    private HashMap<Mon, Double> bangdiem;
    
    public Student(){
        maSV = hoDem = ten =  " ";
        ngaySinh = new VietnamDate(26,02,2003);
        diemTB = 0.0;
        queQuan = sDT = " ";
    }
    
    public Student(String maSV, String hoDem, String ten, VietnamDate ngaySinh, double diemTB,
                    String queQuan, String sDT, HashMap<Mon, Double> bangdiem) {
        this.maSV = maSV;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diemTB = diemTB;
        this.queQuan = queQuan;
        this.sDT = sDT;
        this.bangdiem = bangdiem;
    }

    public Student(String student){
        String[] a = student.split("\\s{2,}");
        maSV = a[0];
        hoDem = a[1];
        ten = a[2];
        ngaySinh = new VietnamDate(a[3]);
        diemTB = Double.parseDouble(a[4]);
        queQuan = a[5];
        sDT = a[6];
        bangdiem = new HashMap<Mon, Double>();
    }
    
    public Student(String[] a){
        maSV = a[0];
        hoDem = a[1];
        ten = a[2];
        ngaySinh = new VietnamDate(a[3]);
        diemTB = Double.parseDouble(a[4]);
        queQuan = a[5];
        sDT = a[6];
        bangdiem = new HashMap<Mon, Double>();
    }
    public String getMaSV() {
        return maSV;
    }
    public String getHoDem() {
        return hoDem;
    }
    public String getTen() {
        return ten;
    }
    public VietnamDate getNgaySinh() {
        return ngaySinh;
    }
    public double getDiemTB() {
        return diemTB;
    }
    public String getQueQuan() {
        return queQuan;
    }
    public String getsDT() {
        return sDT;
    }
    public HashMap<Mon, Double> getBangdiem() {
        return bangdiem;
    }
    
    @Override
    public String toString() {
        return String.format("%-6s %-15s %-10s %-10s %-8.2f %-10s %-10s", maSV, hoDem, ten,ngaySinh, diemTB, queQuan, sDT);
    }
   
    public String toString2() {
        return String.format("%-6s %-15s %-10s %-10s %-10s %-10s", maSV, hoDem, ten,ngaySinh, queQuan, sDT);
    }
    
    public Double Tinh_diemtb (){
        int tongDiem = 0;
        int tongTin = 0;
        for(Mon key : bangdiem.keySet()){
            tongDiem += bangdiem.get(key)*key.getSoTinChi();
            tongTin += key.getSoTinChi();
        }
        return (double) (tongDiem/tongTin);
    }

    public static class tenOrder implements Comparator<Student> {

        @Override
        public int compare(Student v, Student w) {
            return v.ten.compareTo(w.ten);
        }
    }
    
    
    @Override
    public int compareTo(Student that) {
        return Double.compare(this.diemTB, that.diemTB);
    }   
    
    public static void main(String[] args) throws java.io.FileNotFoundException, IOException{
        System.out.println("Do Xuan Trang");
        //System.setIn(new FileInputStream(new File("SortSinhVien.txt")));
        //int n = Integer.parseInt(StdIn.readLine());
        //while(StdIn.hasNextLine()){
        //    String line = StdIn.readLine();
        //    a[i++] = new SinhVien(line);
        //}
        Student[] a = new Student[5];
        a[0] = new Student("1    Do Xuan         Trang    26/02/2003    9.8    Ha Noi        0934");
        a[1] = new Student("2    Nguyen Thanh    Dat      01/01/2004    10     Ha Noi        2222");
        a[2] = new Student("3    Ta Quoc         Viet     02/03/2006    9.2    Thai Binh     3333");
        a[3] = new Student("4    Nguyen Trung    Dung     04/04/2005    9.2    Bac Ninh      4444");
        a[4] = new Student("5    Bui Duc         Anh      07/09/2008    8.4    Anh Quoc      5555");
        StdOut.println("Danh sach sinh vien ban dau");
        for (int k = 0; k < a.length; k++)
            StdOut.println(a[k]);
        StdOut.println();
        StdOut.println("Danh sach sinh vien sap xep theo ten");
        Arrays.sort(a, new Student.tenOrder());
        for (int k = 0; k < a.length; k++)
            StdOut.println(a[k]);
        StdOut.println();
        StdOut.println();
    }
    
    
    
}
