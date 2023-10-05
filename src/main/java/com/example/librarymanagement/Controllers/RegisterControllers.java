package com.example.librarymanagement.Controllers;

import com.example.librarymanagement.Logic.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterControllers implements Initializable {

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public ChoiceBox<String> categoryChoiceBox;

    @FXML
    public Label outputLabel;
    private final String[] typesOfUser = {"Student", "Faculty"};

    public void backToLoginWindow(ActionEvent e)
    {
        try
        {
            Parent root = FXMLLoader.load((getClass().getResource("/FXML/LoginView.fxml")));
            Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);

            stage.setScene(scene);
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
        }
    }

    public void registerUser(ActionEvent e)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String type = categoryChoiceBox.getValue();

        Student student = new Student(username, username, password, type);

        outputLabel.setText("Registered Successfully");
        //System.out.println(username + " " + password + " " + type);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryChoiceBox.getItems().addAll(typesOfUser);
    }
}
