package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class EjercicioJController implements Initializable{
    
	@FXML
    private Button btnApagar;

    @FXML
    private Button btnEncender;
	
	@FXML
    private ToggleGroup colores;
	
	@FXML
    private ImageView imgCAmarillo;

    @FXML
    private ImageView imgCAzul;

    @FXML
    private ImageView imgCAzulOsc;

    @FXML
    private ImageView imgCBlanco;

    @FXML
    private ImageView imgCGris;

    @FXML
    private ImageView imgCGrisOsc;

    @FXML
    private ImageView imgCNegro;

    @FXML
    private ImageView imgCRojo;

    @FXML
    private ImageView imgLuces;

    @FXML
    private ImageView imgLucesA;

    @FXML
    private ImageView imgLucesE;

    @FXML
    private ImageView imgMAmarillo;

    @FXML
    private ImageView imgMAzul;

    @FXML
    private ImageView imgMAzulOsc;

    @FXML
    private ImageView imgMBlanco;

    @FXML
    private ImageView imgMGris;

    @FXML
    private ImageView imgMGrisOsc;

    @FXML
    private ImageView imgMNegro;

    @FXML
    private ImageView imgMRojo;
	
    @FXML
    void apagar(ActionEvent event) {
    	btnApagar.setVisible(false);
    	btnEncender.setVisible(true);
    	imgLuces.setVisible(true);
    }

    @FXML
    void encender(ActionEvent event) {
    	btnApagar.setVisible(true);
    	btnEncender.setVisible(false);
    	imgLuces.setVisible(false);
    }
    
    @FXML
    void selectColores(ActionEvent event) {
    	if (colores.getSelectedToggle()!=null) {
    		ToggleButton selectedRadioButton = (ToggleButton) colores.getSelectedToggle();
	    	String toogleGroupValue = selectedRadioButton.getText();
	    	imgMRojo.setVisible(false);
	    	imgMAzul.setVisible(false);
	    	imgMAzulOsc.setVisible(false);
	    	imgMNegro.setVisible(false);
	    	imgMBlanco.setVisible(false);
	    	imgMGris.setVisible(false);
	    	imgMGrisOsc.setVisible(false);
	    	imgMAmarillo.setVisible(false);
	    	if (toogleGroupValue.equals("rojo")) {
				imgMRojo.setVisible(true);
			}
	    	if (toogleGroupValue.equals("azul")) {
				imgMAzul.setVisible(true);
			}
	    	if (toogleGroupValue.equals("azulOsc")) {
				imgMAzulOsc.setVisible(true);
			}
	    	if (toogleGroupValue.equals("negro")) {
				imgMNegro.setVisible(true);
			}
	    	if (toogleGroupValue.equals("blanco")) {
				imgMBlanco.setVisible(true);
			}
	    	if (toogleGroupValue.equals("gris")) {
				imgMGris.setVisible(true);
			}
	    	if (toogleGroupValue.equals("grisOsc")) {
				imgMGrisOsc.setVisible(true);
			}
	    	if (toogleGroupValue.equals("amarillo")) {
				imgMAmarillo.setVisible(true);
			}
		}
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
