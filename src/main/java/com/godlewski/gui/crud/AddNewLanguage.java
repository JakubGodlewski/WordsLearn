package com.godlewski.gui.crud;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.Language;
import com.godlewski.gui.interfaces.AfterInsertPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 27.05.2017.
 */
public class AddNewLanguage extends JPanel {

    private JLabel lName = new JLabel("Name");
    private JTextField tfName = new JTextField(10);
    private JButton btnAdd = new JButton("Add");
    private JButton btnCancel = new JButton("Cancel");

    private AfterInsertPanel panel;

    public AddNewLanguage(AfterInsertPanel panel) {
        super(new GridBagLayout());
        this.panel = panel;

        JPanel panelMain = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelMain.add(lName, gbcPosition);
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelMain.add(tfName, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelMain.add(btnAdd, gbcPosition);
        btnAdd.addActionListener(e -> add());
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelMain.add(btnCancel, gbcPosition);
        btnCancel.addActionListener(e ->
        {
            JFrame thisFrame = (JFrame)this.getRootPane().getParent();
            thisFrame.dispose();
        });

        add(panelMain);
    }

    private void add()
    {
        String name = tfName.getText();
        if(name.equals(""))
            JOptionPane.showMessageDialog(null, "Invalid language name");
        else
        {
            Language language = new Language(0, tfName.getText());
            DatabaseInterfaceImpl.getInstance().insertLanguage(language);
            panel.afterInsertLanguage(DatabaseInterfaceImpl.getInstance().getLanguageByName(language.getLanguageName()));

            JOptionPane.showMessageDialog(null, "New language added to database");
            JFrame thisFrame = (JFrame)this.getRootPane().getParent();
            thisFrame.dispose();
        }
    }
}
