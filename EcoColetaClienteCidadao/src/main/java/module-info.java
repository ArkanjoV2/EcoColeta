module com.example.ecocoletaclientecidadao {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.ecocoleta.model to javafx.fxml;
    exports br.com.ecocoleta.model;
    exports br.com.ecocoleta.cliente;
    opens br.com.ecocoleta.cliente to javafx.fxml;
}