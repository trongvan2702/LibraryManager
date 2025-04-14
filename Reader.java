public class Reader extends User{
    
    public Reader(String userName, String password){
        super(userName, password);
    }
    @Override public String toString() {
        return "Reader," + super.getName() + super.getPassword();
    }
    @Override public void displayMenu() {
        System.out.println("---Reader Menu---");
        System.out.println("1. View the list of books");
        System.out.println("2. View the list of borrowed books");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a borrowed book");
        System.out.println("0. Logout");
        System.out.print("Choose option number: ");
    }
}
