module org.example.apitest {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires okhttp3;

    opens org.example.apitest to javafx.fxml;
    exports org.example.apitest;


}
