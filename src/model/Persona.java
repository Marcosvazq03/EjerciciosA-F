package model;

import javafx.beans.property.SimpleStringProperty;

public class Persona {

	private SimpleStringProperty nombre,apellido;
	private int edad;
	
	public Persona(String nombre, String apellido, int edad) {
		this.nombre=new SimpleStringProperty(nombre);
		this.apellido=new SimpleStringProperty(apellido);
		this.edad=edad;
	}
	
}
