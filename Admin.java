public class Admin extends User{
    //Khởi tạo đối tượng Admin mới với user name và passwordpassword
    public Admin(String userName, String password){
        super(userName, password);
    }
    @Override public String toString() {
        return "Admin," + super.getName() + super.getPassword();
    }
    @Override public void displayMenu() {
        System.out.println("---Admin Menu---");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. List All User");
        System.out.println("0. Logout");
        System.out.print("Choose option number: ");
    }
}
