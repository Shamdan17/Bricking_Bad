package ui;


import domain.BrickingBad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private JButton mapEditorButton;
    private JButton gamePlayButton;
    private JPanel contPanel;
    private JPanel mainPanel;
    private MapBuildPanel mapBuildPanel;
    private GamePanel gamePanel;
    private CardLayout cardLayout;

    private static final String MAP_EDITOR_MODE = "Map Editor Mode";
    private static final String GAME_PLAY_MODE = "Game Play Mode";
    private static final int LENGTH = 500;
    private static final int WIDTH = 500;

    public MainFrame(BrickingBad bb) {

        mapEditorButton = new JButton(MAP_EDITOR_MODE);
        gamePlayButton = new JButton(GAME_PLAY_MODE);
        contPanel = new JPanel();
        mainPanel = new JPanel(new GridBagLayout());
        mapBuildPanel = new MapBuildPanel(bb);
        gamePanel = new GamePanel(bb);
        cardLayout = new CardLayout();

        contPanel.setLayout(cardLayout);

        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new MKeyListener(bb));
        mapBuildPanel.addKeyListener(new MKeyListener(bb));
        mainPanel.addKeyListener(new MKeyListener(bb));
        contPanel.addKeyListener(new MKeyListener(bb));
        this.addKeyListener(new MKeyListener(bb));

        setFocusable(true);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(mapEditorButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(gamePlayButton);

        mapBuildPanel.backToMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contPanel, "main");
                repaint();
            }
        });

        gamePanel.backToMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contPanel, "main");
                repaint();
            }
        });

        mapEditorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(contPanel, "edit");
                repaint();
            }
        });

        gamePlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(contPanel, "game");
                repaint();
            }
        });

        contPanel.add(mainPanel, "main");
        contPanel.add(mapBuildPanel, "edit");
        contPanel.add(gamePanel, "game");

        cardLayout.show(contPanel, "main");

        this.setTitle("BrickingBad");
        this.add(contPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 800);
        this.setVisible(true);
        this.setFocusable(true);


    }


}


class MKeyListener extends KeyAdapter {
    BrickingBad bb;

    public MKeyListener(BrickingBad bb) {
        this.bb = bb;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println("keyTyped: " + event.getKeyCode());

        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            bb.movePaddleRight();
        }

        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            bb.movePaddleLeft();
        }

        if (event.getKeyCode() == KeyEvent.VK_A) {
            bb.rotatePaddleLeft();
        }

        if (event.getKeyCode() == KeyEvent.VK_D) {
            bb.rotatePaddleRight();
        }

        if (event.getKeyCode() == KeyEvent.VK_T) {
            bb.activateTallerPaddle();
        }

        if (event.getKeyCode() == KeyEvent.VK_M) {
            bb.activateMagnet();
        }

        if (event.getKeyCode() == KeyEvent.VK_W) {
            bb.throwBall();
        }


        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            bb.shootLaser();
        }
    }
}

