module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.junit.jupiter.api;

    opens com.example to javafx.fxml,com.google.gson,org.junit.jupiter.api;
    exports com.example;
}
