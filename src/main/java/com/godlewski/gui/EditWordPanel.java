package com.godlewski.gui;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.*;
import com.godlewski.gui.interfaces.AfterInsertPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 02.06.2017.
 */
public class EditWordPanel extends JPanel implements AfterInsertPanel{

    private JLabel lId = new JLabel("id");
    private JLabel lName = new JLabel("Name:");
    private JLabel lTranslation = new JLabel("Translation:");
    private JLabel lCategory = new JLabel("Category:");
    private JLabel lLanguage = new JLabel("Language:");

    private JTextField tfId = new JTextField(2);
    private JTextField tfName = new JTextField(10);
    private JTextField tfTranslation = new JTextField(10);
    private JComboBox cbCategory = new JComboBox();
    private JComboBox cbLanguage = new JComboBox();

    private JButton btnAddNewCategory = new JButton("+");
    private JButton btnAddNewLanguage = new JButton("+");

    private JButton btnEdit = new JButton("Edit");
    private JButton btnClear = new JButton("Clear");
    private JButton btnCancel = new JButton("Cancel");

    private JButton btnNext = new JButton(">>");
    private JButton btnPrevious = new JButton("<<");

    private java.util.List<UserWordCategoryLanguage> uwclList;
    private java.util.List<Category> categories = DatabaseInterfaceImpl.getInstance().selectCategory();
    private java.util.List<Language> languages = DatabaseInterfaceImpl.getInstance().selectLanguage();

    private User user;
    private MainPanel mainPanel;

    private int index;

    public EditWordPanel(java.util.List<UserWordCategoryLanguage>uwclList, User user, MainPanel mainPanel)
    {
        super(new GridBagLayout());
        this.uwclList = uwclList;
        this.user = user;
        this.mainPanel = mainPanel;
        index = 0;

        JPanel panelNavigation = new JPanel(new GridBagLayout());
        JPanel panelFields = new JPanel(new GridBagLayout());
        JPanel panelButtons = new JPanel(new GridBagLayout());
        JPanel panelMain = new JPanel(new GridBagLayout());

        GridBagConstraints gbcPosition = new GridBagConstraints();

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelNavigation.add(btnPrevious, gbcPosition);
        btnPrevious.addActionListener(e -> previous());

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelNavigation.add(lId, gbcPosition);

        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelNavigation.add(tfId, gbcPosition);
        tfId.setEditable(false);

        gbcPosition.gridx = 3;
        gbcPosition.gridy = 0;
        panelNavigation.add(btnNext, gbcPosition);
        btnNext.addActionListener(e -> next());

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelFields.add(lName, gbcPosition);

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelFields.add(tfName, gbcPosition);

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelFields.add(lTranslation, gbcPosition);

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 1;
        panelFields.add(tfTranslation, gbcPosition);

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 2;
        panelFields.add(lLanguage, gbcPosition);

        JPanel panelLanguage = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelLanguage.add(cbLanguage, gbcPosition);
        languages.forEach(e ->{
            cbLanguage.addItem(e.getLanguageName());
        });

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelLanguage.add(btnAddNewLanguage, gbcPosition);
        btnAddNewLanguage.addActionListener(e ->{
            JFrame frame = new JFrame("Add new language");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            AddNewLanguage panel = new AddNewLanguage(this);
            panel.setVisible(true);

            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.pack();
        });

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 2;
        panelFields.add(panelLanguage, gbcPosition);

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 3;
        panelFields.add(lCategory, gbcPosition);

        JPanel panelCategory = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelCategory.add(cbCategory, gbcPosition);
        categories.forEach(e ->{
            cbCategory.addItem(e.getCategoryName());
        });

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelCategory.add(btnAddNewCategory, gbcPosition);
        btnAddNewCategory.addActionListener(e ->{
            JFrame frame = new JFrame("Add new category");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            AddNewCategory panel = new AddNewCategory(this);
            panel.setVisible(true);

            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.pack();
        });

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 3;
        panelFields.add(panelCategory, gbcPosition);

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelButtons.add(btnEdit, gbcPosition);
        btnEdit.addActionListener(e ->
        {
            edit();
            JOptionPane.showMessageDialog(null, "Word edited");
            mainPanel.updateTable();
        });

        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelButtons.add(btnClear, gbcPosition);
        btnClear.addActionListener(e ->fillFields(uwclList.get(index)));

        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelButtons.add(btnCancel, gbcPosition);
        btnCancel.addActionListener(e ->{
            JFrame thisFrame = (JFrame)this.getRootPane().getParent();
            thisFrame.dispose();
        });

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelMain.add(panelNavigation, gbcPosition);

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelMain.add(panelFields, gbcPosition);

        gbcPosition.gridx = 0;
        gbcPosition.gridy = 2;
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
        fillFields(uwclList.get(index));
    }

    private void fillFields(UserWordCategoryLanguage uwcl)
    {
        tfId.setText(String.valueOf(uwcl.getId()));
        tfName.setText(uwcl.getWordName());
        tfTranslation.setText(uwcl.getTranslation());
        categories.forEach(e ->{
            if(e.getCategoryName().equals(uwcl.getCategoryName()))
                cbCategory.setSelectedItem(e.getCategoryName());
        });
        languages.forEach(e ->{
            if(e.getLanguageName().equals(uwcl.getLanguageName()))
                cbLanguage.setSelectedItem(e.getLanguageName());
        });
    }

    private void next()
    {
        index++;
        if(index>=uwclList.size())
            index = 0;
        fillFields(uwclList.get(index));
    }

    private void previous()
    {
        index--;
        if(index<0)
            index = uwclList.size()-1;
        fillFields(uwclList.get(index));
    }

    @Override
    public void afterInsertCategory(Category category) {
        cbCategory.addItem(category.getCategoryName());
        categories.add(category);
    }

    @Override
    public void afterInsertLanguage(Language language) {
        cbLanguage.addItem(language.getLanguageName());
        languages.add(language);
    }

    private void edit()
    {
        int idUserWord = Integer.parseInt(tfId.getText());
        Word word = new Word(0, tfName.getText(), tfTranslation.getText(), 0,0);
        categories.forEach(e ->{
            if(e.getCategoryName().equals(cbCategory.getSelectedItem()))
                word.setIdCategory(e.getId());
        });
        languages.forEach(e ->{
            if(e.getLanguageName().equals(cbLanguage.getSelectedItem()))
                word.setIdLanguage(e.getId());
        });

        DatabaseInterfaceImpl.getInstance().updateWord(word, idUserWord, user);
        UserWordCategoryLanguage uwcl = DatabaseInterfaceImpl.getInstance().selectUserWordCategoryLanguageById(idUserWord);
        uwclList.get(index).setTranslation(uwcl.getTranslation());
        uwclList.get(index).setWordName(uwcl.getWordName());
        uwclList.get(index).setCategoryName(uwcl.getCategoryName());
        uwclList.get(index).setLanguageName(uwcl.getLanguageName());
        uwclList.get(index).setIdCategory(uwcl.getIdCategory());
        uwclList.get(index).setIdLanguage(uwcl.getIdLanguage());
        fillFields(uwclList.get(index));
    }
}
