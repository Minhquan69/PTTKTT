import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CountingInversions {

   
    private CountingInversions() { }

    
    private static long merge(String[] a, String[] aux, int lo, int mid, int hi) {
        long inversions = 0;

      
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                    a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) { 
                a[k] = aux[j++];
                inversions += (mid - i + 1);
            }
            else                            a[k] = aux[i++];
        }
        return inversions;
    }

    // tr? v? s? l??ng ngh?ch th? trong m?ng con b[lo..hi]
    // t�c d?ng ph? b[lo..hi] ???c s?p x?p theo th? t? t?ng d?n
    private static long count(String[] a, String[] b, String[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid+1, hi);
        inversions += merge(b, aux, lo, mid, hi);
        return inversions;
    }

    /**
     * Tr? v? s? l??ng ngh?ch th? trong m?ng chu?i.
     * M?ng ??i s? kh�ng b? s?a ??i.
     * @param a m?ng
     * @return s? l??ng ngh?ch th? trong m?ng. M?t ngh?ch th? l� m?t c?p ch? s?
     *         {@code i} v� {@code j} sao cho {@code i < j} v�
     *         {@code a[i].compareTo(a[j]) > 0}.
     */
    public static long count(String[] a) {
        String[] b = a.clone();
        String[] aux = a.clone();
        long inversions = count(a, b, aux, 0, a.length - 1);
        System.out.println("Sap xep ho, ten dem: " + Arrays.toString(b));
        return inversions;
    }

    public static void main(String[] args) throws IOException {
        // ??c d? li?u t? file text
        Scanner scanner = new Scanner(new File("danhsachsv.txt"));

        // ??m s? l??ng sinh vi�n
        int n = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            n++;
        }
        scanner.close();

        // Kh?i t?o m?ng ?? l?u danh s�ch sinh vi�n
        String[] a = new String[n];

        // ??c d? li?u t? file text v�o m?ng
        scanner = new Scanner(new File("danhsachsv.txt"));
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLine();
        }
        scanner.close();

        // In danh s�ch sinh vi�n ban ??u
        System.out.println("Danh sach sinh vien ban dau:");
        for (String student : a) {
            System.out.println(student);
        }

        // S? l??ng ngh?ch th? v� s?p x?p l?i danh s�ch sinh vi�n
        long inversions = CountingInversions.count(a);
        System.out.println("So luong nghich the: " + inversions);
    }
}
