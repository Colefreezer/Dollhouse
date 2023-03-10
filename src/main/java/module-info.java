module cherrybombradical.dollhouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens cherrybombradical.dollhouse to javafx.fxml;
    exports cherrybombradical.dollhouse;
    exports cherrybombradical.dollhouse.hello;
    opens cherrybombradical.dollhouse.hello to javafx.fxml;
}