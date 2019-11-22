package domain.account;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

// AccountManager is responsible for authenticating users
public class AccountManager {

    final static Logger logger = Logger.getLogger(AccountManager.class);

    // A list of all the accounts that currently exist
    private List<Account> accounts = new ArrayList<Account>();

    public static AccountManager accountManager = new AccountManager();

    public AccountManager() {
        accounts.add(new Account("Hello", "World"));
    }

    public void Register(String username, String password) {
        if (Account.isValid(username, password)) {
            accounts.add(new Account(username, password));
        }
    }

    public Account Authenticate(String Username, String Password) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(Username)) {
                if (acc.getPassword().equals(Password)) {
                    logger.info("User " + acc.getUsername() + " successfully logged in");
                    return acc;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
