public class Librarian extends User{
    public Librarian(String userName, String password){
        super(userName, password);
    }
    @Override public String toString() {
        return "Librarian," + super.getName() + super.getPassword();
    }   
    @Override public void displayMenu() {
        System.out.println("---Librarian Menu---");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. List All Book");
        System.out.println("4. Borrowing History");
        System.out.println("0. Logout");
        System.out.print("Choose option number: ");
    }
}
