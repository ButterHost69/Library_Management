package com.example.librarymanagement.Controllers.LibraryMembersControllers;

import com.example.librarymanagement.Logic.Books;
import com.example.librarymanagement.Logic.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML public TableView <ObservableMap<String, String>> booksTable;
    @FXML public TableColumn <ObservableMap<String, String> ,String> bookIdCol;
    @FXML public TableColumn <ObservableMap<String, String> ,String> titleCol;
    @FXML public TableColumn <ObservableMap<String, String> ,String> authorCol;
    @FXML public TableColumn <ObservableMap<String, String> ,String> isAvailable;


    public void setUserDetails(String username)
    {
        usernameLabel.setText(username);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Books books = new Books();

        List<HashMap<String, String>> allBookList = books.getAllBooks();

        ObservableList<ObservableMap<String, String>> observableBookList = FXCollections.observableArrayList();

        for (HashMap<String, String> map : allBookList) {
            ObservableMap<String, String> observableMap = FXCollections.observableHashMap();
            observableMap.put("bookId", map.get("bookId"));
            observableMap.put("author", map.get("author"));
            observableMap.put("title", map.get("title"));
            observableMap.put("isAvailable", map.get("isAvailable"));
            observableBookList.add(observableMap);
        }

        booksTable.setItems(observableBookList);


        bookIdCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("bookId")));
        titleCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("title")));
        authorCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("author")));
        isAvailable.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("isAvailable")));

    }
}


