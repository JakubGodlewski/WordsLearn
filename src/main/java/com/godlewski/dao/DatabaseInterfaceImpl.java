package com.godlewski.dao;

import com.godlewski.domain.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 15.05.2017.
 */
public class DatabaseInterfaceImpl implements DatabaseInterface {

    private final String DRIVER = "org.sqlite.JDBC";
    private final String DB_NAME = "jdbc:sqlite:WordsLearn.db";
    private Connection connection;
    private Statement statement;

    private DatabaseInterfaceImpl() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_NAME);
            statement = connection.createStatement();
            createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static DatabaseInterface databaseInterface = null;

    public static DatabaseInterface getInstance()
    {
        if(databaseInterface == null)
        {
            databaseInterface = new DatabaseInterfaceImpl();
        }
        return databaseInterface;
    }

    private void createTable()
    {
        String createUser = "create table if not exists User" +
                "(" +
                "id integer primary key autoincrement," +
                "login varchar(50) not null," +
                "userName varchar(50) not null," +
                "userSurname varchar(50) not null," +
                "email varchar(50) not null," +
                "password varchar(50) not null," +
                "phone varchar(50) not null" +
                ");";

        String createLanguage = "create table if not exists Language"+
                "(" +
                "id integer primary key autoincrement," +
                "languageName varchar(50) not null" +
                ");";

        String createCategory= "create table if not exists Category" +
                "(" +
                "id integer primary key autoincrement," +
                "categoryName varchar(50) not null" +
                ")";

        String createWord = "create table if not exists Word" +
                "(" +
                "id integer primary key autoincrement, " +
                "wordName varchar(50) not null," +
                "translation varchar(50) not null," +
                "idLanguage int not null," +
                "idCategory int not null," +
                "foreign key (idLanguage) references Language(id) on delete cascade on update cascade," +
                "foreign key (idCategory) references Category(id) on delete cascade on update cascade" +
                ");";

        String createUserWord = "create table if not exists UserWord " +
                "(" +
                "id integer primary key autoincrement," +
                "idUser int not null," +
                "idWord int not null," +
                "points int not null" +
                ");";

        try {
            statement.execute(createUser);
            statement.execute(createLanguage);
            statement.execute(createCategory);
            statement.execute(createWord);
            statement.execute(createUserWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByLogin(String login) {

        try {
            String queryIsUser ="select * from user where login=?;";
            PreparedStatement ps = connection.prepareStatement(queryIsUser);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                User user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        String query = "INSERT INTO user (login, userName, userSurname, email, password, phone)\n" +
                "VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getPhone());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserWordCategoryLanguage> selectUserWordCategoryLanguageByUserId(int id) {

        List<UserWordCategoryLanguage> uwcl = new ArrayList<>();
        String query = "select * from UserWord uw " +
                "inner join Word w on w.id = uw.idWord " +
                "inner join Category c on c.id = w.idCategory " +
                "inner join Language l on l.id = w.idLanguage " +
                "where uw.idUser = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                UserWordCategoryLanguage uwclItem = new UserWordCategoryLanguage(
                        rs.getInt(1),
                        rs.getInt(3),
                        rs.getInt(2),
                        rs.getInt(4),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(13),
                        rs.getString(11));
                uwcl.add(uwclItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uwcl;
    }

    @Override
    public List<Category> selectCategory() {
        List<Category> categories = new ArrayList<>();
        String query = "select * from Category;";
        Category category;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
                categories.add(category);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void insertCategory(Category category) {
        String query = "insert into category (categoryName) values (?);";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Category getCategoryByName(String name) {
        String query = "select * from Category where categoryName =?;";
        Category category = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Language> selectLanguage() {
        List<Language> languages = new ArrayList<>();
        String query = "select * from Language;";
        Language language;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                language = new Language(rs.getInt(1), rs.getString(2));
                languages.add(language);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return languages;
    }

    @Override
    public void insertLanguage(Language language) {
        String query = "insert into language (languageName) values (?);";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, language.getLanguageName());
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Language getLanguageByName(String name) {
        String query = "select * from Language where languageName =?;";
        Language language = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                language = new Language(rs.getInt(1), rs.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return language;
    }

    @Override
    public void insertWord(Word word, User user) {

        Word word2 = getWordByWord(word);
        if(word2 == null)
        {
            insertNewWord(word);
            word2 = getWordByWord(word);
        }

        String query = "insert into userword (idUser, idWord, points) " +
                "values(?,?,?);";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.setInt(2, word2.getId());
            ps.setInt(3,0);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertNewWord(Word word) {
        String query ="insert into word (wordName, translation, idLanguage, idCategory)" +
                " values (?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, word.getWordName());
            ps.setString(2, word.getTranslation());
            ps.setInt(3, word.getIdLanguage());
            ps.setInt(4, word.getIdCategory());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Word getWordByWord(Word word) {

        Word retunedWord = null;
        String query = "select * from word where wordName=? and translation=? and idLanguage=? and idCategory=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, word.getWordName());
            ps.setString(2, word.getTranslation());
            ps.setInt(3, word.getIdLanguage());
            ps.setInt(4, word.getIdCategory());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                retunedWord = new Word(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return retunedWord;
    }

    @Override
    public void deleteUserWord(UserWordCategoryLanguage uwcl) {
        String query = "delete from UserWord where id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, uwcl.getId());
            ps.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWord(Word word, int userWordId, User user) {

        String query = "update UserWord set idWord = ? where id=?";
        String query2 = "select count(*) from userword where idWord=?";
        String query3 = "select idWord from userword where id=?";
        String query4 = "update Word set wordName=?, translation=?, idCategory=?, idLanguage=? where id=?";

        Word w = DatabaseInterfaceImpl.getInstance().getWordByWord(word);
        if(w==null)
        {
            try {
                PreparedStatement ps = connection.prepareStatement(query3);
                ps.setInt(1, userWordId);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int idWord = rs.getInt(1);
                    ps = connection.prepareStatement(query2);
                    ps.setInt(1, idWord);
                    System.out.println(idWord);
                    rs = ps.executeQuery();
                    while(rs.next())
                    {
                        if(rs.getInt(1)>1)
                        {
                            insertNewWord(word);
                            w = DatabaseInterfaceImpl.getInstance().getWordByWord(word);
                            ps = connection.prepareStatement(query);
                            ps.setInt(1, w.getId());
                            ps.setInt(2, userWordId);
                            ps.execute();
                        }
                        else
                        {
                            ps = connection.prepareStatement(query4);
                            ps.setString(1, word.getWordName());
                            ps.setString(2, word.getTranslation());
                            ps.setInt(3, word.getIdCategory());
                            ps.setInt(4, word.getIdLanguage());
                            ps.setInt(5, idWord);
                            ps.execute();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, w.getId());
                ps.setInt(2, userWordId);
                ps.execute();

            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public UserWordCategoryLanguage selectUserWordCategoryLanguageById(int id) {
        UserWordCategoryLanguage uwcl = null;

        String query = "select * from UserWord uw " +
                "inner join Word w on w.id = uw.idWord " +
                "inner join Category c on c.id = w.idCategory " +
                "inner join Language l on l.id = w.idLanguage " +
                "where uw.id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                uwcl = new UserWordCategoryLanguage(
                        rs.getInt(1),
                        rs.getInt(3),
                        rs.getInt(2),
                        rs.getInt(4),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(13),
                        rs.getString(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uwcl;
    }
}
