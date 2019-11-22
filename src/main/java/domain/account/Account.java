package domain.account;

public class Account {
    private String username;
    private String password;
    /*
    TODO: More fields here. For example: Saved maps of the user
     */

    /*
    Constants
     */
    // Valid Password constants
    // min password size
    private static final int minSize = 8;
    // does the password need to have at least 1 char
    private static final boolean mustChar = true;
    // does the password need to have at least 1 digit
    private static final boolean mustNum = true;
    // does the password need to have at least 1 special char
    private static final boolean mustSpecialChar = false;

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
        int specialChars = 0, chars = 0, digits = 0;
        for (int i = 0; i < password.length(); i++) {
            char cur = password.charAt(i);
            if (Character.isDigit(cur)) {
                digits++;
            } else if (Character.isAlphabetic(cur)) {
                chars++;
            } else {
                specialChars++;
            }
        }
        // character count check
        if (mustChar && !(chars > 0)) {
            return false;
        }
        // digit count check
        if (mustNum && !(digits > 0)) {
            return false;
        }
        // special character count check
        if (mustSpecialChar && !(specialChars > 0)) {
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


