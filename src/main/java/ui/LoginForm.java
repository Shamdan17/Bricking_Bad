package ui;

import domain.BrickingBad;
import domain.GameEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginForm extends JFrame {

    private static BrickingBad brickingBad = new BrickingBad();
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Create the frame.
     */
    public LoginForm() {
        setTitle("Login");
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 236, 142);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{71, 122, 0};
        gbl_contentPane.rowHeights = new int[]{22, 22, 25, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel usernameLabel = new JLabel("Username");
        GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
        gbc_usernameLabel.anchor = GridBagConstraints.WEST;
        gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_usernameLabel.gridx = 0;
        gbc_usernameLabel.gridy = 0;
        contentPane.add(usernameLabel, gbc_usernameLabel);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        contentPane.add(textField, gbc_textField);
        textField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password");
        GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
        gbc_passwordLabel.anchor = GridBagConstraints.WEST;
        gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordLabel.gridx = 0;
        gbc_passwordLabel.gridy = 1;
        contentPane.add(passwordLabel, gbc_passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 1;
        contentPane.add(passwordField, gbc_passwordField);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        GridBagConstraints gbc_cancelBtn = new GridBagConstraints();
        gbc_cancelBtn.anchor = GridBagConstraints.WEST;
        gbc_cancelBtn.insets = new Insets(0, 0, 0, 5);
        gbc_cancelBtn.gridx = 0;
        gbc_cancelBtn.gridy = 2;
        contentPane.add(cancelBtn, gbc_cancelBtn);

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (brickingBad.loginAttempt(username, password)) {
                  System.out.println("attempt successed");
                } else {
                  System.out.println("attempt failed");
                }
            }
        });
        GridBagConstraints gbc_confirmBtn = new GridBagConstraints();
        gbc_confirmBtn.anchor = GridBagConstraints.EAST;
        gbc_confirmBtn.fill = GridBagConstraints.VERTICAL;
        gbc_confirmBtn.gridx = 1;
        gbc_confirmBtn.gridy = 2;
        contentPane.add(confirmBtn, gbc_confirmBtn);
    }
}
