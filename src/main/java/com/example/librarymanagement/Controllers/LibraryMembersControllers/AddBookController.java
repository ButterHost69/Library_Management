package com.example.librarymanagement.Controllers.LibraryMembersControllers;

import com.example.librarymanagement.Logic.Books;
import com.example.librarymanagement.Logic.SQLHandler;
import com.example.librarymanagement.Logic.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {


    @FXML public Label usernameLabel;
    @FXML public TableView <Books> booksTable;
    @FXML public TableColumn <Books ,String> bookIdCol;
    @FXML public TableColumn <Books ,String> titleCol;
    @FXML public TableColumn <Books ,String> authorCol;
    @FXML public TableColumn <Books ,String> isAvailable;
    @FXML public TableColumn <Books ,CheckBox> issueBookCol;


    ObservableList<Books> allBookList;

    public void setUserDetails(String username)
    {
        usernameLabel.setText(username);
    }


    @FXML
    public void issueBook(ActionEvent e)
    {
        System.out.println("--- Selected Books ---");
        for (Books books : allBookList) {
            if (books.getSelectBook().isSelected()) {
                System.out.println(books.getBookId());
                SQLHandler.addEntryInDB(usernameLabel.getText(), books.getBookId());
                System.out.println("Books Added Succesfully");
                books.getSelectBook().setSelected(false);
            }
        }
        allBookList = SQLHandler.getAllBooks();
        booksTable.setItems(allBookList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookIdCol.setCellValueFactory(new PropertyValueFactory<Books, String>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        isAvailable.setCellValueFactory(new PropertyValueFactory<Books, String>("isAvailable"));
        issueBookCol.setCellValueFactory(new PropertyValueFactory<Books, CheckBox>("selectBook"));

        allBookList = SQLHandler.getAllBooks();
        booksTable.setItems(allBookList);

    }
}


