package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class EjercicioLControllerAniadirAviones implements Initializable{
    
    private EjercicioLControllerAeropuertos ejLControllerAeropuerto; 
    
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Label txtTitulo;
    
    @FXML
    private ComboBox<String> cbAeropuertos;
    
    @FXML
    private ComboBox<String> cbAviones;
   
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

    private AeropuertoDao aD;
    
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
    	if (ejLControllerAeropuerto.isModificar()) {
			int activado=0;
			if (rbActivado.isSelected()) {
				activado=1;
			}
			ejLControllerAeropuerto.modificarAvion(cbAviones.getSelectionModel().getSelectedItem(),activado);
			
			//Ventana de informacion
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Avion editado correctamente");
            alert.showAndWait();
          
            //Cerrar ventana modal
        	//Me devuelve el elemento al que hice click
        	Node source = (Node) event.getSource();     
        	//Me devuelve la ventana donde se encuentra el elemento
        	Stage stage = (Stage) source.getScene().getWindow();    
        	stage.close();
		}else {
			if (ejLControllerAeropuerto.isBorrar()) {
				ejLControllerAeropuerto.borrarAvion(cbAviones.getSelectionModel().getSelectedItem());
				
				//Ventana de informacion
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Info");
	            alert.setHeaderText(null);
	            alert.setContentText("Avion borrado correctamente");
	            alert.showAndWait();
	          
	            //Cerrar ventana modal
	        	//Me devuelve el elemento al que hice click
	        	Node source = (Node) event.getSource();     
	        	//Me devuelve la ventana donde se encuentra el elemento
	        	Stage stage = (Stage) source.getScene().getWindow();    
	        	stage.close();
			}else {
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
					int siActivado = 1;
					if (rbDesactivado.isSelected()) {
						siActivado=0;
					}
					ejLControllerAeropuerto.crearAvion(txtModelo.getText().toString(), Integer.parseInt(txtAsientos.getText().toString()), Integer.parseInt(txtVelMax.getText().toString()), siActivado, aD.buscarIDNombre(cbAeropuertos.getSelectionModel().getSelectedItem()));
					
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
			}
		}
    }
    
    @FXML
    void cambiarAviones(ActionEvent event) {
    	//Contenido del comboBox
    	cbAviones.setItems(FXCollections.observableArrayList(aD.cargarAviones(aD.buscarIDNombre(cbAeropuertos.getSelectionModel().getSelectedItem()))));
    	cbAviones.getSelectionModel().select(0);
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	aD = new AeropuertoDao();
    	
    	//Contenido del comboBox
    	cbAeropuertos.setItems(FXCollections.observableArrayList(aD.cargarAeropuertos()));
    	cbAeropuertos.getSelectionModel().selectFirst();
    	
    	if (ejLControllerAeropuerto!=null) {
    		if (ejLControllerAeropuerto.isModificar()) {
    			//Contenido del comboBox
    	    	cambiarAviones(null);
			}
    		if (ejLControllerAeropuerto.isBorrar()) {
    			//Contenido del comboBox
    	    	cambiarAviones(null);
    	    	
    	    	txtTitulo.setText("BORRAR AVION");
    	    	rbActivado.setVisible(false);
    	    	rbDesactivado.setVisible(false);
    	    	btnGuardar.setText("Borrar");
			}
		}
    }
    
    public void setControlerL(EjercicioLControllerAeropuertos ej) {
    	this.ejLControllerAeropuerto= ej;
    }
    
}
