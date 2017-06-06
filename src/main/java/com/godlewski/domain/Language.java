package com.godlewski.domain;

/**
 * Created by jakub on 15.05.2017.
 */
public class Language {

    private int id;
    private String languageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (id != language.id) return false;
        return languageName != null ? languageName.equals(language.languageName) : language.languageName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (languageName != null ? languageName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", languageName='" + languageName + '\'' +
                '}';
    }

    public Language(int id, String languageName) {
        this.id = id;
        this.languageName = languageName;
    }

    public Language() {
    }
}
