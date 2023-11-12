package dao;


import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import conexion.ConexionBDAeropuertos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aeropuerto;


public class AeropuertoDao {
    private ConexionBDAeropuertos conexion;
    
    public int ultimoIDAer() {
    	//Saca el ultimo id
    	int id=-1;
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select id from aeropuertos";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id");
		            if (idA>id) {
						id=idA;
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
    
    private int ultimoIDDir() {
    	//Saca el ultimo id
    	int id=-1;
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select id from direcciones";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idD = rs.getInt("id");
		            if (idD>id) {
						id=idD;
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
    
    public int ultimoIDAvi() {
    	//Saca el ultimo id
    	int id=-1;
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select id from aviones";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id");
		            if (idA>id) {
						id=idA;
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
    
    public String listarAviones(int id) {
    	String aviones="";
    	
    	try {
    		conexion = new ConexionBDAeropuertos();
        	String consulta = "select * from aeropuertos.aviones";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id_aeropuerto = rs.getInt("id_aeropuerto");
				 if (id == id_aeropuerto) {
					 String modelo = rs.getString("modelo");
					 int numero_asientos = rs.getInt("numero_asientos");
					 int velocidad_maxima = rs.getInt("velocidad_maxima");
					 int activado = rs.getInt("activado");
					 String txtActivado="  Desactivado";
					 if (activado==1) {
						txtActivado="  Activado";
					}
					 aviones=aviones+"  Modelo: "+modelo+"\n"
					 		+ "  Numero de asientos: "+numero_asientos+"\n"
					 		+ "  Velocidad maxima: "+velocidad_maxima+"\n"
					 		+ txtActivado +"\n";
				}				 
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    	
    	return aviones;
    }
    
    private int insertDireccion(String pais, String ciudad, String calle, int numero) {
    	int id_direccion=ultimoIDDir();
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "INSERT INTO direcciones(id,pais,ciudad,calle,numero) VALUES("+id_direccion+",'"+pais+"','"+ciudad+"','"+calle+"', "+numero+")";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    	
    	return id_direccion;
    }
    
    private int modDireccion(String pais, String ciudad, String calle, int numero, int id) {
    	
    	int id_direccion=-1;
    	//modificar objeto en la BBDD
    	try {
            conexion = new ConexionBDAeropuertos();
            String consulta2 = "select id_direccion from aeropuertos.aeropuertos where id="+id;
            PreparedStatement pstmt2 = conexion.getConexion().prepareStatement(consulta2);      
       	 	ResultSet rs2 = pstmt2.executeQuery();   
			rs2.next();
			id_direccion = rs2.getInt("id_direccion");
        	
			String consulta = "UPDATE direcciones SET pais = '"+pais+"',ciudad = '"+ciudad+"',calle = '"+calle+"',numero = "+numero+" WHERE id = "+id_direccion;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    	
    	return id_direccion;
    }

    public void elimAvion(int id) {
    	//Eliminar objeto en la BBDD
    	try {
            conexion = new ConexionBDAeropuertos();        	
            String consulta = "DELETE FROM aeropuertos.aviones WHERE id = "+id;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void elimAeropuerto(int id) {
    	//Eliminar objeto en la BBDD
    	try {
            conexion = new ConexionBDAeropuertos();        	
            String consulta = "DELETE FROM aeropuertos.aviones WHERE id_aeropuerto = "+id;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
			
        	consulta = "DELETE FROM aeropuertos.aeropuertos WHERE id = "+id;
        	pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void insertAeropuerto(int id, String nombre, String pais, String ciudad, String calle, int numero, int anio, int capacidad, boolean publico, int financiacion, int num_trab, int num_soc) {
    	//Inserta objeto en la BBDD
    	int id_direccion = insertDireccion(pais, ciudad, calle, numero);
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "INSERT INTO aeropuertos(id,nombre,anio_inauguracion, capacidad, id_direccion) VALUES("+id+",'"+nombre+"',"+anio+","+capacidad+", "+id_direccion+")";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
			if (publico) {
				consulta = "INSERT INTO aeropuertos_publicos(id_aeropuerto,financiacion,num_trabajadores) VALUES("+id+","+financiacion+","+num_trab+")";
	        	pstmt = conexion.getConexion().prepareStatement(consulta);
				pstmt.execute();
			}else {
				consulta = "INSERT INTO aeropuertos_privados(id_aeropuerto,numero_socios) VALUES("+id+","+num_soc+")";
	        	pstmt = conexion.getConexion().prepareStatement(consulta);  
				pstmt.execute();
			}
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void modAvion(int id, int activado) {
    	//Modifica objeto en la BBDD
    	try {
	        conexion = new ConexionBDAeropuertos();        	
        	String consulta = "UPDATE aeropuertos.aviones SET activado = "+activado+" WHERE id = "+id;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void modAeropuerto(int id, String nombre, String pais, String ciudad, String calle, int numero, int anio, int capacidad, boolean publico, int financiacion, int num_trab, int num_soc) {
    	//Modifica objeto en la BBDD
    	int id_direccion = modDireccion(pais, ciudad, calle, numero, id);
    	try {
	        conexion = new ConexionBDAeropuertos();        	
        	String consulta = "UPDATE aeropuertos.aeropuertos SET nombre = '"+nombre+"', anio_inauguracion = "+anio+", capacidad = "+capacidad+", id_direccion = "+id_direccion+" WHERE id = "+id;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
			if (publico) {
				consulta = "UPDATE aeropuertos.aeropuertos_publicos SET financiacion = "+financiacion+", num_trabajadores ="+num_trab+" WHERE id_aeropuerto = "+id;
	        	pstmt = conexion.getConexion().prepareStatement(consulta);
				pstmt.execute();
			}else {
				consulta = "UPDATE aeropuertos.aeropuertos_privados SET numero_socios = "+num_soc+" WHERE id_aeropuerto = "+id;
	        	pstmt = conexion.getConexion().prepareStatement(consulta);  
				pstmt.execute();
			}
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void insertAvion(int id, String modelo, int numero_asiento, int velocidad_maxima, int activado, int id_aeropuerto) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "INSERT INTO aviones(id,modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto) VALUES("+id+",'"+modelo+"',"+numero_asiento+","+velocidad_maxima+", "+activado+","+id_aeropuerto+")";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
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
    
    public int buscarIDModelo(String nombreB){
    	//Busca el id por su modelo
    	int id=-1;
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select * from aviones";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idAero = rs.getInt("id");
		            String nombre = rs.getString("modelo");
		            if (nombre.equals(nombreB)) {
						id=idAero;
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
    
    public int buscarIDNombre(String nombreB){
    	//Busca el id por su nombre
    	int id=-1;
    	try {
            conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select * from aeropuertos";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idAero = rs.getInt("id");
		            String nombre = rs.getString("nombre");
		            if (nombre.equals(nombreB)) {
						id=idAero;
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
    
    public ObservableList<String> cargarAviones(int id_aeropuerto)  {
    	ObservableList<String> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select * from aviones where id_aeropuerto = "+id_aeropuerto;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();
			 while (rs.next()) {
				 String nombre = rs.getString("modelo");
				 aeropuertos.add(nombre);
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return aeropuertos;    
    }
    
    public ObservableList<String> cargarAeropuertos()  {
    	ObservableList<String> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBDAeropuertos();        	
        	String consulta = "select * from aeropuertos";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 String nombre = rs.getString("nombre");
				 aeropuertos.add(nombre);
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return aeropuertos;    
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
				 InputStream image = rs2.getString("imagen");
				 
				 String consulta3 = "select * from direcciones where id="+id_direccion;
				 PreparedStatement pstmt3 = conexion.getConexion().prepareStatement(consulta3);      
				 ResultSet rs3 = pstmt3.executeQuery();
				 rs3.next();
				 
				 String pais = rs3.getString("pais");
				 String ciudad = rs3.getString("ciudad");
				 String calle = rs3.getString("calle");
				 int numero = rs3.getInt("numero");
				 
				 Aeropuerto a = new Aeropuerto(id, nombre, pais, ciudad, calle, numero, anio, capacidad, image);
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
				 InputStream image = rs2.getString("imagen");
				 
				 String consulta3 = "select * from direcciones where id="+id_direccion;
				 PreparedStatement pstmt3 = conexion.getConexion().prepareStatement(consulta3);      
				 ResultSet rs3 = pstmt3.executeQuery();
				 rs3.next();
				 
				 String pais = rs3.getString("pais");
				 String ciudad = rs3.getString("ciudad");
				 String calle = rs3.getString("calle");
				 int numero = rs3.getInt("numero");
				 
				 Aeropuerto a = new Aeropuerto(id, nombre, pais, ciudad, calle, numero, anio, capacidad, image);
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
