package com.godlewski.dao;

import com.godlewski.domain.*;

import java.util.List;

/**
 * Created by jakub on 15.05.2017.
 */
public interface DatabaseInterface {

    User findUserByLogin(String login);
    void insertUser(User user);
    List<UserWordCategoryLanguage> selectUserWordCategoryLanguageByUserId(int id);
    List<Category> selectCategory();
    void insertCategory(Category category);
    Category getCategoryByName(String name);
    List<Language> selectLanguage();
    void insertLanguage(Language language);
    Language getLanguageByName(String name);
    void insertWord(Word word, User user);
    Word getWordByWord(Word word);
    void deleteUserWord(UserWordCategoryLanguage uwcl);
    void updateWord(Word word, int userWordId);
}
