package ui.load;

import domain.BrickingBad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapLoadPage extends JPanel implements ActionListener {

    BrickingBad brickingBad;
    CardLayout cardLayout;
    JPanel contPanel;

    public MapLoadPage(BrickingBad brickingaBad, CardLayout cardLayout, JPanel contPanel){
        this.brickingBad = brickingaBad;
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;

    }

    public void handle(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
