package ui.load;

import domain.BrickingBad;
import ui.panels.Game;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// This class shows Loading option for user

public class GameLoadPage extends JPanel implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contPanel;
    private BrickingBad brickingBad;

    private JButton loadButton;
    private JButton cancelButton;

    private JList saveJList;
    private JScrollPane scrollPane;

    private List<String> savedList;
    private String prevLabel;

    public GameLoadPage(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel, String prevLabel){
        this.brickingBad = brickingBad;
        this.cardLayout = cardLayout;
        this.contPanel = contPanel;
        this.prevLabel = prevLabel;
        this.loadButton = new JButton(Constants.LOAD_BUTTON);
        this.cancelButton = new JButton(Constants.CANCEL_BUTTON);

        loadButton.addActionListener(this);
        cancelButton.addActionListener(this);
        addButtons();
    }

    private void addButtons(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font(Constants.LOAD_MENU_FONT, Font.PLAIN, Constants.LOAD_MENU_FONT_SIZE);
        Dimension listSize = new Dimension(Constants.LOAD_MENU_WIDTH,Constants.LOAD_MENU_LENGTH);
        Dimension buttonSize = new Dimension(Constants.BUTTON_WIDTH,Constants.BUTTON_LENGTH);
        savedList = brickingBad.getGamesList();
        if(savedList.isEmpty()){
            JOptionPane.showMessageDialog(null, "No saves available");
        }
        saveJList = new JList(savedList.toArray());
        saveJList.setFont(font);
        scrollPane = new JScrollPane(saveJList);
        scrollPane.setPreferredSize(listSize);
        loadButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);
        gbc.gridy = 0;
        gbc.gridy = 0;
        add(scrollPane,gbc);
        gbc.gridy = 1;
        add(loadButton,gbc);
        gbc.gridy = 2;
        add(cancelButton,gbc);
    }

    public void handle(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals(Constants.CANCEL_BUTTON)){
            cardLayout.show(contPanel,prevLabel);
        }
        if(actionEvent.getActionCommand().equals(Constants.LOAD_BUTTON)){
            int index = saveJList.getSelectedIndex();
            if(index == -1){
                JOptionPane.showMessageDialog(null,"No Load was selected");
                return ;
            }
            Game game = new Game(brickingBad,cardLayout,contPanel);
            brickingBad.loadGame(savedList.get(index));
            contPanel.add(game,Constants.GAME_LABEL);
            cardLayout.show(contPanel,Constants.GAME_LABEL);
        }
    }
}
