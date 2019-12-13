package domain.account;


import domain.storage.BinaryStorage;
import domain.storage.StorageManager;
import org.apache.log4j.Logger;

/** AccountManager is responsible for adding, authenticating and removing users
 * 
 */
public class AccountManager {
    
    /** Instance of the accounts store (database) */
    private StorageManager accounts;
    
    
    /** Creates an AccountManager with the specified name.
     * @param dataName  The name of the saved accounts entry on the storage layer
     */
    public AccountManager(String dataName) throws IllegalArgumentException {
    	if (dataName == null) {
    		throw new IllegalArgumentException("data name cannot be null");
    	}
    
        accounts = (StorageManager) new BinaryStorage(dataName);
        accounts.put("admin", "admin");
    }
    
    
    /**
     * Adds a new account 
     * @param username The username of the new account (must be uniqe)
     * @param password The password of the new account
     * TODO: Turn password into password hash, to avoid storing actual passwords in the storage layer
     */
    public void Register(String username, String password) {
    	if(username == null || password == null) {
    		throw new IllegalArgumentException("cannot register sername or password with null");
    	}
    	
        if (Account.isValid(username, password) && accounts.contains(username)) {
            accounts.put(username, password);
        }
    }

    
    /**
     * Verifies that the username and password matches
     * @param username
     * @param password
     * @return an instance of the user data 
     * TODO: Do not return an instance of the user data, should be a boolean, or an info collection
     */
    public Account Authenticate(String username, String password) {
    	if(username == null || password == null) {
    		throw new IllegalArgumentException("cannot authenticate username or password with null");
    	}
    	
    	if(!accounts.contains(username)) {
    		// Maybe replace null return with exception or false boolean
    		return null;
    	}
    	
    	
        String actualPass = (String) accounts.get(username);
        if (actualPass.equals(password)) {
            return new Account(username, password);
        }
        
        return null;
    }
}
