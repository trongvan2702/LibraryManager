import java.lang.Boolean;

public class PrintedBook extends Book{
    private int numberOfPages;
    //private LocalDate dueDate;

    public PrintedBook(String isbn, String title, String genre, String author, String publisher, boolean availabilityStatus, int numberOfPages){
        super(isbn, title, genre, author, publisher, availabilityStatus);
        this.numberOfPages = numberOfPages;
    }
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public int getNumberOfPages(){
        return numberOfPages;
    }
    @Override public void displayBook() {
        if (super.getAvailabilityStatus() == true) {
            System.out.printf("ISBN: %s | Tilte: %s | Genre: %s | Author: %s | Publisher: %s | Status: Can Borrow | Number of Pages: %d\n", super.getISBN(), super.getTitle(), super.getGenre(), super.getAuthor(), super.getPublisher(), numberOfPages);
        } else {
            System.out.printf("ISBN: %s | Tilte: %s | Genre: %s | Author: %s | Publisher: %s Status: Can't Borrow | Number of Pages: %d\n", super.getISBN(), super.getTitle(), super.getGenre(), super.getAuthor(), super.getPublisher(), numberOfPages);
        }
    }
    @Override public String toString() {
        return super.getISBN() + "," + super.getTitle() + "," + super.getGenre() + "," + super.getAuthor() + "," + super.getPublisher() + "," + Boolean.toString(super.getAvailabilityStatus()) + "," + Integer.toString(numberOfPages) + "\n";
    }
}
