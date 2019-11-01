package domain;


import java.util.ArrayList;
import java.util.List;

// Game Engine is the controller
public class GameEngine {
    private List<PropertyListener> listeners = new ArrayList<PropertyListener>();

    public void addPropertyListener(PropertyListener pl){
        listeners.add(pl);
    }

    public void onPropertyEvent(String name, String value){
        for(PropertyListener pl : listeners){
            pl.onPropertyEvent("domain.GameEngine", name, value);
        }
    }

    public void loginAttempt(String username, String password){

    }
}
