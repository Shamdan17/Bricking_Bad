package ui.load;

import domain.BrickingBad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class shows Loading option for user

public class GameLoadPage extends JPanel implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contPanel;
    private BrickingBad brickingBad;

    public GameLoadPage(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel){
        this.brickingBad = brickingBad;
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;

        handle();
    }

    public void handle(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
