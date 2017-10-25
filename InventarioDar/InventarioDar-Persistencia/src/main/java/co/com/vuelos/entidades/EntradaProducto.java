package co.com.vuelos.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase que permite generar la tabla entrada en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "entradaProducto")
public class EntradaProducto implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	private Long id;

	private Entrada entrada;

	private Producto producto;

	private int cantidad;

	private double precioUnitario;

	private double precioTotal;

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
	 * @return entrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "entrada")
	public Entrada getEntrada() {
		return entrada;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param entrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return producto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	// fetch = FetchType.LAZY, cascade = CascadeType.REFRESH
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
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
	 * @return precioUnitario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param precioUnitario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return precioTotal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Transient
	public double getPrecioTotal() {

		precioTotal = cantidad * precioUnitario;
		return precioTotal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param precioTotal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

}
