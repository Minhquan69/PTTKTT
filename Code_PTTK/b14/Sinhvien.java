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
public class Sinhvien implements Comparable<Sinhvien>
{ 
    private String maSV;
    private String hoDem;
    private String ten;
    private VietnamDate ngaySinh;
    private double diemTB;
    private String queQuan;
    private String sDT;
    private HashMap<Mon, Double> bangdiem;
    private HashMap<Mon, Double> diemTrungBinhKy;
    public Sinhvien(){
        maSV = hoDem = ten =  " ";
        ngaySinh = new VietnamDate(27,02,2004);
        diemTB = 0.0;
        queQuan = sDT = " ";
    }
    
    public Sinhvien(String maSV, String hoDem, String ten, VietnamDate ngaySinh, double diemTB,
                    String queQuan, String sDT, HashMap<Mon, Double> bangdiem, HashMap<Mon, Double> diemTrungBinhKy) {
        this.maSV = maSV;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diemTB = diemTB;
        this.queQuan = queQuan;
        this.sDT = sDT;
        this.bangdiem = bangdiem;
        this.diemTrungBinhKy = diemTrungBinhKy;
    }

    public Sinhvien(String student){
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
    
    public Sinhvien(String[] a){
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
        if (tongTin == 0) {
        return 0.0; // Hoac mot gia tri mac dinh khac
    }
        return (double) (tongDiem/tongTin);
    }
    public void luuDiemTrungBinhMon(Mon mon, double diemTB) {
        diemTrungBinhKy.put(mon, diemTB);
    }

    public Double getDiemTrungBinhMon(Mon mon) {
        return diemTrungBinhKy.get(mon);
    }
    public static class tenOrder implements Comparator<Sinhvien> {

        @Override
        public int compare(Sinhvien v, Sinhvien w) {
            return v.ten.compareTo(w.ten);
        }
    }
    
    
    @Override
    public int compareTo(Sinhvien that) {
        return Double.compare(this.diemTB, that.diemTB);
    }   
    

    
}
