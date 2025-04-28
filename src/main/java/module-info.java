module com.lab.serializationtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.lab.serializationtest to javafx.fxml;
    exports com.lab.serializationtest;
}