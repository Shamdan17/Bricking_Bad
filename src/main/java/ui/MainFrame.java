package ui;


import domain.BrickingBad;
import ui.panels.MainMenu;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contPanel;
    private MainMenu mainMenu;
    private CardLayout cardLayout;
    private BrickingBad brickingBad;

    public MainFrame(BrickingBad bb) {

        brickingBad = bb;
        contPanel = new JPanel();
        cardLayout = new CardLayout();
        contPanel.setLayout(cardLayout);
        mainMenu = new MainMenu(brickingBad,cardLayout,contPanel);

        contPanel.add(mainMenu, Constants.MENU_LABEL);
        cardLayout.show(contPanel, Constants.MENU_LABEL);
        this.addKeyListener(new MKeyListener(brickingBad));
        setFocusable(true);
        setTitle("BrickingBad");
        add(contPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(Constants.MAX_X,Constants.MAX_Y);
        setVisible(true);
        setFocusable(true);
    }

}

