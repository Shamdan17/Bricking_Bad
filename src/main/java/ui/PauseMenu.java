package ui;

import domain.BrickingBad;
import ui.load.GameLoadPage;
import ui.save.GameSavePage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JPanel implements ActionListener {

  public JButton menuButton;
  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;
  private JButton saveButton;
  private JButton loadButton;
  private JButton gameButton;

  public PauseMenu(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    this.saveButton = new JButton(Constants.SAVE_BUTTON);
    this.loadButton = new JButton(Constants.LOAD_BUTTON);
    this.menuButton = new JButton(Constants.MENU_BUTTON);
    this.gameButton = new JButton(Constants.GAME_BUTTON);

    menuButton.addActionListener(this);
    saveButton.addActionListener(this);
    loadButton.addActionListener(this);
    gameButton.addActionListener(this);

    addButtons();
  }

  void addButtons() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(gameButton);
    gbc.gridx = 1;
    this.add(saveButton);
    gbc.gridx = 2;
    this.add(loadButton);
    gbc.gridx = 3;
    this.add(menuButton);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
      if(actionEvent.getActionCommand().equals(Constants.GAME_BUTTON)){
          cardLayout.show(contPanel,Constants.GAME_LABEL);
      }
      if(actionEvent.getActionCommand().equals(Constants.SAVE_BUTTON)){
          GameSavePage savePage = new GameSavePage(brickingBad,cardLayout,contPanel);
          contPanel.add(savePage,Constants.GAME_SAVE_LABEL);
          cardLayout.show(contPanel,Constants.GAME_SAVE_LABEL);
      }
      if(actionEvent.getActionCommand().equals(Constants.LOAD_BUTTON)){
          GameLoadPage loadPage = new GameLoadPage(brickingBad,cardLayout,contPanel);
          contPanel.add(loadPage,Constants.GAME_LOAD_LABEL);
          cardLayout.show(contPanel,Constants.GAME_LOAD_LABEL);
      }
      if(actionEvent.getActionCommand().equals(Constants.MENU_BUTTON)){
          cardLayout.show(contPanel,Constants.MENU_LABEL);
      }
  }
}
