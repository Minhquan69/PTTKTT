public class VNDate implements Comparable<VNDate> {
    private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int month;   // month (between 1 and 12)
    private final int day;     // day   (between 1 and DAYS[month]
    private final int year;    // year

   /**
     * Initializes a new date from the month, day, and year.
     * @param month the month (between 1 and 12)
     * @param day the day (between 1 and 28-31, depending on the month)
     * @param year the year
     * @throws IllegalArgumentException if this date is invalid
     */
    public VNDate(int day,int month, int year) {
        if (!isValid(day, month, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day   = day;
        this.year  = year;
    }

    public VNDate(String date) {
        String[] fields = date.split("/");
        if (fields.length != 3) {
            throw new IllegalArgumentException("Invalid date");
        }
        month = Integer.parseInt(fields[1]);
        day   = Integer.parseInt(fields[0]);
        year  = Integer.parseInt(fields[2]);
        if (!isValid(day, month, year)) throw new IllegalArgumentException("Invalid date");
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }


    // is the given date valid?
    private static boolean isValid(int d, int m, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // is y a leap year?
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    public VNDate next() {
        if (isValid( day + 1,month, year))    return new VNDate( day + 1,month, year);
        else if (isValid( 1,month + 1, year)) return new VNDate( 1,month + 1, year);
        else                                  return new VNDate(1, 1, year + 1);
    }

    public boolean isAfter(VNDate that) {
        return compareTo(that) > 0;
    }

    public boolean isBefore(VNDate that) {
        return compareTo(that) < 0;
    }

    @Override
    public int compareTo(VNDate that) {
        if (this.year  < that.year)  return -1;
        if (this.year  > that.year)  return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day   < that.day)   return -1;
        if (this.day   > that.day)   return +1;
        return 0;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        VNDate that = (VNDate) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + month;
        hash = 31*hash + day;
        hash = 31*hash + year;
        return hash;
    }

    public static void main(String[] args) {
        VNDate today = new VNDate( 25, 2, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        VNDate birthday = new VNDate(16,10, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
        VNDate test = new VNDate("4/8/1955");
        StdOut.println(test);
    }

}