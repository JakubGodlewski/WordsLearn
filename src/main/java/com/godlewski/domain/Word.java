package com.godlewski.domain;

/**
 * Created by jakub on 15.05.2017.
 */
public class Word {

    private int id;
    private String wordName;
    private String translation;
    private int idLanguage;
    private int idCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (id != word.id) return false;
        if (idLanguage != word.idLanguage) return false;
        if (idCategory != word.idCategory) return false;
        if (wordName != null ? !wordName.equals(word.wordName) : word.wordName != null) return false;
        return translation != null ? translation.equals(word.translation) : word.translation == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wordName != null ? wordName.hashCode() : 0);
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + idLanguage;
        result = 31 * result + idCategory;
        return result;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", wordName='" + wordName + '\'' +
                ", translation='" + translation + '\'' +
                ", idLanguage=" + idLanguage +
                ", idCategory=" + idCategory +
                '}';
    }

    public Word(int id, String wordName, String translation, int idLanguage, int idCategory) {
        this.id = id;
        this.wordName = wordName;
        this.translation = translation;
        this.idLanguage = idLanguage;
        this.idCategory = idCategory;
    }

    public Word() {
    }
}
