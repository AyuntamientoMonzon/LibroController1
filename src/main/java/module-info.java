module com.aytomonzon.librocontroller1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;

    opens com.aytomonzon.librocontroller1 to javafx.fxml;
    exports com.aytomonzon.librocontroller1;
}
