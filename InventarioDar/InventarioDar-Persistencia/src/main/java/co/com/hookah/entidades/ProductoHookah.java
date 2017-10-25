/**
 * 
 */
package co.com.hookah.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase que permite generar la tabla producto en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "productohookah")
public class ProductoHookah implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	// Identificacion unica de la entidad
	private Long id;

	private int cantidad;

	private TipoProductoHookah tipoProductoHookah;

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
	 * @return cantidad
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param cantidad
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return tipoProductoHookah
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne
	@JoinColumn(name = "tipoProductoHookah")
	public TipoProductoHookah getTipoProductoHookah() {
		return tipoProductoHookah;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param tipoProductoHookah
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setTipoProductoHookah(TipoProductoHookah tipoProductoHookah) {
		this.tipoProductoHookah = tipoProductoHookah;
	}

}
