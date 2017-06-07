package com.godlewski.gui;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 18.05.2017.
 */
public class LoginPanel extends JPanel {

    User user;

    private JButton btnLogin = new JButton("Login");
    private JButton btnRegister = new JButton("Register");
    private JButton btnCancel = new JButton("Cancel");

    private JLabel lLogin = new JLabel("Login");
    private JLabel lPassword = new JLabel("Password");

    private JTextField tfLogin = new JTextField(10);
    private JPasswordField pfPassword = new JPasswordField(10);

    public LoginPanel() {
        super(new GridBagLayout());

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelFields.add(lLogin, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelFields.add(tfLogin, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelFields.add(lPassword, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelFields.add(pfPassword, gbcPosition);

        JPanel panelButtons = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelButtons.add(btnLogin, gbcPosition);
        btnLogin.addActionListener(e ->login());

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelButtons.add(btnRegister, gbcPosition);
        btnRegister.addActionListener(e ->showRegisterWindow());

        gbcPosition.gridx = 2; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelButtons.add(btnCancel, gbcPosition);
        btnCancel.addActionListener(e ->{
            JFrame frame = (JFrame)this.getRootPane().getParent();
            frame.dispose();
        });

        JPanel panelMain = new JPanel(new GridBagLayout());

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelMain.add(panelFields, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
    }

    private void login()
    {
        this.user = DatabaseInterfaceImpl.getInstance().findUserByLogin(tfLogin.getText());

        if(user == null || !user.getPassword().equals(String.valueOf(pfPassword.getPassword())))
        {
            JOptionPane.showMessageDialog(null, "Invalid user and/or password!");
        }
        else
        {
            showMainWindow(user);
        }
    }

    private void showMainWindow(User user)
    {
        JFrame frame = new JFrame("Words learning application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel panel = new MainPanel(user);
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setJMenuBar(panel.createMenuBar());
        frame.pack();

        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }

    private void showRegisterWindow()
    {
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RegisterPanel panel = new RegisterPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();

        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }
}
