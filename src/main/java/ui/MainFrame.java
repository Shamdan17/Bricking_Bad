package ui;


import domain.BrickingBad;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private static final String MAP_EDITOR_MODE = "Map Editor Mode";
    private static final String GAME_PLAY_MODE = "Game Play Mode";
    private static final int LENGTH = 500;
    private static final int WIDTH = 500;
    private JButton mapEditorButton;
    private JButton gamePlayButton;
    private JPanel contPanel;
    private JPanel mainPanel;
    private MapBuildPanel mapBuildPanel;
    private GamePanel gamePanel;
    private CardLayout cardLayout;
    private BrickingBad brickingBad;

    public MainFrame(BrickingBad bb) {

        brickingBad = bb;
        mapEditorButton = new JButton(MAP_EDITOR_MODE);
        gamePlayButton = new JButton(GAME_PLAY_MODE);
        contPanel = new JPanel();
        mainPanel = new JPanel(new GridBagLayout());
        mapBuildPanel = new MapBuildPanel(bb);
        gamePanel = new GamePanel(bb);
        cardLayout = new CardLayout();

        contPanel.setLayout(cardLayout);

        gamePanel.setFocusable(true);

        addKeyListeners();

        addPanelButtons();
        addBackButtons();

        contPanel.add(mainPanel, "main");
        contPanel.add(mapBuildPanel, "edit");
        contPanel.add(gamePanel, "game");
        cardLayout.show(contPanel, "main");
        setFocusable(true);
        setTitle("BrickingBad");
        add(contPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
        setFocusable(true);
    }

    void addPanelButtons(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(mapEditorButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(gamePlayButton);
    }

    void addKeyListeners(){
        gamePanel.addKeyListener(new MKeyListener(brickingBad));
        mapBuildPanel.addKeyListener(new MKeyListener(brickingBad));
        mainPanel.addKeyListener(new MKeyListener(brickingBad));
        contPanel.addKeyListener(new MKeyListener(brickingBad));
        this.addKeyListener(new MKeyListener(brickingBad));
    }

    void addBackButtons(){
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
    }
}

