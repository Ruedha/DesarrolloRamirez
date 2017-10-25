/**
 * 
 */
package co.com.vuelos.entidades;

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
@Table(name = "producto")
public class Producto implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	// Identificacion unica de la entidad
	private Long id;

	private String nombre;

	private double saldoActual;

	private String codigo;

	private int stockMinimo;

	private int cantidad;

	// private List<Entrada> listaEntradas;

	private List<Salida> listaSalidas;

	private TipoProducto tipoProducto;

	private String presentacion;

	private int cantidadPorCaja;

	private List<EntradaProducto> listaEntradaProductos;

	private int cantidadTemporal;

	private double valorUnitarioTemporal;

	private double valorTotalTemporal;

	private String establecimiento;

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
	 * @return stockMinimo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public int getStockMinimo() {
		return stockMinimo;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param stockMinimo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
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

	// /**
	// * Devuelve el valor del parametro
	// *
	// * @return listaEntradas
	// *
	// * @author Deivid Arias Ramirez
	// */
	// @OneToMany(mappedBy = "producto")
	// public List<Entrada> getListaEntradas() {
	// return listaEntradas;
	// }
	//
	// /**
	// * Asigna valor al parametro
	// *
	// * @param listaEntradas
	// *
	// * @author Deivid Arias Ramirez
	// */
	// public void setListaEntradas(List<Entrada> listaEntradas) {
	// this.listaEntradas = listaEntradas;
	// }

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaSalidas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "producto")
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

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return tipoProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@ManyToOne
	@JoinColumn(name = "tipoProducto")
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param tipoProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
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

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return presentacion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param presentacion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return saldoActual
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getSaldoActual() {
		return saldoActual;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param saldoActual
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return cantidadPorCaja
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public int getCantidadPorCaja() {
		return cantidadPorCaja;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param cantidadPorCaja
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCantidadPorCaja(int cantidadPorCaja) {
		this.cantidadPorCaja = cantidadPorCaja;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaEntradaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + cantidadPorCaja;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((listaEntradaProductos == null) ? 0 : listaEntradaProductos
						.hashCode());
		result = prime * result
				+ ((listaSalidas == null) ? 0 : listaSalidas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((presentacion == null) ? 0 : presentacion.hashCode());
		long temp;
		temp = Double.doubleToLongBits(saldoActual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stockMinimo;
		result = prime * result
				+ ((tipoProducto == null) ? 0 : tipoProducto.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (cantidad != other.cantidad)
			return false;
		if (cantidadPorCaja != other.cantidadPorCaja)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaEntradaProductos == null) {
			if (other.listaEntradaProductos != null)
				return false;
		} else if (!listaEntradaProductos.equals(other.listaEntradaProductos))
			return false;
		if (listaSalidas == null) {
			if (other.listaSalidas != null)
				return false;
		} else if (!listaSalidas.equals(other.listaSalidas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (presentacion == null) {
			if (other.presentacion != null)
				return false;
		} else if (!presentacion.equals(other.presentacion))
			return false;
		if (Double.doubleToLongBits(saldoActual) != Double
				.doubleToLongBits(other.saldoActual))
			return false;
		if (stockMinimo != other.stockMinimo)
			return false;
		if (tipoProducto == null) {
			if (other.tipoProducto != null)
				return false;
		} else if (!tipoProducto.equals(other.tipoProducto))
			return false;
		return true;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return cantidadTemporal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Transient
	public int getCantidadTemporal() {
		return cantidadTemporal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param cantidadTemporal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCantidadTemporal(int cantidadTemporal) {
		this.cantidadTemporal = cantidadTemporal;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return valorUnitarioTemporal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Transient
	public double getValorUnitarioTemporal() {
		return valorUnitarioTemporal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param valorUnitarioTemporal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setValorUnitarioTemporal(double valorUnitarioTemporal) {
		this.valorUnitarioTemporal = valorUnitarioTemporal;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return valorTotalTemporal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Transient
	public double getValorTotalTemporal() {

		valorTotalTemporal = cantidadTemporal * valorUnitarioTemporal;

		return valorTotalTemporal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param valorTotalTemporal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setValorTotalTemporal(double valorTotalTemporal) {
		this.valorTotalTemporal = valorTotalTemporal;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return establecimiento
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getEstablecimiento() {
		return establecimiento;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param establecimiento
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

}
