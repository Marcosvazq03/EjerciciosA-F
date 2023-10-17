package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EjercicioEController2 implements Initializable{

	@FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;
    
    private static EjercicioEController ejEController;

    @FXML
    void cancelar(ActionEvent event) {
    	//Cerrar ventana modal
    	//Me devuelve el elemento al que hice click
    	Node source = (Node) event.getSource();     
    	//Me devuelve la ventana donde se encuentra el elemento
    	Stage stage = (Stage) source.getScene().getWindow();    
    	stage.close();
    }

    @FXML
    void guardar(ActionEvent event) {
    	try {
        	//Comprobar que en un numero
    		boolean resultado = true;
        	try {
                Integer.parseInt(txtEdad.getText().toString());
            } catch (NumberFormatException excepcion) {
                resultado = false;
            }
        	//Alerta introducir todos los datos
        	if (txtNombre.getText().toString().equals("") || resultado==false || txtApellido.getText().toString().equals("") || txtEdad.getText().toString().equals("")) {
        		String errNombre = "";
        		if (txtNombre.getText().toString().equals("")) {
					errNombre = "El campo nombre es obligatorio\n";
				}
        		
        		String errApellido = "";
        		if (txtApellido.getText().toString().equals("")) {
					errApellido = "El campo apellido es obligatorio\n";
				}
				
        		String errEdad = "";
        		if (txtEdad.getText().toString().equals("")) {
					errEdad = "El campo edad es obligatorio";
				}else {
					if (resultado==false) {
						errEdad = "El campo edad tiene que ser numerico";
					}
				}
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TUS DATOS");
                alert.setHeaderText(null);
                alert.setContentText(errNombre+errApellido+errEdad);
                alert.showAndWait();
			}else {
				if (ejEController.isModificar()) {
					ejEController.modificarPersona(txtNombre.getText().toString(), txtApellido.getText().toString(), Integer.parseInt(txtEdad.getText().toString()));
					
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
				}else {
					if (ejEController.crearPersona(txtNombre.getText().toString(), txtApellido.getText().toString(),Integer.parseInt(txtEdad.getText().toString()))) {
						//Ventana de informacion
			        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
			            alert.setTitle("Info");
			            alert.setHeaderText(null);
			            alert.setContentText("Persona añadida correctamente");
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
		                alert.setContentText("Persona ya existe!");
		                alert.showAndWait();
					}
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
    	if (ejEController!=null) {
    		if (ejEController.isModificar()) {
				txtNombre.setText(ejEController.getTbPersona().getSelectionModel().getSelectedItem().getNombre());
				txtApellido.setText(ejEController.getTbPersona().getSelectionModel().getSelectedItem().getApellido());
				txtEdad.setText(ejEController.getTbPersona().getSelectionModel().getSelectedItem().getEdad()+"");
			}
		}
    }
    
    public void setControlerE(EjercicioEController ej) {
    	this.ejEController = ej;
    }
    
}
