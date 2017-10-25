package co.com.inventario.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.Empleado;

/**
 * Clase que permite realizar cambios sobre la tabla empleado en la bd
 * 
 * @author Santiago Rueda Ramirez
 * 
 **/
@Stateless
@Dependent
public class EmpleadoBean implements Serializable {

	// Serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public EmpleadoBean() {

	}

	/**
	 * Metodo que permite buscar un empleado por medio de su id
	 * 
	 * @param id
	 *            , filtro de busqueda
	 * @return devuelve el empleado encontrado
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public Empleado buscarEmpleado(Long id) {

		Empleado empleado = entityManager.find(Empleado.class, id);

		return empleado;
	}

	public Empleado buscarEmpleadoXNombre(String nombre) throws Exception {
		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select p from Empleado e where e.nombre=:nombre")
					.setParameter("nombre", nombre);

			Empleado empleado = (Empleado) query.getSingleResult();

			return empleado;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	/**
	 * Metodo que permite listar todos los empleado creados en el sistema
	 * 
	 * @return listado de empleados
	 * 
	 * @author Santiago Rueda Ramirez
	 */

	public List<Empleado> listarEmpleados() {

		Query query = entityManager.createQuery("Select e From Empleado e");

		return query.getResultList();
	}

	/**
	 * Metodo que permite editar un empleado existente en el sistema
	 * 
	 * @author Santiago Rueda Ramirez
	 * 
	 * @param Empleado
	 */
	public void editarEmpleado(Empleado empleado) {
		entityManager.merge(empleado);
	}

	/**
	 * Metodo que permite registrar un empleado
	 * 
	 * @param Empleado
	 */
	public void registrarEmpleado(Empleado empleado) {

		try {

			Query query = entityManager
					.createQuery("Select max(e.id) from Empleado e");

			Long ultimoId = (Long) query.getSingleResult();
			empleado.setId(ultimoId + 1);

		} catch (Exception e) {
			System.out.println("*******Entro");

			empleado.setId(1L);
		}

		entityManager.persist(empleado);
	}
}
