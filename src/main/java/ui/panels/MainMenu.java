package ui.panels;

import domain.BrickingBad;
import ui.MKeyListener;
import ui.load.GameLoadPage;
import ui.load.MapLoadPage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {

  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;

  private MapBuild mapBuildPanel;
  private Help help;

  private JButton startGameButton;
  private JButton loadGameButton;
  private JButton helpButton;
  private JButton exitButton;
  private JButton logoutButton;
  // this class represents the main menu

  public MainMenu(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    this.logoutButton = new JButton(Constants.LOGOUT_BUTTON);
    this.startGameButton = new JButton(Constants.START_GAME_BUTTON);
    this.loadGameButton = new JButton(Constants.LOAD_BUTTON);
    this.helpButton = new JButton(Constants.HELP_BUTTON);
    this.exitButton = new JButton(Constants.EXIT_BUTTON);
    this.addKeyListener(new MKeyListener(brickingBad));

    addButtons();

    startGameButton.addActionListener(this);
    loadGameButton.addActionListener(this);
    helpButton.addActionListener(this);
    exitButton.addActionListener(this);
    logoutButton.addActionListener(this);
  }

  // here add buttons to main menu
  private void addButtons() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    Dimension size = new Dimension(Constants.MAIN_MENU_BUTTON_WIDTH,Constants.MAIN_MENU_BUTTON_LENGTH);
    startGameButton.setPreferredSize(size);
    loadGameButton.setPreferredSize(size);
    helpButton.setPreferredSize(size);
    exitButton.setPreferredSize(size);
    logoutButton.setPreferredSize(size);
    gbc.gridy = 0;
    gbc.gridx = 0;
    add(startGameButton,gbc);
    gbc.gridy = 1;
    add(loadGameButton,gbc);
    gbc.gridy = 2;
    add(helpButton,gbc);
    gbc.gridy = 3;
    add(exitButton,gbc);
    gbc.gridy = 4;
    add(logoutButton,gbc);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    if (actionEvent.getActionCommand().equals(Constants.START_GAME_BUTTON)) {
      mapBuildPanel = new MapBuild(brickingBad, cardLayout, contPanel);
      contPanel.add(mapBuildPanel, Constants.MAP_BUILD_LABEL);
      cardLayout.show(contPanel, Constants.MAP_BUILD_LABEL);
    }
    if (actionEvent.getActionCommand().equals(Constants.LOAD_BUTTON)) {
      GameLoadPage loadPage = new GameLoadPage(brickingBad, cardLayout, contPanel,Constants.MENU_LABEL);
      contPanel.add(loadPage,Constants.GAME_LOAD_LABEL);
      cardLayout.show(contPanel,Constants.GAME_LOAD_LABEL);
    }
    if (actionEvent.getActionCommand().equals(Constants.HELP_BUTTON)) {
      help = new Help(cardLayout, contPanel);
      contPanel.add(help, Constants.HELP_LABEL);
      cardLayout.show(contPanel, Constants.HELP_LABEL);
    }
    if(actionEvent.getActionCommand().equals(Constants.LOGOUT_BUTTON)){
        cardLayout.show(contPanel,Constants.LOGIN_LABEL);
    }
    if (actionEvent.getActionCommand().equals(Constants.EXIT_BUTTON)) {
      System.exit(0);
    }
  }
}
