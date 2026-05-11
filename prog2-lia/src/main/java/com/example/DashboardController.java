package com.example;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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
     * Link to the label where the welcome text is displayed.
     */
    @FXML
    private Label welcomeLabel;
    /**
     * Link to the label where the message for the user's monthly account fees is displayed.
     */
    @FXML
    private Label monthlyFeeLabel;
    // TODO javadoc
    @FXML
    private Label statusExpiredField;
    // TODO javadoc
    @FXML
    private Label contactLabel;
    // TODO javadoc
    @FXML
    private Label rewardsProgramMemberLabel;
    // TODO javadoc
    @FXML
    private Label memberSinceLabel;
    // TODO javadoc
    @FXML
    private Label pointsLabel;
    // TODO javadoc
    private DoubleProperty points=new SimpleDoubleProperty(0);
    /**
     * Called when the scene is intialised.
     * Sets up the accountView and transactionView.
     */
    @FXML
    public void initialize(){
        accountsView.setItems(App.driver.filteredAccounts);
        // accountsView.getSelectionModel().select(0);
        // TODO get rid of this NullPointer because of no selection
        accountsView.getSelectionModel().selectedItemProperty().addListener((obs,old,newSelection)->App.driver.selectedAccount.set(newSelection.getAccountID()));
        transactionsView.setItems(App.driver.getLatestTransactions());
        String activeClientID=App.driver.getActiveClient();
        Client activeClient=App.driver.getClient(activeClientID);
        @SuppressWarnings("rawtypes")
        Class clientType=App.driver.getClient(activeClientID).getClass();
        String name;
        if(clientType==IndividualClient.class)name=((IndividualClient)activeClient).getName();
        else if(clientType==StudentClient.class)name=((StudentClient)activeClient).getName();
        else if(clientType==VipClient.class)name=((VipClient)activeClient).getName();
        else if(clientType==CorporateClient.class)name=((CorporateClient)activeClient).getCompanyName();
        else name="Invalid Name";
        int monthlyFee=10;
        if(App.driver.hasWaivedFees(activeClientID))monthlyFee=0;
        monthlyFeeLabel.setText("(!) Each open account will cost you "+monthlyFee+"$ each month.");
        welcomeLabel.setText("Welcome, "+name+"!");
        boolean statusValid=false;
        if(clientType==StudentClient.class)if(((StudentClient)activeClient).isStatusValid())statusValid=true;
        if(clientType!=StudentClient.class)statusValid=true;
        if(statusValid)statusExpiredField.setText("");
        String contact="";
        if(clientType==IndividualClient.class||clientType==StudentClient.class)contact=((StandardClient)activeClient).getContact();
        if(clientType==VipClient.class)contact=((VipClient)activeClient).getContact();
        if(clientType==CorporateClient.class){
            ArrayList<String> contacts=((CorporateClient)activeClient).getClientManagerContacts();
            for(int i=0;i<contacts.size();++i){
                contact+=contacts.get(i);
                if(i!=contacts.size()-2)contact+=", ";
                else contact+=", or ";
            }
        } else contact="invalid contact";
        contactLabel.setText("We will contact you at "+contact+".");
        boolean rewardsProgramMember=false;
        boolean premiumClient=false;
        if(clientType==CorporateClient.class||clientType==VipClient.class){
            premiumClient=true;
            rewardsProgramMember=App.driver.isPremium(activeClientID);
        }
        if(rewardsProgramMember)rewardsProgramMemberLabel.setText("You are currently a member of our rewards porgram.");
        else if(premiumClient)rewardsProgramMemberLabel.setText("You are not curerntly a member of our rewards program.");
        else rewardsProgramMemberLabel.setText("(!) Did you know? We have a rewards program! To opt in, contact a bank associate to upgrade to vip position.");
        memberSinceLabel.setText("You have been with since "+activeClient.getDateOpened().format(DateTimeFormatter.ofPattern("d MMM uuuu"))+", thank you for your loyalty!");
        if(rewardsProgramMember){
            for (String accountID:activeClient.getAccounts()) {
                App.driver.getAccount(accountID).getPoints().addListener((obs,oldValue,newValue)->refreshPoints(newValue.doubleValue()-oldValue.doubleValue()));
                refreshPoints(App.driver.getAccount(accountID).getPoints().getValue());
            }
            System.out.println("**rewards program member detected");
        }
    }
    // TODO javadoc
    private void refreshPoints(Number addedPoints) {
        System.out.println("**points refreshed");
        points.set(points.get()+addedPoints.doubleValue());
        rewardsProgramMemberLabel.setText("You currently have "+points.get()+" points.");
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
            return;
        } catch (MissingChequingException e){
            App.displayError("Please open a chequeing account first.");
            return;
        } catch (NullPointerException e){
            App.displayError("Please select an account type.");
            return;
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
}