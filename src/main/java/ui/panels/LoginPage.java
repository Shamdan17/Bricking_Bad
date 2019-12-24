package ui.panels;

import domain.BrickingBad;
import ui.panels.MainMenu;
import utils.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.CharArrayReader;

public class LoginPage extends JPanel implements ActionListener {

  // TODO: complete

  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;

  private JTextField usernameField;
  private JPasswordField passwordField;
  private JLabel usernameLabel;
  private JLabel passwordLabel;

  private JButton loginButton;
  private JButton createAccountButton;
  private JButton exitButton;

  public LoginPage(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    this.usernameField = new JTextField();
    this.passwordField = new JPasswordField();
    this.usernameLabel = new JLabel(Constants.USERNAME_LABEL);
    this.passwordLabel = new JLabel(Constants.PASSWORD_LABEL);
    this.exitButton = new JButton(Constants.EXIT_BUTTON);
    this.loginButton = new JButton(Constants.LOGIN_BUTTON);
    this.createAccountButton = new JButton(Constants.CREATE_ACCOUNT_BUTTON);
    setup();
  }

  private void setup(){
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      Dimension fieldSize = new Dimension(Constants.LOGIN_INPUT_WIDTH,Constants.LOGIN_INPUT_LENGTH);
      usernameField.setPreferredSize(fieldSize);
      passwordField.setPreferredSize(fieldSize);
      usernameLabel.setFont(Constants.DEFAULT_FONT);
      passwordLabel.setFont(Constants.DEFAULT_FONT);
      loginButton.addActionListener(this);
      createAccountButton.addActionListener(this);
      exitButton.addActionListener(this);

      loginButton.setPreferredSize(fieldSize);
      createAccountButton.setPreferredSize(fieldSize);
      exitButton.setPreferredSize(fieldSize);
      JLabel intro = new JLabel("BrickingBad");
      intro.setFont(Constants.VERY_BIG_FONT);
      intro.setBounds(0,0,300,300);

//      gbc.gridheight = 4;
//      gbc.gridwidth = 2;

      gbc.gridx = 1;
      gbc.gridy = 0;
      add(intro, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(usernameLabel,gbc);
      gbc.gridx = 1;
      add(usernameField,gbc);
      gbc.gridx = 0;
      gbc.gridy = 2;
      add(passwordLabel,gbc);
      gbc.gridx = 1;
      add(passwordField,gbc);
      gbc.gridx = 1;
      gbc.gridy = 3;
      add(loginButton,gbc);
      gbc.gridy = 4;
      add(createAccountButton,gbc);
      gbc.gridy = 5;
      add(exitButton,gbc);

  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
      if(actionEvent.getActionCommand().equals(Constants.LOGIN_BUTTON)){
          String username = usernameField.getText();
          String password = passwordField.getText();
          boolean success = brickingBad.loginAttempt(username,password);
          if(!success){
              JOptionPane.showMessageDialog(null,"Login Unsuccessful");
              return;
          }
          MainMenu menu = new MainMenu(brickingBad,cardLayout,contPanel);
          contPanel.add(menu,Constants.MENU_LABEL);
          cardLayout.show(contPanel,Constants.MENU_LABEL);
      }
      if(actionEvent.getActionCommand().equals(Constants.CREATE_ACCOUNT_BUTTON)){
          String username = usernameField.getText();
          String password = passwordField.getText();
          boolean success = brickingBad.creatAccount(username,password);
          if(!success){
              JOptionPane.showMessageDialog(null,"Creating Account Failed");
              return;
          }
          JOptionPane.showMessageDialog(null,"AccountCreationSuccessful");
      }
      if(actionEvent.getActionCommand().equals(Constants.EXIT_BUTTON)){
          System.exit(0);
      }
  }
}
