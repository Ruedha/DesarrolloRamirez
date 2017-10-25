package co.com.vuelos.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
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
@Table(name = "Empleado")
public class Empleado implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombreCompleto;
	private String numeroContacto;
	private String direccion;

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return id
	 * 
	 * @author Santiago Rueda Ramirez
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
	 * @author Santiago Rueda Ramirez
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombre
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombre
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombreCompleto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return numeroContacto
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public String getNumeroContacto() {
		return numeroContacto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param numeroContacto
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return direccion
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param direccion
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}

