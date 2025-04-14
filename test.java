import java.util.Scanner;

public class test {
    public static void displayMainMenu(){
        System.out.println("---Main Menu---");
        System.out.println("1. Login");
        System.out.println("2. Exist");
        System.out.printf("Choose: ");
    }

    public static void userController(User user, int choose, LibraryManager LM){
        if(user instanceof Admin) adminController(choose, LM);
        else if(user instanceof Librarian) librarianController(choose, LM);
        else readerController(choose, LM);
    }
    public static void adminController(int choose, LibraryManager LM){
        switch (choose) {
            case 1:
                LM.addUser();
                break;
            case 2:
                LM.removeUser();
                break;
            case 3:
                LM.displayUser();
                break;
            default:
                System.out.println("Invalid");
                break;
        }
    }
    public static void librarianController(int choose, LibraryManager LM){
        switch (choose) {
            case 1:
                LM.addBook();
                break;
            case 2:
                LM.removeBook();
                break;
            case 3:
                LM.displayBook();
            default:
                System.out.println("Invalid");
                break;
        }
    }
    public static void readerController(int choose, LibraryManager LM){

    }
    public static void main(String[] args) {
        LibraryManager LM = new LibraryManager();
        Scanner sc  = new Scanner(System.in);
        while(true){
            displayMainMenu();
            int x = Integer.parseInt(sc.nextLine());
            switch (x) {
                case 1:
                    System.out.printf("Usersname: ");
                    String userName = sc.nextLine();
                    System.out.printf("Password: ");
                    String password = sc.nextLine();
                    if(LM.login(userName, password)){
                        User currentUser = LM.getCurrentUser();
                        while(true){
                            currentUser.displayMenu();
                            x = Integer.parseInt(sc.nextLine());
                            if(x == 0) break;
                            userController(currentUser, x, LM);
                        }
                    }
                    break;
                case 2:
                    return;            
                default:
                    System.out.println("Invaild input");
                    break;
            }{
        
            }    
        }
    }
}
