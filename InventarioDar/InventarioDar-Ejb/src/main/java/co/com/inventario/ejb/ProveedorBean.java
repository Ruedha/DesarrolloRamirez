package co.com.inventario.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.Proveedor;

/**
 * Clase que permite realizar cambios sobre la tabla proveedor en la bd
 * 
 * @author Deivid Arias Ramirez
 * 
 **/
@Stateless
@Dependent
public class ProveedorBean implements Serializable {

	// Serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public ProveedorBean() {

	}

	/**
	 * Metodo que permite buscar una proveedor por medio de su id
	 * 
	 * @param id
	 *            , filtro de busqueda
	 * @return devuelve el proveedor encontrada
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Proveedor buscarProveedor(Long id) {

		Proveedor proveedor = entityManager.find(Proveedor.class, id);

		return proveedor;
	}

	public Proveedor buscarProveedorXNombre(String nombre) throws Exception {
		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select p from Proveedor p where p.nombre=:nombre")
					.setParameter("nombre", nombre);

			Proveedor proveedor = (Proveedor) query.getSingleResult();

			return proveedor;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	/**
	 * Metodo que permite listar todos los proveedores creados en el sistema
	 * 
	 * @return listado de proveedores
	 * 
	 * @author Deivid Arias Ramirez
	 */

	public List<Proveedor> listarProveedores() {

		Query query = entityManager.createQuery("Select p From Proveedor p");

		return query.getResultList();
	}

	/**
	 * Metodo que permite editar un proveedor existente en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 * @param Proveedor
	 */
	public void editarProveedor(Proveedor proveedor) {
		entityManager.merge(proveedor);
	}

	/**
	 * Metodo que permite registrar un proveedor
	 * 
	 * @param tipoProducto
	 */
	public void registrarProveedor(Proveedor proveedor) {

		try {

			Query query = entityManager
					.createQuery("Select max(p.id) from Proveedor p");

			Long ultimoId = (Long) query.getSingleResult();
			proveedor.setId(ultimoId + 1);

		} catch (Exception e) {
			System.out.println("*******Entro");

			proveedor.setId(1L);
		}

		entityManager.persist(proveedor);
	}
}
