package controllers;

import java.io.ByteArrayInputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import model.Animal;

public class EjercicioSControllerEditarAnimal implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnSelImagen;
    
    @FXML
    private ImageView ivAnimal;

    @FXML
    private RadioButton rbtnHombre;

    @FXML
    private RadioButton rbtnMujer;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfEspecie;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPeso;

    @FXML
    private TextField tfRaza;

    @FXML
    private ToggleGroup tgSexo;

    @FXML
    private Text txtError;

    @FXML
    private Text txtTitulo;

    byte[] imageBinary = null;
    
    private Animal animal;
    private EjercicioSControllerAnimal controller;
    
    public void setController(EjercicioSControllerAnimal controller) {
    	this.controller = controller;
    }
    
    public void setAnimal(Animal animal) {
    	this.animal = animal;
    }

    /**
     * Cancelar
     * @param event
     */
    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }

    /**
     * Guardar
     * @param event
     */
    @FXML
    void click_guardar(ActionEvent event) {
    	String msg = validar();
    	if(msg.isEmpty()) {
    		
    		 animal.setNombre(tfNombre.getText());
    		 animal.setSexo(rbtnHombre.isSelected()? 'M':'F');
    		 animal.setEdad(Integer.parseInt(tfEdad.getText()));
    		 animal.setPeso(Float.parseFloat(tfPeso.getText()));
    		 animal.setImagen(imageBinary);
    		 animal.setRaza(tfRaza.getText());
    		 animal.setEspecie(tfEspecie.getText());
    		 
    		if(controller.editarAnimal(animal)) {
    			click_cancelar(event);
    		}else
    			txtError.setText("No se ha podido editar el animal.");
    	}else
    		txtError.setText(msg);
    }

    /**
     * Seleccionar imagen
     * @param event
     */
    @FXML
    void click_select_imagen(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = new Stage();
    	fileChooser.setTitle("Seleccionar Imagen ");
    	ExtensionFilter jpgFilter = new ExtensionFilter("Imagen JPG (*.jpg)", "*.jpg");
    	fileChooser.getExtensionFilters().add(jpgFilter);
    	File imageFile = fileChooser.showOpenDialog(stage);
    	if(imageFile != null) {
    		try {
    			Image img = new Image(imageFile.toURI().toString());
				InputStream imageInputStream = new FileInputStream(imageFile);
				imageBinary = imageInputStream.readAllBytes();
				imageInputStream.close();
				ivAnimal.setVisible(true);
				ivAnimal.setImage(img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    /**
     * Validar datos
     * @return
     */
    private String validar() {
    	if(tfNombre.getText().isEmpty() || tfEdad.getText().isEmpty() || tfPeso.getText().isEmpty() || tfRaza.getText().isEmpty() || tfEspecie.getText().isEmpty())
    		return "Algo campo esta vacio.";
    	try {
			Integer.parseInt(tfEdad.getText());
			Float.parseFloat(tfPeso.getText());
		} catch (Exception e) {
			return "Alguno de los campos numerico, no es numerico";
		}
    	return "";
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	txtTitulo.setText("Editar Animal");
    	tfNombre.setText(animal.getNombre());
    	
    	if(animal.getSexo() == 'F')
    		rbtnMujer.setSelected(true);
    		
    	tfEdad.setText(animal.getEdad() + "");
    	tfPeso.setText(animal.getPeso() + " ");
    	tfRaza.setText(animal.getRaza());
    	tfEspecie.setText(animal.getEspecie());
    	
    	if(animal.getImagen() != null) {
    		InputStream image = new ByteArrayInputStream(animal.getImagen());
	 		ivAnimal.setImage(new Image(image));
    	}
    	
    }
    
}
