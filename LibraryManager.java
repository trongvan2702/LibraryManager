import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LibraryManager {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> printed_book = new ArrayList<>();
    private ArrayList<Book> e_book = new ArrayList<>();
    private ArrayList<BorrowBook> borrow_history = new ArrayList<>();
    private User currentUser;
    //Khởi tạo đối tượng LibraryManager mới
    public LibraryManager() {
        getAccount();
        getListOfBooks();
    }
    //Lưu thông tin của người dùng
    public void getAccount() {
        try {
            File accountFile = new File("account.csv");
            Scanner sc = new Scanner(accountFile);
            String temp;
            // 1,admin,abc123
            while(sc.hasNextLine()){
                temp = sc.nextLine();
                String[] account = temp.split(",");
                if(account[0].equals("Admin")){
                    Admin admin = new Admin(account[1], account[2]);
                    users.add(admin);
                } else if(account[0].equals("Librarian")){
                    Librarian librarian = new Librarian(account[1], account[2]);
                    users.add(librarian);
                } else if (account[0].equals("Reader")) {
                    Reader reader = new Reader(account[1], account[2]);
                    users.add(reader);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            e.fillInStackTrace();
        }
    }
    //Lưu thông tin của Printed Book, E-book và lịch sử mượn sách
    public void getListOfBooks() {
        try {
            File printedBookFile = new File("printed_book.csv");
            File eBookFile = new File("e_book.csv");
            File borrowFile = new File("transaction.csv");
            String temp;
            Scanner sc = new Scanner(printedBookFile);
            while(sc.hasNextLine()){
                temp = sc.nextLine();
                String book[] = temp.split(",");
                Book printedBook = new PrintedBook(book[0], book[1], book[2], book[3], book[4], Boolean.parseBoolean(book[5]), Integer.parseInt(book[6]));
                printed_book.add(printedBook);
            }
            sc.close();
            sc = new Scanner(eBookFile);
            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                String book[] = temp.split(",");
                Book eBook = new Ebook(book[0], book[1], book[2], book[3], book[4], false, book[6]);
                e_book.add(eBook);
            }
            sc.close();
            sc = new Scanner(borrowFile);
            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                String book[] = temp.split(",");
                //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                BorrowBook bb = new BorrowBook(LocalDate.parse(book[0]), LocalDate.parse(book[1]), book[2], book[3], Boolean.parseBoolean(book[4]));
                borrow_history.add(bb);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            e.fillInStackTrace();
        }
    }
    //Cập nhật thông tin người dùng vào filefile
    public void writeAccount() {
        try {
            FileWriter fw = new FileWriter("account.csv");
            for (User user : users) {
                String info = user.getClass().getSimpleName() + "," + user.getName() + "," + user.getPassword() + "\n";
                fw.write(info);
            }
            fw.close();
        /*} catch (FileNotFoundException e) {
            e.fillInStackTrace();*/
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    //Cập nhật thông tin Printed Book, E-book và lịch sử mượn sách 
    public void writeBooks() {
        try {
            FileWriter printed = new FileWriter("printed_book.csv");
            FileWriter e = new FileWriter("e_book.csv");
            FileWriter borrowed_book = new FileWriter("transaction.csv");
            Collections.sort(printed_book, new SortBooks());
            for (Book book : printed_book) {
                printed.write(book.toString());
            }
            Collections.sort(e_book, new SortBooks());
            for (Book book : e_book) {
                e.write(book.toString());
            }
            Collections.sort(borrow_history, new SortBorrowBooks());
            for (BorrowBook bb : borrow_history) {
                String info = bb.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," + bb.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," + bb.getTitle() + "," + bb.getName() + "," + Boolean.toString(bb.getStatus()) + "\n";
                borrowed_book.write(info);
            }
            printed.close();
            e.close();
            borrowed_book.close();
        }  catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public ArrayList<User> getUsers(){return users;}
    public ArrayList<Book> getPrintedBooks(){return printed_book;}
    public ArrayList<Book> getEBooks(){return e_book;}
    public void setCurrentUser(User user) { this.currentUser = user; }
    public User getCurrentUser(){return currentUser;}
    //Kiểm tra thông tin đăng nhập có hợp lệ hay không
    public boolean login(String userName, String password){
        for(User user : users){
            if(user.getName().equals(userName) && user.getPassword().equals(password)){
                //currentUser = user;
                System.out.println("Successfully Log In.\n");
                return true;
            }
        }
        System.out.println("Invalid username or password. Please try again.\n");
        return false;
    }
    //Tìm kiếm người dùng thông qua User Name
    public User findUser(String userName) {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    //Tìm kiếm người dùng thông qua User Name và password
    public User findUser(String userName, String password) {
        for (User user : users) {
            if (user.getName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    //Hiển thị thông tin của toàn bộ người dùng (Role, UserName) (Admin)
    public void displayUser(){
        Collections.sort(users, new SortByRoles());
        System.out.println("\n----List Of Users----");
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("%d. Roles: %s \t User Name: %s\n", i + 1, users.get(i).getClass().getSimpleName(), users.get(i).getName());
        }
        System.out.println();
    }
    //Thêm người dùng mới (Admin)
    public void addUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Add User----");
        System.out.printf("User Name: ");
        String userName = sc.nextLine();
        for (User user : users) {
            if(user.getName().equals(userName)){
                System.out.println("This Username has been used.\n");
                return;
            }
        }
        System.out.printf("Password: ");
        String password = sc.nextLine();
        
        System.out.println("1. Admin \t 2. Librarian \t 3. Reader");
        System.out.print("Choose Role: ");
        int role = sc.nextInt();
        if(role == 1) {
            users.add(new Admin(userName, password));
        } else if(role == 2) {
            users.add(new Librarian(userName, password));
        } else if(role == 3) {
            users.add(new Reader(userName, password));
        }
        //Collections.sort(users, new SortByRoles());
        System.out.println("New user has been added.\n");
        
    }
    //Loại bỏ người dùng (Admin)
    public void removeUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Remove User----");
        System.out.print("Username: ");
        String userName = sc.nextLine();
        for(User user : users){
            if(user.getName().equals(userName)){
                users.remove(user);
                System.out.println("User has been removed.\n");
                return;
            }
        }
        System.out.println("User doesn't exist.\n");
        sc.close();
    }
    //Tìm sách thông qua title của sách (Reader)
    public Book findBook(String title) {
        for (Book pb : printed_book) {
            if (pb.getTitle().equals(title)) {
                return pb;
            }
        }
        return null;
    }
    //Hiển thị thông tin của toàn bộ sách có trong thư viện (Librarian & Reader)
    public void displayBook(){
        //if(currentUser instanceof Librarian){
        ArrayList<Book> toDisplay = new ArrayList<>();
        System.out.println("\n----List Of Books----");
        int count = 0;
        for(Book pb : printed_book){
            toDisplay.add(pb);
            //System.out.printf("%d. ", count + 1);
            //Tiltle: %s, Author: %s, Genre: %s, ISBN: %s\n", i + 1, books.get(i).getClass().getSimpleName() ,books.get(i).getTitle(), books.get(i).getAuthor(), books.get(i).getGenre(), books.get(i).getISBN());
            //pb.displayBook();
            //count++;
        }
        for (Book e : e_book) {
            String title = e.getTitle();
            if (findBook(title) == null) {
                toDisplay.add(e);
                //System.out.printf("%d. ", count + 1);
                //e.displayBook();
                //count++;
            }
        }
        Collections.sort(toDisplay, new SortBooks());
        for (Book b : toDisplay) {
            System.out.printf("%d. ", count + 1);
            count++;
            //b.displayBook();
            if (b.getAvailabilityStatus() == true) {
                System.out.printf("ISBN: %s | Tilte: %s | Genre: %s | Author: %s | Publisher: %s | Status: Can Borrow\n", b.getISBN(), b.getTitle(), b.getGenre(), b.getAuthor(), b.getPublisher());
            } else {
                System.out.printf("ISBN: %s | Tilte: %s | Genre: %s | Author: %s | Publisher: %s | Status: Can't Borrow\n", b.getISBN(), b.getTitle(), b.getGenre(), b.getAuthor(), b.getPublisher());
            }
        }
        //Collections.sort(toDisplay, new SortBooks());
        System.out.println();
        //}
    }
    //Thêm 1 sách mới (PrintedBook hoặc EbookEbook) (Librarian)
    public void addBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Add Book----");
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        for (Book pb : printed_book) {
            if (pb.getISBN().equals(isbn)) {
                System.out.println("This ISBN has been used. Please try again.\n");
                return;
            }
        }
        for (Book e : e_book) {
            if (e.getISBN().equals(isbn)) {
                System.out.println("This ISBN has been used. Please try again.\n");
                return;
            }
        }
        System.out.print("Book Title: ");
        String title = sc.nextLine();
        int checkPrintedExist = 0, checkEbookExist = 0;
        for (Book pb : printed_book) {
            if (pb.getTitle().equals(title)) {
                checkPrintedExist = 1;
                break;
            }
        }
        for (Book e : e_book) {
            if (e.getTitle().equalsIgnoreCase(title)) {
                checkEbookExist = 1;
                break;
            }
        }
        if (checkPrintedExist == 1 && checkEbookExist == 1) {
            System.out.println("This book has existed.\n");
            return;
        }
        System.out.print("Book Genre: ");
        String genre = sc.nextLine();
        System.out.print("Book Author: ");
        String author = sc.nextLine();
        System.out.print("Publisher: ");
        String publisher = sc.nextLine();
        System.out.print("Number Of Pages: ");
        int numberOfPage = sc.nextInt();
        while (true) {
            System.out.println("Which type of books do you want to add?");
            System.out.println("1: Printed Book \t 2: E-Book");
            System.out.print("Choose option number: ");
            int choose = sc.nextInt();
            if (choose == 1) {
                if (checkPrintedBook == 1) {
                   System.out.println("This book had a printed version.\n");
                } else {
                    printed_book.add(new PrintedBook(isbn, title, genre, author, publisher, true, numberOfPage));
                    System.out.println("New printed book has been added.\n");
                }
                break;
            } else if (choose == 2) {
                if (checkEBook == 1) {
                    System.out.println("This book had an electronic version.\n");
                }  else {
                    e_book.add(new Ebook(isbn, title, genre, author, publisher, false, "pdf"));
                    System.out.println("New e-book has been added.\n");
                }
                break;
            } else {
                System.out.println("Invalid option. Please try again\n");
            }
        }
    }
    //Loại bỏ sách ra khỏi hệ thống (Librarian)
    public void removeBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Remove Book----");
        System.out.printf("Book Title: ");
        String title = sc.nextLine();
        int flag1 = 0, flag2 = 0;
        for (Book pb : printed_book){
            if(pb.getTitle().equals(title)){
                printed_book.remove(pb);
                flag1 = 1;
                break;
            }
        }
        for (Book e : e_book) {
            if (e.getTitle().equals(title)) {
                e_book.remove(e);
                flag2 = 1;
                break;
            }
        }
        if (flag1 == 1 || flag2 == 1) {
            System.out.println("This book has been removed.\n");
            return;
        }
        System.out.println("This book doesn't existed.\n");
    }
    //Mượn sách (Reader) 
    public void borrowingBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Borrow Book----");
        System.out.printf("Type book title : ");
        String title = sc.nextLine();
        for (Book book : printed_book) {
            //if (book instanceof PrintedBook) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
                if (book.getAvailabilityStatus() == true) {
                    borrow_history.add(0, new BorrowBook(LocalDate.now(), LocalDate.now().plusDays(14), book.getTitle(), currentUser.getName(), true));
                    System.out.println("You must return this book 14 days from now.");
                    System.out.println("Due Date: " + LocalDate.now().plusDays(14));
                    System.out.println("Borrow Book Succesfully.\n");
                    book.setAvailabilityStatus(false);
                } else {
                    System.out.println("Somebody had borrowed this book.\n");
                }
                return;
            }
        }
        System.out.println("This book doesn't exist.");
    }
    //Trả sách (Reader) 
    public void returnBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Return Book----");
        System.out.printf("Type book title: ");
        String title = sc.nextLine();
        for (BorrowBook bb : borrow_history) {
            if (bb.getName().equals(currentUser.getName())) { 
                if (bb.getTitle().toLowerCase().equals(title.toLowerCase())) {
                    bb.setEndDate(LocalDate.now());
                    bb.setStatus(false);
                    for (Book pb : printed_book) { //for (int i = 0; i < printed_book.size(); i++) { 
                        /*if (printed_book.get(i).getTitle().equals(title)) {
                            printed_book.get(i).setAvailabilityStatus(true);
                            break;
                        }*/
                        if (pb.getTitle().toLowerCase().equals(title.toLowerCase())) {
                            pb.setAvailabilityStatus(true);
                            break;
                        }
                    }
                    System.out.println("You has returned this book. Thank you.\n");
                    return;
                }
            }
        }
        System.out.println("You hadn't borrowed this book.\n");
    }
    //Hiển thị lịch sử mượn sách của thư viện (Librarian) 
    public void displayBorrowHistory() {
        System.out.println("\n----Borrow History----");
        Collections.sort(borrow_history, new SortBorrowBooks());
        //System.out.println(" Start \t End \t Title \t UserName");
        for (int i = 0; i < borrow_history.size(); i++) {
            System.out.printf("%d. ", i + 1);
            borrow_history.get(i).displayBorrowBook1();
        }
        System.out.println();
    }
    //Hiển thị lịch sử mượn sách của 1 Reader thông qua User Name (Reader) 
    public void displayBorrowHistory(String userName) {
        System.out.println("\n----Borrow History----");
        int count = 0;
        Collections.sort(borrow_history, new SortBorrowBooks());
        for (BorrowBook bb : borrow_history) {
            if (bb.getName().equals(userName)) {
                System.out.printf("%d. ", count + 1);
                count++;
                bb.displayBorrowBook2();
            }
        }
        System.out.println();
    }
}
