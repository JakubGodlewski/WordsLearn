package com.godlewski.domain.models;

import com.godlewski.domain.UserWordAnswer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jakub on 14.07.2017.
 */
public class CustomTableModelLearning extends AbstractTableModel{

    private List<UserWordAnswer> rows;
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
        UserWordAnswer uwa = rows.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return uwa.getWordName();
            case 1:
                return uwa.getTranslation();
            default:
                return uwa.getAnswer();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public CustomTableModelLearning(List<UserWordAnswer> rows) {
        this.rows = rows;
        columnNames = new ArrayList<>();
        Collections.addAll(columnNames,
                "WORD NAME", "TRANSLATION", "ANSWER");
    }

    public void update(List<UserWordAnswer> rows) {
        this.rows = rows;
    }

    public UserWordAnswer getSelectedValue(int rowIndex)
    {
        UserWordAnswer uwa = rows.get(rowIndex);
        return uwa;
    }
}
