package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class EjercicioLControllerAniadirAeropuertos implements Initializable{
    
    private EjercicioLControllerAeropuertos ejLControllerAeropuerto; 
   
    @FXML
    private ImageView imageSelected;
    
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
    
    private InputStream imageBinary = null;

    protected InputStream seleccionarImagen(boolean editar) {
    	InputStream imageBinary = null;
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = new Stage();
    	fileChooser.setTitle("Seleccionar Imagen ");
    	ExtensionFilter jpgFilter = new ExtensionFilter("Imagen JPG (*.jpg)", "*.jpg");
    	fileChooser.getExtensionFilters().add(jpgFilter);
    	File imageFile = fileChooser.showOpenDialog(stage);
    	if(imageFile != null) {
    		try {
    			Image img = new Image(imageFile.toURI().toString());
				imageBinary = new FileInputStream(imageFile);
				imageSelected.setVisible(true);
	    		imageSelected.setImage(img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	}else {
    		if(!editar) {
	    		imageSelected.setVisible(false);
	    		imageSelected.setImage(null);
    		}
    	}
    	return imageBinary;
    }
    
	@FXML
    void click_select_imagen(ActionEvent event) {
		imageBinary = seleccionarImagen(false);
	}
    
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
    	
    	//Comprobar que en un numero
		boolean esNumNumero = esNumero(txtNumero);
    	boolean esNumAnio = esNumero(txtAnio);
    	boolean esNumCapacidad = esNumero(txtCapacidad);
    	boolean esNumNTrabajadores = true;
    	boolean esNumNSocios = true;
    	boolean esNumFinanciacion=true;
    	
    	
    	//Alerta introducir todos los datos
    	boolean publico=true;
    	boolean mal = false;
    	if (rbPublico.isSelected()) {
    		if (!txtFinanciacion.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
        		esNumFinanciacion=false;
			}
    		esNumNTrabajadores = esNumero(txtNTrabajadores);
			if (txtFinanciacion.getText().equals("") || txtNTrabajadores.getText().equals("")){
				mal=true;
			}
			txtNSocio.setText("0");
		}
    	
    	if (rbPrivado.isSelected()) {
    		publico=false;
    		esNumNSocios = esNumero(txtNSocio);
    		if (txtNSocio.getText().equals("")){
				mal=true;
			}
    		txtFinanciacion.setText("0");
    		txtNTrabajadores.setText("0");
    	}
		if (txtNombre.getText().toString().equals("") || txtPais.getText().toString().equals("") 
				|| txtCiudad.getText().toString().equals("") || txtCalle.getText().toString().equals("") 
				|| esNumNumero==false || esNumAnio==false || esNumCapacidad==false || esNumFinanciacion==false 
				|| esNumNTrabajadores==false || esNumNSocios==false || mal) {
    		String err = "";
			if (txtNombre.getText().toString().equals("") || txtPais.getText().toString().equals("") 
				|| txtCiudad.getText().toString().equals("") || txtCalle.getText().toString().equals("") || mal) {
				err="Rellenar todos los campos\n";
			}
			String err2 = "";
			if (esNumNumero==false || esNumAnio==false || esNumCapacidad==false || esNumFinanciacion==false 
    				|| esNumNTrabajadores==false || esNumNSocios==false) {
				err2="Los campos no tienen el correcto formato";
			}
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TUS DATOS");
            alert.setHeaderText(null);
            alert.setContentText(err+err2);
            alert.showAndWait();
		}else {
			if (ejLControllerAeropuerto.isModificar()) {
				ejLControllerAeropuerto.modificarAeropuerto(txtNombre.getText().toString(), txtPais.getText().toString(), txtCiudad.getText().toString(), 
						txtCalle.getText().toString(), Integer.parseInt(txtNumero.getText().toString()), Integer.parseInt(txtAnio.getText().toString()), 
						Integer.parseInt(txtCapacidad.getText().toString()), publico, Integer.parseInt(txtFinanciacion.getText().toString()), 
						Integer.parseInt(txtNTrabajadores.getText().toString()), Integer.parseInt(txtNSocio.getText().toString()));
				
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
			}else {
				if (ejLControllerAeropuerto.crearAeropuerto(txtNombre.getText().toString(), txtPais.getText().toString(), txtCiudad.getText().toString(), 
						txtCalle.getText().toString(), Integer.parseInt(txtNumero.getText().toString()), Integer.parseInt(txtAnio.getText().toString()), 
						Integer.parseInt(txtCapacidad.getText().toString()), publico, Integer.parseInt(txtFinanciacion.getText().toString()), 
						Integer.parseInt(txtNTrabajadores.getText().toString()), Integer.parseInt(txtNSocio.getText().toString()), imageBinary)) {
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
			}
		}
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	if (ejLControllerAeropuerto!=null) {
    		if (ejLControllerAeropuerto.isModificar()) {
				txtNombre.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getNombre());
				txtPais.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getPais());
				txtCiudad.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getCiudad());
				txtCalle.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getCalle());
				txtNumero.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getNumero()+"");
				txtAnio.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getAno()+"");
				txtCapacidad.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getCapacidad()+"");
				txtNSocio.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getNSocios()+"");
				txtFinanciacion.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getFinanciacion()+"");
				txtNTrabajadores.setText(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getNTrabajadores()+"");
				if (ejLControllerAeropuerto.getRbPrivados().isSelected()) {
					rbPrivado.setSelected(true);
					rbPublico.setSelected(false);
					clickPrivado(null);
				}
				rbPrivado.setDisable(true);
				rbPublico.setDisable(true);
			}
    		if(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getImage() != null) {
		 		imageSelected.setImage(new Image(ejLControllerAeropuerto.getTbAeropuerto().getSelectionModel().getSelectedItem().getImage()));
		 	}
		}
    	
    }
    
    public void setControlerL(EjercicioLControllerAeropuertos ej) {
    	this.ejLControllerAeropuerto= ej;
    }
    
}
