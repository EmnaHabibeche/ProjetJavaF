module com.system.projectmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens Entity to javafx.base;

    opens com.system.projectmanagementsystem to javafx.fxml;
    exports com.system.projectmanagementsystem;
}


