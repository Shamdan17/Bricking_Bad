package ui.panels;

import domain.BrickingBad;
import ui.MKeyListener;
import ui.load.GameLoadPage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {

  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;

  private MapBuild mapBuildPanel;
  private Help help;
  private GameLoadPage loadPage;

  private JButton startGameButton;
  private JButton loadGameButton;
  private JButton helpButton;
  private JButton exitButton;
  // this class represents the main menu

  public Menu(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

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
  }

  // here add buttons to main menu
  void addButtons() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(startGameButton);
    gbc.gridy = 1;
    add(loadGameButton);
    gbc.gridy = 2;
    add(helpButton);
    gbc.gridy = 3;
    add(exitButton);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    if (actionEvent.getActionCommand().equals(Constants.START_GAME_BUTTON)) {
      mapBuildPanel = new MapBuild(brickingBad, cardLayout, contPanel);
      contPanel.add(mapBuildPanel, Constants.MAP_BUILD_LABEL);
      cardLayout.show(contPanel, Constants.MAP_BUILD_LABEL);
    }
    if (actionEvent.getActionCommand().equals(Constants.LOAD_BUTTON)) {
      loadPage = new GameLoadPage(brickingBad, cardLayout, contPanel);
      // TODO: modify this after having proper load save mechanisms
      brickingBad.loadGame();
    }
    if (actionEvent.getActionCommand().equals(Constants.HELP_BUTTON)) {
      help = new Help(cardLayout, contPanel);
      contPanel.add(help, Constants.HELP_LABEL);
      cardLayout.show(contPanel, Constants.HELP_LABEL);
    }
    if (actionEvent.getActionCommand().equals(Constants.EXIT_BUTTON)) {
      System.exit(0);
    }
  }
}
