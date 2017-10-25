package co.com.vuelos.entidades;

import java.io.Serializable;
import java.util.Date;
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

/**
 * Clase que permite generar la tabla entrada en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "entrada")
public class Entrada implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private Long id;
	// private Producto producto;
	private Date fecha;
	private int cantidad;
	private Usuario usuario;
	private double total;
	private String destino;
	private double valorUnitario;
	private String ubicacion;
	private String numeroFactura;
	private Proveedor proveedor;
	private List<EntradaProducto> listaEntradaProductos;
	private String nombreDocumento;

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

	// /**
	// * Devuelve el valor del parametro
	// *
	// * @return producto
	// *
	// * @author Deivid Arias Ramirez
	// */
	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "producto")
	// public Producto getProducto() {
	// return producto;
	// }
	//
	// /**
	// * Asigna valor al parametro
	// *
	// * @param producto
	// *
	// * @author Deivid Arias Ramirez
	// */
	// public void setProducto(Producto producto) {
	// this.producto = producto;
	// }

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
	 * Devuelve el valor del parametro
	 * 
	 * @return usuario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne
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
	 * @return total
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param total
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setTotal(double total) {
		this.total = total;
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
	 * @return valorUnitario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param valorUnitario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return ubicacion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param ubicacion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return numeroFactura
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param numeroFactura
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return proveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param proveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaEntradaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL)
	public List<EntradaProducto> getListaEntradaProductos() {
		return listaEntradaProductos;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaEntradaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaEntradaProductos(
			List<EntradaProducto> listaEntradaProductos) {
		this.listaEntradaProductos = listaEntradaProductos;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreDocumento
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreDocumento
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

}
