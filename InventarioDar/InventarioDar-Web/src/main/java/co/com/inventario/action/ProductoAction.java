package co.com.inventario.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.ProductoBean;
import co.com.inventario.ejb.TipoProductoBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Producto;
import co.com.vuelos.entidades.TipoProducto;
import co.com.vuelos.entidades.Usuario;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes al producto
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "productoAction")
@SessionScoped
public class ProductoAction implements Serializable {

	/**
	 * identificacion de la serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	private double precio;

	private String codigo;

	private int stockMinimo;

	private int cantidad;

	private String presentacion;

	private String ubicacion;

	private int cantidadPorCaja;

	private String establecimiento;

	/**
	 * Permite acceder al bean del producto alojado en el contexto del servidor
	 */
	@Inject
	private ProductoBean productoBean;

	private Usuario usuarioEnSesion;

	private List<TipoProducto> listaTipoProductos;

	@Inject
	private TipoProductoBean tipoProductoBean;

	private String codigoTipo;

	private List<Producto> listaProductos;

	public ProductoAction() {

	}

	/**
	 * Metodo que permite inicializar todos los campos de la aplicacion en el
	 * producto
	 */
	public String limpiarCampos(Usuario usuarioSesion) {

		usuarioEnSesion = usuarioSesion;

		nombre = ConstantesGenerales.CADENA_VACIA;

		precio = 0.0;

		codigo = ConstantesGenerales.CADENA_VACIA;

		stockMinimo = 0;

		cantidad = 0;

		ubicacion = ConstantesGenerales.CADENA_VACIA;

		presentacion = ConstantesGenerales.CADENA_VACIA;

		listaTipoProductos = tipoProductoBean.listartipoProducto();

		codigoTipo = ConstantesGenerales.CADENA_VACIA;

		establecimiento = ConstantesGenerales.CADENA_VACIA;

		cantidadPorCaja = 0;

		listaProductos = productoBean.listarProductos();

		return "/Producto/registrar_producto.xhtml";
	}

	public void insertarMasivos() {

		productoBean.insertarProductosMasivos();
	}

	public void onRowEdit(RowEditEvent event) throws Exception {

		Producto producto = (Producto) event.getObject();

		TipoProducto tipoProductoCombo = tipoProductoBean
				.buscarTipoProductoXCodigo2(producto.getTipoProducto()
						.getCodigo());

		producto.setTipoProducto(tipoProductoCombo);

		productoBean.editarProducto(producto);

		listaProductos = productoBean.listarProductos();

		FacesContext contex = FacesContext.getCurrentInstance();
		contex.getExternalContext().redirect(
				"/InventarioDar/Producto/registrar_producto.xhtml");

	}

	public void onRowCancel(RowEditEvent event) throws IOException {

		listaProductos = productoBean.listarProductos();

		FacesContext contex = FacesContext.getCurrentInstance();
		contex.getExternalContext().redirect(
				"/InventarioDar/Producto/registrar_producto.xhtml");

	}

	/**
	 * 
	 * Metodo que permite el registro de un usuario con todas sus condiciones
	 * 
	 * @throws Exception
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 */
	public String registrarProducto() throws Exception {

		TipoProducto tipoProductoCombo = tipoProductoBean
				.buscarTipoProductoXCodigo2(codigoTipo);

		if (tipoProductoCombo == null) {

			Utilitario.errorDesdeCodigo("Debe seleccionar un tipo de producto");

			return "";

		}

		Producto producto = new Producto();

		producto.setCantidad(cantidad);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setSaldoActual(0);
		producto.setStockMinimo(stockMinimo);
		producto.setPresentacion(presentacion);
		producto.setCantidadPorCaja(cantidadPorCaja);
		producto.setTipoProducto(tipoProductoCombo);
		producto.setEstablecimiento(establecimiento);

		if (stockMinimo == 0) {

			Utilitario
					.errorDesdeCodigo("El stock Minimo debe ser mayor que cero.");

			return ConstantesGenerales.CADENA_VACIA;

		}

		Producto validarProducto = productoBean.buscarProductoXCodigo(codigo);

		if (validarProducto != null) {

			Utilitario
					.errorDesdeCodigo("Ya existe un producto con ese codigo de referencia en el sistema");

			return ConstantesGenerales.CADENA_VACIA;
		}

		productoBean.registrarProducto(producto);

		// mensaje de indicando que se registro correctamente
		Utilitario
				.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_EXITO_PRODUCTO_REGISTRAR);

		// reseteamos todos los campos
		limpiarCampos(usuarioEnSesion);

		return "/Usuario/registrar_producto.xhtml";
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

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioEnSesion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Usuario getUsuarioEnSesion() {
		return usuarioEnSesion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioEnSesion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioEnSesion(Usuario usuarioEnSesion) {
		this.usuarioEnSesion = usuarioEnSesion;
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
	 * @return listaTipoProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<TipoProducto> getListaTipoProductos() {
		return listaTipoProductos;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaTipoProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaTipoProductos(List<TipoProducto> listaTipoProductos) {
		this.listaTipoProductos = listaTipoProductos;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return codigoTipo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getCodigoTipo() {
		return codigoTipo;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param codigoTipo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
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
	 * @return listaProductos
	 * 
	 * @author Deivid Arias Ramirez
	 */
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
