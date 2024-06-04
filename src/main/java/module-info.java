module org.example.modul6tugas {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.modul6tugas to javafx.fxml;
    opens data to javafx.base;
    opens books to javafx.base;
    exports org.example.modul6tugas;

}