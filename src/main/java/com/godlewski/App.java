package com.godlewski;

import com.godlewski.dao.DatabaseInterface;
import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.gui.login.LoginPanel;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void createWindow() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginPanel panel = new LoginPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.pack();
    }


    public static void main( String[] args )
    {
        DatabaseInterface databaseInterface = DatabaseInterfaceImpl.getInstance();

        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        createWindow();
                    }
                }
        );

    }
}
