package com.example.librarymanagement.Controllers.LibraryMembersControllers;

import com.example.librarymanagement.Logic.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class MainMenuController{


    @FXML public Label memberIdLabel;
    @FXML public Label nameLabel;
    @FXML public Label typeLabel;
    @FXML public Label booksBorrowedLabel;

    private String username;

    public void setUserDetails(HashMap<String, String> userDetails)
    {
        memberIdLabel.setText(userDetails.get("memberId"));
        username = userDetails.get("memberId");

        nameLabel.setText(userDetails.get("name"));
        typeLabel.setText(userDetails.get("type"));
        booksBorrowedLabel.setText(userDetails.get("booksBorrowed"));
    }

    public void toAddBooksView(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/FXML/StudentViews/AddBooksView.fxml")));
        Parent root = loader.load();

        AddBookController addBookController = loader.getController();
        addBookController.setUserDetails(username);

        Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    public void toReturnBookView(ActionEvent e)
    {

    }


}
