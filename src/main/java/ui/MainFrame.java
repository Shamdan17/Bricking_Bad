package ui;


import domain.BrickingBad;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private JPanel contPanel;
    private Menu mainMenu;
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

