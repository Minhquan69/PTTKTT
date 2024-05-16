import java.io.*;
import java.util.*;

public class DSSinhvien{
    private static ST<String, Sinhvien> st = new ST<>();
    private static ST<String, Queue<Sinhvien>> ts = new ST<>();

    public static void main(String[] arg) throws IOException {
        System.setIn(new FileInputStream(new File("sinhvien.csv")));
        loadData();

     
        InDanhsachDonghuong("Ha Noi");

         IndanhsachSinhvien(8.0);
    }

    private static void loadData() {
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String line = StdIn.readLine();
            String[] tokens = line.split(",");
            String keyMaSV = tokens[0];
            String keyQueQuan = tokens[5];
            Sinhvien sinhvien = new Sinhvien(tokens);
            st.put(keyMaSV, sinhvien);
            if (ts.get(keyQueQuan) == null) {
                Queue<Sinhvien> sv = new Queue<>();
                sv.enqueue(sinhvien);
                ts.put(keyQueQuan, sv);
            } else {
                ts.get(keyQueQuan).enqueue(sinhvien);
            }
        }
    }

    public static void InDanhsachDonghuong(String tinh) {
        Queue<Sinhvien> danhSachDongHuong = ts.get(tinh);
        if (danhSachDongHuong == null) {
            System.out.println("Khong co sinh vien nao que o " + tinh);
        } else {
            System.out.println("Danh sach sinh vien que o " + tinh + ":");
            while (!danhSachDongHuong.isEmpty()) {
                Sinhvien sinhVien = danhSachDongHuong.dequeue();
                System.out.println(sinhVien);
            }
        }
    }

    public static void IndanhsachSinhvien(double diem) {
        ArrayList<Sinhvien> danhSachSinhVien = new ArrayList<>();
        for (String maSV : st.keys()) {
            danhSachSinhVien.add(st.get(maSV));
        }

        Collections.sort(danhSachSinhVien, new Comparator<Sinhvien>() {
            @Override
            public int compare(Sinhvien sv1, Sinhvien sv2) {
                return Double.compare(sv2.getDiemTB(), sv1.getDiemTB());
            }
        });

        System.out.println("Danh sach sinh vien co diem trung binh tong >= " + diem + ":");
        for (Sinhvien sinhVien : danhSachSinhVien) {
            if (sinhVien.getDiemTB() >= diem) {
                System.out.println(sinhVien);
            }
        }
    }
}