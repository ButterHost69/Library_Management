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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AddBookController implements Initializable{


    @FXML public Label usernameLabel;
    @FXML public Button usernameBtn;
    @FXML public TableView <Books> booksTable;
    @FXML public TableColumn <Books ,String> bookIdCol;
    @FXML public TableColumn <Books ,String> titleCol;
    @FXML public TableColumn <Books ,String> authorCol;
    @FXML public TableColumn <Books ,String> isAvailable;
    @FXML public TableColumn <Books ,CheckBox> issueBookCol;


    ObservableList<Books> allBookList;

    @FXML
    public void setUserDetails(String username)
    {
        usernameBtn.setText(username);
    }

    public void toReturnBookView(ActionEvent e) throws IOException {

        //HashMap<String, String> userDetails = new Student().getMember("memberId", usernameBtn.getText());

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/FXML/StudentViews/returnBookView.fxml")));
        Parent root = loader.load();

        returnBookController r_returnBookController = loader.getController();
        r_returnBookController.setUserDetails(usernameBtn.getText());

        Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    public void toMainMenuView(ActionEvent e) throws IOException {

        HashMap<String, String> userDetails = new Student().getMember("memberId", usernameBtn.getText());

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/FXML/StudentViews/StudentView.fxml")));
        Parent root = loader.load();

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUserDetails(userDetails);

        Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    @FXML
    public void issueBook(ActionEvent e)
    {
        System.out.println("--- Selected Books ---");
        for (Books books : allBookList) {
            if (books.getSelectBook().isSelected()) {
                System.out.println(books.getBookId());
                SQLHandler.addEntryInDB(usernameBtn.getText(), books.getBookId());
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


