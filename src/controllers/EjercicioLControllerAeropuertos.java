package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EjercicioLControllerAeropuertos implements Initializable{
    
    private EjercicioLControllerLogin ejLControllerLogin; 
    
    @FXML
    private TableColumn<?, ?> lsAno;

    @FXML
    private TableColumn<?, ?> lsCalle;

    @FXML
    private TableColumn<?, ?> lsCapacidad;

    @FXML
    private TableColumn<?, ?> lsCiudad;

    @FXML
    private TableColumn<?, ?> lsId;

    @FXML
    private TableColumn<?, ?> lsNSocios;

    @FXML
    private TableColumn<?, ?> lsNombre;

    @FXML
    private TableColumn<?, ?> lsNumero;

    @FXML
    private TableColumn<?, ?> lsPais;

    @FXML
    private TableView<?> tbAeropuerto;

    @FXML
    private TextField txtFiltro;
    
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void setControlerL(EjercicioLControllerLogin ej) {
    	this.ejLControllerLogin = ej;
    }
    
}
