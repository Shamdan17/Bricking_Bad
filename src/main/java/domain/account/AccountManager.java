package domain.account;


import domain.storage.BinaryStorage;
import domain.storage.StorageManager;
import org.apache.log4j.Logger;

// AccountManager is responsible for authenticating users
public class AccountManager {

    final static Logger logger = Logger.getLogger(AccountManager.class);
    private StorageManager accounts;

    public static AccountManager accountManager = new AccountManager();

    public AccountManager() {
        accounts = (StorageManager) new BinaryStorage("account-manager-data");
        accounts.put("Hello", "World");
    }

    public void Register(String username, String password) {
        if (Account.isValid(username, password)) {
            accounts.put(username, password);
        }
    }

    public Account Authenticate(String Username, String Password) {
        String actualPass = (String) accounts.get(Username);
        if (actualPass.equals(Password)) {
            return new Account(Username, Password);
        }

        return null;
    }
}
