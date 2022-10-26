module uia.com.agendafx.agendafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens uia.com.agendafx.agendafx to javafx.fxml;
    exports uia.com.agendafx.agendafx;
}