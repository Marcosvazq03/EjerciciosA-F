package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EjercicioJController implements Initializable{
    
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
    void selectColores(ActionEvent event) {
    	System.out.println(colores.getSelectedToggle().toString());
    	
    	if (colores.getSelectedToggle().toString()=="imgCAzul") {
			imgMAzul.setVisible(true);
		}
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	
    }
}
