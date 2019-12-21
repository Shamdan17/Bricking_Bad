package domain.game;

import domain.model.SpecificType;
import domain.model.powerup.PowerUp;

import java.util.ArrayList;

public class Inventory {
    private Board board;
    private ArrayList<PowerUp> powerUps;

    public Inventory(Board b) {
        powerUps = new ArrayList<>();
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


}
