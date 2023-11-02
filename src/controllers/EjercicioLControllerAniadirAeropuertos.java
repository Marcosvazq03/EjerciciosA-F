package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class EjercicioLControllerAniadirAeropuertos implements Initializable{
    
    private EjercicioLControllerAeropuertos ejLControllerAeropuerto; 
   
    @FXML
    private Label lbFinanciacion;

    @FXML
    private Label lbNSocios;

    @FXML
    private Label lbNTrabajadores;
    
    @FXML
    private ToggleGroup rgPrivacidad;

    @FXML
    private TextField txtAnio;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtFinanciacion;

    @FXML
    private TextField txtNSocio;

    @FXML
    private TextField txtNTrabajadores;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtPais;
    
    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;

    @FXML
    void clickPrivado(ActionEvent event) {
		lbFinanciacion.setVisible(false);
		lbNTrabajadores.setVisible(false);
		lbNSocios.setVisible(true);
		txtFinanciacion.setVisible(false);
		txtNTrabajadores.setVisible(false);
		txtNSocio.setVisible(true);
    }

    @FXML
    void clickPublico(ActionEvent event) {
    	lbFinanciacion.setVisible(true);
		lbNTrabajadores.setVisible(true);
		lbNSocios.setVisible(false);
		txtFinanciacion.setVisible(true);
		txtNTrabajadores.setVisible(true);
		txtNSocio.setVisible(false);
    }
    
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
    	try {
        	//Comprobar que en un numero
    		boolean esNumNumero = esNumero(txtNumero);
        	boolean esNumAnio = esNumero(txtAnio);
        	boolean esNumCapacidad = esNumero(txtCapacidad);
        	boolean esNumNTrabajadores = esNumero(txtNTrabajadores);
        	boolean esNumNSocios = esNumero(txtNSocio);
        	boolean esNumFinanciacion=true;
        	if (!txtFinanciacion.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
        		esNumFinanciacion=false;
			}
        	
        	//Alerta introducir todos los datos
        	if (rbPublico.isSelected()) {
        		if (txtNombre.getText().toString().equals("") || txtPais.getText().toString().equals("") 
        				|| txtCiudad.getText().toString().equals("") || txtCalle.getText().toString().equals("") 
        				|| esNumNumero==false || esNumAnio==false || esNumCapacidad==false || esNumFinanciacion==false 
        				|| esNumNTrabajadores==false) {
            		String err = "";
        			if (txtNombre.getText().toString().equals("") || txtPais.getText().toString().equals("") 
        				|| txtCiudad.getText().toString().equals("") || txtCalle.getText().toString().equals("")) {
						err="Rellenar todos los campos\n";
					}
        			String err2 = "";
        			if (esNumNumero==false || esNumAnio==false || esNumCapacidad==false || esNumFinanciacion==false 
            				|| esNumNTrabajadores==false) {
						err2="Los campos no tienen el correcto formato";
					}
            		
            		Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("TUS DATOS");
                    alert.setHeaderText(null);
                    alert.setContentText(err+err2);
                    alert.showAndWait();
    			}else {
    				/*if (ejLControllerAeropuerto.isModificar()) {
    					ejLControllerAeropuerto.modificarPersona(txtNombre.getText().toString(), txtApellido.getText().toString(), Integer.parseInt(txtEdad.getText().toString()));
    					
    					//Ventana de informacion
    		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		            alert.setTitle("Info");
    		            alert.setHeaderText(null);
    		            alert.setContentText("Persona editada correctamente");
    		            alert.showAndWait();
    		          
    		            //Cerrar ventana modal
    		        	//Me devuelve el elemento al que hice click
    		        	Node source = (Node) event.getSource();     
    		        	//Me devuelve la ventana donde se encuentra el elemento
    		        	Stage stage = (Stage) source.getScene().getWindow();    
    		        	stage.close();
    				}else {*/
    					if (ejLControllerAeropuerto.crearAeropuerto(txtNombre.getText().toString(), txtApellido.getText().toString(),Integer.parseInt(txtEdad.getText().toString()))) {
    						//Ventana de informacion
    			        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    			            alert.setTitle("Info");
    			            alert.setHeaderText(null);
    			            alert.setContentText("Aeropuerto añadido correctamente");
    			            alert.showAndWait();
    			          
    			            //Cerrar ventana modal
    			        	//Me devuelve el elemento al que hice click
    			        	Node source = (Node) event.getSource();     
    			        	//Me devuelve la ventana donde se encuentra el elemento
    			        	Stage stage = (Stage) source.getScene().getWindow();    
    			        	stage.close();
    					}else {
    						//Alerta persona existe en la tabla
    						Alert alert = new Alert(Alert.AlertType.ERROR);
    		                alert.setTitle("TUS DATOS");
    		                alert.setHeaderText(null);
    		                alert.setContentText("Aeropuerto ya existe!");
    		                alert.showAndWait();
    					}
    				//}
    			}
			}        	
        } catch (NumberFormatException e) {
        	//Ventana error formato no numerico
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	/*if (ejLControllerAeropuerto!=null) {
    		if (ejLControllerAeropuerto.isModificar()) {
				txtNombre.setText(ejLControllerAeropuerto.getTbPersona().getSelectionModel().getSelectedItem().getNombre());
				txtApellido.setText(ejLControllerAeropuerto.getTbPersona().getSelectionModel().getSelectedItem().getApellido());
				txtEdad.setText(ejLControllerAeropuerto.getTbPersona().getSelectionModel().getSelectedItem().getEdad()+"");
			}
		}*/
    }
    
    public void setControlerL(EjercicioLControllerAeropuertos ej) {
    	this.ejLControllerAeropuerto= ej;
    }
    
}
