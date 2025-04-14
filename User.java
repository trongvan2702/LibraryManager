abstract public class User {
    private String userName;
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public void setName(String userName) {
        this.userName = userName;
    }
    public String getName(){
        return userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    abstract public String toString();
    abstract public void displayMenu();
}
