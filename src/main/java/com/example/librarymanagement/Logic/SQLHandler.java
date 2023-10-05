package com.example.librarymanagement.Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLHandler {

    private final String _URL = "jdbc:mysql://localhost:3306/LibraryManagement";
    private final String _Username = "root";
    private final String _Password = "deep1520";
    private final String _TableName = "Books";

    public void addBook(String bookId, String title, String author)
    {
        try
        {
//            this.bookId = bookId;
//            this.title = title;
//            this.author = author;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL + "/" + _TableName, _Username, _Password);

            /*  ---------- Checks If Table is There Or Not ----------
            Statement statement = connection.createStatement();
            String ifTableQuery = "SELECT * FROM information_schema.tables" +
                    "WHERE table_schema = " + connection.getCatalog() +
                    "AND table_name = " + _TableName;

            ResultSet resultSet = statement.executeQuery(ifTableQuery);
            int rowsAffected = resultSet.getInt(0);
            if(rowsAffected == 0){
                Statement createDatabase = connection.createStatement();
                String createTable = "CREATE TABLE " + _TableName;
                System.out.println("Table "+_TableName + "Has Been Created");
            }*/

            String addBookQuery = "INSERT VALUES INTO " + _TableName + " (bookId, title, author) VALUES (?,?,?)";
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

}
