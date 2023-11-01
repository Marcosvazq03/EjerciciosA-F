package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import dao.PersonaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;

public class EjercicioLControllerLogin implements Initializable{

	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;
    
    private AeropuertoDao aeDao;

    @FXML
    void login(ActionEvent event) {
    	if (txtUsuario.getText().toString().equals("") || txtPassword.getText().toString().equals("")) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has introducido usuario o password!");
            alert.showAndWait();
		}else {
			if (aeDao.validarUser(txtUsuario.getText().toString(), txtPassword.getText().toString())) {
				try {
					//Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage2 = (Stage) source.getScene().getWindow();    
		        	stage2.close();
		        	
		        	//Abrir ventana modal
		    		FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/EjercicioLfxmlAeropuertos.fxml"));
			    	Stage stage = new Stage();
			    	EjercicioLControllerAeropuertos ejLC = new EjercicioLControllerAeropuertos();
			    	loader.setController(ejLC);
			    	Parent root = loader.load();
			    	EjercicioLControllerAeropuertos ejLC2 = loader.getController();
			    	ejLC2.setControlerL(this);
			        stage.setScene(new Scene(root,800,500));
			        //stage.setResizable(false);
			        stage.initOwner(this.btnLogin.getScene().getWindow());
			        stage.setTitle("Nueva Persona");
			        stage.initModality(Modality.APPLICATION_MODAL);
			        stage.show();
		    	}catch (Exception e) {
		    		e.printStackTrace();
				}
			}else {
				//Ventana error
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("Usuario o password incorrectos!");
	            alert.showAndWait();
			}
		}
    	
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	aeDao = new AeropuertoDao();
    }
}
