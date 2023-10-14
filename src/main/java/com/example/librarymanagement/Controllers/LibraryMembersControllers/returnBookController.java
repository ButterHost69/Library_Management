package com.example.librarymanagement.Controllers.LibraryMembersControllers;

import com.example.librarymanagement.Logic.Books;
import com.example.librarymanagement.Logic.LoggedinUserDetails;
import com.example.librarymanagement.Logic.SQLHandler;
import com.example.librarymanagement.Logic.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class returnBookController implements Initializable {

    public static String memberID;


    @FXML public Text booksBorrowedLabel;
    @FXML public Button usernameBtn;
    @FXML public TableView<Books> booksTable;
    @FXML public TableColumn<Books ,String> bookIdCol;
    @FXML public TableColumn <Books ,String> titleCol;
    @FXML public TableColumn <Books ,String> authorCol;
    @FXML public TableColumn <Books ,String> isAvailable;
    @FXML public TableColumn <Books , CheckBox> returnBookCol;


    ObservableList<Books> allBookList;

    @FXML
    public void setUserDetails(String username)
    {
        usernameBtn.setText(username);
        //bookBorrowedLabel.setText((SQLHandler.getTotalBorrowedBooks(username)).toString());
        //memberID = username;
    }


    public void toAddBooksView(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/FXML/StudentViews/AddBooksView.fxml")));
        Parent root = loader.load();

        AddBookController addBookController = loader.getController();
        addBookController.setUserDetails(memberID);

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
    public void returnBook(ActionEvent e)
    {
        System.out.println("--- Selected Books ---");
        for (Books books : allBookList) {
            if (books.getSelectBook().isSelected()) {
                SQLHandler.returnBookInDB(usernameBtn.getText(), books.getBookId());
                System.out.println("Books Returned Succesfully");
                books.getSelectBook().setSelected(false);
            }
        }
        allBookList = SQLHandler.getUserIssuedBooks(usernameBtn.getText());
        booksTable.setItems(allBookList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookIdCol.setCellValueFactory(new PropertyValueFactory<Books, String>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        //isAvailable.setCellValueFactory(new PropertyValueFactory<Books, String>("isAvailable"));
        returnBookCol.setCellValueFactory(new PropertyValueFactory<Books, CheckBox>("selectBook"));


        memberID = LoggedinUserDetails.Details.getMemberID();

        //System.out.println(memberID);
        allBookList = SQLHandler.getUserIssuedBooks(memberID);
        booksTable.setItems(allBookList);
        Integer borrowedBooks = SQLHandler.getTotalBorrowedBooks(memberID);
        //System.out.println(borrowedBooks);
        booksBorrowedLabel.setText(borrowedBooks.toString());

    }

}
