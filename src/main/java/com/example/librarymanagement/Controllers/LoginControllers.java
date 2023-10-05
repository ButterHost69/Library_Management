package com.example.librarymanagement.Controllers;


import com.example.librarymanagement.Controllers.LibraryMembersControllers.MainMenuController;
import com.example.librarymanagement.Logic.Faculty;
import com.example.librarymanagement.Logic.LibraryMember;
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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class LoginControllers implements Initializable {

    @FXML public TextField usernameField;
    @FXML public PasswordField passwordField;
    @FXML
    public ChoiceBox<String> categoryChoiceBox;
    @FXML
    public Label outputLabel;

    private final String[] typesOfUser = {"Student", "Faculty", "Admin"};

    public void toRegisterWindow(ActionEvent e)
    {
        try
        {
            Parent root = FXMLLoader.load((getClass().getResource("/FXML/RegisterView.fxml")));
            Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);

            stage.setScene(scene);
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
        }
    }

    public void login(ActionEvent e)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String type = categoryChoiceBox.getValue();
        boolean ifLogin = false;

        if(type.equals("Student")){
            Student student = new Student();
            ifLogin = student.login(username, password, type);
        }
        else if (type.equals("Faculty")) {
            Faculty faculty = new Faculty();
            ifLogin = faculty.login(username, password, type);
        }

        if(ifLogin)
        {
            outputLabel.setText("Logged In Successfully !");
            try
            {

                if(type.equals("Student")){
                    HashMap<String, String> userDetails = new Student().getMember("memberId", username);

                    FXMLLoader loader = new FXMLLoader((getClass().getResource("/FXML/StudentViews/StudentView.fxml")));
                    Parent root = loader.load();

                    MainMenuController mainMenuController = loader.getController();
                    mainMenuController.setUserDetails(userDetails);

                    Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                }
            }
            catch (IOException error)
            {
                System.out.println(error.getMessage());
            }

        }
        else {
            outputLabel.setText("Incorrect Username/Password !");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryChoiceBox.getItems().addAll(typesOfUser);
    }
}
