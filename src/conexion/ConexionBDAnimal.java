package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.TimeZone;

public class ConexionBDAnimal {
	
	private Connection conexion;
	
	public ConexionBDAnimal() throws SQLException {
		ResourceBundle bundle = ResourceBundle.getBundle("configuration");
        String host = bundle.getString("bd_host");
        String baseDatos = bundle.getString("bd_name2");
        String usuario = bundle.getString("bd_user");
        String password = bundle.getString("bd_password");
        String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos+ "?serverTimezone=" + TimeZone.getDefault().getID();
        conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
        conexion.setAutoCommit(true);
		
	}
	
	public Connection getConexion() {
		return conexion;
	}

	public void closeConexion() throws SQLException {
		conexion.close();
	}
	
}
