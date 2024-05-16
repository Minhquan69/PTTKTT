
import java.util.Arrays;
import java.util.Comparator;
import java.io.FileInputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SinhVien implements Comparable<SinhVien> {
    private final String msv;
    private final String hoDem;
    private final String ten;     
    private final VietnamDate ngaySinh; 
    private  double diemTB;
    private final String queQuan;
    private final String sdt;

    public SinhVien(String ten, String msv, VietnamDate ngaySinh, String hoDem, double diemTB, String sdt, String queQuan ) {
        if (Double.isNaN(diemTB) || Double.isInfinite(diemTB))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.msv   = msv; 
        this.hoDem   = hoDem;
        this.ten    = ten;
        this.ngaySinh = ngaySinh;
        this.diemTB = diemTB;
        this.sdt = sdt;
        this.queQuan = queQuan;
    }
    public SinhVien(){
        msv = hoDem = ten =  " ";
        ngaySinh = new VietnamDate(26,02,2003);
        diemTB = 0;
        queQuan = sdt = " ";
    }
    public SinhVien(String sinhvien) {
        String[] a = sinhvien.split("\\s{2,}");
        msv = a[0];
        hoDem = a[1];
        ten    = a[2];
        ngaySinh   = new VietnamDate(a[3]);
        diemTB = Double.parseDouble(a[4]);
        queQuan = a[5];
        sdt = a[6];
        if (Double.isNaN(diemTB) || Double.isInfinite(diemTB))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }
    public SinhVien(String a[]){
        msv = a[0];
        hoDem = a[1];
        ten = a[2];
        ngaySinh = new VietnamDate(a[3]);
        diemTB = Double.parseDouble(a[4]);
        queQuan = a[5];
        sdt = a[6];  
    }
    
    public String msv() {
        return msv;
    }
    
    public String ten() {
        return ten;
    }
 
    public String hoDem() {
        return hoDem;
    }
    
    public VietnamDate ngaySinh() {
        return ngaySinh;
    }
    
    public double diemTB() {
        return diemTB;
    }
    
    public String sdt() {
        return sdt;
    }
    
    public String queQuan() {
        return queQuan;
    }
    @Override
    public String toString() {
        return String.format("%-6s %-20s %-10s %-10s %-8.2f %-10s %-10s", msv, hoDem, ten, ngaySinh, diemTB, queQuan, sdt);
    }

    public int compareTo(SinhVien that) {
        return Double.compare(this.diemTB, that.diemTB);
    }    
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        SinhVien that = (SinhVien) other;
        return (this.diemTB == that.diemTB);
    }
    public int hashCode() {
        int hash = 1;
        hash = 31*hash + msv.hashCode();
        hash = 31*hash + hoDem.hashCode();
        hash = 31*hash + ten.hashCode();
        hash = 31*hash + ngaySinh.hashCode();
        hash = 31*hash + ((Double) diemTB).hashCode();
        hash = 31*hash + queQuan.hashCode();
        hash = 31*hash + sdt.hashCode(); 
        return hash;
    }
    public static class tenOrder implements Comparator<SinhVien> {

        @Override
        public int compare(SinhVien v, SinhVien w) {
            return v.ten.compareTo(w.ten);
        }
    }

    public static class msvOrder implements Comparator<SinhVien> {

        @Override
        public int compare(SinhVien v, SinhVien w) {
            return v.msv.compareTo(w.msv);
        }
    }
    
    public static class ngaySinhOrder implements Comparator<SinhVien> {

        @Override
        public int compare(SinhVien v, SinhVien w) {
            return v.ngaySinh.compareTo(w.ngaySinh);
        }
    }

    public static class queOrder implements Comparator<SinhVien> {

        @Override
        public int compare(SinhVien v, SinhVien w) {
            return v.queQuan.compareTo(w.queQuan);
        }
    }
    public static class TBCOrder implements Comparator<SinhVien> {

        @Override
        public int compare(SinhVien v, SinhVien w) {
            return Double.compare(v.diemTB, w.diemTB);
        }
    }

    public static void main(String[] args) throws java.io.FileNotFoundException, IOException{
        System.out.println("Do Xuan Trang");
        //System.setIn(new FileInputStream(new File("SortSinhVien.txt")));
        //int n = Integer.parseInt(StdIn.readLine());
        //while(StdIn.hasNextLine()){
        //    String line = StdIn.readLine();
        //    a[i++] = new SinhVien(line);
        //}
        SinhVien[] a = new SinhVien[5];
        a[0] = new SinhVien("1    Do Xuan         Trang    26/02/2003    9.8    Ha Noi        0934");
        a[1] = new SinhVien("2    Nguyen Thanh    Dat      01/01/2004    10     Ha Noi        2222");
        a[2] = new SinhVien("3    Ta Quoc         Viet     02/03/2006    9.2    Thai Binh     3333");
        a[3] = new SinhVien("4    Nguyen Trung    Dung     04/04/2005    9.2    Bac Ninh      4444");
        a[4] = new SinhVien("5    Bui Duc         Anh      07/09/2008    8.4    Anh Quoc      5555");
        StdOut.println("Danh sach sinh vien ban dau");
        for (int k = 0; k < a.length; k++)
            StdOut.println(a[k]);
        StdOut.println();
        StdOut.println("Danh sach sinh vien sap xep theo ten");
        Arrays.sort(a, new SinhVien.tenOrder());
        for (int k = 0; k < a.length; k++)
            StdOut.println(a[k]);
        StdOut.println();

        StdOut.println("Danh sach sinh vien sap xep theo ngay sinh");
        Arrays.sort(a, new SinhVien.ngaySinhOrder());
        for (int k = 0; k < a.length; k++)
            StdOut.println(a[k]);
        StdOut.println();

        StdOut.println("Danh sach sinh vien sap xep theo que");
        Arrays.sort(a, new SinhVien.queOrder());
        for (int k = 0; k < a.length; k++)
            StdOut.println(a[k]);
        StdOut.println();
    }

}
