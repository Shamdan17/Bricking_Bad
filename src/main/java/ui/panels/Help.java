package ui.panels;

import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JPanel implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contPanel;
    private JButton backToMenu;

    private JTextArea helpInfo;

    public Help(CardLayout cardLayout, JPanel contPanel) {
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;
        this.backToMenu = new JButton(Constants.MENU_BUTTON);
        this.helpInfo = new JTextArea("Welcome to BrickingBad a fun and challenging games! \n" +
                "In this game you will have a set of bricks which you will need to break all of them to win! \n" +
                "But it’s not that easy, there will be some aliens preventing you from reaching your goal, and simultaneously, \n" +
                "some power-ups to help you defeat the aliens and win the game." +
                "\n\n\n" +
                "As a player, you control a paddle. This paddle can move left and right, and also rotate left and right. \n" +
                "Also, there is a ball floating around hitting stuff, and using the paddle you should prevent the ball from falling down. \n" +
                "At the start of the game, you have 3 lives. Each time the ball falls down beyond the paddle you lose 1 life, \n" +
                "and the ball respawns on the paddle. You lose the game when you run out of lives or the time finishes. \n" +
                "You have 10 minutes to finish the game. You win when you destroy all the bricks. " +
                "\n\n\n" +
                "Bricks\n" +
                "Simple Brick:\t\tA brick which can be broken in one hit from anywhere\n" +
                "Half-Metal Brick:\tA brick can be broken by only hit from top of the brick\n" +
                "Mine Brick:\t\tCircular explosive bricks, destroys the neighbor area when it’s hit\n" +
                "Wrapper Brick:\t\tA simple brick with power-up or alien inside\n" +
                "\n\n\n" +
                "Aliens\n" +
                "Repairing Alien: \tBuild a simple brick each five seconds\n" +
                "Protecting Alien: \tProtect bricks by moving horizontally\n" +
                "Cooperative Alien: \tRandomly destroys a row of bricks\n" +
                "Drunk Alien: \t\tCan act as any type of alien depending of the remaining bricks\n" +
                "\n\n\n" +
                "Power-Ups\n" +
                "Taller Paddle:\tDoubles the length of the paddle, activated by T, lasts for 30 seconds \n" +
                "Magnet: \tCatches the ball, activated by M, ball can be thrown with W\n" +
                "Laser: \tDestroys bricks by shooting a laser gun, activated by SPACE\n" +
                "Fireball: \tDestroys the brick and its neighbors. Can destroy half-metal bricks by two hits\n" +
                "Chemical Ball: \tDestroys any bricks and doesn’t reflect after hit, activated by C, lasts for 1min\n" +
                "Gang-of-Balls: \tCreates multiple balls \n" +
                "\n\n\n" +
                "Keys:\n\n" +
                "<-: \tMove Paddle Right\n" +
                "->: \tMove Paddle Left\n" +
                "A: \tRotate paddle left\n" +
                "D: \tRotate paddle Right\n" +
                "T: \tAcitvate taller paddle powerup\n" +
                "M: \tActivate magnet paddle powerup\n" +
                "W: \tThrow the ball\n" +
                "SPACE: \tFire Laser\n" +
                "C: \tActivate chemical ball\n" +
                "\n\n\n\n");
        helpInfo.setFont(Constants.DEFAULT_FONT);
        helpInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(helpInfo);
        scrollPane.setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        backToMenu.addActionListener(this);
        add(scrollPane);
        add(backToMenu);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(Constants.MENU_BUTTON)) {
            cardLayout.show(contPanel, Constants.MENU_LABEL);
        }
    }
}
