package com.godlewski.gui;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 21.05.2017.
 */
public class RegisterPanel extends JPanel{

    private JButton btnRegister = new JButton("Register");
    private JButton btnClear = new JButton("Clear");
    private JButton btnCancel = new JButton("Cancel");

    private JLabel lLogin = new JLabel("Login");
    private JLabel lName = new JLabel("Name");
    private JLabel lSurname = new JLabel("Surname");
    private JLabel lEMail = new JLabel("E-mail");
    private JLabel lPassword = new JLabel("Password");
    private JLabel lRepeatPassword = new JLabel("Repeat Password");
    private JLabel lPhone = new JLabel("Phone");

    private JTextField tfLogin = new JTextField(10);
    private JTextField tfName = new JTextField(10);
    private JTextField tfSurname = new JTextField(10);
    private JTextField tfEMail = new JTextField(10);
    private JPasswordField pfPassword = new JPasswordField(10);
    private JPasswordField pfRepeatPassword = new JPasswordField(10);
    private JTextField tfPhone = new JTextField(10);

    public RegisterPanel()
    {
        super();

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
        panelFields.add(lName, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelFields.add(tfName, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 2; //wiersz
        panelFields.add(lSurname, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 2; //wiersz
        panelFields.add(tfSurname, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 3; //wiersz
        panelFields.add(lEMail, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 3; //wiersz
        panelFields.add(tfEMail, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 4; //wiersz
        panelFields.add(lPassword, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 4; //wiersz
        panelFields.add(pfPassword, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 5; //wiersz
        panelFields.add(lRepeatPassword, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 5; //wiersz
        panelFields.add(pfRepeatPassword, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 6; //wiersz
        panelFields.add(lPhone, gbcPosition);

        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 6; //wiersz
        panelFields.add(tfPhone, gbcPosition);

        JPanel panelButtons = new JPanel(new GridBagLayout());

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelButtons.add(btnRegister, gbcPosition);
        btnRegister.addActionListener(e ->register());

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelButtons.add(btnClear, gbcPosition);
        btnClear.addActionListener(e -> clear());

        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelButtons.add(btnCancel, gbcPosition);
        btnCancel.addActionListener(e -> close());

        JPanel panelMain = new JPanel(new GridBagLayout());

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelMain.add(panelFields, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
    }

    private void register()
    {
        if(tfLogin.getText().isEmpty()||tfEMail.getText().isEmpty()||tfName.getText().isEmpty()||tfSurname.getText().isEmpty()||tfPhone.getText().isEmpty()||pfPassword.getPassword().length==0)
        {
            JOptionPane.showMessageDialog(null, "Invalid values");
        }
        else if(!String.valueOf(pfPassword.getPassword()).equals(String.valueOf(pfRepeatPassword.getPassword())))
        {
            JOptionPane.showMessageDialog(null, "Passwords aren't the same");
        }
        else if(DatabaseInterfaceImpl.getInstance().findUserByLogin(tfLogin.getText())!=null)
        {
            JOptionPane.showMessageDialog(null, "The user already exists in database");
        }
        else
        {
            User user = new User(0, tfLogin.getText(), tfName.getText(), tfSurname.getText(), tfEMail.getText(), String.valueOf(pfPassword.getPassword()), tfPhone.getText());
            DatabaseInterfaceImpl.getInstance().insertUser(user);
            JOptionPane.showMessageDialog(null, "user added to database");
            clear();
        }
    }

    private void clear()
    {
        tfLogin.setText("");
        tfName.setText("");
        tfSurname.setText("");
        tfEMail.setText("");
        tfPhone.setText("");
        pfPassword.setText("");
        pfRepeatPassword.setText("");
    }
    private void close()
    {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginPanel panel = new LoginPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();

        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }
}
