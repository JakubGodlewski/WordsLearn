package com.godlewski.domain;

/**
 * Created by jakub on 24.05.2017.
 */
public class UserWordCategoryLanguage {
    private int id;
    private int idWord;
    private int idUser;
    private int points;
    private String wordName;
    private String translation;
    private int idLanguage;
    private int idCategory;
    private String languageName;
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWord() {
        return idWord;
    }

    public void setIdWord(int idWord) {
        this.idWord = idWord;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWordCategoryLanguage that = (UserWordCategoryLanguage) o;

        if (id != that.id) return false;
        if (idWord != that.idWord) return false;
        if (idUser != that.idUser) return false;
        if (points != that.points) return false;
        if (idLanguage != that.idLanguage) return false;
        if (idCategory != that.idCategory) return false;
        if (wordName != null ? !wordName.equals(that.wordName) : that.wordName != null) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;
        if (languageName != null ? !languageName.equals(that.languageName) : that.languageName != null) return false;
        return categoryName != null ? categoryName.equals(that.categoryName) : that.categoryName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idWord;
        result = 31 * result + idUser;
        result = 31 * result + points;
        result = 31 * result + (wordName != null ? wordName.hashCode() : 0);
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + idLanguage;
        result = 31 * result + idCategory;
        result = 31 * result + (languageName != null ? languageName.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserWordCategoryLanguage{" +
                "id=" + id +
                ", idWord=" + idWord +
                ", idUser=" + idUser +
                ", points=" + points +
                ", wordName='" + wordName + '\'' +
                ", translation='" + translation + '\'' +
                ", idLanguage=" + idLanguage +
                ", idCategory=" + idCategory +
                ", languageName='" + languageName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public UserWordCategoryLanguage(int id, int idWord, int idUser, int points, String wordName, String translation, int idLanguage, int idCategory, String languageName, String categoryName) {
        this.id = id;
        this.idWord = idWord;
        this.idUser = idUser;
        this.points = points;
        this.wordName = wordName;
        this.translation = translation;
        this.idLanguage = idLanguage;
        this.idCategory = idCategory;
        this.languageName = languageName;
        this.categoryName = categoryName;
    }

    public UserWordCategoryLanguage() {
    }
}
