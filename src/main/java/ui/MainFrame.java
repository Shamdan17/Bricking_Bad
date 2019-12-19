package ui;


import domain.BrickingBad;
import ui.panels.Menu;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contPanel;
    private ui.panels.Menu mainMenu;
    private CardLayout cardLayout;
    private BrickingBad brickingBad;

    public MainFrame(BrickingBad bb) {

        brickingBad = bb;
        contPanel = new JPanel();
        cardLayout = new CardLayout();
        contPanel.setLayout(cardLayout);
        mainMenu = new Menu(brickingBad,cardLayout,contPanel);

        contPanel.add(mainMenu, Constants.MENU_LABEL);
        cardLayout.show(contPanel, Constants.MENU_LABEL);
        this.addKeyListener(new MKeyListener(brickingBad));
        setFocusable(true);
        setTitle("BrickingBad");
        add(contPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
        setFocusable(true);
    }



}

