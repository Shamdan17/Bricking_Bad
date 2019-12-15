package domain.account;

public class Account {
    private String username;
    private String password;
    /*
     * TODO: More fields here. For example: Saved maps of the user
     */

    /*
     * Constants
     */

    // minimum password size
    private static final int minSize = 8;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean isValid(String username, String password) {
        return isValidPassword(password) && isValidUsername(username);
    }

    public static boolean isValid(Account acc) {
        return isValid(acc.username, acc.password);
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < minSize) {
            return false;
        }

        return true;
    }

    private static boolean isValidUsername(String username) {
        return username.length() > 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
