package com.example.librarymanagement.Logic;

import java.sql.*;
import java.util.HashMap;

public abstract class LibraryMember {
    private String memberId;
    private String name;
    private String type;
    private Integer totalBooksBorrowed;


    private final String _URL = "jdbc:mysql://localhost:3306/LibraryManagement";
    private final String _Username = "root";
    private final String _Password = "deep1520";
    private final String _TableName = "LibraryMembers";

    public LibraryMember(){}

    public LibraryMember(String memberId, String name, String password, String type) {
        try
        {
            this.memberId = memberId;
            this.name = name;
            this.type = type;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL, _Username, _Password);

            String addMemberQuery = "INSERT INTO " + _TableName + "(memberId, name, password, type) VALUES (?, ?, ? , ?)";
            PreparedStatement addMemberStatement = connection.prepareStatement(addMemberQuery);
            addMemberStatement.setString(1, this.memberId);
            addMemberStatement.setString(2, this.name);
            addMemberStatement.setString(3, password);
            addMemberStatement.setString(4, this.type);

            int rowsAffected = addMemberStatement.executeUpdate();
            if(rowsAffected < 0)
            {
                System.out.println("Query Not Executed Properly");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public HashMap<String, String> getMember(String searchBy, String searchItem)
    {
        HashMap<String, String> finalResult = new HashMap<>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL, _Username, _Password);

            String searchBookQuery = "SELECT * FROM " + _TableName + " WHERE " + searchBy + " = ?";

            PreparedStatement searchBookStatement = connection.prepareStatement(searchBookQuery);
            searchBookStatement.setString(1, searchItem);

            ResultSet resultSet = searchBookStatement.executeQuery();
            resultSet.next();

            this.memberId = resultSet.getNString(1);
            this.name = resultSet.getNString(2);
            this.type = resultSet.getNString(4);
            this.totalBooksBorrowed = resultSet.getInt(5);


            if (this.memberId != null && this.name != null && this.type != null)
            {

                finalResult.put("memberId", this.memberId);
                finalResult.put("name" , this.name);
                finalResult.put("type" , this.type);
                finalResult.put("totalBooksBorrowed" , (this.totalBooksBorrowed).toString());

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

    public boolean login(String memberId, String password, String type)
    {
        String Query = "SELECT * FROM " + _TableName +
                " WHERE memberId= '" + memberId +
                "' AND password='" + password +
                "' AND type='" + type+ "'";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(_URL, _Username, _Password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);
            resultSet.next();
            if(memberId.equals(resultSet.getString(1)) && password.equals(resultSet.getString(3)) && type.equals(resultSet.getString(4)))
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public abstract void borrowBook();
    public abstract void returnBook();
}
