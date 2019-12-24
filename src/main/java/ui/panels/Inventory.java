package ui.panels;

import domain.BrickingBad;
import domain.model.SpecificType;
import domain.model.powerup.PowerUp;
import ui.ImageFactory;
import utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Inventory extends JPanel implements ActionListener {

  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;

  private JButton magnetPaddleButton;
  private JButton tallerPaddleButton;
  private JButton chemicalBallButton;
  private JButton laserButton;

  private JLabel magnetLeftLabel;
  private JLabel tallerLeftLabel;
  private JLabel chemicalLeftLabel;
  private JLabel laserLeftLabel;

  public Inventory(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    this.magnetPaddleButton = new JButton(Constants.MAGNET_POWERUP_BUTTON);
    this.tallerPaddleButton = new JButton(Constants.TALLER_POWERUP_BUTTON);
    this.chemicalBallButton = new JButton(Constants.CHEMICAL_POWERUP_BUTTON);
    this.laserButton = new JButton(Constants.LASER_POWERUP_BUTTON);

    this.magnetLeftLabel = new JLabel(Constants.POWERUP_LEFT_DEFAULT);
    this.tallerLeftLabel = new JLabel(Constants.POWERUP_LEFT_DEFAULT);
    this.chemicalLeftLabel = new JLabel(Constants.POWERUP_LEFT_DEFAULT);
    this.laserLeftLabel = new JLabel(Constants.POWERUP_LEFT_DEFAULT);

    setLayout(null);
    setBackground(new Color(100, 100, 200));
    setPreferredSize(new Dimension(Constants.SIDE_BAR_WIDTH, Constants.SIDE_BAR_LENGTH));
    setFocusable(false);
    addButtons();
  }

  private void addButtons() {
    magnetPaddleButton.setBounds(
        Constants.POWERUP_X,
        Constants.MAGNET_POWERUP_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    magnetLeftLabel.setBounds(
        Constants.POWERUP_LEFT_X,
        Constants.MAGNET_LEFT_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    tallerPaddleButton.setBounds(
        Constants.POWERUP_X,
        Constants.TALLER_POWERUP_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    tallerLeftLabel.setBounds(
        Constants.POWERUP_LEFT_X,
        Constants.TALLER_LEFT_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    chemicalBallButton.setBounds(
        Constants.POWERUP_X,
        Constants.CHEMICAL_POWERUP_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    chemicalLeftLabel.setBounds(
        Constants.POWERUP_LEFT_X,
        Constants.CHEMICAL_LEFT_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    laserButton.setBounds(
        Constants.POWERUP_X,
        Constants.LASER_POWERUP_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    laserLeftLabel.setBounds(
        Constants.POWERUP_LEFT_X,
        Constants.LASER_LEFT_Y,
        Constants.POWERUP_WIDTH,
        Constants.POWERUP_LENGTH);

    magnetPaddleButton.addActionListener(this);
    tallerPaddleButton.addActionListener(this);
    chemicalBallButton.addActionListener(this);
    laserButton.addActionListener(this);

    magnetPaddleButton.setIcon(
        new ImageIcon(
            ImageFactory.getImageButton(
                SpecificType.MagnetPowerup,
                Constants.POWERUP_WIDTH,
                Constants.POWERUP_LENGTH)));
    magnetPaddleButton.setBorderPainted(false);
    magnetPaddleButton.setFocusPainted(false);
    magnetPaddleButton.setContentAreaFilled(false);


    tallerPaddleButton.setIcon(
        new ImageIcon(
            ImageFactory.getImageButton(
                SpecificType.TallerPaddlePowerup,
                Constants.POWERUP_WIDTH,
                Constants.POWERUP_LENGTH)));
    tallerPaddleButton.setBorderPainted(false);
    tallerPaddleButton.setFocusPainted(false);
    tallerPaddleButton.setContentAreaFilled(false);


    chemicalBallButton.setIcon(
        new ImageIcon(
            ImageFactory.getImageButton(
                SpecificType.ChemicalBallPowerup,
                Constants.POWERUP_WIDTH,
                Constants.POWERUP_LENGTH)));
    chemicalBallButton.setBorderPainted(false);
    chemicalBallButton.setFocusPainted(false);
    chemicalBallButton.setContentAreaFilled(false);

    laserButton.setIcon(
        new ImageIcon(
            ImageFactory.getImageButton(
                SpecificType.DestructiveLaserGun,
                Constants.POWERUP_WIDTH,
                Constants.POWERUP_LENGTH)));
    laserButton.setBorderPainted(false);
    laserButton.setFocusPainted(false);
    laserButton.setContentAreaFilled(false);


    magnetLeftLabel.setFont(Constants.POWERUP_FONT);
    tallerLeftLabel.setFont(Constants.POWERUP_FONT);
    chemicalLeftLabel.setFont(Constants.POWERUP_FONT);
    laserLeftLabel.setFont(Constants.POWERUP_FONT);

    add(magnetPaddleButton);
    add(magnetLeftLabel);
    add(tallerPaddleButton);
    add(tallerLeftLabel);
    add(chemicalBallButton);
    add(chemicalLeftLabel);
    add(laserButton);
    add(laserLeftLabel);
  }

  public void updatePowerups(List<PowerUp> pList, int laserCount) {
    int magentCount = 0;
    int tallerCount = 0;
    int chemicalCount = 0;
    for (PowerUp p : pList) {
      if (p.getSpecificType() == SpecificType.MagnetPowerup) magentCount++;
      if (p.getSpecificType() == SpecificType.TallerPaddlePowerup) tallerCount++;
      if (p.getSpecificType() == SpecificType.ChemicalBallPowerup) chemicalCount++;
    }
    magnetLeftLabel.setText(magentCount + " Left");
    tallerLeftLabel.setText(tallerCount + " Left");
    chemicalLeftLabel.setText(chemicalCount + " Left");
    laserLeftLabel.setText(laserCount + " Left");
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getActionCommand().equals(Constants.MAGNET_POWERUP_BUTTON)) {
      brickingBad.activateMagnet();
    }
    if (actionEvent.getActionCommand().equals(Constants.TALLER_POWERUP_BUTTON)) {
      brickingBad.activateTallerPaddle();
    }
    if (actionEvent.getActionCommand().equals(Constants.CHEMICAL_POWERUP_BUTTON)) {
      brickingBad.activateChemicalBall();
    }
    if (actionEvent.getActionCommand().equals(Constants.LASER_POWERUP_BUTTON)) {
      brickingBad.shootLaser();
    }
  }
}
