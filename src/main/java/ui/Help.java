package ui;

import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JPanel implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contPanel;
    private JButton backToMenu;

    private JLabel helpInfo;

    public Help(CardLayout cardLayout , JPanel contPanel){
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;
        this.backToMenu = new JButton(Constants.MENU_BUTTON);
        this.helpInfo = new JLabel("No Help Available now");
        add(helpInfo,BorderLayout.CENTER);
        add(backToMenu);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals(Constants.MENU_BUTTON)){
            cardLayout.show(contPanel,Constants.MENU_LABEL);
        }
    }
}
