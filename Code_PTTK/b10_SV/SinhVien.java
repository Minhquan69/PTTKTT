import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class SinhVien {
    private String ten;
    private VNDate ngaySinh;
    private String queQuan;
    private List<Mon> diemMonHoc;

    public SinhVien(String ten, VNDate ngaySinh, String queQuan) {
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.diemMonHoc = new ArrayList<>();
    }

    public String getTen() {
        return ten;
    }

    public VNDate getNgaySinh() {
        return ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void nhapDiemMonSV(Mon mon, double diem) {
        Mon monHoc = new Mon(mon.getTen(), diem); 
        this.diemMonHoc.add(monHoc); 
    }

    public double tinhDiemTBC() {
        if (diemMonHoc.isEmpty()) {
            return 0;
        }
        double tongDiem = 0;
        for (Mon mon : diemMonHoc) {
            tongDiem += mon.getDiem();
        }
        return tongDiem / diemMonHoc.size();
    }

    public double tinhDiemTBCHocKy(String kyThu) {
        int tongDiem = 0;
        int soMonKyThu = 0;
        for (Mon mon : diemMonHoc) {
            if (mon.getTen().endsWith(kyThu)) {
                tongDiem += mon.getDiem();
                soMonKyThu++;
            }
        }
        if (soMonKyThu == 0) {
            return 0;
        }
        return (double) tongDiem / soMonKyThu;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        List<SinhVien> danhSachSinhVien = new ArrayList<>();
        int[] soHocKy = new int[100]; // Giả sử không quá 100 học kỳ
    
        // Đọc dữ liệu từ file và tạo danh sách sinh viên
        File file = new File("sinhvien.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String ten = parts[0].trim();
                VNDate ngaySinh = new VNDate(parts[1].trim());
                String queQuan = parts[2].trim();
                SinhVien sinhVien = new SinhVien(ten, ngaySinh, queQuan);
                for (int i = 3; i < parts.length; i += 3) {
                    String tenMon = parts[i].trim() + "_" + parts[i + 2].trim();
                    double diem = Double.parseDouble(parts[i + 1].trim());
                    Mon mon = new Mon(tenMon, diem);
                    sinhVien.nhapDiemMonSV(mon, diem);
                    int hocKy = Integer.parseInt(parts[i + 2].trim());
                    soHocKy[danhSachSinhVien.size()] = Math.max(soHocKy[danhSachSinhVien.size()], hocKy);
                }
                danhSachSinhVien.add(sinhVien);
            }
        }
        scanner.close();
    
        // In ra thông tin và điểm trung bình của từng sinh viên
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sv = danhSachSinhVien.get(i);
            System.out.println("Tên: " + sv.getTen());
            System.out.println("Ngày sinh: " + sv.getNgaySinh());
            System.out.println("Quê quán: " + sv.getQueQuan());
            System.out.println("Điểm trung bình: " + sv.tinhDiemTBC());
            for (int j = 1; j <= soHocKy[i]; j++) {
                double diemTBHK = sv.tinhDiemTBCHocKy(String.valueOf(j));
                if (diemTBHK != 0) {
                    System.out.println("Điểm trung bình của kỳ " + j + ": " + diemTBHK);
                } else {
                    System.out.println("Không có dữ liệu cho kỳ " + j);
                }
            }
            System.out.println();
        }
    }
}