package com.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class NewClientController {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField nameField;
    @FXML TextField emailField;
    @FXML ComboBox<String> typeSelector;
    @FXML CheckBox rewardsProgramCheck;
    @FXML
    private void createIndividualClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        String[] fields={username,passsword,name,contact};
        if(hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createIndividualClient(username, passsword, name, contact);
        App.setRoot("login");
    }
    @FXML
    private void confirmSelection(ActionEvent event) throws IOException{
        String choice=typeSelector.getValue();
        System.out.println("**found choice: <"+choice+">");
        if(choice==null){
            App.displayError("Please make a selection...");
        }
        else if(choice.equals("Individual"))App.setRoot("newIndividualClient");
        else if(choice.equals("Student"))App.setRoot("newStudentClient");
        else if(choice.equals("Corporate"))App.setRoot("newCorporateClient");
        else if(choice.equals("Vip"))App.setRoot("newVipClient");
        else {
            App.displayError("Invalid selection...");
        }
    }
    private boolean hasEmptyFields(String[] fields){
        for (String field : fields) {
            if(field.equals(""))return true;
        }
        return false;
    }
    @FXML
    private void createStudentClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        String[] fields={username,passsword,name,contact};
        if(hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createStudentClient(username, passsword, name, contact);
        App.setRoot("login");
    }
    @FXML
    private void createCorporateClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        ArrayList<String> clientManagerContacts=parseEmails(contact);
        boolean rewardsProgramMember=rewardsProgramCheck.isSelected();
        String[] fields={username,passsword,name,contact};
        if(hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createCorporateClient(username, passsword, name, clientManagerContacts, rewardsProgramMember);
        App.setRoot("login");
    }
    private ArrayList<String> parseEmails(String contact) {
        Scanner parser=new Scanner(contact).useDelimiter(",");
        ArrayList<String> emails=new ArrayList<>();
        while (parser.hasNext()) {
            String email=parser.next();
            email.trim();
            emails.add(email);
        }
        parser.close();
        return emails;
    }
    @FXML
    private void createVipClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        String[] fields={username,passsword,name,contact};
        boolean rewardsProgramMember=rewardsProgramCheck.isSelected();
        if(hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createVipClient(username, passsword, rewardsProgramMember, name, contact);
        App.setRoot("login");
    }
    @FXML
    private void cancel() throws IOException{
        App.setRoot("login");
    }
}