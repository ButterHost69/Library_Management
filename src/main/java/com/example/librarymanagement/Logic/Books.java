package com.example.librarymanagement.Logic;

import java.sql.*;
import java.util.*;

public class Books {

    private String bookId;
    private String title;
    private String author;
    //int quantity;

//    private final String _URL = "jdbc:mysql://localhost:3306/LibraryManagement";
//    private final String _Username = "root";
//    private final String _Password = "deep1520";
//    private final String _TableName = "Books";

    public Books(){}

    public Books(String bookId, String title, String author)
    {
//        try
//        {
//            this.bookId = bookId;
//            this.title = title;
//            this.author = author;
//
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(_URL + "/" + _TableName, _Username, _Password);
//
//            /*  ---------- Checks If Table is There Or Not ----------
//            Statement statement = connection.createStatement();
//            String ifTableQuery = "SELECT * FROM information_schema.tables" +
//                    "WHERE table_schema = " + connection.getCatalog() +
//                    "AND table_name = " + _TableName;
//
//            ResultSet resultSet = statement.executeQuery(ifTableQuery);
//            int rowsAffected = resultSet.getInt(0);
//            if(rowsAffected == 0){
//                Statement createDatabase = connection.createStatement();
//                String createTable = "CREATE TABLE " + _TableName;
//                System.out.println("Table "+_TableName + "Has Been Created");
//            }*/
//
//            String addBookQuery = "INSERT VALUES INTO " + _TableName + " (bookId, title, author) VALUES (?,?,?)";
//            PreparedStatement addBookStatement = connection.prepareStatement(addBookQuery);
//
//            addBookStatement.setString(1, bookId);
//            addBookStatement.setString(2, title);
//            addBookStatement.setString(3, author);
//
//            int rowsAffected = addBookStatement.executeUpdate();
//            if(rowsAffected < 0)
//            {
//                System.out.println("Query Not Executed Properly");
//            }
//            connection.close();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }

    }

    public HashMap<String, String> getBook(String type, String searchItem)
    {
        HashMap<String, String> finalResult = new HashMap<>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL, _Username, _Password);

            String searchBookQuery = "SELECT * FROM " + _TableName + " WHERE " + type + "=" + searchItem;
            Statement searchBookStatement = connection.createStatement();
            ResultSet resultSet = searchBookStatement.executeQuery(searchBookQuery);


            this.bookId = resultSet.getNString(1);
            this.title = resultSet.getNString(2);
            this.author = resultSet.getNString(3);


            if (this.bookId != null && this.title != null && this.author != null)
            {

                finalResult.put("bookId", this.bookId);
                finalResult.put("title" , this.title);
                finalResult.put("author" , this.author);

                return finalResult;
            }

            return finalResult;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return finalResult;
    }

    public List<HashMap<String, String>> getAllBooks(){
        List<HashMap<String, String>> allBookDetails = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL, _Username, _Password);

            String searchBookQuery = "SELECT * FROM " + _TableName;
            Statement searchBookStatement = connection.createStatement();
            ResultSet resultSet = searchBookStatement.executeQuery(searchBookQuery);

            while (resultSet.next())
            {
                HashMap<String, String> current = new HashMap<>();
                Integer tempBookId = resultSet.getInt(1);
                Boolean tempIsAvailable = resultSet.getBoolean(4);

                current.put("bookId" , tempBookId.toString());
                current.put("title" , (resultSet.getNString(2)).toString());
                current.put("author" , (resultSet.getNString(3)).toString());
                current.put("isAvailable" , tempIsAvailable.toString());

                allBookDetails.add(current);
            }

            return allBookDetails;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return allBookDetails;
        }
    }

}
