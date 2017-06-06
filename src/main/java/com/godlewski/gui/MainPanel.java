package com.godlewski.gui;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.User;
import com.godlewski.domain.UserWordCategoryLanguage;
import com.godlewski.domain.models.CustomTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jakub on 21.05.2017.
 */
public class MainPanel extends JPanel{

    private CustomTableModel customTableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private User user;
    private JButton btnAddWord = new JButton("Add word");
    private JButton btnEditWord = new JButton("Edit word");
    private JButton btnDeleteWord = new JButton("Delete word");

    public MainPanel(User user)
    {
        super(new BorderLayout());

        GridBagConstraints gbcMain = new GridBagConstraints();
        this.user = user;
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridx = 1200;
        customTableModel = new CustomTableModel(DatabaseInterfaceImpl.getInstance().selectUserWordCategoryLanguageByUserId(user.getId()));

        table = new JTable(customTableModel);
        scrollPane = new JScrollPane(table);
        //add(scrollPane, gbcMain);

        JPanel panelButtons = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        btnAddWord.addActionListener(e ->{
            JFrame frame = new JFrame("Add new word");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            AddNewWordPanel panel = new AddNewWordPanel(user, this);
            panel.setVisible(true);

            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.pack();
        });
        panelButtons.add(btnAddWord, gbcPosition);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        panelButtons.add(btnEditWord, gbcPosition);
        btnEditWord.addActionListener(e ->edit());

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        panelButtons.add(btnDeleteWord, gbcPosition);
        btnDeleteWord.addActionListener(e -> delete());

        JPanel panelMain = new JPanel(new GridBagLayout());

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelMain.add(scrollPane, gbcPosition);

        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
    }

    public void updateTable()
    {
        customTableModel.update(DatabaseInterfaceImpl.getInstance().selectUserWordCategoryLanguageByUserId(user.getId()));
        table.updateUI();
    }

    private void delete()
    {
        UserWordCategoryLanguage uwcl;
        int rowIndex;
        for(int i=0; i<table.getSelectedRows().length; i++)
        {
            rowIndex = table.getSelectedRows()[i];
            uwcl = customTableModel.getSelectedValue(rowIndex);
            DatabaseInterfaceImpl.getInstance().deleteUserWord(uwcl);
        }
        customTableModel.update(DatabaseInterfaceImpl.getInstance().selectUserWordCategoryLanguageByUserId(user.getId()));
        table.updateUI();
    }

    private void edit()
    {
        UserWordCategoryLanguage uwcl;
        java.util.List<UserWordCategoryLanguage> uwclList = new ArrayList<>();
        int rowIndex;
        for(int i=0; i<table.getSelectedRows().length; i++)
        {
            rowIndex = table.getSelectedRows()[i];
            uwcl = customTableModel.getSelectedValue(rowIndex);
            uwclList.add(uwcl);
        }

        if(!uwclList.isEmpty())
        {
            JFrame frame = new JFrame("Edit words");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            EditWordPanel panel = new EditWordPanel(uwclList, user, this);
            panel.setVisible(true);

            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.pack();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Select words from table");
        }

    }

}
