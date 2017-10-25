package co.com.inventario.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.TipoProducto;

/**
 * Clase que permite realizar cambios sobre la tabla tipoProducto en la bd
 * 
 * @author Deivid Arias Ramirez
 * 
 **/
@Stateless
@Dependent
public class TipoProductoBean implements Serializable {

	// Serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public TipoProductoBean() {

	}

	/**
	 * Metodo que permite buscar una tipoProducto por medio de su id
	 * 
	 * @param id
	 *            , filtro de busqueda
	 * @return devuelve el tipoProducto encontrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public TipoProducto buscarTipoProducto(Long id) {

		TipoProducto tipoProducto = entityManager.find(TipoProducto.class, id);

		return tipoProducto;
	}

	/**
	 * 
	 * 
	 * Metodo que permite buscar un tipo producto por codigo para ser editado
	 * 
	 * @param codigo
	 *            por el cual sera buscado el usuario
	 * @return usuario encontrado con el codigo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public TipoProducto buscarTipoProductoXCodigo(String codigo)
			throws Exception {

		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select tp from TipoProducto tp where tp.codigo=:codigo")
					.setParameter("codigo", codigo);

			TipoProducto tipoProducto = (TipoProducto) query.getSingleResult();

			return tipoProducto;
		} catch (Exception e) {
			throw new Exception();
		}

	}

	/**
	 * 
	 * 
	 * Metodo que permite buscar un tipo producto por codigo para ser editado
	 * 
	 * @param codigo
	 *            por el cual sera buscado el usuario
	 * @return usuario encontrado con el codigo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public TipoProducto buscarTipoProductoXCodigo2(String codigo)
			throws Exception {

		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select tp from TipoProducto tp where tp.codigo=:codigo")
					.setParameter("codigo", codigo);

			TipoProducto tipoProducto = (TipoProducto) query.getSingleResult();

			return tipoProducto;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Metodo que permite listar todas los tipo de identificacion creadas en el
	 * sistema
	 * 
	 * @return listado de tipo de identificacion
	 * 
	 * @author Deivid Arias Ramirez
	 */

	public List<TipoProducto> listartipoProducto() {

		Query query = entityManager.createQuery("Select t From TipoProducto t");

		return query.getResultList();
	}

	/**
	 * Metodo que permite editar un tipo de producto existente en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 * @param tipoDocumento
	 */
	public void editarTipoProducto(TipoProducto tipoDocumento) {
		entityManager.merge(tipoDocumento);
	}

	/**
	 * Metodo que permite registrar un tipo de producto
	 * 
	 * @param tipoProducto
	 */
	public void registrarTipoProducto(TipoProducto tipoProducto) {

		Query query = entityManager
				.createQuery("Select max(t.id) from TipoProducto t");

		Long ultimoId = (Long) query.getSingleResult();
		tipoProducto.setId(ultimoId + 1);

		entityManager.persist(entityManager.merge(tipoProducto));
	}
}
