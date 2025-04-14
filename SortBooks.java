import java.util.*;

class SortBooks implements Comparator<Book> {
    public int compare(Book a, Book b) {
        int isbn_a = Integer.parseInt(a.getISBN());
        int isbn_b = Integer.parseInt(b.getISBN());
        if (isbn_a < isbn_b) {
            return -1;
        }
        return 1;
    }
}