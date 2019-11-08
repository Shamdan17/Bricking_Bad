package domain;

import domain.account.Account;
import domain.account.AccountManager;

import java.util.ArrayList;
import java.util.List;

//BrickingBad is the main controller
public class BrickingBad {

    private List<PropertyListener> listeners = new ArrayList<PropertyListener>();
    private AccountManager accountManager = AccountManager.getInstance();
    private static BrickingBad brickingBad = new BrickingBad();

    public static BrickingBad getInstance(){
        return brickingBad;
    }

    public void addPropertyListener(PropertyListener pl){
        listeners.add(pl);
    }

    private void onPropertyEvent(String name, String value){
        for(PropertyListener pl : listeners){
            pl.onPropertyEvent("domain.GameEngine", name, value);
        }
    }

    public void loginAttempt(String username, String password){
        Account acc = accountManager.Authenticate(username, password);
        if(acc == null){
            onPropertyEvent("login", "failed");
        }else{
            onPropertyEvent("login", "success");
        }
    }
}
