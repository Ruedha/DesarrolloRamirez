package co.com.vuelos.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que permite generar la tabla Proveedor en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "Proveedor")
public class Proveedor implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String numeroContacto;
	private String nombreContacto;
	private String direccion;
	private List<Entrada> listaEntradas;

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Id
	public Long getId() {
		return id;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombre
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombre
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaEntradas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "proveedor")
	public List<Entrada> getListaEntradas() {
		return listaEntradas;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaEntradas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaEntradas(List<Entrada> listaEntradas) {
		this.listaEntradas = listaEntradas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return numeroContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNumeroContacto() {
		return numeroContacto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param numeroContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return direccion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param direccion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
