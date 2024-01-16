package dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import conexion.ConexionBDAnimal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Animal;
import model.Consulta;

public class AnimalDao {

	private ConexionBDAnimal conn;
	
	public ObservableList<Animal> get(){
		ObservableList<Animal> lstAnimales = FXCollections.observableArrayList();
		try {
			conn = new ConexionBDAnimal();
			String consulta = "select * from Animal";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("Id");
				String nombre = rs.getString("Nombre");
				char sexo = rs.getString("Sexo").charAt(0);
				int edad = rs.getInt("Edad");
				float peso = rs.getFloat("Peso");
				java.sql.Date date = rs.getDate("Fecha_Primera_Consulta");
				Date fecha = null;
				if(date != null)
					fecha = new Date(date.getTime());
				
				byte[]imageByte = null;
				try (InputStream image =  rs.getBinaryStream("Foto");){
					if(image != null)
						imageByte = image.readAllBytes();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				String raza = rs.getString("Raza");
				String especie = rs.getString("Especie");
				
				Animal animal = new Animal(id, nombre, sexo, edad, peso, fecha, imageByte, raza, especie);
				
				lstAnimales.add(animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstAnimales;
	}
	
	public ObservableList<Consulta> get(Animal animal){
		ObservableList<Consulta> lstConsulta = FXCollections.observableArrayList();
		try {
			conn = new ConexionBDAnimal();
			String consulta = "SELECT * from Consulta WHERE IDAnimal = ?";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setInt(1, animal.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {;
				int id = rs.getInt("Id");
				String observacion = rs.getString("observacion");
				java.sql.Date date = rs.getDate("Fecha");
				Date fecha = null;
				if(date != null)
					fecha = new Date(date.getTime());
				
				Consulta c = new Consulta();
				c.setId(id);
				c.setAnimal(animal);
				c.setFecha(fecha);
				c.setObservacion(observacion);
				lstConsulta.add(c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstConsulta;
	}
	
	public boolean add(Animal animal) {
		try {
			conn = new ConexionBDAnimal();

			String consulta = "INSERT INTO Animal(Nombre,Sexo,Edad,Peso,Foto,Raza,Especie) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, animal.getNombre());
			ps.setString(2, animal.getSexo() + "");
			ps.setInt(3, animal.getEdad());
			ps.setFloat(4, animal.getPeso());
			if(animal.getImagen() != null)
				ps.setBinaryStream(5,  new ByteArrayInputStream(animal.getImagen()));
			else 
				ps.setBinaryStream(5, null);
			ps.setString(6, animal.getRaza());
			ps.setString(7, animal.getEspecie());
		
			int actualizado = ps.executeUpdate();
			if(actualizado == 0)
				return false;
			
			ResultSet rs = ps.getGeneratedKeys();
			if(!rs.next())
				return false;
			
			animal.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean add(Consulta c) {
		try {
			conn = new ConexionBDAnimal();

			String consulta = "INSERT INTO Consulta(Fecha,IDAnimal,Observacion) VALUES(?,?,?)";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new java.sql.Date(c.getFecha().getTime()));
			ps.setInt(2, c.getAnimal().getId());
			ps.setString(3, c.getObservacion());
			
		
			int actualizado = ps.executeUpdate();
			if(actualizado == 0)
				return false;
			
			ResultSet rs = ps.getGeneratedKeys();
			if(!rs.next())
				return false;
			
			c.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean mod(Animal animal) {
		try {
			conn = new ConexionBDAnimal();
			String consulta = "UPDATE Animal SET Nombre = ?, Sexo = ?, Edad = ?, Peso = ?, Foto = ?, Raza = ?, Especie = ? WHERE Id = ?";
			
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setString(1, animal.getNombre());
			ps.setString(2, animal.getSexo() + "");
			ps.setInt(3, animal.getEdad());
			ps.setFloat(4, animal.getPeso());
			if(animal.getImagen() != null)
				ps.setBinaryStream(5,  new ByteArrayInputStream(animal.getImagen()));
			else 
				ps.setBinaryStream(5, null);
			ps.setString(6, animal.getRaza());
			ps.setString(7, animal.getEspecie());
			ps.setInt(8, animal.getId());
			
			int actualizado = ps.executeUpdate();

			if(actualizado == 0)
				return false;
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean mod(Consulta c) {
		try {
			conn = new ConexionBDAnimal();
			String consulta = "UPDATE Consulta SET Fecha = ?, Observacion = ? WHERE Id = ?";
			
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setDate(1, new java.sql.Date(c.getFecha().getTime()));
			ps.setString(2, c.getObservacion());
			ps.setInt(3, c.getId());
			
			int actualizado = ps.executeUpdate();

			if(actualizado == 0)
				return false;
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateFecha(Animal animal) {
		try {
			conn = new ConexionBDAnimal();
			String consulta = "UPDATE Animal SET Fecha_Primera_Consulta = ? WHERE Id = ?";
			
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setDate(1, new java.sql.Date(animal.getFecha_primera_consulta().getTime()));
			ps.setInt(2, animal.getId());
			
			int actualizado = ps.executeUpdate();

			if(actualizado == 0)
				return false;
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean remove(Animal animal) {
		try {
			if(animal.getConsultas().isEmpty() || removeAllBy(animal.getId())) {
				conn = new ConexionBDAnimal();
				String consulta = "DELETE FROM Animal WHERE Id = ?";
				PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
				ps.setInt(1, animal.getId());
				
				int actualizado = ps.executeUpdate();
				if (actualizado == 0)
					return false;
			}else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean remove(Consulta c) {
		try {
			conn = new ConexionBDAnimal();
			String consulta = "DELETE FROM Consulta WHERE Id = ?";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setInt(1, c.getId());
			
			int actualizado = ps.executeUpdate();
			if (actualizado == 0)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean removeAllBy(int id_animal) {
		try {
			conn = new ConexionBDAnimal();
			String consulta = "DELETE FROM Consulta WHERE IDAnimal = ?";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setInt(1, id_animal);
			
			int actualizado = ps.executeUpdate();
			if (actualizado == 0)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
