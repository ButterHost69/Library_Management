package com.example.librarymanagement.Logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.sql.*;
import java.util.*;

public class Books {

    private SimpleStringProperty bookId;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty isAvailable;
    //int quantity;
    private CheckBox selectBook;

//    private final String _URL = "jdbc:mysql://localhost:3306/LibraryManagement";
//    private final String _Username = "root";
//    private final String _Password = "deep1520";
//    private final String _TableName = "Books";

    public void insertBooks(String bookId, String title, String author){

        new SQLHandler().addBook(bookId, title, author);
    }

    public Books(String bookId, String title, String author, String isAvailable)
    {
        this.bookId = new SimpleStringProperty(bookId);
        this.title = new SimpleStringProperty(title);
        this.isAvailable = new SimpleStringProperty(isAvailable);
        this.author = new SimpleStringProperty(author);
        this.selectBook = new CheckBox();
    }

// GETTER AND SETTER -- BEGIN

    public String getBookId() {
        return bookId.get();
    }

//    public void setBookId(String bookId) {
//        this.bookId = bookId;
//    }

    public String getTitle() {
        return title.get();
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getAuthor() {
        return author.get();
    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public CheckBox getSelectBook() {
        return selectBook;
    }

    public void setSelectBook(CheckBox selectBook) {
        this.selectBook = selectBook;
        System.out.println(this.selectBook);
    }

    public String getIsAvailable() {
        return isAvailable.get();
    }

    public SimpleStringProperty isAvailableProperty() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable.set(isAvailable);
    }

    // GETTER AND SETTER -- END




//    public HashMap<String, String> getBook(String type, String searchItem)
//    {
//        HashMap<String, String> finalResult = new HashMap<>();
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(_URL, _Username, _Password);
//
//            String searchBookQuery = "SELECT * FROM " + _TableName + " WHERE " + type + "=" + searchItem;
//            Statement searchBookStatement = connection.createStatement();
//            ResultSet resultSet = searchBookStatement.executeQuery(searchBookQuery);
//
//
//            this.bookId = resultSet.getNString(1);
//            this.title = resultSet.getNString(2);
//            this.author = resultSet.getNString(3);
//
//
//            if (this.bookId != null && this.title != null && this.author != null)
//            {
//
//                finalResult.put("bookId", this.bookId);
//                finalResult.put("title" , this.title);
//                finalResult.put("author" , this.author);
//
//                return finalResult;
//            }
//
//            return finalResult;
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//        return finalResult;
//    }

}
