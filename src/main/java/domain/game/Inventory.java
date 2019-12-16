package domain.game;

import domain.model.SpecificType;
import domain.model.powerup.PowerUp;

import java.util.ArrayList;

public class Inventory {
    private Board board;
    private ArrayList<PowerUp> powerUps;

    public Inventory(Board b) {
        this.board = b;
    }

    // Adds a powerup to the given powerups
    public void addPowerup(PowerUp p) {
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
}
