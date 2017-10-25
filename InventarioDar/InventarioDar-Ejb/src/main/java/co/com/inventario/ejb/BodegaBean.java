package co.com.inventario.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.Bodega;

@Stateless
@Dependent
public class BodegaBean implements Serializable {

	// Serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public BodegaBean() {

	}

	/**
	 * Metodo que permite buscar una bodega por medio de su id
	 * 
	 * @param id
	 *            , filtro de busqueda
	 * @return devuelve el bodega encontrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Bodega buscarBodega(Long id) {

		Bodega bodega = entityManager.find(Bodega.class, id);

		return bodega;
	}

	public Bodega buscarBodegaXNombre(String nombre) throws Exception {
		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select p from Bodega p where p.nombre=:nombre")
					.setParameter("nombre", nombre);

			Bodega bodega = (Bodega) query.getSingleResult();

			return bodega;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	/**
	 * Metodo que permite listar todos los bodegaes creados en el sistema
	 * 
	 * @return listado de bodegaes
	 * 
	 * @author Deivid Arias Ramirez
	 */

	public List<Bodega> listarBodegas() {

		Query query = entityManager.createQuery("Select p From Bodega p");

		return query.getResultList();
	}

	/**
	 * Metodo que permite editar un bodega existente en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 * @param bodega
	 */
	public void editarBodega(Bodega bodega) {
		entityManager.merge(bodega);
	}

	/**
	 * Metodo que permite registrar un bodega
	 * 
	 * @param tipoProducto
	 */
	public void registrarBodega(Bodega bodega) {
		entityManager.persist(bodega);
	}

}
