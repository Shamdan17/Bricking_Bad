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
        for (PowerUp p : powerUps) {
            if (p.activate(type, board)) {
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
