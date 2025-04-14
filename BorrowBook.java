import java.time.LocalDate;

class BorrowBook {
    private LocalDate start;
    private LocalDate end;
    private String title;
    private String userName;
    private boolean status;
    public BorrowBook(LocalDate start, LocalDate end, String title, String userName, boolean status) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.userName = userName;
        this.status = status;
    }
    public void setStartDate(LocalDate start) {
        this.start = start;
    }
    public LocalDate getStartDate() {
        return start;
    }
    public void setEndDate(LocalDate end) {
        this.end = end;
    }
    public LocalDate getEndDate() {
        return end;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setName(String userName) {
        this.userName = userName;
    }
    public String getName() {
        return userName;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Boolean getStatus() {
        return status;
    }
    //Hiển thị thông tin mượn sách (kèm theo User Name)
    public void displayBorrowBook1() {
        if (status == false) {
            System.out.println("Start: " + start + " | End: " +  end + " | Title: " +  title + " | User Name: " + userName + " | Status: Returned");
        } else if (status == true) {
            System.out.println("Start: " + start + " | End: " +  end + " | Title: " +  title + " | User Name: " + userName + " | Status: Borrowing");
        }
    }
    //Hiển thị thông tin mượn sách của 1 Reader 
    public void displayBorrowBook2() {
        if (status == false) {
            System.out.println("Start: " + start + " | End: " +  end + " | Title: " +  title + " | Status: Returned");
        } else if (status == true)  {
            System.out.println("Start: " + start + " | End: " +  end + " | Title: " +  title + " | Status: Borrowing");
        }
    }
}