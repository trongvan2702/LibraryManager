public abstract class Book {
    private String title, author, genre, isbn, publisher;
    private boolean availabilityStatus;

    public Book(String isbn, String title, String genre, String author, String publisher, boolean availabilityStatus){
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.availabilityStatus = availabilityStatus;
    }
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }
    public String getISBN(){
        return isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre(){
        return genre;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
    public boolean getAvailabilityStatus(){
        return availabilityStatus;
    }
    abstract public void displayBook();
    abstract public String toString();
}
