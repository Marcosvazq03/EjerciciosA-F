package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;

public class EjercicioCController implements Initializable{

	@FXML
    private TableColumn<Persona, String> lsApellidos;
	
    @FXML
    private TableColumn<Persona, Integer> lsEdad;

    @FXML
    private TableColumn<Persona, String> lsNombre;
    
    @FXML
    private TableView<Persona> tbPersona;
	
	@FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;
    
    private ObservableList<Persona> o1;

    @FXML
    void agregar(ActionEvent event) {
    	try {
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
				boolean esta=false;
				if (o1 !=null) {
					//Comprobar si existe en la tabla
					for (int i = 0; i < o1.size(); i++) {
						if (o1.get(i).getNombre().equals(txtNombre.getText().toString()) && o1.get(i).getApellido().equals(txtApellido.getText().toString()) &&  o1.get(i).getEdad() == Integer.parseInt(txtEdad.getText().toString())) {
							esta=true;
						}
					}
				}
				if (esta) {
					//Alerta persona existe en la tabla
					Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setTitle("TUS DATOS");
	                alert.setHeaderText(null);
	                alert.setContentText("Persona ya existe!");
	                alert.showAndWait();
				}else {
					//Crear persona y añadirla a la tabla
					Persona p1 = new Persona(txtNombre.getText().toString(), txtApellido.getText().toString(),Integer.parseInt(txtEdad.getText().toString()));
					o1.add(p1);
					tbPersona.getItems().clear();
					tbPersona.getItems().addAll(o1);
					tbPersona.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Persona añadida correctamente");
		            alert.showAndWait();
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Valores de la columna de la tabla
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
    	lsApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
    	lsEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    	
    	o1= FXCollections.observableArrayList();
    }
}
