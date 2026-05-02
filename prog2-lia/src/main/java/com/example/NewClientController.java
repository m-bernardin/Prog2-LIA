package com.example;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
public class NewClientController {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField nameField;
    @FXML TextField emailField;
    @FXML ComboBox<String> typeSelector;
    @FXML
    private void createIndividualClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        App.driver.createIndividualClient(username, passsword, name, contact);
        ArrayList<Client> clients=App.driver.getClients();
        for (Client client : clients) {
            System.out.println(client);
        }
    }
    @FXML
    private void confirmSelection(ActionEvent event) throws IOException{
        String choice=typeSelector.getValue();
        System.out.println("**found choice: "+choice);
        if(choice.equals("Individual"))App.setRoot("newIndividualClient");
        else if(choice.equals("Student"))App.setRoot("newStudentClient");
        else if(choice.equals("Corporate"))App.setRoot("newCorporateClient");
        else if(choice.equals("Vip"))App.setRoot("newVipClient");
        else System.out.println("**error getting selection...");
    }
}