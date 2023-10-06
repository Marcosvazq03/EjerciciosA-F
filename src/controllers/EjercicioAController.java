package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EjercicioAController implements Initializable{

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private CheckBox cbDeporte;

    @FXML
    private ComboBox<String> cbEdad;

    @FXML
    private ToggleGroup gpSexo;

    @FXML
    private ListView<String> lvDeportes;

    @FXML
    private RadioButton rbHombre;

    @FXML
    private RadioButton rbMujer;

    @FXML
    private RadioButton rbOtro;

    @FXML
    private Slider sdCine;

    @FXML
    private Slider sdCompras;

    @FXML
    private Slider sdTelevision;

    @FXML
    private TextField txtNumHermanos;

    @FXML
    private TextField txtProfesion;
    
    @FXML
    public void activo(ActionEvent event) {
        try {
        	if (cbDeporte.isSelected()) {
    			lvDeportes.setDisable(false);
    		}else {
    			lvDeportes.setDisable(true);
    		}
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void aceptar(ActionEvent event) {
        try {
        	
        	if (txtProfesion.getText().toString().equals("") || txtNumHermanos.getText().toString().equals("") ) {
        		String errProf = "";
        		if (txtProfesion.getText().toString().equals("")) {
					errProf = "Hay que rellenar el campo profesion\n";
				}
        		
        		String errNumHer = "";
        		if (txtNumHermanos.getText().toString().equals("")) {
					errNumHer = "Hay que rellenar el campo Nº Hermanos\n";
				}
        		
        		String errDepor = "";
        		if (cbDeporte.isSelected()) {
					if (lvDeportes.getSelectionModel().isEmpty()) {
						errDepor = "Tienes que indicar los deportes que practicas";
					}
				}
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TUS DATOS");
                alert.setHeaderText(null);
                alert.setContentText(errProf+errNumHer+errDepor);
                alert.showAndWait();
			}else {
				String prof = txtProfesion.getText().toString()+"\n";
				String numH = txtNumHermanos.getText().toString()+"\n";
				String edad = cbEdad.getSelectionModel().getSelectedItem()+"\n";
				String sexo = "";
				if (rbHombre.isSelected()) {
					sexo = "Hombre\n";
				}
				if (rbMujer.isSelected()) {
					sexo = "Mujer\n";
				}
				if (rbOtro.isSelected()) {
					sexo = "Otro\n";
				}
				String deport="";
				if (cbDeporte.isSelected()) {
					ObservableList<String> lsD = lvDeportes.getSelectionModel().getSelectedItems();
					for (int i = 0; i < lsD.size(); i++) {
						deport = deport + lsD.get(i)+"\n";
					}
					
						
				}
				
				//Ventana de informacion
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("TUS DATOS");
	            alert.setHeaderText(null);
	            alert.setContentText("Profesion: "+prof+"Nº de hermanos: "+numH+"Edad: "+edad+"Sexo: "+sexo+"Deportes que practicas:\n"+deport);
	            alert.showAndWait();
			}
        	
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void cerrar(ActionEvent event) {
        try {
        	//Accion de btnCerrar ventana
        	System.exit(0);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Contenido del comboBox
    	cbEdad.setItems(FXCollections.observableArrayList("Menores de 18","Entre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70"));
    	cbEdad.getSelectionModel().select(0);
    	
    	//Contenido de la lista
    	lvDeportes.setItems(FXCollections.observableArrayList("Tenis","Futbol","Baloncesto","Natacion","Ciclismo","Otro"));
    	
    	//Para seleccionar mas de un valor en la lista
    	lvDeportes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    

}

