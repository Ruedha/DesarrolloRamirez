package co.com.vuelos.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que permite generar la tabla entrada en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "salida")
public class Salida implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private long id;
	private Producto producto;
	private Date fecha;
	private int cantidad;
	private Usuario usuario;
	private String destino;
	private double precio;
	private Bodega bodega;

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
	 * @return producto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param producto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return fecha
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param fecha
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * Asigna valor al parametro
	 * 
	 * @param id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return destino
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param destino
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return precio
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param precio
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return bodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne
	@JoinColumn(name = "bodega")
	public Bodega getBodega() {
		return bodega;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param bodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

}
