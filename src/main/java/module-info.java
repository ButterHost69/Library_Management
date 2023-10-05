module com.example.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.librarymanagement to javafx.fxml;
    exports com.example.librarymanagement;
    exports com.example.librarymanagement.Controllers;
    exports com.example.librarymanagement.Controllers.LibraryMembersControllers;
    exports com.example.librarymanagement.Logic;
}