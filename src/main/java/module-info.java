module com.example.minisoldiers {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.minisoldiers to javafx.fxml;
    exports com.example.minisoldiers;
    exports com.example.minisoldiers.Unit;
    opens com.example.minisoldiers.Unit to javafx.fxml;
}