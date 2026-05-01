package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class LoginController{
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;
    @FXML
    private void login(ActionEvent event) throws IOException{
        System.out.println("**login pressed!");
        System.out.println("**username: "+usernameField.getText());
        System.out.println("**password: "+passwordField.getText());
        if(App.driver.login(usernameField.getText(),passwordField.getText())){
            App.setRoot("dashboard");
        }
        else 
        {
            usernameField.clear();
            passwordField.clear();
            System.out.println("**invalid username or password... Please try again");
            // TODO add alert box
        }
    }
    @FXML
    private void newClient() throws IOException{
        System.out.println("**new client pressed");
        App.setRoot("newClient");
    }
}