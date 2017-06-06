package com.godlewski.domain;

/**
 * Created by jakub on 15.05.2017.
 */
public class UserWord {

    private int id;
    private int idWord;
    private int idUser;
    private int points;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWord userWord = (UserWord) o;

        if (id != userWord.id) return false;
        if (idWord != userWord.idWord) return false;
        if (idUser != userWord.idUser) return false;
        return points == userWord.points;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idWord;
        result = 31 * result + idUser;
        result = 31 * result + points;
        return result;
    }

    @Override
    public String toString() {
        return "UserWord{" +
                "id=" + id +
                ", idWord=" + idWord +
                ", idUser=" + idUser +
                ", points=" + points +
                '}';
    }

    public UserWord(int id, int idWord, int idUser, int points) {
        this.id = id;
        this.idWord = idWord;
        this.idUser = idUser;
        this.points = points;
    }

    public UserWord() {
    }
}
