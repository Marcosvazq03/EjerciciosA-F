package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Aeropuerto;
import model.Persona;

public class EjercicioLControllerAeropuertos implements Initializable{
    
    private EjercicioLControllerLogin ejLControllerLogin; 
    
    @FXML
    private TableColumn<Aeropuerto, Integer> lsAno;

    @FXML
    private TableColumn<Aeropuerto, String> lsCalle;

    @FXML
    private TableColumn<Aeropuerto, Integer> lsCapacidad;

    @FXML
    private TableColumn<Aeropuerto, String> lsCiudad;

    @FXML
    private TableColumn<Aeropuerto, Integer> lsId;

    @FXML
    private TableColumn<Aeropuerto, Integer> lsNSocios;

    @FXML
    private TableColumn<Aeropuerto, String> lsNombre;

    @FXML
    private TableColumn<Aeropuerto, Integer> lsNumero;
    
    @FXML
    private TableColumn<Aeropuerto, Integer> lsFinanciacion;
    
    @FXML
    private TableColumn<Aeropuerto, Integer> lsNTrabajadores;

    @FXML
    private TableColumn<Aeropuerto, String> lsPais;

    @FXML
    private TableView<Aeropuerto> tbAeropuerto;

    @FXML
    private TextField txtFiltro;
    
    @FXML
    private ToggleGroup gpPrivacidad;
    
    @FXML
    private RadioButton rbPrivados;

    @FXML
    private RadioButton rbPublicos;
    
    @FXML
    private MenuItem miAniadir;
    
    private ObservableList<Aeropuerto> o1;
    
    private boolean modificar;
    
    private AeropuertoDao aD;
    
    // Crear un FilteredList respaldado por la lista de objetos
    private FilteredList<Aeropuerto> filteredList;
    
    public boolean isModificar() {
		return modificar;
	}

    // Metodos de Aeropuerto
 	public boolean crearAeropuerto(String nombre, String apellido, int edad) {
     	Aeropuerto p = new Aeropuerto(nombre, apellido, edad);
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
 			//Crear y añadirla a la tabla
 			aD.insertPersona(aD.ultimoID(), nombre, apellido, edad);
 			o1.add(p);
 			
 			return true;
 		}
     }
     
     public void modificarAeropuerto(String nombre, String apellido, int edad) {
     	//Modificar objeto de la tabla
     	Aeropuerto p = new Aeropuerto(nombre, apellido, edad);
     	for (int i = 0; i < o1.size(); i++) {
 			if (tbAeropuerto.getSelectionModel().getSelectedItem()==o1.get(i)) {
 				aD.modPersona(tbAeropuerto.getSelectionModel().getSelectedItem().getNombre(),nombre, apellido, edad);
 				o1.set(i, p);
 			}
 		}
     }
    
	@FXML
    void clickPrivado(ActionEvent event) {
    	o1.setAll(aD.cargarAeropuertosPri());
		lsNSocios.setVisible(true);
		lsFinanciacion.setVisible(false);
		lsNTrabajadores.setVisible(false);
    }

    @FXML
    void clickPublico(ActionEvent event) {
    	o1.setAll(aD.cargarAeropuertosPub());
		lsNSocios.setVisible(false);
		lsFinanciacion.setVisible(true);
		lsNTrabajadores.setVisible(true);
    }
    
    @FXML
    void aniadirAeropuerto(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioLfxmlAniadirAeropuerto.fxml"));
    	Stage stage = new Stage();
    	EjercicioLControllerAniadirAeropuertos ejLC = new EjercicioLControllerAniadirAeropuertos();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioLControllerAniadirAeropuertos ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,500,700));
	        //stage.setResizable(false);
	        stage.initOwner(this.tbAeropuerto.getScene().getWindow());
	        stage.setTitle("Añadir Aeropuerto");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    public void initialize(URL location, ResourceBundle resources) {
    	//Valores de la columna de la tabla
    	lsId.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("id"));
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("nombre"));
    	lsPais.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("pais"));
    	lsCiudad.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("ciudad"));
    	lsCalle.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("calle"));
    	lsNumero.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("numero"));
    	lsAno.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("ano"));
    	lsCapacidad.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("capacidad"));
    	lsNSocios.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("nSocios"));
    	lsFinanciacion.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("financiacion"));
    	lsNTrabajadores.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("nTrabajadores"));
    	
    	o1 = FXCollections.observableArrayList();
    	
    	aD = new AeropuertoDao();
		
		o1.setAll(aD.cargarAeropuertosPri());
		
    	modificar=false;
    	
    	txtFiltro.setPromptText("Buscar...");
    	
    	filteredList = new FilteredList<Aeropuerto>(o1, b -> true);
    	
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
        
        SortedList<Aeropuerto> sortedData = new SortedList<Aeropuerto>(filteredList);
    	sortedData.comparatorProperty().bind(tbAeropuerto.comparatorProperty());
    	
    	tbAeropuerto.setItems(sortedData);
    }
    
    public void setControlerL(EjercicioLControllerLogin ej) {
    	this.ejLControllerLogin = ej;
    }
    
}
