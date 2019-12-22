package ui;

import domain.BrickingBad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JPanel implements ActionListener {

  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;

  public PauseMenu(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {}
}
