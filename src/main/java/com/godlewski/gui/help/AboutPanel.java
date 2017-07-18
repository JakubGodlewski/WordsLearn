package com.godlewski.gui.help;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 07.06.2017.
 */
public class AboutPanel extends JPanel{

    JLabel lInfo1 = new JLabel("Words learning application");
    JLabel lInfo2 = new JLabel("Made as part of Km-programs course");
    JLabel lInfo3 = new JLabel("http://km-programs.pl/");
    JLabel lInfo4 = new JLabel("Author: Jakub Godlewski");
    JButton btnOk = new JButton("OK");

    public AboutPanel() {

        super(new BorderLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        mainPanel.add(lInfo1, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        mainPanel.add(lInfo2, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        mainPanel.add(lInfo3, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 3;
        mainPanel.add(lInfo4, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 4;
        mainPanel.add(btnOk, gbcMain);

        btnOk.addActionListener(e ->{
            JFrame frame = (JFrame)this.getRootPane().getParent();
            frame.dispose();
        });

        add(mainPanel);
    }
}
