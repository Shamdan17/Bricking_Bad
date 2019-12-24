package ui.save;

import domain.BrickingBad;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSavePage extends JPanel implements ActionListener {

  BrickingBad brickingBad;
  CardLayout cardLayout;
  JPanel contPanel;

  JButton cancelButton;
  JButton saveButton;
  JTextField textInput;

  public GameSavePage(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    this.saveButton = new JButton(Constants.SAVE_BUTTON);
    this.cancelButton = new JButton(Constants.CANCEL_BUTTON);
    this.textInput = new JTextField(Constants.ENTER_SAVE_NAME);

    saveButton.addActionListener(this);
    cancelButton.addActionListener(this);
    textInput.addActionListener(this);
    addButtons();
    setVisible(true);
  }

  private void addButtons(){
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    Dimension textInputSize = new Dimension(Constants.TEXT_INPUT_WIDTH,Constants.TEXT_INPUT_LENGTH);
    Dimension buttonSize = new Dimension(Constants.BUTTON_WIDTH,Constants.BUTTON_LENGTH);
    textInput.setPreferredSize(textInputSize);
    cancelButton.setPreferredSize(buttonSize);
    saveButton.setPreferredSize(buttonSize);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(textInput,gbc);
    gbc.gridy = 1;
    add(saveButton,gbc);
    gbc.gridy = 2;
    add(cancelButton,gbc);
  }

  public void handle() {

  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
      if(actionEvent.getActionCommand().equals(Constants.CANCEL_BUTTON)){
          cardLayout.show(contPanel,Constants.PAUSE_LABEL);
      }
      if(actionEvent.getActionCommand().equals(Constants.SAVE_BUTTON)){
          String name = textInput.getText();
          if(name == null){
              JOptionPane.showMessageDialog(null,"Save Name Empty");
              return;
          }
          brickingBad.saveGame(name);
          cardLayout.show(contPanel, Constants.PAUSE_LABEL);
      }
  }
}
