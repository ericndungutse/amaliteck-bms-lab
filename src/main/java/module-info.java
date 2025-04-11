module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.accounts;
    opens org.example.demo.accounts to javafx.fxml;
    exports org.example.demo.accountTypes;
    opens org.example.demo.accountTypes to javafx.fxml;
}