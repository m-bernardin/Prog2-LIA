package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
/**
 * FXML controller for the new client pages.
 * @author Mathieu Bernardin
 */
public class NewClientController {
    /**
     * Link to the field where the user enters their username.
     */
    @FXML private TextField usernameField;
    /**
     * Link to the field where the user enters their password.
     */
    @FXML private TextField passwordField;
    /**
     * Link to the field where the user enters their name or company's name.
     */
    @FXML private TextField nameField;
    /**
     * Link to the field where the user enters their contact.
     */
    @FXML private TextField emailField;
    /**
     * Link to the selector for client type.
     */
    @FXML private ComboBox<String> typeSelector;
    /**
     * Link to the checkbox where the user may opt into the rewards program, where applicable.
     */
    @FXML private CheckBox rewardsProgramCheck;
    /**
     * Allows for creation of individual clients based off entered info.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     * @throws InvalidTypeException if something goes wrong.
     */
    @FXML
    private void createIndividualClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        String[] fields={username,passsword,name,contact};
        if(App.hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createIndividualClient(username, passsword, name, contact);
        App.setRoot("login");
    }
    /**
     * Advances to the next page, based off the user's selection.
     * @param event - the FXML event
     * @throws IOException if something goes wrong.
     */
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
    /**
     * Allows for creation of student clients based off entered info.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     * @throws InvalidTypeException if something goes wrong.
     */
    @FXML
    private void createStudentClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        String[] fields={username,passsword,name,contact};
        if(App.hasEmptyFields(fields)){
            App.displayError("Please fill all information.");
            return;
        }
        App.driver.createStudentClient(username, passsword, name, contact);
        App.setRoot("login");
    }
    /**
     * Allows for creation of corporate clients based off entered info.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     * @throws InvalidTypeException if something goes wrong.
     */
    @FXML
    private void createCorporateClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        ArrayList<String> clientManagerContacts=parseEmails(contact);
        boolean rewardsProgramMember=rewardsProgramCheck.isSelected();
        String[] fields={username,passsword,name,contact};
        if(App.hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createCorporateClient(username, passsword, name, clientManagerContacts, rewardsProgramMember);
        App.setRoot("login");
    }
    /**
     * Parses emails from a specified String into an ArrayList.
     * @param contact - the String to be parsed.
     * @return the parsed emails.
     */
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
    /**
     * Allows for creation of vip clients based off entered info.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     * @throws InvalidTypeException if something goes wrong.
     */
    @FXML
    private void createVipClient(ActionEvent event) throws IOException, InvalidTypeException{
        String username=usernameField.getText();
        String passsword=passwordField.getText();
        String name=nameField.getText();
        String contact=emailField.getText();
        String[] fields={username,passsword,name,contact};
        boolean rewardsProgramMember=rewardsProgramCheck.isSelected();
        if(App.hasEmptyFields(fields)){
            App.displayError("Please fill all information...");
            return;
        }
        App.driver.createVipClient(username, passsword, rewardsProgramMember, name, contact);
        App.setRoot("login");
    }
    /**
     * Cancels client registration and returns to the login page.
     * @throws IOExceptionss if something goes wrong.
     */
    @FXML
    private void cancel() throws IOException{
        App.setRoot("login");
    }
}