package ui.load;

import domain.BrickingBad;

import javax.swing.*;
import java.awt.*;

// This class shows Loading option for user

public class GameLoadPage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contPanel;
    private BrickingBad brickingBad;

    public GameLoadPage(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel){
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;
    }

}
