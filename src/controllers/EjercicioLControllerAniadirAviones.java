package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class EjercicioLControllerAniadirAviones implements Initializable{
    
    private EjercicioLControllerAeropuertos ejLControllerAeropuerto; 
    
    @FXML
    private ComboBox<String> cbAeropuertos;
   
    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup rgPrivacidad;

    @FXML
    private TextField txtAsientos;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtVelMax;

    
    @FXML
    void cancelar(ActionEvent event) {
    	//Cerrar ventana modal
    	//Me devuelve el elemento al que hice click
    	Node source = (Node) event.getSource();     
    	//Me devuelve la ventana donde se encuentra el elemento
    	Stage stage = (Stage) source.getScene().getWindow();    
    	stage.close();
    }

    private boolean esNumero(TextField txt) {
    	boolean esNum = true;
    	try {
            Integer.parseInt(txt.getText().toString());
        } catch (NumberFormatException excepcion) {
            esNum = false;
        }
    	return esNum;
    }
    
    @FXML
    void guardar(ActionEvent event) {
    	
    	//Comprobar que en un numero
		boolean esNumAsien = esNumero(txtAsientos);
    	boolean esNumVelM = esNumero(txtVelMax);
    	
		if (txtModelo.getText().toString().equals("") || txtAsientos.getText().toString().equals("") 
				|| txtVelMax.getText().toString().equals("") || esNumAsien==false || esNumVelM==false) {
    		String err = "";
			if (txtModelo.getText().toString().equals("") || txtAsientos.getText().toString().equals("") 
					|| txtVelMax.getText().toString().equals("")) {
				err="Rellenar todos los campos\n";
			}
			String err2 = "";
			if (esNumAsien==false || esNumVelM==false) {
				err2="Los campos no tienen el correcto formato";
			}
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TUS DATOS");
            alert.setHeaderText(null);
            alert.setContentText(err+err2);
            alert.showAndWait();
		}else {
			/*if (ejLControllerAeropuerto.isModificar()) {
				ejLControllerAeropuerto.modificarAeropuerto();
				
				//Ventana de informacion
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Info");
	            alert.setHeaderText(null);
	            alert.setContentText("Aeropuerto editado correctamente");
	            alert.showAndWait();
	          
	            //Cerrar ventana modal
	        	//Me devuelve el elemento al que hice click
	        	Node source = (Node) event.getSource();     
	        	//Me devuelve la ventana donde se encuentra el elemento
	        	Stage stage = (Stage) source.getScene().getWindow();    
	        	stage.close();
			}else {*/
				int siActivado = 1;
				if (rbDesactivado.isSelected()) {
					siActivado=0;
				}
				ejLControllerAeropuerto.crearAvion(txtModelo.getText().toString(), Integer.parseInt(txtAsientos.getText().toString()), Integer.parseInt(txtVelMax.getText().toString()), siActivado);
				
				//Ventana de informacion
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Info");
	            alert.setHeaderText(null);
	            alert.setContentText("Avion añadido correctamente");
	            alert.showAndWait();
	          
	            //Cerrar ventana modal
	        	//Me devuelve el elemento al que hice click
	        	Node source = (Node) event.getSource();     
	        	//Me devuelve la ventana donde se encuentra el elemento
	        	Stage stage = (Stage) source.getScene().getWindow();    
	        	stage.close();
			}
		//}
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	//Contenido del comboBox
    	cbAeropuertos.setItems(FXCollections.observableArrayList("Menores de 18","Entre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70"));
    	cbAeropuertos.getSelectionModel().select(0);
    	/*if (ejLControllerAeropuerto!=null) {
    		if (ejLControllerAeropuerto.isModificar()) {
				
			}
		}*/
    }
    
    public void setControlerL(EjercicioLControllerAeropuertos ej) {
    	this.ejLControllerAeropuerto= ej;
    }
    
}
