package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
public class DashboardController {
    @FXML TextField depositField;
    @FXML TextField transferToField;
    @FXML TextField amntField;
    @FXML ListView<Account> accountsView;
    @FXML ComboBox<String> typeSelector;
    @FXML
    private void transfer(ActionEvent event) throws IOException{
        String transferTo=transferToField.getText();
        double amnt=0;
        String[] fields={transferToField.getText(),amntField.getText()};
        if(App.hasEmptyFields(fields)){
            App.displayError("Please enter a value for all fields.");
            return;
        }
        try {
            amnt=Double.parseDouble(amntField.getText());
        } catch (Exception e) {
            App.displayError("Please enter a decimal value for transfer amnt.");
            return;
        }
        try {
            App.driver.transfer(accountsView.getSelectionModel().getSelectedItem().getAccountID(), transferTo, amnt);
        } catch (NullPointerException e) {
            App.displayError("Please select an account.");
        } catch (InvestmentLockException e) {
            App.displayError("Cannot withdraw from an investment account less than a year after it has been opened...");
        } catch (InsufficientFundsException e){
            App.displayError("Insufficient funds in this account.");
        }
    }
    @FXML
    private void deposit(ActionEvent event) throws IOException{
        double amnt=0;
        try {
            amnt=Double.parseDouble(amntField.getText());
        } catch (Exception e) {
            App.displayError("Please enter a decimal value for deposit amnt.");
            return;
        }
        try {
            App.driver.deposit(amnt, accountsView.getSelectionModel().getSelectedItem().getAccountID());
        } catch (NullPointerException e) {
            App.displayError("Please select an account.");
        }
    }
    @FXML
    private void withdraw(ActionEvent event) throws IOException{
        double amnt=0;
        try {
            amnt=Double.parseDouble(amntField.getText());
        } catch (Exception e) {
            App.displayError("Please enter a decimal value for transfer amnt.");
            return;
        }
        try {
            App.driver.withdraw(amnt, accountsView.getSelectionModel().getSelectedItem().getAccountID());
        } catch (NullPointerException e) {
            App.displayError("Please select an account.");
        } catch (InvestmentLockException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InsufficientFundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    private void logout() throws IOException{
        App.driver.logout();
        App.setRoot("login");
    }
    @FXML
    public void initialize(){
        accountsView.setItems(App.driver.observableActiveAccounts);
        
    }
    @FXML
    public void openAccount() throws IOException{
        String selection=typeSelector.getValue();
        try {
            if(selection.equals("Chequeing"))App.driver.openChequeing();
            else if(selection.equals("Savings"))App.driver.openSavings();
            else if(selection.equals("Investment"))App.driver.openInvestment();
            else App.displayError("Please make choose a valid option.");
        } catch (InvalidTypeException e) {
            App.displayError("System error: error finding account type");
        } catch (NullPointerException e){
            App.displayError("Please select an account type.");
        }
    }
}