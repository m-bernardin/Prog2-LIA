package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * Main class for this application.
 * @author Mathieu Bernardin
 */
public class App extends Application {
    /**
     * The object manager.
     */
    public static Driver driver;
    /**
     * The active scene.
     */
    private static Scene scene;
    /**
     * Sets up the basics for this app.
     * @param stage - the primary stage for this app.
     * @throws IOException if something goes wrong..
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Awesome Banking");
    }
    /**
     * Sets the current scene to be displayed.
     * @param fxml - the fxml of the scene to be displayed.
     * @throws IOException if this fxml does not exist.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /**
     * Loads an fxml file to a scene.
     * @param fxml - the fxml to be loaded.
     * @return the loaded scene, as a Parent.
     * @throws IOException if somethign goes wrong.
     * @see Parent
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /**
     * This project's main method. Loads a Driver class for object managment, loads all data from json, then launches the app. Saves all the data after app is closed.
     * @param args - arguments provided from the cli.
     */
    public static void main(String[] args) {
        driver=new Driver();
        try {
            driver.loadData();
        } catch (MissingFileException e) {
            System.out.println("Fatal error occured: "+e.getMessage());
            Platform.exit();
        }
        launch();
        try {
            driver.saveData();
        } catch (IOException e) {
            System.out.println("**Error saving data; Some data loss may have occured...");
        }
    }
    /**
     * Allows JavaFX controllers to display error messages to the user based on a specified message.
     * @param message - the message to be displayed with the error.
     */
    public static void displayError(String message){
        Alert alert=new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
    /**
     * Checks if any String in a provided array is empty.
     * @param fields - the array to be checked.
     * @return true if there are empty fields; false otherwise.
     */
    public static boolean hasEmptyFields(String[] fields){
        for (String field : fields) {
            if(field.trim().equals(""))return true;
        }
        return false;
    }
    /**
     * Allows JavaFX controllers to display messages to the user.
     * @param message - the message to be displayed.
     */
    public static void displayMessage(String message){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}