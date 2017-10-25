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
 * Clase que permite generar la tabla tipo producto en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "tipoProducto")
public class TipoProducto implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private Long id;
	private String codigo;
	private String descripcion;
	private List<Producto> listaProductos;

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
	 * @return descripcion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param descripcion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return codigo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param codigo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "tipoProducto")
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return codigo;
	}
	
	
	

}
