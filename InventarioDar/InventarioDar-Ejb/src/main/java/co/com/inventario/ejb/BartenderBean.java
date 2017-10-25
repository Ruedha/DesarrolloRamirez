package co.com.inventario.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.Bartender;

/**
 * Clase que permite realizar cambios sobre la tabla bartender en la bd
 * 
 * @author Deivid Arias Ramirez
 * 
 **/
@Stateless
@Dependent
public class BartenderBean implements Serializable {

	// Serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public BartenderBean() {

	}

	/**
	 * Metodo que permite buscar un bartender por medio de su id
	 * 
	 * @param id
	 *            , filtro de busqueda
	 * @return devuelve el bartender encontrado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Bartender buscarBartender(Long id) {

		Bartender bartender = entityManager.find(Bartender.class, id);

		return bartender;
	}

	/**
	 * Metodo que permite listar todas los bartender creados en el sistema
	 * 
	 * @return listado de bartender
	 * 
	 * @author Deivid Arias Ramirez
	 */

	public List<Bartender> listarBartender() {

		Query query = entityManager.createQuery("Select b From Bartender b");

		return query.getResultList();
	}

	/**
	 * Metodo que permite editar un bartender existente en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 * @param bartender
	 *            a editar
	 */
	public void editarBartender(Bartender bartender) {
		entityManager.merge(bartender);
	}

	/**
	 * Metodo que permite registrar un bartender
	 * 
	 * @param Bartender
	 *            a registrar
	 */
	public void registrarBartender(Bartender bartender) {
		entityManager.persist(bartender);
	}

}
