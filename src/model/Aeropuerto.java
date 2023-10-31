package model;

import java.util.Objects;

//Clase Persona
public class Aeropuerto {
	
	private String nombre, pais, ciudad, calle;
	private int numero, id, ano, capacidad, nSocios;
	
	public Aeropuerto(int id,String nombre, String pais, String ciudad, String calle, int numero, int ano, int capacidad, int nSocios) {
		this.id=id;
		this.nombre=nombre;
		this.pais=pais;
		this.ciudad=ciudad;
		this.calle=calle;
		this.numero=numero;
		this.ano=ano;
		this.capacidad=capacidad;
		this.nSocios=nSocios;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getAno() {
		return ano;
	}



	public void setAno(int ano) {
		this.ano = ano;
	}



	public int getCapacidad() {
		return capacidad;
	}



	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}



	public int getnSocios() {
		return nSocios;
	}



	public void setnSocios(int nSocios) {
		this.nSocios = nSocios;
	}



	@Override
	public int hashCode() {
		return Objects.hash(ano, calle, capacidad, ciudad, id, nSocios, nombre, numero, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return ano == other.ano && Objects.equals(calle, other.calle) && capacidad == other.capacidad
				&& Objects.equals(ciudad, other.ciudad) && id == other.id && nSocios == other.nSocios
				&& Objects.equals(nombre, other.nombre) && numero == other.numero && Objects.equals(pais, other.pais);
	}
	
}
