package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Persona;

public class PersonaDao {
    private ConexionBD conexion;
    
    public int ultimoID() {
    	//Saca el ultimo id de Personas
    	int id=-1;
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select id from Persona";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idPersona = rs.getInt("id");
		            if (idPersona>id) {
						id=idPersona;
					}
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
    	id=id+1;
    	return id;
    }
    
    private int buscarIDNombre(String nombreB){
    	//Busca el id de la persona por su nombre
    	int id=-1;
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Persona";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idPersona = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            if (nombre.equals(nombreB)) {
						id=idPersona;
						break;
					}
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }
    	return id;
    }
    
    public void insertPersona(int id,String nombre, String apellidos, int edad) {
    	//Inserta Persona en la BBDD
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "INSERT INTO Persona(id,nombre,apellidos,edad)VALUES("+id+",'"+nombre+"','"+apellidos+"',"+edad+")";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void modPersona(String nombreV,String nombre, String apellidos, int edad) {
    	//Modifica Persona en la BBDD
    	int id = buscarIDNombre(nombreV);
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "UPDATE personas.Persona SET nombre = '"+nombre+"', apellidos = '"+apellidos+"', edad = "+edad+" WHERE id = "+id;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void elimPersona(String nombreV) {
    	//Eliminar Persona en la BBDD
    	int id = buscarIDNombre(nombreV);
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "DELETE FROM personas.Persona WHERE id = "+id;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }

    public ObservableList<Persona> cargarPersonas()  {
    	
    	ObservableList<Persona> personas = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Persona";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            String nombre = rs.getString("nombre");
		            String apellidos = rs.getString("apellidos");
		            int edad = rs.getInt("edad");
		         
	                Persona a = new Persona(nombre, apellidos, edad);
	                //System.out.println(a.toString());
	                personas.add(a);
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return personas;    
    }
}