package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDAeropuertos;


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
    
}