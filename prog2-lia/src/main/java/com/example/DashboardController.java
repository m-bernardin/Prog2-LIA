package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class DashboardController {
    @FXML TextField depositField;
    @FXML TextField transferToField;
    @FXML TextField amntField;
    @FXML
    private void transfer(ActionEvent event) throws IOException{
        // App.driver.
    }
    @FXML
    private void deposit(ActionEvent event) throws IOException{

    }
    @FXML
    private void logout() throws IOException{
        App.driver.logout();
        App.setRoot("login");
    }

}