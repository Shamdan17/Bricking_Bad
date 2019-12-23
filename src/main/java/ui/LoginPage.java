package ui;

import domain.BrickingBad;
import domain.model.brick.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {

    // TODO: complete

    private BrickingBad brickingBad;
    private CardLayout cardLayout;
    private JPanel contPanel;

    private JTextField userNameField;
    private JTextField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginPage(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel){
        this.brickingBad = brickingBad;
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
