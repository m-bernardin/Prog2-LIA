package com.example;
import java.io.IOException;
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
}