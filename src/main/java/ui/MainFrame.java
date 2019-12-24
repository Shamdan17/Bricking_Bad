package ui;


import domain.BrickingBad;
import ui.panels.LoginPage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contPanel;
    //    private MainMenu mainMenu;
    private CardLayout cardLayout;
    private BrickingBad brickingBad;
    private LoginPage loginPage;

    public MainFrame(BrickingBad bb) {

        brickingBad = bb;
        contPanel = new JPanel();
        cardLayout = new CardLayout();
        contPanel.setLayout(cardLayout);

        loginPage = new LoginPage(brickingBad, cardLayout, contPanel);
        contPanel.add(loginPage, Constants.LOGIN_LABEL);
        cardLayout.show(contPanel, Constants.LOGIN_LABEL);

        this.addKeyListener(new MKeyListener(brickingBad));
        setFocusable(true);
        setTitle("BrickingBad");
        add(contPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setBackground(new Color(12, 17, 60));
        setSize(Constants.MAX_X, Constants.MAX_Y);
        setVisible(true);
        setFocusable(true);
    }

}

