module cherrybombradical.dollhouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens cherrybombradical.dollhouse to javafx.fxml;
    exports cherrybombradical.dollhouse;
}