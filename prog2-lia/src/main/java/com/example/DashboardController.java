package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class DashboardController {
    @FXML TextField depositField;
    @FXML
    private void transfer(ActionEvent event) throws IOException{
        System.out.println(depositField.getText()+"$ deposited!");
    }
    @FXML
    private void deposit(ActionEvent event) throws IOException{

    }
}