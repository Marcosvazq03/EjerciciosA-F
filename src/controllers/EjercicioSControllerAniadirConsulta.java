package controllers;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import model.Animal;
import model.Consulta;

public class EjercicioSControllerAniadirConsulta implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Animal> cmbAnimal;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea taObservacion;

    @FXML
    private Text txtError;

    @FXML
    private Text txtTitulo;
    
    private EjercicioSControllerAnimal controller;
    
    public void setController(EjercicioSControllerAnimal controller) {
    	this.controller = controller;
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
    		txtError.setText("");
    		Consulta consulta = new Consulta();
    		ZoneId zoneId = ZoneId.systemDefault();
    		Date date = Date.from(dpFecha.getValue().atStartOfDay(zoneId).toInstant());
    		consulta.setFecha(date);
    		consulta.setObservacion(taObservacion.getText());
    		consulta.setAnimal(cmbAnimal.getSelectionModel().getSelectedItem());
    		
    		if(controller.aniadirConsulta(consulta)) {
    			Animal a = controller.getAnimals().get(cmbAnimal.getSelectionModel().getSelectedIndex());
    			a.addConsulta(consulta);
    			if(a.getFecha_primera_consulta() == null || a.fechaMaxPequenia(consulta.getFecha())) {
    				a.setFecha_primera_consulta(consulta.getFecha());
    				controller.modificarFechaAnimal(a);
    			}
    			click_cancelar(event);
    		}else
    			txtError.setText("No se ha podido insertar consulta.");
    		
    	}else
    		txtError.setText(msg);
    }
    
    /**
     * Validar datos
     * @return
     */
    private String validar() {
    	if(taObservacion.getText().isEmpty())
    		return "No se ha puesto ninguna observacion";
    	System.out.println("Fecha: " +dpFecha.getValue());
    	if(dpFecha.getValue() == null)
    		return "No se selecciona la fecha";
    	return "";
    }
    
    @FXML
    void select_Observacion(ActionEvent event) {}
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	txtTitulo.setText("Añadir Consulta");
    	cmbAnimal.setItems(controller.getAnimals());
    	cmbAnimal.getSelectionModel().selectFirst();
    }

}
