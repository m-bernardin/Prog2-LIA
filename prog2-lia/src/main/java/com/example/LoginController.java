package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class LoginController{
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML
    private void login(ActionEvent event) throws IOException{
        System.out.println("login pressed!");
        System.out.println("username: "+usernameField.getText());
        System.out.println("password: "+passwordField.getText());
    }
}