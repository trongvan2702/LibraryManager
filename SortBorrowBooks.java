import java.util.*;

class SortBorrowBooks implements Comparator<BorrowBook> {
    public int compare (BorrowBook a, BorrowBook b) {
        if (a.getStartDate().compareTo(b.getStartDate()) > 0) {
            return -1;
        } else if (a.getStartDate().compareTo(b.getStartDate()) == 0) {
            if (a.getEndDate().compareTo(b.getEndDate()) > 0) {
                return -1;
            }
            return 1;
        }
        return 1;
    }
}