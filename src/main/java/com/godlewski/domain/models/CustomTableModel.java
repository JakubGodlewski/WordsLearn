package com.godlewski.domain.models;

import com.godlewski.domain.UserWord;
import com.godlewski.domain.UserWordCategoryLanguage;
import com.godlewski.domain.Word;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jakub on 23.05.2017.
 */
public class CustomTableModel extends AbstractTableModel{

    private List<UserWordCategoryLanguage> rows;
    private List<String> columnNames;

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserWordCategoryLanguage uwcl = rows.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return uwcl.getId();
            case 1:
                return uwcl.getWordName();
            case 2:
                return uwcl.getTranslation();
            case 3:
                return uwcl.getPoints();
            case 4:
                return uwcl.getLanguageName();
            default:
                return uwcl.getCategoryName();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public CustomTableModel(List<UserWordCategoryLanguage> rows) {
        this.rows = rows;
        columnNames = new ArrayList<>();
        Collections.addAll(columnNames,
                "ID WORD", "WORD NAME", "TRANSLATION", "POINTS", "LANGUAGE", "CATEGORY");
    }

    public void update(List<UserWordCategoryLanguage> rows) {
        this.rows = rows;
    }

    public UserWordCategoryLanguage getSelectedValue(int rowIndex)
    {
        UserWordCategoryLanguage uwcl = rows.get(rowIndex);
        return uwcl;
    }

}
