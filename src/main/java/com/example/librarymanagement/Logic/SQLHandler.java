package com.example.librarymanagement.Logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import java.sql.*;
import java.util.HashMap;

public class SQLHandler {

    private static final String _URL = "jdbc:mysql://localhost:3306/LibraryManagement";
    private static final String _Username = "root";
    private static final String _Password = "deep1520";
    private static final String _BookTableName = "books";

    private static final String _BookEntryTableName = "memberbook";

    public static Connection connectToDB()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL , _Username, _Password);

            return connection;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void addBook(String bookId, String title, String author)
    {
        try
        {

            Connection connection = connectToDB();

            /*  ---------- Checks If Table is There Or Not ----------
            Statement statement = connection.createStatement();
            String ifTableQuery = "SELECT * FROM information_schema.tables" +
                    "WHERE table_schema = " + connection.getCatalog() +
                    "AND table_name = " + _BookTableName;

            ResultSet resultSet = statement.executeQuery(ifTableQuery);
            int rowsAffected = resultSet.getInt(0);
            if(rowsAffected == 0){
                Statement createDatabase = connection.createStatement();
                String createTable = "CREATE TABLE " + _BookTableName;
                System.out.println("Table "+_BookTableName + "Has Been Created");
            }*/

            String addBookQuery = "INSERT VALUES INTO " + _BookTableName + " (bookId, title, author) VALUES (?,?,?)";
            PreparedStatement addBookStatement = connection.prepareStatement(addBookQuery);

            addBookStatement.setString(1, bookId);
            addBookStatement.setString(2, title);
            addBookStatement.setString(3, author);

            int rowsAffected = addBookStatement.executeUpdate();
            if(rowsAffected < 0)
            {
                System.out.println("Query Not Executed Properly");
            }
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public static Integer getTotalBorrowedBooks(String memberId)
    {
        try
        {
            Integer totalBooks = 0;

            Connection connection = connectToDB();

            CallableStatement callableStatement = connection.prepareCall("{CALL get_total_borrowed_books(?)}");
            callableStatement.setString(1, memberId);

            ResultSet rs = callableStatement.executeQuery();
            while (rs.next())
            {
                totalBooks = rs.getInt("total");
                System.out.println(totalBooks);
            }

            return totalBooks;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return  0;
    }

    public static ObservableList<Books> getAllBooks()
    {
        Connection connection = connectToDB();
        ObservableList<Books> list = FXCollections.observableArrayList();

        try
        {
            String searchBookQuery = "SELECT * FROM " + _BookTableName;
            Statement searchBookStatement = connection.createStatement();
            ResultSet rs = searchBookStatement.executeQuery(searchBookQuery);
            //rs.next();
            while (rs.next())
            {
                String t_bookId = rs.getString("bookId");
                //System.out.println("Book Id: " + t_bookId);
                String t_title = rs.getString("title");
                String t_author = rs.getString("author");
                String t_isAvailable = rs.getString("isAvailable");
                list.add(new Books(t_bookId, t_title, t_author, t_isAvailable));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }


    public static ObservableList<Books> getUserIssuedBooks(String memberID)
    {
        Connection connection = connectToDB();

        ObservableList<Books> list = FXCollections.observableArrayList();

        try
        {
            //String searchBookQuery = "SELECT * FROM " + _BookTableName;
            //Statement searchBookStatement = connection.createStatement();

            CallableStatement callableStatement = connection.prepareCall("{CALL get_user_issued_book_details(?)}");
            System.out.println("Member ID: " + memberID);
            callableStatement.setString(1, memberID);
            ResultSet rs = callableStatement.executeQuery();
            //rs.next();
            while (rs.next())
            {
                String t_bookId = rs.getString("bookId");
                System.out.println("Book Id: " + t_bookId);
                String t_title = rs.getString("title");
                String t_author = rs.getString("author");
                //String t_isAvailable = rs.getString("isAvailable");
                list.add(new Books(t_bookId, t_title, t_author, "null"));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        return list;


    }

    public static void addEntryInDB(String memberId, String bookId) {
        try
        {
            Connection connection = connectToDB();

            CallableStatement callableStatement = connection.prepareCall("{CALL issue_book(?, ?)}");
            callableStatement.setString(1, memberId);
            callableStatement.setInt(2, Integer.parseInt(bookId));

            int rowsAffected = callableStatement.executeUpdate();
            if(rowsAffected < 0)
            {
                System.out.println("Query Not Executed Properly");
            }
            else {
                System.out.println("Member: " + memberId + " has issued Book: " + bookId);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public static void returnBookInDB(String memberId, String bookId) {
        try
        {
            Connection connection = connectToDB();

            CallableStatement callableStatement = connection.prepareCall("{CALL return_book(?, ?)}");
            callableStatement.setString(1, memberId);
            callableStatement.setInt(2, Integer.parseInt(bookId));

            int rowsAffected = callableStatement.executeUpdate();
            if(rowsAffected < 0)
            {
                System.out.println("Query Not Executed Properly");
            }
            else {
                System.out.println("Member: " + memberId + " has issued Book: " + bookId);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


}
