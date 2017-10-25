package co.com.inventario.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.BartenderBean;
import co.com.inventario.ejb.ProductoBean;
import co.com.inventario.ejb.TipoProductoBean;
import co.com.vuelos.entidades.Bartender;
import co.com.vuelos.entidades.Entrada;
import co.com.vuelos.entidades.EntradaProducto;
import co.com.vuelos.entidades.Producto;
import co.com.vuelos.entidades.Salida;
import co.com.vuelos.entidades.TipoProducto;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a las entradas de producto
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "informesAction")
@SessionScoped
public class InformesAction implements Serializable {

	/**
	 * identificacion de la serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	private List<Producto> listaProductos;

	private List<Entrada> listaEntradas;

	private List<Salida> listaSalidas;

	private List<Bartender> listaBartenders;

	@Inject
	private ProductoBean productoBean;

	@Inject
	private BartenderBean bartenderBean;

	@Inject
	private TipoProductoBean tipoProductoBean;

	private double saldoTotal;

	private String codigoTipo;

	private List<TipoProducto> listaTipoProductos;

	private Date fechaInicial;

	private Date fechaFinal;

	private List<EntradaProducto> listaProductosXEntrada;

	private String establecimiento;

	@PostConstruct
	public void init() {

		listaProductos = new ArrayList<Producto>();

		listaEntradas = new ArrayList<Entrada>();

		listaSalidas = new ArrayList<Salida>();

		listaBartenders = new ArrayList<Bartender>();

		listaProductosXEntrada = new ArrayList<EntradaProducto>();

		saldoTotal = 0;

		codigoTipo = ConstantesGenerales.CADENA_VACIA;

		establecimiento = ConstantesGenerales.CADENA_VACIA;

		listaTipoProductos = tipoProductoBean.listartipoProducto();

	}

	/**
	 * Informe de citas activas
	 * 
	 * @return
	 */
	public String listarStockProductos() {

		listaProductos = productoBean.listarProductos();

		for (int i = 0; i < listaProductos.size(); i++) {

			saldoTotal = saldoTotal + listaProductos.get(i).getSaldoActual();

		}

		return "/Informes/stock_productos.xhtml";

	}

	/**
	 * Informe de citas activas
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listarStockProductosXTipoOFecha() throws Exception {

		saldoTotal = 0;

		// LISTAR PRODUCTOS POR ESTABLECIMIENTO O SIN NINGUN FILTRO
		if (codigoTipo.equals(ConstantesGenerales.CADENA_VACIA)) {
			listaProductos = productoBean
					.listarProductosXEstablecimiento(establecimiento);

			for (int i = 0; i < listaProductos.size(); i++) {

				saldoTotal = saldoTotal
						+ listaProductos.get(i).getSaldoActual();

			}
			return "/Informes/stock_productos.xhtml";
		}

		// LISTAR PRODUCTOS POR TIPO DE PRODUCTO (FAMILIA)
		TipoProducto tipoProductoCombo = tipoProductoBean
				.buscarTipoProductoXCodigo2(codigoTipo);

		listaProductos = productoBean.listarStockProductosXTipoOFecha(
				tipoProductoCombo.getCodigo(), establecimiento);

		for (int i = 0; i < listaProductos.size(); i++) {

			saldoTotal = saldoTotal + listaProductos.get(i).getSaldoActual();

		}

		return "/Informes/stock_productos.xhtml";

	}

	public String redirigirInformeStock() {

		listaProductos = new ArrayList<Producto>();

		saldoTotal = 0;

		codigoTipo = ConstantesGenerales.CADENA_VACIA;

		listaTipoProductos = tipoProductoBean.listartipoProducto();

		establecimiento = ConstantesGenerales.CADENA_VACIA;

		return "/Informes/stock_productos.xhtml";
	}

	/**
	 * Informe de citas activas
	 * 
	 * @return
	 */
	public String listarEntradas() {

		listaEntradas = productoBean.listarEntradas();

		return "/Informes/entrada_productos.xhtml";

	}

	/**
	 * Informe de citas activas
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listarEntradasXTipoOFecha() throws Exception {

		// cuando le da el buscar sin ningun filtro
		if (codigoTipo.equals(ConstantesGenerales.CADENA_VACIA)
				&& fechaInicial == null && fechaFinal == null) {

			listaEntradas = productoBean.listarEntradas();

			return "/Informes/entrada_productos.xhtml";
		}

		// cuando le da buscar con todos los filtros
		if (codigoTipo.length() > 0 && fechaInicial != null
				&& fechaFinal != null) {

			TipoProducto tipoProductoCombo = tipoProductoBean
					.buscarTipoProductoXCodigo2(codigoTipo);

			listaEntradas = productoBean.listarEntradasXTipoOFechaCompleto(
					tipoProductoCombo.getCodigo(), fechaInicial, fechaFinal);

			return "/Informes/entrada_productos.xhtml";

		}
		// cuando le da buscar solo con los filtros de fechas
		if (codigoTipo.equals("") && fechaInicial != null && fechaFinal != null) {

			listaEntradas = productoBean.listarEntradasSoloFechas(fechaInicial,
					fechaFinal);

			return "/Informes/entrada_productos.xhtml";
		}

		// cuando le da buscar solo con el tipo de producto
		if (fechaInicial == null && fechaFinal == null
				&& codigoTipo.length() > 0) {

			listaEntradas = productoBean.listarEntradasSoloTipos(codigoTipo);

			return "/Informes/entrada_productos.xhtml";
		}

		return "";

	}

	public void verDocumento(Entrada entrada) throws IOException {

		FacesContext contex = FacesContext.getCurrentInstance();
		contex.getExternalContext().redirect(
				"/proveedor/Proveedor/" + entrada.getProveedor().getId() + "/"
						+ entrada.getId() + "/" + entrada.getNombreDocumento());

	}

	public void verProductosPorEntrada(Entrada entrada) {

		System.out.println("**************Entro este hp " + entrada);

		listaProductosXEntrada = productoBean.listarProductosXEntrada(entrada);
	}

	public String redirigirInformeEntradas() {

		listaEntradas = new ArrayList<Entrada>();

		codigoTipo = ConstantesGenerales.CADENA_VACIA;

		listaTipoProductos = tipoProductoBean.listartipoProducto();

		fechaInicial = null;

		fechaFinal = null;

		return "/Informes/entrada_productos.xhtml";
	}

	/**
	 * Informe de citas activas
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listarSalidasXTipoOFecha() throws Exception {

		System.out.println("*********Fecha inicial" + fechaInicial);

		// cuando le da el buscar sin ningun filtro
		if (codigoTipo.equals(ConstantesGenerales.CADENA_VACIA)
				&& fechaInicial == null && fechaFinal == null) {

			System.out.println("*********Entro al if");
			listaSalidas = productoBean.listarSalidas();

			return "/Informes/salida_productos.xhtml";
		}

		// cuando le da buscar con todos los filtros
		if (codigoTipo.length() > 0 && fechaInicial != null
				&& fechaFinal != null) {

			TipoProducto tipoProductoCombo = tipoProductoBean
					.buscarTipoProductoXCodigo2(codigoTipo);

			System.out.println("------------------" + tipoProductoCombo);

			listaSalidas = productoBean.listarSalidasXTipoOFechaCompleto(
					tipoProductoCombo.getCodigo(), fechaInicial, fechaFinal);

			return "/Informes/salida_productos.xhtml";

		}
		// cuando le da buscar solo con los filtros de fechas
		if (codigoTipo.equals("") && fechaInicial != null && fechaFinal != null) {

			listaSalidas = productoBean.listarSalidaSoloFechas(fechaInicial,
					fechaFinal);

			return "/Informes/salida_productos.xhtml";
		}

		// cuando le da buscar solo con el tipo de producto
		if (fechaInicial == null && fechaFinal == null
				&& codigoTipo.length() > 0) {

			listaSalidas = productoBean.listarSalidaSoloTipos(codigoTipo);

			return "/Informes/salida_productos.xhtml";
		}

		return "";

	}

	public String redirigirInformeSalidas() {

		listaSalidas = new ArrayList<Salida>();

		codigoTipo = ConstantesGenerales.CADENA_VACIA;

		listaTipoProductos = tipoProductoBean.listartipoProducto();

		fechaInicial = null;

		fechaFinal = null;

		return "/Informes/salida_productos.xhtml";
	}

	/**
	 * Informe de citas activas
	 * 
	 * @return
	 */
	public String listarSalidas() {

		listaSalidas = productoBean.listarSalidas();

		return "/Informes/salida_productos.xhtml";

	}

	/**
	 * Informe de bartenders
	 * 
	 * @return
	 */
	public String listarBartenders() {
		listaBartenders = bartenderBean.listarBartender();

		return "/Informes/bartenders.xhtml";
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
	 * @return listaEntradas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Entrada> getListaEntradas() {
		return listaEntradas;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaEntradas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaEntradas(List<Entrada> listaEntradas) {
		this.listaEntradas = listaEntradas;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaSalidas
	 * 
	 * @author Deivid Arias Ramirez
	 */
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
	 * @return listaBartenders
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Bartender> getListaBartenders() {
		return listaBartenders;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaBartenders
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaBartenders(List<Bartender> listaBartenders) {
		this.listaBartenders = listaBartenders;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return saldoTotal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getSaldoTotal() {
		return saldoTotal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param saldoTotal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
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
	 * @return fechaInicial
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param fechaInicial
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return fechaFinal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param fechaFinal
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaProductosXEntrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<EntradaProducto> getListaProductosXEntrada() {
		return listaProductosXEntrada;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProductosXEntrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProductosXEntrada(
			List<EntradaProducto> listaProductosXEntrada) {
		this.listaProductosXEntrada = listaProductosXEntrada;
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
