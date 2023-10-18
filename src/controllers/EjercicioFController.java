package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;

public class EjercicioFController implements Initializable{

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
			o1.add(p);
			
			return true;
		}
    }
    
    public void modificarPersona(String nombre, String apellido, int edad) {
    	//Modificar Persona de la tabla
    	Persona p = new Persona(nombre, apellido, edad);
    	for (int i = 0; i < o1.size(); i++) {
			if (tbPersona.getSelectionModel().getSelectedItem()==o1.get(i)) {
				o1.set(i, p);
			}
		}
    }
    
    // Metodos para archivos
    private void readCSV() {
    	 
    	FileChooser fileChooser = new FileChooser();
    	// Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        // Obtener csv seleccionada
        Stage stage = new Stage();
        File csvFile = fileChooser.showOpenDialog(stage);

        // Mostar csv
        File csv = new File("");
        if (csvFile != null) {
            csv = new File(csvFile.getAbsolutePath());
        }
        
        String FieldDelimiter = ",";
 
        BufferedReader br;
 
        try {
            br = new BufferedReader(new FileReader(csv));
 
            String line;
            
            //Leo la primera linea para que no introducirla en la tabla
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                Persona p = new Persona(fields[0], fields[1],Integer.parseInt(fields[2]));
                boolean esta=false;
        		if (o1 != null) {
        			//Comprobar si existe en la tabla
        			if (o1.contains(p)) {
        				esta=true;
        			}
        		}
        		if (esta) {
        			//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText(fields[0]+" ya existe en la tabla y no se puede añadir");
		            alert.showAndWait();
        		}else {
        			//Crear persona y añadirla a la tabla
        			o1.add(p);
        		}
            }
 
        } catch (FileNotFoundException ex) {
        	System.out.println(ex.getMessage());
        } catch (IOException ex) {
        	System.out.println(ex.getMessage());
        }
 
    }

    @FXML
    void agregar(ActionEvent event) {
    	modificar=false;
    	try {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioFfxml2.fxml"));
	    	Stage stage = new Stage();
	    	Parent root = loader.load();
	    	EjercicioFController2 ejFC2 = loader.getController();
	    	ejFC2.setControlerF(this);
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
	    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioFfxml2.fxml"));
		    	Stage stage = new Stage();
		    	Parent root = loader.load();
		    	EjercicioFController2 ejFC2 = loader.getController();
		    	ejFC2.setControlerF(this);
		        stage.setScene(new Scene(root,400,200));
		        stage.setResizable(false);
		        stage.initOwner(this.bntAgregar.getScene().getWindow());
		        stage.setTitle("Editar Persona");
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.show();
		        
	    	}catch (Exception e) {
	    		e.printStackTrace();
			}
		}
    }
    
    @FXML
    void exportar(ActionEvent event) {

    }

    @FXML
    void importar(ActionEvent event) {
    	readCSV();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Valores de la columna de la tabla
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
    	lsApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
    	lsEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    	
    	o1 = FXCollections.observableArrayList();
    	
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
