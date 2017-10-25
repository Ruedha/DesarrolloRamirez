package co.com.inventario.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.BodegaBean;
import co.com.inventario.ejb.ProductoBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Bodega;
import co.com.vuelos.entidades.Producto;
import co.com.vuelos.entidades.Salida;
import co.com.vuelos.entidades.Usuario;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a las salidas de producto
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "salidaAction")
@SessionScoped
public class SalidaAction implements Serializable {

	/**
	 * identificacion de la serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	private String idProducto;

	private String idBodega;

	private List<Producto> listaProductos;

	private Usuario usuarioEnSesion;

	private int cantidad;

	List<Producto> listaProductoSeleccionado;

	private boolean detalleProducto;

	private String destino;

	private double precioSugerido;

	private double precio;

	@Inject
	private BodegaBean bodegaBean;

	private List<Bodega> listaBodegas;

	/**
	 * Permite acceder al bean del producto alojado en el contexto del servidor
	 */
	@Inject
	private ProductoBean productoBean;

	/**
	 * Metodo que permite limpiar los campos de la aplicacion
	 * 
	 * @param usuarioSesion
	 *            en sesion en el sistema
	 * @return redireccion a la pagina
	 */
	public String limpiarCampos(Usuario usuarioSesion) {

		usuarioEnSesion = usuarioSesion;
		listaProductos = productoBean.listarProductos();
		listaBodegas = bodegaBean.listarBodegas();
		cantidad = 0;
		idProducto = ConstantesGenerales.CADENA_VACIA;
		idBodega = ConstantesGenerales.CADENA_VACIA;
		detalleProducto = false;
		destino = ConstantesGenerales.CADENA_VACIA;

		return "/Salida/producto_salida.xhtml";
	}

	/**
	 * Metodo que permite registrar una salida de productos en el sistema
	 * 
	 * @throws Exception
	 */
	public void registrarSalida() throws Exception {

		Salida salida = new Salida();

		Producto productoCombo = productoBean.buscarProductoXCodigo(idProducto);

		if (productoCombo == null) {

			Utilitario.errorDesdeCodigo("Debe seleccionar un producto");
			return;

		}

		Bodega bodegaCombo = bodegaBean.buscarBodegaXNombre(idBodega);

		if (bodegaCombo == null) {

			Utilitario.errorDesdeCodigo("Debe seleccionar la bodega de salida");
			return;

		}

		salida.setCantidad(cantidad);

		productoCombo.setCantidad(productoCombo.getCantidad() - cantidad);

		productoCombo.setSaldoActual(productoCombo.getSaldoActual()
				- (precio * cantidad));

		salida.setDestino(destino);

		salida.setPrecio(precio);

		salida.setProducto(productoCombo);

		salida.setBodega(bodegaCombo);

		salida.setFecha(new Date());

		salida.setUsuario(usuarioEnSesion);

		productoBean.registrarSalida(salida);

		limpiarCampos(usuarioEnSesion);

		Utilitario
				.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_EXITO_PRODUCTO_REGISTRAR);

	}

	/**
	 * Metodo que permite mostrar los datos del producto seleccionado
	 * 
	 * @throws Exception
	 */
	public void habilitarTabla() throws Exception {

		Producto productoCombo = productoBean.buscarProductoXCodigo(idProducto);

		if (productoCombo == null) {

			Utilitario.errorDesdeCodigo("Debe seleccionar un producto");
			return;

		} else {
			detalleProducto = true;

			listaProductoSeleccionado = new ArrayList<Producto>();

			listaProductoSeleccionado.add(productoCombo);

			precioSugerido = productoCombo.getSaldoActual()
					/ productoCombo.getCantidad();

			precio = precioSugerido;

		}
		detalleProducto = true;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return idProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param idProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
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
	 * @return listaProductoSeleccionado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Producto> getListaProductoSeleccionado() {
		return listaProductoSeleccionado;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProductoSeleccionado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProductoSeleccionado(
			List<Producto> listaProductoSeleccionado) {
		this.listaProductoSeleccionado = listaProductoSeleccionado;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return detalleProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isDetalleProducto() {
		return detalleProducto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param detalleProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDetalleProducto(boolean detalleProducto) {
		this.detalleProducto = detalleProducto;
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
	 * @return precioSugerido
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getPrecioSugerido() {
		return precioSugerido;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param precioSugerido
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPrecioSugerido(double precioSugerido) {
		this.precioSugerido = precioSugerido;
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
	 * @return idBodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getIdBodega() {
		return idBodega;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param idBodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setIdBodega(String idBodega) {
		this.idBodega = idBodega;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaBodegas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Bodega> getListaBodegas() {
		return listaBodegas;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaBodegas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaBodegas(List<Bodega> listaBodegas) {
		this.listaBodegas = listaBodegas;
	}

}
