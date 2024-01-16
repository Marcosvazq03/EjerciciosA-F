package model;

import java.util.Date;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Animal {
	
	private int id;
	
	private String nombre;
	
	private char sexo;
	
	private int edad;
	
	private float peso;
	
	private Date fecha_primera_consulta;
	
	private byte[] imagen;
	
	private String raza;
	
	private String especie;
	
	private ObservableList<Consulta> consultas = FXCollections.observableArrayList();
	
	public Animal(int id, String nombre, char sexo, int edad, float peso, Date fecha_primera_consulta, byte[] imagen,
			String raza, String especie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
		this.peso = peso;
		this.fecha_primera_consulta = fecha_primera_consulta;
		this.imagen = imagen;
		this.raza = raza;
		this.especie = especie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Date getFecha_primera_consulta() {
		return fecha_primera_consulta;
	}

	public void setFecha_primera_consulta(Date fecha_primera_consulta) {
		this.fecha_primera_consulta = fecha_primera_consulta;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public void addConsulta(Consulta consulta) {
		consultas.add(consulta);
	}
	
	public void delConsulta(Consulta consulta) {
		consultas.remove(consulta);
	}
	
	public String infoConsultas() {
		String str = "";
		if(consultas.size() == 0)
			return "";
		if(consultas.size() == 1)
			str = "Consulta:\n";
		else
			str = "Consultas:\n";
		for(int i = 0; i < consultas.size(); i++) {
			Consulta c = consultas.get(i);
			str += "ID: " + c.getId() + "\nFecha: " + c.getFecha() + "\nObservacion:\n\t" + c.getObservacion().replace("\n", "\n\t") + "\n";
		}
		return str;
	}
	
	public void modConsulta(int i, Consulta consulta) {
		Consulta c = consultas.get(i);
		c.setFecha(consulta.getFecha());
		c.setObservacion(consulta.getObservacion());
	}
	
	public boolean fechaMaxPequenia(Date date) {
		for(int i = 0; i < consultas.size(); i++) {
			Consulta c = consultas.get(i);
			if(c.getFecha().compareTo(date) < 0) {
				return false;
			}
		}
		return true;
	}
	
	public void newFechaPequenia() {
		if(consultas.size() == 0) {
			fecha_primera_consulta = null;
			return;
		}
			
		Date fecha = new Date();
		for(int i = 0; i < consultas.size(); i++) {
			Consulta c = consultas.get(i);
			if(i == 0) {
				fecha = c.getFecha();
				continue;
			}
				
			if(c.getFecha().compareTo(fecha) > 0) {
				fecha = c.getFecha();
			}
		}
		this.fecha_primera_consulta = fecha;
	}
	
	public ObservableList<Consulta> getConsultas() {
		return consultas;
	}
	
	public void setConsultas(ObservableList<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return id == other.id;
	}
	
}
