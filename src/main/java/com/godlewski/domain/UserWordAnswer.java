package com.godlewski.domain;

/**
 * Created by jakub on 14.07.2017.
 */
public class UserWordAnswer {
    private String wordName;
    private String translation;
    private String answer;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWordAnswer that = (UserWordAnswer) o;

        if (wordName != null ? !wordName.equals(that.wordName) : that.wordName != null) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;
        return answer != null ? answer.equals(that.answer) : that.answer == null;
    }

    @Override
    public int hashCode() {
        int result = wordName != null ? wordName.hashCode() : 0;
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserWordAnswer{" +
                "wordName='" + wordName + '\'' +
                ", translation='" + translation + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public UserWordAnswer(String wordName, String translation, String answer) {
        this.wordName = wordName;
        this.translation = translation;
        this.answer = answer;
    }

    public UserWordAnswer() {
    }
}
