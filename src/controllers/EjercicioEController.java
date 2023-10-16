package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;

public class EjercicioEController implements Initializable{

	@FXML
    private Button bntAgregar;
	
	@FXML
    private TableColumn<Persona, String> lsApellidos;
	
    @FXML
    private TableColumn<Persona, Integer> lsEdad;

    @FXML
    private TableColumn<Persona, String> lsNombre;
    
    @FXML
    private TableView<Persona> tbPersona;
	
    private ObservableList<Persona> o1;
    
    public boolean crearPersona(String nombre, String apellido, int edad) {
    	Persona p = new Persona(nombre, apellido, edad);
    	boolean esta=false;
		if (o1 !=null) {
			//Comprobar si existe en la tabla
			if (o1.contains(p)) {
				esta=true;
			}
		}
		if (esta) {
			return false;
		}else {
			//Crear persona y añadirla a la tabla
			o1.add(p);
			tbPersona.getItems().clear();
			tbPersona.getItems().addAll(o1);
			tbPersona.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
			return true;
            
		}
    }
    

    @FXML
    void agregar(ActionEvent event) {
    	try {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioDfxml2.fxml"));
	    	Stage stage = new Stage();
	    	Parent root = loader.load();
	    	EjercicioDController2 ejDC2 = loader.getController();
	    	//ejDC2.setControlerD(this);
	        stage.setScene(new Scene(root,400,200));
	        stage.setResizable(false);
	        stage.initOwner(this.bntAgregar.getScene().getWindow());
	        stage.setTitle("Nueva Persona");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
    	}catch (Exception e) {
    		e.printStackTrace();
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
