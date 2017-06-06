package com.godlewski.gui;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.Category;
import com.godlewski.domain.Language;
import com.godlewski.domain.User;
import com.godlewski.domain.Word;
import com.godlewski.gui.interfaces.AfterInsertPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by jakub on 26.05.2017.
 */
public class AddNewWordPanel extends JPanel implements AfterInsertPanel{

    private JButton btnAdd = new JButton("Add");
    private JButton btnClear = new JButton("Clear");
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnAddNewLanguage = new JButton("Add New Language");
    private JButton btnAddNewCategory = new JButton("Add New Category");

    private JLabel lWord = new JLabel("Word:");
    private JLabel lTranslation = new JLabel("Translation:");
    private JLabel lLanguage = new JLabel("Language:");
    private JLabel lCategory = new JLabel("Category:");

    private JTextField tfWord = new JTextField(10);
    private JTextField tfTranslation = new JTextField(10);

    private JComboBox cbLanguage = new JComboBox();
    private JComboBox cbCategory = new JComboBox();

    private Map<Category, String> categoriesNames = new HashMap<>();
    private Map<Language, String> languagesNames = new HashMap<>();

    private User user;
    private MainPanel mainPanel;

    public AddNewWordPanel(User user, MainPanel mainPanel) {
        super(new GridBagLayout());
        this.user = user;
        this.mainPanel = mainPanel;

        JPanel panelFields1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelFields1.add(lWord, gbcPosition);
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelFields1.add(tfWord, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelFields1.add(lTranslation, gbcPosition);
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 1; //wiersz
        panelFields1.add(tfTranslation, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 2; //wiersz
        panelFields1.add(lLanguage, gbcPosition);
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 2; //wiersz
        DatabaseInterfaceImpl.getInstance().selectLanguage().forEach(x ->{
            cbLanguage.addItem(x.getLanguageName());
            languagesNames.put(x, x.getLanguageName());
        });
        panelFields1.add(cbLanguage, gbcPosition);

        JPanel panelFields2 = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelFields2.add(lCategory, gbcPosition);
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 0; //wiersz
        DatabaseInterfaceImpl.getInstance().selectCategory().forEach(x ->{
            cbCategory.addItem(x.getCategoryName());
            categoriesNames.put(x, x.getCategoryName());
        });
        panelFields2.add(cbCategory, gbcPosition);

        JPanel panelButtons = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelButtons.add(btnAdd, gbcPosition);
        btnAdd.addActionListener(e ->{
            add();
        });
        gbcPosition.gridx = 1; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelButtons.add(btnClear, gbcPosition);
        btnClear.addActionListener(e ->{
            clear();
        });
        gbcPosition.gridx = 2; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelButtons.add(btnCancel, gbcPosition);
        btnCancel.addActionListener(e ->{
            JFrame thisFrame = (JFrame)this.getRootPane().getParent();
            thisFrame.dispose();
        });

        JPanel panelMain = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 0; //wiersz
        panelMain.add(panelFields1, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 1; //wiersz
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
        panelMain.add(btnAddNewLanguage, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 2; //wiersz
        panelMain.add(panelFields2, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 3; //wiersz
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
        panelMain.add(btnAddNewCategory, gbcPosition);
        gbcPosition.gridx = 0; //kolumna
        gbcPosition.gridy = 4; //wiersz
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
    }

    private void clear()
    {
        tfWord.setText("");
        tfTranslation.setText("");
        cbLanguage.setSelectedItem(cbLanguage.getItemAt(0));
        cbCategory.setSelectedItem(cbCategory.getItemAt(0));
    }

    private void add()
    {
        Word word = getWordFromFields();
        if(word.getWordName().equals("") || word.getTranslation().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Invalid values");
        }
        else
        {
            DatabaseInterfaceImpl.getInstance().insertWord(word, user);
            JOptionPane.showMessageDialog(null, "Word added to database");
            clear();
            mainPanel.updateTable();
        }
    }

    private Word getWordFromFields()
    {
        int lanIndex =0;
        for(String l: languagesNames.values())
        {
            if(l.equals(cbLanguage.getSelectedItem()))
                break;
            lanIndex++;
        }

        java.util.List<Language> languageList = new ArrayList<Language>(languagesNames.keySet());
        Language language = languageList.get(lanIndex);

        int catIndex =0;
        for(String c: categoriesNames.values())
        {
            if(c.equals(cbCategory.getSelectedItem()))
                break;
            catIndex++;
        }

        java.util.List<Category> categoryList = new ArrayList<Category>(categoriesNames.keySet());
        Category category = categoryList.get(catIndex);

        Word word = new Word(0, tfWord.getText(), tfTranslation.getText(), language.getId(), category.getId());

        return word;
    }

    @Override
    public void afterInsertCategory(Category category) {
        cbCategory.addItem(category.getCategoryName());
        categoriesNames.put(category, category.getCategoryName());
    }

    @Override
    public void afterInsertLanguage(Language language) {
        cbLanguage.addItem(language.getLanguageName());
        languagesNames.put(language, language.getLanguageName());
    }
}
