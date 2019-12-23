package domain.game;

import domain.model.SpecificType;
import domain.model.powerup.PowerUp;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Board board;
    private List<PowerUp> powerUps;

    public Inventory(Board b) {
        powerUps = new ArrayList<>();
        this.board = b;
    }

    public Inventory(Board b, List<PowerUp> pList){
        this.powerUps = pList;
        this.board = b;
    }

    // Adds a powerup to the given powerups
    public void addPowerup(PowerUp p) {
        if (p.isInstant()) {
            p.activate(p.getSpecificType(), board);
            return;
        }
        powerUps.add(p);
    }

    // Activates one powerup of the given specific type
    public void activatePowerup(SpecificType type) {
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp p = powerUps.get(i);
            if (p.activate(type, board)) {
                powerUps.remove(i);
                return;
            }
        }
    }

    public void removePowerup(SpecificType type) {
        powerUps.removeIf(powerUp -> {
            if (powerUp.getSpecificType() == type)
                return true;
            return false;
        });
    }

    public List<PowerUp> getPowerupList(){
        return powerUps;
    }


}
