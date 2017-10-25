package co.com.hookah.entidades;

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
@Table(name = "tipoProductohookah")
public class TipoProductoHookah implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private Long id;
	private String codigo;
	private String descripcion;
	private List<ProductoHookah> listaProductosHookah;

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
	@OneToMany(mappedBy = "tipoProductoHookah")
	public List<ProductoHookah> getListaProductos() {
		return listaProductosHookah;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProductos(List<ProductoHookah> listaProductos) {
		this.listaProductosHookah = listaProductos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return codigo;
	}

}
