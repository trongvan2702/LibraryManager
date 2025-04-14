import java.lang.Boolean;

public class Ebook extends Book{
    private String fileFormat;

    public Ebook(String isbn, String title, String genre, String author, String publisher, boolean availabilityStatus, String fileFormat) {
        super(isbn, title, genre, author, publisher, availabilityStatus);
        this.fileFormat = fileFormat;
    }
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileFormat(){
        return fileFormat;
    }
    @Override public void displayBook() {
        System.out.printf("ISBN: %s | Tilte: %s | Genre: %s | Author: %s | Publisher: %s | Status: Can't Borrow | File Format: %s\n", super.getISBN(), super.getTitle(), super.getGenre(), super.getAuthor(), super.getPublisher(), fileFormat);
    }
    @Override public String toString() {
        return super.getISBN() + "," + super.getTitle() + "," + super.getGenre() + "," + super.getAuthor() + "," + super.getPublisher() + "," + Boolean.toString(false) + "," + fileFormat + "\n";
    }
}
