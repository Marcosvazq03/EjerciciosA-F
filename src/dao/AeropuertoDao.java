package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDAeropuertos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aeropuerto;


public class AeropuertoDao {
    private ConexionBDAeropuertos conexion;
    
    public boolean validarUser(String user, String password){
    	//Validar inicio de sesion
    	try {
            conexion = new ConexionBDAeropuertos();
        	String consulta = "select * from aeropuertos.usuarios";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 String usu = rs.getString("usuario");
				 String pass = rs.getString("password");
				 if (usu.equals(user) && pass.equals(password)) {
					 return true;
				 }
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }
    	return false;
    }
    
    public ObservableList<Aeropuerto> cargarAeropuertosPri()  {
    	
    	ObservableList<Aeropuerto> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select * from aeropuertos_privados";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int idA = rs.getInt("id_aeropuerto");
				 int nSocicos=rs.getInt("numero_socios");
	             conexion = new ConexionBDAeropuertos();        	
	        	 String consulta2 = "select * from aeropuertos where id="+idA;
	        	 PreparedStatement pstmt2 = conexion.getConexion().prepareStatement(consulta2);      
	        	 ResultSet rs2 = pstmt2.executeQuery();   
				 rs2.next();
				
				 int id = rs2.getInt("id");
				 String nombre = rs2.getString("nombre");
				 int anio = rs2.getInt("anio_inauguracion");
				 int capacidad = rs2.getInt("capacidad");
				 int id_direccion = rs2.getInt("id_direccion");
				 
				 String consulta3 = "select * from direcciones where id="+id_direccion;
				 PreparedStatement pstmt3 = conexion.getConexion().prepareStatement(consulta3);      
				 ResultSet rs3 = pstmt3.executeQuery();
				 rs3.next();
				 
				 String pais = rs3.getString("pais");
				 String ciudad = rs3.getString("ciudad");
				 String calle = rs3.getString("calle");
				 int numero = rs3.getInt("numero");
				 
				 Aeropuerto a = new Aeropuerto(id, nombre, pais, ciudad, calle, numero, anio, capacidad);
	             a.setNSocios(nSocicos);
				 aeropuertos.add(a);
				 rs3.close();
				 rs2.close();
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return aeropuertos;    
    }
    
    public ObservableList<Aeropuerto> cargarAeropuertosPub()  {
    	
    	ObservableList<Aeropuerto> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select * from aeropuertos_publicos";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int idA = rs.getInt("id_aeropuerto");
				 int financiacion=rs.getInt("financiacion");
				 int nTrbajadores=rs.getInt("num_trabajadores");
	             conexion = new ConexionBDAeropuertos();        	
	        	 String consulta2 = "select * from aeropuertos where id="+idA;
	        	 PreparedStatement pstmt2 = conexion.getConexion().prepareStatement(consulta2);      
	        	 ResultSet rs2 = pstmt2.executeQuery();   
				 rs2.next();
				
				 int id = rs2.getInt("id");
				 String nombre = rs2.getString("nombre");
				 int anio = rs2.getInt("anio_inauguracion");
				 int capacidad = rs2.getInt("capacidad");
				 int id_direccion = rs2.getInt("id_direccion");
				 
				 String consulta3 = "select * from direcciones where id="+id_direccion;
				 PreparedStatement pstmt3 = conexion.getConexion().prepareStatement(consulta3);      
				 ResultSet rs3 = pstmt3.executeQuery();
				 rs3.next();
				 
				 String pais = rs3.getString("pais");
				 String ciudad = rs3.getString("ciudad");
				 String calle = rs3.getString("calle");
				 int numero = rs3.getInt("numero");
				 
				 Aeropuerto a = new Aeropuerto(id, nombre, pais, ciudad, calle, numero, anio, capacidad);
	             a.setFinanciacion(financiacion);
				 a.setNTrabajadores(nTrbajadores);
				 aeropuertos.add(a);
				 rs3.close();
				 rs2.close();
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return aeropuertos;    
    }
    
}