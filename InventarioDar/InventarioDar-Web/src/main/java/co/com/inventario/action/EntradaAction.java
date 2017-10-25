package co.com.inventario.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.ProductoBean;
import co.com.inventario.ejb.ProveedorBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Entrada;
import co.com.vuelos.entidades.EntradaProducto;
import co.com.vuelos.entidades.Producto;
import co.com.vuelos.entidades.Proveedor;
import co.com.vuelos.entidades.Usuario;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a las entradas de producto
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "entradaAction")
@SessionScoped
public class EntradaAction implements Serializable {

	/**
	 * identificacion de la serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	private String idProducto;

	private String idProveedor;

	private List<Producto> listaProductos;

	private Usuario usuarioEnSesion;

	private int cantidad;

	private String ubicacion;

	List<Producto> listaProductoSeleccionado;

	private boolean detalleProducto;

	private double valorUnitario;

	private String numeroFactura;

	private List<Proveedor> listaProveedores;

	private List<Producto> listaProductosAgregados;

	@Inject
	private ProveedorBean proveedorBean;

	private boolean estadoPanel;

	private double valorTotal;

	private UploadedFile documento;

	/**
	 * Permite acceder al bean del producto alojado en el contexto del servidor
	 */
	@Inject
	private ProductoBean productoBean;

	public String limpiarCampos(Usuario usuarioSesion) {

		usuarioEnSesion = usuarioSesion;
		listaProductos = productoBean.listarProductos();
		cantidad = 0;
		idProducto = ConstantesGenerales.CADENA_VACIA;
		idProveedor = ConstantesGenerales.CADENA_VACIA;
		ubicacion = ConstantesGenerales.CADENA_VACIA;
		idProducto = ConstantesGenerales.CADENA_VACIA;
		detalleProducto = false;
		valorUnitario = 0;
		numeroFactura = ConstantesGenerales.CADENA_VACIA;
		listaProveedores = proveedorBean.listarProveedores();
		listaProductosAgregados = new ArrayList<Producto>();
		estadoPanel = false;
		valorTotal = 0.0;
		documento = null;

		return "/Entrada/producto_entrada.xhtml";
	}

	public String limpiarCamposCancelar() {

		listaProductos = productoBean.listarProductos();
		cantidad = 0;
		idProducto = ConstantesGenerales.CADENA_VACIA;
		idProveedor = ConstantesGenerales.CADENA_VACIA;
		ubicacion = ConstantesGenerales.CADENA_VACIA;
		idProducto = ConstantesGenerales.CADENA_VACIA;
		detalleProducto = false;
		valorUnitario = 0;
		numeroFactura = ConstantesGenerales.CADENA_VACIA;
		listaProveedores = proveedorBean.listarProveedores();
		listaProductosAgregados = new ArrayList<Producto>();
		estadoPanel = false;
		valorTotal = 0.0;
		documento = null;

		return "/Entrada/producto_entrada.xhtml";
	}

	public void cargarDocumento(FileUploadEvent event) throws IOException {

		documento = event.getFile();

		System.out.println("********Documento " + documento.getFileName());

	}

	/**
	 * Metodo que permite registrar una entrada de productos en el sistema
	 * 
	 * @throws Exception
	 */
	public void registrarEntrada() throws Exception {

		Entrada entrada = new Entrada();

		Proveedor proveedorCombo = proveedorBean
				.buscarProveedorXNombre(idProveedor);

		if (proveedorCombo == null) {

			Utilitario.errorDesdeCodigo("Debe seleccionar un proveedor!");

			return;
		}

		if (documento == null) {
			Utilitario.errorDesdeCodigo("Debe cargar el documento!");
			return;
		}

		entrada.setNombreDocumento(documento.getFileName());

		entrada.setUbicacion(ubicacion);

		entrada.setProveedor(proveedorCombo);

		entrada.setFecha(new Date());

		entrada.setUsuario(usuarioEnSesion);

		entrada.setNumeroFactura(numeroFactura);

		if (listaProductosAgregados.size() > 0) {

			EntradaProducto entradaProducto = new EntradaProducto();

			List<EntradaProducto> entradaProductos = new ArrayList<EntradaProducto>();
			productoBean.registrarEntrada(entrada);

			entradaProducto.setEntrada(entrada);

			for (int i = 0; i < listaProductosAgregados.size(); i++) {

				Producto productoAgregado = listaProductosAgregados.get(i);

				entradaProducto.setProducto(productoAgregado);

				entradaProducto.setCantidad(productoAgregado
						.getCantidadTemporal());

				entradaProducto.setPrecioUnitario(productoAgregado
						.getValorUnitarioTemporal());

				entradaProductos.add(entradaProducto);

				productoBean.registrarEntradaProducto(entradaProducto);

			}

			entrada.setTotal(valorTotal);

			Utilitario
					.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_EXITO_PRODUCTO_REGISTRAR);

			productoBean.editarEntrada(entrada);

			String directorioEntrada = System.getenv("JBOSS_HOME")
					+ "\\standalone\\deployments\\proveedor.war\\Proveedor\\"
					+ entrada.getProveedor().getId() + "\\" + entrada.getId();

			File directorio = new File(directorioEntrada);
			directorio.mkdirs();

			String filename = FilenameUtils.getName(documento.getFileName());
			InputStream input = documento.getInputstream();
			OutputStream output = new FileOutputStream(new File(
					directorioEntrada + "\\", filename));

			entrada.setNombreDocumento(documento.getFileName());
			productoBean.editarEntrada(entrada);

			limpiarCampos(usuarioEnSesion);

			try {
				IOUtils.copy(input, output);
			} finally {
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(output);
			}

		} else {
			Utilitario
					.errorDesdeCodigo("Debe seleccionar uno o varios productos!");
		}

	}

	/**
	 * guardar productos para agregar a las entradas
	 * 
	 * @throws Exception
	 */
	public void agregarProductos() throws Exception {

		Producto productoCombo = productoBean.buscarProductoXCodigo(idProducto);

		if (productoCombo == null) {

			Utilitario.errorDesdeCodigo("Debe seleccionar un producto!");

			return;
		}

		productoCombo.setCantidad(productoCombo.getCantidad() + cantidad);

		productoCombo.setSaldoActual(productoCombo.getSaldoActual()
				+ (valorUnitario * cantidad));

		productoCombo.setCantidadTemporal(cantidad);

		productoCombo.setValorUnitarioTemporal(valorUnitario);

		productoBean.editarProducto(productoCombo);

		listaProductosAgregados.add(productoCombo);

		valorTotal = valorTotal + (cantidad * valorUnitario);

		detalleProducto = true;

	}

	public void eliminarProductoAgregado(Producto productoTabla) {

		productoTabla.setSaldoActual(productoTabla.getSaldoActual()
				- productoTabla.getValorTotalTemporal());
		productoTabla.setCantidad(productoTabla.getCantidad()
				- productoTabla.getCantidadTemporal());

		productoBean.editarProducto(productoTabla);

		listaProductosAgregados.remove(productoTabla);

		valorTotal = valorTotal - productoTabla.getValorTotalTemporal();

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

			// listaProductoSeleccionado = new ArrayList<Producto>();
			//
			// listaProductoSeleccionado.add(productoCombo);

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
	 * @return destino
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param destino
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
	 * @return idProveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param idProveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaProveedores
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProveedores
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estadoPanel
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isEstadoPanel() {
		return estadoPanel;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estadoPanel
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstadoPanel(boolean estadoPanel) {
		this.estadoPanel = estadoPanel;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaProductosAgregados
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Producto> getListaProductosAgregados() {
		return listaProductosAgregados;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProductosAgregados
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProductosAgregados(
			List<Producto> listaProductosAgregados) {
		this.listaProductosAgregados = listaProductosAgregados;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return valorTotal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getValorTotal() {
		return valorTotal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param valorTotal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return documento
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public UploadedFile getDocumento() {
		return documento;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param documento
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDocumento(UploadedFile documento) {
		this.documento = documento;
	}

}
