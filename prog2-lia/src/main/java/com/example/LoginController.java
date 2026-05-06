package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
/**
 * The FXML controller for the login page.
 * @author Mathieu Bernardin
 */
public class LoginController{
    /**
     * Link to the field where the user enters their username.
     */
    @FXML private TextField usernameField;
    /**
     * Link to the field where the user enters their password.
     */
    @FXML private TextField passwordField;
    /**
     * Allows the loginButton to function. Attempts to log the user in.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     */
    @FXML
    private void login(ActionEvent event) throws IOException{
        if(App.driver.login(usernameField.getText(),passwordField.getText())){
            App.setRoot("dashboard");
        } else{
            usernameField.clear();
            passwordField.clear();
            App.displayError("Invalid username or password... Please try again");
        }
    }
    /**
     * Allows the newClientButton to function. Navigates the user to the newClient page.
     */
    @FXML
    private void newClient() throws IOException{
        App.setRoot("newClient");
    }
}