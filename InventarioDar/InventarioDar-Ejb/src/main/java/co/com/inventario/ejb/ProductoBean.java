package co.com.inventario.ejb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.Entrada;
import co.com.vuelos.entidades.EntradaProducto;
import co.com.vuelos.entidades.Producto;
import co.com.vuelos.entidades.Salida;

/**
 * Clase que permite realizar acciones sobre la tabla producto en la bd
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Stateless
@Dependent
public class ProductoBean implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public ProductoBean() {

	}

	/**
	 * 
	 * 
	 * Metodo que permite realizar la insercion de un producto si desde el pojo
	 * viene con todos los datos correctos
	 * 
	 * @param producto
	 *            , el cual sera creado en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void registrarProducto(Producto producto) {

		Query query = entityManager
				.createQuery("Select max(p.id) from Producto p");

		Long ultimoId = (Long) query.getSingleResult();

		producto.setId(ultimoId + 1);

		entityManager.persist(producto);

	}

	public void insertarProductosMasivos() {

		Query query = entityManager
				.createNativeQuery("insert into producto (id,nombre,tipoproducto,codigo,presentacion,cantidadporcaja,stockminimo,establecimiento,cantidad,saldoactual) values (1,'Absolut',1,'1101','750','12','0','Club','0','0')");

		query.executeUpdate();

	}

	/**
	 * 
	 * 
	 * Metodo que permite buscar un producto por su codigo de referencia
	 * 
	 * @param login
	 *            , nombre de inicio de sesion
	 * @param contrasena
	 *            , contraseña de inicio de sesion
	 * @return, devuelve el producto que encontro con ese codigo
	 * 
	 * @throws Exception
	 *             , en caso tal que ocurra un error de conexion con la base de
	 *             datos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Producto buscarProductoXCodigo(String codigo) throws Exception {
		try {

			// consulta para traer el producto de la base de datos
			Query consulta = entityManager.createQuery(
					"select p from Producto p where p.codigo=:codigo")
					.setParameter("codigo", codigo);

			Producto producto = (Producto) consulta.getSingleResult();

			return producto;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Metodo que permite realizar la actualizacion de un producto si desde el
	 * pojo viene con todos los datos correctos
	 * 
	 * @param producto
	 *            que sera actualizado en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void editarProducto(Producto producto) {

		entityManager.merge(producto);

	}

	/**
	 * Metodo que permite realizar la actualizacion de un producto si desde el
	 * pojo viene con todos los datos correctos
	 * 
	 * @param producto
	 *            que sera actualizado en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void editarEntrada(Entrada entrada) {

		entityManager.merge(entrada);

	}

	/**
	 * Metodo que permite registrar una entrada de un producto
	 * 
	 * @param entrada
	 */
	public void registrarEntrada(Entrada entrada) {

		entityManager.persist(entrada);
	}

	public List<Producto> listarProductos() {
		Query consulta = entityManager
				.createQuery("select p from Producto p order by p.id");

		List<Producto> listaProductos = consulta.getResultList();

		return listaProductos;
	}

	public List<Producto> listarProductosXEstablecimiento(String establecimiento) {

		if (establecimiento.length() > 0) {

			System.out.println("***************Entro " + establecimiento);
			Query consulta = entityManager
					.createQuery("select p from Producto p where p.establecimiento='"
							+ establecimiento + "' order by p.id");

			List<Producto> listaProductos = consulta.getResultList();

			return listaProductos;
		} else {

			System.out.println("***************Entro al 2 " + establecimiento);
			Query consulta = entityManager
					.createQuery("select p from Producto p order by p.id");

			List<Producto> listaProductos = consulta.getResultList();

			return listaProductos;

		}
	}

	public List<Producto> listarStockProductosXTipoOFecha(
			String codigoProducto, String establecimiento) {

		if (establecimiento.length() > 0) {
			Query consulta = entityManager
					.createQuery("select p from Producto p where p.tipoProducto.codigo='"
							+ codigoProducto
							+ "' and p.establecimiento='"
							+ establecimiento + "'");

			List<Producto> listaProductos = consulta.getResultList();

			return listaProductos;

		} else {
			Query consulta = entityManager
					.createQuery("select p from Producto p where p.tipoProducto.codigo='"
							+ codigoProducto + "'");

			List<Producto> listaProductos = consulta.getResultList();

			return listaProductos;

		}
	}

	public List<Entrada> listarEntradas() {
		Query consulta = entityManager.createQuery("select e from Entrada e");

		List<Entrada> listaEntradas = consulta.getResultList();

		return listaEntradas;
	}

	public List<EntradaProducto> listarProductosXEntrada(Entrada entrada) {
		Query consulta = entityManager
				.createQuery("select ep from EntradaProducto ep where ep.entrada.id='"
						+ entrada.getId() + "'");

		List<EntradaProducto> listaProductosXEntrada = consulta.getResultList();

		return listaProductosXEntrada;
	}

	public List<Entrada> listarEntradasXTipoOFechaCompleto(
			String codigoProducto, Date fechaInicial, Date fechaFinal) {

		String fechaInicialFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 00:00:00").format(fechaInicial);

		String fechaFinalFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 23:59:00").format(fechaFinal);

		Query consulta = entityManager
				.createQuery("select e from Entrada e where e.fecha BETWEEN '"
						+ fechaInicialFormateada + "' and '"
						+ fechaFinalFormateada
						+ "' and e.producto.tipoProducto.codigo='"
						+ codigoProducto + "'");

		return consulta.getResultList();

	}

	public List<Entrada> listarEntradasSoloFechas(Date fechaInicial,
			Date fechaFinal) {

		String fechaInicialFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 00:00:00").format(fechaInicial);

		String fechaFinalFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 23:59:00").format(fechaFinal);

		Query consulta = entityManager
				.createQuery("select e from Entrada e where e.fecha BETWEEN '"
						+ fechaInicialFormateada + "' and '"
						+ fechaFinalFormateada + "'");

		return consulta.getResultList();

	}

	public List<Entrada> listarEntradasSoloTipos(String codigoProducto) {

		Query consulta = entityManager
				.createQuery("select e from Entrada e where e.producto.tipoProducto.codigo='"
						+ codigoProducto + "'");

		return consulta.getResultList();

	}

	public List<Salida> listarSalidas() {
		Query consulta = entityManager.createQuery("select s from Salida s");

		List<Salida> listaSalidas = consulta.getResultList();

		return listaSalidas;
	}

	public List<Salida> listarSalidasXTipoOFechaCompleto(String codigoProducto,
			Date fechaInicial, Date fechaFinal) {

		String fechaInicialFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 00:00:00").format(fechaInicial);

		String fechaFinalFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 23:59:00").format(fechaFinal);

		Query consulta = entityManager
				.createQuery("select e from Salida e where e.fecha BETWEEN '"
						+ fechaInicialFormateada + "' and '"
						+ fechaFinalFormateada
						+ "' and e.producto.tipoProducto.codigo='"
						+ codigoProducto + "'");

		return consulta.getResultList();

	}

	public List<Salida> listarSalidaSoloFechas(Date fechaInicial,
			Date fechaFinal) {

		String fechaInicialFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 00:00:00").format(fechaInicial);

		String fechaFinalFormateada = new SimpleDateFormat(
				"yyyy-MM-dd 23:59:00").format(fechaFinal);

		Query consulta = entityManager
				.createQuery("select e from Salida e where e.fecha BETWEEN '"
						+ fechaInicialFormateada + "' and '"
						+ fechaFinalFormateada + "'");

		return consulta.getResultList();

	}

	public List<Salida> listarSalidaSoloTipos(String codigoProducto) {

		Query consulta = entityManager
				.createQuery("select e from Salida e where e.producto.tipoProducto.codigo='"
						+ codigoProducto + "'");

		return consulta.getResultList();

	}

	/**
	 * Metodo que permite registrar una entrada de un producto
	 * 
	 * @param entrada
	 */
	public void registrarSalida(Salida salida) {

		entityManager.merge(salida);
	}

	public double totalEntradasXproducto(Long producto) {

		Query consulta = entityManager
				.createQuery("select sum(e.total) from Entrada e where e.producto.id="
						+ producto);

		double total = (Double) consulta.getSingleResult();

		return total;

	}

	public Long cantidadDeEntradasXproducto(Long producto) {

		Query consulta = entityManager
				.createQuery("select sum(e.cantidad) from Entrada e where e.producto.id="
						+ producto);

		Long cantidad = (Long) consulta.getSingleResult();

		return cantidad;

	}

	public void registrarEntradaProducto(EntradaProducto entradaProducto) {

		entityManager.persist(entityManager.merge(entradaProducto));

	}

}
