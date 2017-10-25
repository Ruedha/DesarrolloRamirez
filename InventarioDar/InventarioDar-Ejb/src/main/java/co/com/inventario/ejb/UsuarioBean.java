package co.com.inventario.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.vuelos.entidades.TipoProducto;
import co.com.vuelos.entidades.Usuario;

/**
 * Clase que permite realizar acciones sobre la tabla usuario en la bd
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Stateless
@Dependent
public class UsuarioBean implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	/**
	 * Permite establecer la conexion con la base de datos para realizar
	 * acciones sobre las tablas
	 * 
	 */
	@PersistenceContext(unitName = "inventariodar-ds")
	private EntityManager entityManager;

	public UsuarioBean() {

	}

	/**
	 * 
	 * 
	 * Metodo que permite realizar la insercion de un usuario si desde el pojo
	 * viene con todos los datos correctos
	 * 
	 * @param usuario
	 *            , el cual sera creado en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void registrarUsuario(Usuario usuario) {

		Query query = entityManager
				.createQuery("Select max(u.id) from Usuario u");

		Long ultimoId = (Long) query.getSingleResult();

		usuario.setId(ultimoId + 1);

		entityManager.persist(entityManager.merge(usuario));

	}

	/**
	 * 
	 * 
	 * Metodo que permite buscar un usuario por login y contraseña para iniciar
	 * sesion
	 * 
	 * @param login
	 *            , nombre de inicio de sesion
	 * @param contrasena
	 *            , contraseña de inicio de sesion
	 * @return, devuelve el usuario que encontro con ese login y esa contrasena
	 * 
	 * @throws Exception
	 *             , en caso tal que ocurra un error de conexion con la base de
	 *             datos
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Usuario buscarUsuarioXloginYContrasena(String login,
			String contrasena) throws Exception {
		try {

			// consulta para traer el usuario de la base de datos
			Query consulta = entityManager
					.createQuery(
							"select u from Usuario u where u.login=:login and u.contrasena=:contrasena")
					.setParameter("login", login)
					.setParameter("contrasena", contrasena);

			Usuario usuario = (Usuario) consulta.getSingleResult();

			return usuario;
		} catch (Exception e) {
			throw new Exception();
		}

	}

	/**
	 * 
	 * 
	 * Metodo que permite buscar un usuario por login para ser editado
	 * 
	 * @param login
	 *            por el cual sera buscado el usuario
	 * @return usuario encontrado con el login
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Usuario buscarUsuarioXLogin(String login) throws Exception {

		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select u from Usuario u where u.login=:login")
					.setParameter("login", login);

			Usuario usuario = (Usuario) query.getSingleResult();

			return usuario;
		} catch (Exception e) {
			throw new Exception();
		}

	}

	public Usuario buscarUsuarioXLogin2(String login) throws Exception {

		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select u from Usuario u where u.login=:login")
					.setParameter("login", login);

			Usuario usuario = (Usuario) query.getSingleResult();

			return usuario;
		} catch (Exception e) {
			return null;
		}

	}

	public Usuario buscarUsuarioXCedula(String cedula) throws Exception {

		try {
			// Hago la consulta para buscar el usuario
			Query query = entityManager.createQuery(
					"select u from Usuario u where u.cedula=:cedula")
					.setParameter("cedula", cedula);

			Usuario usuario = (Usuario) query.getSingleResult();

			return usuario;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Metodo que permite realizar la actualizacion de un usuario si desde el
	 * pojo viene con todos los datos correctos
	 * 
	 * @param usuario
	 *            que sera actualizado en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void editarUsuario(Usuario usuario) {

		entityManager.merge(usuario);

	}

	/**
	 * Metodo que permite listar todas los usuarios creadas en el sistema
	 * 
	 * @return listado de usuarios
	 * 
	 * @author Deivid Arias Ramirez
	 */

	public List<Usuario> listarUsuarios() {

		Query query = entityManager.createQuery("Select u From Usuario u");

		return query.getResultList();
	}

}
