import java.io.*;
import java.util.*;

public class DSLop {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream(new File("sinhvien.csv")));
        ST<String, Sinhvien> st = new ST<>();

       
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String line = StdIn.readLine();
            String[] tokens = line.split(",");
            String maSV = tokens[0];
            Sinhvien sinhvien = new Sinhvien(tokens);
            st.put(maSV, sinhvien);
        }

        for (int i = 0; i < args.length; i++) {
            In in = new In(args[i]);
            String[] tenMon = args[i].split(".csv");
            String line = in.readLine();
            String[] line_SoTin = line.split(",");
            Mon monHoc = new Mon(tenMon[0], Integer.parseInt(line_SoTin[1]));
            while (!in.isEmpty()) {
                line = in.readLine();
                String[] maVaDiem = line.split(",");
                Sinhvien sv = st.get(maVaDiem[0]);
                sv.getBangdiem().put(monHoc, Double.parseDouble(maVaDiem[1]));
                double diemTB = sv.Tinh_diemtb();
                sv.luuDiemTrungBinhMon(monHoc, diemTB); // L?u ?i?m trung b�nh k?
            }
        }

        // In 10 sinh vi�n c� ?i?m trung b�nh k? cao nh?t
        // T?o danh s�ch sinh vi�n t? ST
        List<Sinhvien> danhSachSV = new ArrayList<>();
        for (String key : st.keys()) {
            danhSachSV.add(st.get(key));
        }
        
        // S?p x?p danh s�ch sinh vi�n theo ?i?m trung b�nh k? gi?m d?n
        danhSachSV.sort((sv1, sv2) -> Double.compare(sv2.getDiemTB(), sv1.getDiemTB()));
        
        // In 10 sinh vi�n c� ?i?m trung b�nh k? cao nh?t
        System.out.println("Top 10 sinh vien co diem trung binh ky cao nhat:");
        for (int i = 0; i < 10 && i < danhSachSV.size(); i++) {
            System.out.println(danhSachSV.get(i));
        }

        // i. In danh s�ch l?p s?p x?p theo t�n
        System.out.println("\nDanh sach lop sap xep theo ten:");
        danhSachSV.sort(new Sinhvien.tenOrder());
        for (Sinhvien sv : danhSachSV) {
            System.out.println(sv);
        }

        // ii. In danh s�ch l?p s?p x?p theo ng�y sinh t? gi� ??n tr?
        System.out.println("\nDanh sach lop sap xep theo ngay sinh tu gia den tre:");
        Comparator<Sinhvien> ngaySinhComparator = (sv1, sv2) -> sv1.getNgaySinh().compareTo(sv2.getNgaySinh());
        danhSachSV.sort(ngaySinhComparator);
        for (Sinhvien sv : danhSachSV) {
            System.out.println(sv);
        }

        // iii. In danh s�ch sinh vi�n theo Qu� (c�c sinh vi�n c�ng qu� s?p x?p theo t�n)
        Map<String, List<Sinhvien>> sinhVienTheoQue = new HashMap<>();
        for (Sinhvien sv : danhSachSV) {
            String queQuan = sv.getQueQuan();
            if (!sinhVienTheoQue.containsKey(queQuan)) {
                sinhVienTheoQue.put(queQuan, new ArrayList<>());
            }
            sinhVienTheoQue.get(queQuan).add(sv);
        }

        System.out.println("\nDanh sach sinh vien theo Que (sap xep theo ten):");
        for (String queQuan : sinhVienTheoQue.keySet()) {
            System.out.println("Que: " + queQuan);
            List<Sinhvien> dsSinhVienCungQue = sinhVienTheoQue.get(queQuan);
            dsSinhVienCungQue.sort(new Sinhvien.tenOrder());
            for (Sinhvien sv : dsSinhVienCungQue) {
                System.out.println(sv);
            }
            System.out.println();
        }
    }
}