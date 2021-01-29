module de.azubiag.SchoolExample {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.azubiag.SchoolExample to javafx.fxml;
    exports de.azubiag.SchoolExample;
}