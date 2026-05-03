package com.example;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
public class DashboardController {
    @FXML TextField depositField;
    @FXML TextField transferToField;
    @FXML TextField amntField;
    @FXML ListView<Account> accountsView;
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
            App.displayError("Please enter a valid accountID...");
        } catch (InvestmentLockException e) {
            App.displayError("Cannot withdraw from an investment account less than a year after it has been opened...");
        }
    }
    @FXML
    private void deposit(ActionEvent event) throws IOException{

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

    }
}