/**
 * 
 */
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
 * @author PC
 * 
 */
@Entity
@Table(name = "bodega")
public class Bodega implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private List<Salida> listaSalidas;

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	 * @return listaSalidas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "bodega")
	public List<Salida> getListaSalidas() {
		return listaSalidas;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaSalidas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaSalidas(List<Salida> listaSalidas) {
		this.listaSalidas = listaSalidas;
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

}
