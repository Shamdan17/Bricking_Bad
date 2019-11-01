package domain;

import java.util.ArrayList;
import java.util.List;

// LoginManager is responsible for authenticating users
public class LoginManager {
    // A list of all the accounts that currently exist
    private List<Account> accounts = new ArrayList<Account>();

    public Account Authenticate(String Username, String Password){
        for(Account acc : accounts){
            if(acc.getUsername().equals(Username)){
                if(acc.getPassword().equals(Password)){
                    return acc;
                }else{
                    return null;
                }
            }
        }
        return null;
    }


}
