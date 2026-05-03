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
 * JavaFX App
 */
public class App extends Application {
    public static Driver driver;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

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
    public static void displayError(String message){
        Alert alert=new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
    public static boolean hasEmptyFields(String[] fields){
        for (String field : fields) {
            if(field.equals(""))return true;
        }
        return false;
    }
}