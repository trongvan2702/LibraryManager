import java.util.*;

public class Main {
    //Main Case
    public static void main(String[] args) throws InputMismatchException {
        LibraryManager LM = new LibraryManager();
        //LM.displayUser();
        //LM.displayBook();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWELCOME TO LIBRARY MANAGEMENT SYSTEM");
            System.out.println("===============================");
            System.out.println("1: Log In \t 2: Exit");
            System.out.print("Choose option number: ");
            int option = sc.nextInt();
            if (option == 1) {
                System.out.println("\n----Log In----");
                System.out.printf("User Name : ");
                String userName = sc.nextLine() + sc.nextLine();
                System.out.printf("Password : ");
                String password = sc.nextLine();
                boolean validLogin = LM.login(userName, password);
                while (validLogin == true) {
                    LM.setCurrentUser(LM.findUser(userName, password));
                    //LM.getCurrentUser().displayMenu();
                    int choose; //= sc.nextInt();
                    if (LM.getCurrentUser() instanceof Admin) {
                        ((Admin)LM.getCurrentUser()).displayMenu();
                        choose = sc.nextInt();
                        switch (choose) {
                            case 0: validLogin = false; break;   
                            case 1: LM.addUser(); break;
                            case 2: LM.removeUser(); break;
                            case 3: LM.displayUser(); break;
                            default:
                                System.out.printf("Invalid number. Please try again.\n");
                                break;
                        }
                    } else if (LM.getCurrentUser() instanceof Librarian) {
                        ((Librarian)LM.getCurrentUser()).displayMenu();
                        choose = sc.nextInt();
                        switch (choose) {
                            case 0: validLogin = false; break;
                            case 1: LM.addBook(); break;
                            case 2: LM.removeBook(); break;
                            case 3: LM.displayBook(); break;
                            case 4: LM.displayBorrowHistory(); break;
                            default:
                                System.out.printf("Invalid number. Please try again.\n");
                                break;
                        }
                    } else if (LM.getCurrentUser() instanceof Reader) {
                        ((Reader)LM.getCurrentUser()).displayMenu();
                        choose = sc.nextInt();
                        switch (choose) {
                            case 0: validLogin = false; break;
                            case 1: LM.displayBook(); break;
                            case 2: LM.displayBorrowHistory(LM.getCurrentUser().getName()); break;
                            case 3: LM.borrowingBook(); break;
                            case 4: LM.returnBook(); break;
                            default: 
                                System.out.printf("Invalid number. Please try again.\n");
                                break;
                        }
                    }
                }
            } else if (option == 2) {
                System.out.println("Thank you for using. Good bye");
                break;
            } else {
                System.out.printf("Invalid option number. Please try again.\n");
            }
        }
        LM.writeAccount();
        LM.writeBooks();
        //sc.close();
    }
}