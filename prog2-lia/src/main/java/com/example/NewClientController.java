package com.example;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class NewClientController {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField typeField;
    @FXML
    private void create(ActionEvent event) throws IOException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        int type=Integer.parseInt(typeField.getText());
        try {
            App.driver.createClient(username, passsword, type);
            System.out.println("**created client");
        } catch (InvalidTypeException e) {
            System.out.println("**couldnt create client...");
        }
        ArrayList<Client> clients=App.driver.getClients();
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}