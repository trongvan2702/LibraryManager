import java.util.*;

class SortByRoles implements Comparator<User> {
    public int numberOfRole(User user) {
        if (user instanceof Admin) {
            return 1;
        } else if (user instanceof Librarian) {
            return 2;
        }
        return 3;
    }
    public int compare(User a, User b) {
        if (numberOfRole(a) > numberOfRole(b)) {
            return 1;
        } else if (numberOfRole(a) == numberOfRole(b)) {
            if (a.getName().compareTo(b.getName()) == 1) {
                return 1;
            }
            return -1;
        }
        return -1;
    }
}