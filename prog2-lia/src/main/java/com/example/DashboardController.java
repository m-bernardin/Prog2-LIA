package com.example;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * FXML controller for the client dashboard.
 * @author Mathieu Bernardin
 */
public class DashboardController {
    /**
     * Link to the field where the user enters the ID for a transfer, if appplicable.
     */
    @FXML TextField transferToField;
    /**
     * Link to the field where the user enters the amount for a transfer.
     */
    @FXML TextField amntField;
    /**
     * Link to the list of accounts for the active client.
     */
    @FXML ListView<Account> accountsView;
    /**
     * Link to the selector for account opening.
     */
    @FXML ComboBox<String> typeSelector;
    /**
     * Link to the list of transactions for the active client.
     */
    @FXML ListView<Transaction> transactionsView;
    /**
     * Called when the scene is intialised.
     * Sets up the accountView and transactionView.
     */
    @FXML
    public void initialize(){
        accountsView.setItems(App.driver.filteredAccounts);
        System.out.println("**latest transactions: "+App.driver.getLatestTransactions());
        accountsView.getSelectionModel().selectedItemProperty().addListener((obs,old,newSelection)->changeAccount(newSelection));
        transactionsView.setItems(App.driver.getLatestTransactions());
        // accountsView.getSelectionModel().select(0);
        // App.driver.selectedAccount.bind(new SimpleStringProperty(accountsView.getSelectionModel().selectedItemProperty().get().accountID));
    }
    /**
     * Allows the openAccountButton to function. Opens an account of the type selected in the typeSelector for the active client.
     * @throws IOException if something goes wrong.
     */
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
        App.displayMessage("New "+selection.toLowerCase()+" account opened.");
    }
    /**
     * Allows the transferButton to function. Transfers an amnt specified in the amntField from the selected account to the account specified in the transferToIDField;
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     */
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
            amntField.clear();
            transferToField.clear();
        } catch (NullPointerException e) {
            App.displayError("Please select an account.");
        } catch (InvestmentLockException e) {
            App.displayError("Cannot withdraw from an investment account less than a year after it has been opened...");
        } catch (InsufficientFundsException e){
            App.displayError("Insufficient funds in this account.");
        } catch (InvalidTypeException e) {
            App.displayError("System error: error getting type");
        }
    }
    /**
     * Allows the depositButton to function. Deposits the amount specified in the amntField to the currently selected account.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     */
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
            amntField.clear();
        } catch (NullPointerException e) {
            App.displayError("Please select an account.");
        } catch (InvalidTypeException e) {
            App.displayError("System error: error getting type");
        }
    }
    /**
     * Allows the withdrawButton to function. Withdraws the amount specified in the amntField from the currently selected account.
     * @param event - the FXML event.
     * @throws IOException if something goes wrong.
     */
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
            amntField.clear();
        } catch (NullPointerException e) {
            App.displayError("Please select an account.");
        } catch (InvestmentLockException e) {
            App.displayError("Cannot withdraw from an investment account less than a year after it has been opened...");
        } catch (InsufficientFundsException e) {
            App.displayError("Insufficient funds in this account.");
        } catch (InvalidTypeException e) {
            App.displayError("System error: error getting type");
        }
    }
    /**
     * Allows logoutButton to function.
     * @throws IOException if something goes wrong.
     */
    @FXML
    private void logout() throws IOException{
        App.driver.logout();
        App.setRoot("login");
    }
    // TODO fold back into initialize?
    private void changeAccount(Account newSelection) {
        App.driver.selectedAccount.set(newSelection.getAccountID());
        System.out.println("**new account selected: "+newSelection.accountID);
        System.out.println("**associated transactions: "+App.driver.getLatestTransactions());
    }
}