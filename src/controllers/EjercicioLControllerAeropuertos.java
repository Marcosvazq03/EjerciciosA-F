package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonaDao;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Persona;

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
    	//Valores de la columna de la tabla
    	lsId.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
    	
    	lsEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    	
    	o1 = FXCollections.observableArrayList();
    	
    	pD = new PersonaDao();
    	
    	o1.setAll(pD.cargarPersonas());
    			
    	modificar=false;
    	
    	txtFiltro.setPromptText("Buscar...");
    	
    	filteredList = new FilteredList<Persona>(o1, b -> true);
    	
    	// Agregar un ChangeListener a la propiedad text del TextField
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            // Actualizar el predicado de filtrado con el nuevo valor del TextField
            filteredList.setPredicate(objeto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos los objetos si no hay texto en el TextField
                }
                // Comparar el valor del TextField con la propiedad del objeto
                return objeto.getNombre().contains(newValue);
            });
        });
        
        SortedList<Persona> sortedData = new SortedList<Persona>(filteredList);
    	sortedData.comparatorProperty().bind(tbPersona.comparatorProperty());
    	
    	tbPersona.setItems(sortedData);
    }
    
    public void setControlerL(EjercicioLControllerLogin ej) {
    	this.ejLControllerLogin = ej;
    }
    
}
