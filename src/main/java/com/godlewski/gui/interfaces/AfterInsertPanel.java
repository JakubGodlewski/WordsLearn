package com.godlewski.gui.interfaces;

import com.godlewski.domain.Category;
import com.godlewski.domain.Language;

/**
 * Created by jakub on 05.06.2017.
 */
public interface AfterInsertPanel {
    void afterInsertCategory(Category category);
    void afterInsertLanguage(Language language);
}
