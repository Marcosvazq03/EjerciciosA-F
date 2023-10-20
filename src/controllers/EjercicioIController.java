package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;

public class EjercicioIController implements Initializable{

	@FXML
    private TextField txtFiltro;
	
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
    
    private boolean modificar;
    
	private PersonaDao pD;

    
    // Crear un FilteredList respaldado por la lista de objetos
    FilteredList<Persona> filteredList;
	
    public boolean isModificar() {
		return modificar;
	}

	public TableView<Persona> getTbPersona() {
		return tbPersona;
	}

	private ObservableList<Persona> o1;
    
	
	// Metodos de Persona
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
			pD.insertPersona(pD.ultimoID(), nombre, apellido, edad);
			o1.add(p);
			
			return true;
		}
    }
    
    public void modificarPersona(String nombre, String apellido, int edad) {
    	//Modificar Persona de la tabla
    	Persona p = new Persona(nombre, apellido, edad);
    	for (int i = 0; i < o1.size(); i++) {
			if (tbPersona.getSelectionModel().getSelectedItem()==o1.get(i)) {
				pD.modPersona(tbPersona.getSelectionModel().getSelectedItem().getNombre(),nombre, apellido, edad);
				o1.set(i, p);
			}
		}
    }
    
    @FXML
    void agregar(ActionEvent event) {
    	modificar=false;
    	try {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioIfxml2.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioIController2 ejHC2 = new EjercicioIController2();
	    	loader.setController(ejHC2);
	    	Parent root = loader.load();
	    	EjercicioIController2 ejIC2 = loader.getController();
	    	ejIC2.setControlerI(this);
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
    
    @FXML
    void eliminar(ActionEvent event) {
    	//Comprobar que hay seleccionado una persona en la tabla
    	if (tbPersona.getSelectionModel().isEmpty()) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has seleccionado ninguna persona de la tabla!");
            alert.showAndWait();
		}else {
			//Eliminar Persona de la tabla
	    	for (int i = 0; i < o1.size(); i++) {
				if (tbPersona.getSelectionModel().getSelectedItem()==o1.get(i)) {
					pD.elimPersona(tbPersona.getSelectionModel().getSelectedItem().getNombre());
					o1.remove(i);
				}
			}
			
			//Ventana de informacion
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Info");
	        alert.setHeaderText(null);
	        alert.setContentText("Persona eliminada correctamente");
	        alert.showAndWait();
	        
		}
    }

    @FXML
    void modificar(ActionEvent event) {
    	//Comprobar que hay seleccionado una persona en la tabla
    	if (tbPersona.getSelectionModel().isEmpty()) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has seleccionado ninguna persona de la tabla!");
            alert.showAndWait();
		}else {
			modificar=true;
			try {
	    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioIfxml2.fxml"));
		    	Stage stage = new Stage();
		    	EjercicioIController2 ejHC2 = new EjercicioIController2();
		    	loader.setController(ejHC2);
		    	ejHC2.setControlerI(this);
		    	Parent root = loader.load();
		        stage.setScene(new Scene(root,400,200));
		        stage.setResizable(false);
		        stage.initOwner(this.bntAgregar.getScene().getWindow());
		        stage.setTitle("Editar Persona");
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
	    	}catch (Exception e) {
	    		e.printStackTrace();
			}
		}
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Valores de la columna de la tabla
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
    	lsApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
    	lsEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    	
    	o1 = FXCollections.observableArrayList();
    	
    	pD = new PersonaDao();
    	
    	o1.setAll(pD.cargarPersonas());
    			
    	modificar=false;
    	
    	txtFiltro.setPromptText("Buscar...");
    	
    	filteredList = new FilteredList<Persona>(o1, b -> true);
    	
    	// Agregar un ChangeListener a la propiedad text del TextField
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            // Actualizar el predicado de filtrado con el nuevo valor del TextField
            filteredList.setPredicate(objeto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos los objetos si no hay texto en el TextField
                }
                // Comparar el valor del TextField con la propiedad del objeto
                return objeto.getNombre().contains(newValue);
            });
        });
        
        SortedList<Persona> sortedData = new SortedList<Persona>(filteredList);
    	sortedData.comparatorProperty().bind(tbPersona.comparatorProperty());
    	
    	tbPersona.setItems(sortedData);
    }
}
