package co.com.inventario.action;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.UsuarioBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Usuario;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes al usuario
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "userAction")
@SessionScoped
public class UsuarioAction implements Serializable {

	/**
	 * identificacion de la serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	// Cedula correspondiente al usuario
	private String cedula;

	// nombre del usuario
	private String nombre;

	// Primer apellido del usuario
	private String primerApellido;

	// Segundo apellido del usuario
	private String segundoApellido;

	// nickname con el cual el usuario iniciara sesion
	private String login;

	// Contrasena para el inicio de sesion
	private String contrasena;

	// Indica si el usuario es administrador
	private boolean administrador;

	// Correo correspondiente al usuario
	private String email;

	// Indica si el usuario estara activo o inactivo en el sistema
	private String estado;

	// nombre completo del usuario
	private String nombreIntegrado;

	// usuario el cual sera inactivado en el sistema
	private Usuario usuarioInactivar;

	// sera el login del usuario que se inactivara
	private String loginInactivar;

	// es el nombre que se mostrara cuando se haga la busqueda para inactivar
	private String nombreIntegradoInactivar;

	// es la bandera para mostrar u ocultar el panel
	private boolean estadoPanelActivarInactivar = false;

	// bandera para ocultar o mostrar el boton activar usuario
	private boolean botonActivar = false;

	// bandera para ocultar o mostrar el boton inactivar usuario
	private boolean botonInactivar = false;

	// Cedula correspondiente al usuario
	private String cedulaEditar;

	// nombre del usuario
	private String nombreEditar;

	// Primer apellido del usuario
	private String primerApellidoEditar;

	// Segundo apellido del usuario
	private String segundoApellidoEditar;

	// nickname con el cual el usuario iniciara sesion
	private String loginEditar;

	// Contrasena para el inicio de sesion
	private String contrasenaEditar;

	// Indica si el usuario es administrador
	private boolean administradorEditar;

	// Correo correspondiente al usuario
	private String emailEditar;

	// Indica si el usuario estara activo o inactivo en el sistema
	private String estadoEditar;

	// nombre completo del usuario
	private String nombreIntegradoEditar;

	// usuario a editar en el sistema
	private Usuario usuarioEditar;

	// bandera para mostra u ocultar los datos del usuario
	private boolean visibleDatosUsuario = false;

	// Login del usuario a editar
	private String loginEditarNuevo;

	private List<Usuario> listaUsuarios;

	private boolean usuarioHookah;

	private boolean usuarioHookahEditar;

	/**
	 * Permite acceder al bean del usuario alojado en el contexto del servidor
	 */
	@Inject
	private UsuarioBean usuarioBean;

	public UsuarioAction() {

	}

	/**
	 * Metodo que permite inicializar todos los campos de la aplicacion
	 */
	public String limpiarCampos() {

		usuarioHookah = false;

		cedula = ConstantesGenerales.CADENA_VACIA;

		nombre = ConstantesGenerales.CADENA_VACIA;

		primerApellido = ConstantesGenerales.CADENA_VACIA;

		segundoApellido = ConstantesGenerales.CADENA_VACIA;

		login = ConstantesGenerales.CADENA_VACIA;

		contrasena = ConstantesGenerales.CADENA_VACIA;

		administrador = false;

		email = ConstantesGenerales.CADENA_VACIA;

		estado = ConstantesGenerales.CADENA_VACIA;

		nombreIntegrado = ConstantesGenerales.CADENA_VACIA;

		loginInactivar = ConstantesGenerales.CADENA_VACIA;

		visibleDatosUsuario = false;
		cedulaEditar = ConstantesGenerales.CADENA_VACIA;
		nombreEditar = ConstantesGenerales.CADENA_VACIA;
		primerApellidoEditar = ConstantesGenerales.CADENA_VACIA;
		segundoApellidoEditar = ConstantesGenerales.CADENA_VACIA;
		loginEditar = ConstantesGenerales.CADENA_VACIA;
		contrasenaEditar = ConstantesGenerales.CADENA_VACIA;
		emailEditar = ConstantesGenerales.CADENA_VACIA;
		estadoEditar = ConstantesGenerales.CADENA_VACIA;
		nombreIntegradoEditar = ConstantesGenerales.CADENA_VACIA;
		loginEditarNuevo = ConstantesGenerales.CADENA_VACIA;

		return "/Usuario/registrar_usuario.xhtml";
	}

	/**
	 * Metodo que permite listar los usuarios del sistema
	 * 
	 * @return listado de usuarios
	 */
	public String limpiarCamposListar() {

		listaUsuarios = usuarioBean.listarUsuarios();

		return "/Usuario/lista_usuario.xhtml";
	}

	/**
	 * 
	 * Metodo que permite el registro de un usuario con todas sus condiciones
	 * 
	 * @throws Exception
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 */
	public String registrarUsuario() throws Exception {

		Usuario usuario = new Usuario();

		// Asignamos los valores de los campos al usuario
		usuario.setCedula(cedula);
		usuario.setPrimerApellido(primerApellido);
		usuario.setSegundoApellido(segundoApellido);
		usuario.setNombre(nombre);
		usuario.setLogin(login);
		// cambiar para la seguridad
		usuario.setAdministrador(administrador);
		usuario.setEmail(email);
		usuario.setEstado(ConstantesGenerales.ESTADO_ACTIVO_USUARIO);
		usuario.setNombreIntegrado(nombre + ConstantesGenerales.ESPACIO_BLANCO
				+ primerApellido + ConstantesGenerales.ESPACIO_BLANCO
				+ segundoApellido);
		usuario.setContrasena(login);

		if (usuarioHookah == true) {
			usuario.setUsuarioHookah("SI");
		} else {
			usuario.setUsuarioHookah("NO");
		}

		Usuario validarCedula = usuarioBean.buscarUsuarioXCedula(cedula);

		if (validarCedula != null) {

			Utilitario
					.errorDesdeCodigo("Ya existe un usuario con esa cedula en el sistema");

			return ConstantesGenerales.CADENA_VACIA;
		}

		Usuario validarLogin = usuarioBean.buscarUsuarioXLogin2(login);

		if (validarLogin != null) {

			Utilitario.errorDesdeCodigo("El login ya existe en el sistema");

			return ConstantesGenerales.CADENA_VACIA;
		}

		// Accedemos al ejb para registrar el usuario con los atributos
		// asignados
		usuarioBean.registrarUsuario(usuario);

		// mensaje de indicando que se registro correctamente
		Utilitario
				.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_EXITO_USUARIO_REGISTRAR);

		// reseteamos todos los campos
		limpiarCampos();

		return "/Usuario/registrar_usuario.xhtml";

	}

	/**
	 * Metodo que permite cambiar el estado del usuario para saber si puede o no
	 * iniciar sesion en el sistema
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 */
	public void cambiarEstado() {

		// Si el estado es activo quiere decir que se inactivara
		if (usuarioInactivar.getEstado().equals(
				ConstantesGenerales.ESTADO_ACTIVO_USUARIO)) {
			usuarioInactivar
					.setEstado(ConstantesGenerales.ESTADO_INACTIVO_USUARIO);
			usuarioBean.editarUsuario(usuarioInactivar);

			Utilitario
					.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_USUARIO_INACTIVADO);

		}
		// Si el estado es inactivo quiere decir que se activara
		else {
			usuarioInactivar
					.setEstado(ConstantesGenerales.ESTADO_ACTIVO_USUARIO);
			usuarioBean.editarUsuario(usuarioInactivar);
			Utilitario
					.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_USUARIO_ACTIVADO);

		}
		iniciarPaginaCambiarEstado();
	}

	/**
	 * Metodo que permite asignar el texto del boton para diferenciar si es
	 * activar o inactivar
	 * 
	 * @return texto del boton
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String textoBoton() {

		if (usuarioInactivar != null) {
			// si el estado es activo entonces se mostrara la cadena 'inactivar'
			if (usuarioInactivar.getEstado().equals(
					ConstantesGenerales.ESTADO_ACTIVO_USUARIO)) {

				return ConstantesGenerales.TEXTO_BOTON_INACTIVAR;

			}
			// si el estado es inactivo entonces se mostrara la cadena 'Activar'
			else {
				return ConstantesGenerales.TEXTO_BOTON_ACTIVAR;
			}
		} else {
			return ConstantesGenerales.CADENA_VACIA;
		}

	}

	/**
	 * Metodo que permite buscar un usuario por login para luego editar su
	 * estado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void buscarUsuarioPorLoginInactivar() {
		try {
			usuarioInactivar = usuarioBean.buscarUsuarioXLogin(loginInactivar);

		} catch (Exception e) {

			estadoPanelActivarInactivar = false;

			// Error en caso tal que la consulta no arroje datos
			Utilitario
					.errorDesdeCodigo(ConstantesGenerales.MENSAJE_ERROR_BUSCAR_USUARIO_EDITAR);

			return;

		}
	}

	/**
	 * Metodo que permite inicializar los campos cuando se vaya a acceder a la
	 * pagina
	 * 
	 * @return url a redirigir
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String iniciarPaginaCambiarEstado() {

		estadoPanelActivarInactivar = false;
		loginInactivar = ConstantesGenerales.CADENA_VACIA;

		return "/Usuario/cambiar_estado_usuario.html?faces-redirect=true";

	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Metodo que permite mostrar los datos del usuario para activarlo o
	 *         inactivarlo
	 */
	public void mostrarDatosParaCambiarEstado() {
		// Busco el usuario por login

		usuarioInactivar = null;

		buscarUsuarioPorLoginInactivar();

		if (usuarioInactivar == null) {
			estadoPanelActivarInactivar = false;
			return;
		} else {

			estadoPanelActivarInactivar = true;

			nombreIntegradoInactivar = usuarioInactivar.getNombreIntegrado();

			// Si el estado es activo quiere decir que se inactivara
			if (usuarioInactivar.getEstado().equals(
					ConstantesGenerales.ESTADO_ACTIVO_USUARIO)) {

				botonInactivar = true;

			}
			// Si el estado es inactivo quiere decir que se activara
			else {
				botonActivar = true;
			}

		}

	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Metodo que permite mostrar los datos del usuario cuando se
	 *         realice la busqueda por login
	 */
	public void mostrarDatosUsuarioEditar() {

		usuarioEditar = null;

		// Busco el usuario por login
		buscarUsuarioPorLogin();

		if (usuarioEditar == null) {

			System.out.println("*************+ -_______________");
			visibleDatosUsuario = false;
			cedulaEditar = ConstantesGenerales.CADENA_VACIA;
			nombreEditar = ConstantesGenerales.CADENA_VACIA;
			primerApellidoEditar = ConstantesGenerales.CADENA_VACIA;
			segundoApellidoEditar = ConstantesGenerales.CADENA_VACIA;
			contrasenaEditar = ConstantesGenerales.CADENA_VACIA;
			emailEditar = ConstantesGenerales.CADENA_VACIA;
			estadoEditar = ConstantesGenerales.CADENA_VACIA;
			nombreIntegradoEditar = ConstantesGenerales.CADENA_VACIA;
			loginEditarNuevo = ConstantesGenerales.CADENA_VACIA;

			return;
		}

		visibleDatosUsuario = true;

		loginEditar = usuarioEditar.getLogin();

		nombreEditar = usuarioEditar.getNombre();

		primerApellidoEditar = usuarioEditar.getPrimerApellido();

		segundoApellidoEditar = usuarioEditar.getSegundoApellido();

		cedulaEditar = usuarioEditar.getCedula();

		administradorEditar = usuarioEditar.isAdministrador();

		emailEditar = usuarioEditar.getEmail();

		loginEditar = usuarioEditar.getLogin();

		contrasenaEditar = usuarioEditar.getContrasena();

		estadoEditar = usuarioEditar.getEstado();

		if (usuarioEditar.getUsuarioHookah().equals("SI")) {

			usuarioHookahEditar = true;
		} else {
			usuarioHookahEditar = false;
		}

		nombreIntegradoEditar = usuarioEditar.getNombreIntegrado();

	}

	/**
	 * Metodo que permite buscar un usuario por login para luego editar su
	 * informacion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void buscarUsuarioPorLogin() {
		try {

			usuarioEditar = usuarioBean.buscarUsuarioXLogin(loginEditar);

		} catch (Exception e) {
			// Error en caso tal que la consulta no arroje datos
			Utilitario
					.errorDesdeCodigo(ConstantesGenerales.MENSAJE_ERROR_BUSCAR_USUARIO_EDITAR);

			return;

		}
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Metodo que permite editar la informacion del usuario cuando este
	 *         haya sido encontrado por la busqueda
	 * @throws Exception
	 * 
	 */
	public void editarUsuario() throws Exception {

		Usuario validarCedula = usuarioBean.buscarUsuarioXCedula(cedulaEditar);

		String cedulaEditar1 = cedulaEditar;

		if (validarCedula != null) {
			String cedulaEditar2 = validarCedula.getCedula();

			if (cedulaEditar1.compareTo(cedulaEditar2) != 0) {

				Utilitario
						.errorDesdeCodigo("Ya existe un usuario con esa cedula en el sistema");

				return;
			}

		}

		// Asignamos los nuevos valores al usuario en los campos
		usuarioEditar.setCedula(cedulaEditar);
		usuarioEditar.setNombre(nombreEditar);
		usuarioEditar.setPrimerApellido(primerApellidoEditar);
		usuarioEditar.setSegundoApellido(segundoApellidoEditar);
		usuarioEditar.setLogin(loginEditar);
		usuarioEditar.setContrasena(contrasenaEditar);
		usuarioEditar.setAdministrador(administradorEditar);
		usuarioEditar.setEmail(emailEditar);
		usuarioEditar.setEstado(estadoEditar);
		usuarioEditar.setNombreIntegrado(nombreEditar
				+ ConstantesGenerales.ESPACIO_BLANCO + primerApellidoEditar
				+ ConstantesGenerales.ESPACIO_BLANCO + segundoApellidoEditar);

		if (usuarioHookahEditar == true) {
			usuarioEditar.setUsuarioHookah("SI");
		} else {
			usuarioEditar.setUsuarioHookah("NO");
		}

		// Editamos el usuario
		usuarioBean.editarUsuario(usuarioEditar);

		// mensaje de exito
		Utilitario
				.exitoDesdeCodigo(ConstantesGenerales.MENSAJE_EXITO_USUARIO_EDITAR);

		// reiniciamos los campos para volver a crear un usuario nuevo
		limpiarCamposEditar();

	}

	/**
	 * Metodo que inicializa los campos del editar usuario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String limpiarCamposEditar() {
		visibleDatosUsuario = false;
		cedulaEditar = ConstantesGenerales.CADENA_VACIA;
		nombreEditar = ConstantesGenerales.CADENA_VACIA;
		primerApellidoEditar = ConstantesGenerales.CADENA_VACIA;
		segundoApellidoEditar = ConstantesGenerales.CADENA_VACIA;
		loginEditar = ConstantesGenerales.CADENA_VACIA;
		contrasenaEditar = ConstantesGenerales.CADENA_VACIA;
		emailEditar = ConstantesGenerales.CADENA_VACIA;
		estadoEditar = ConstantesGenerales.CADENA_VACIA;
		nombreIntegradoEditar = ConstantesGenerales.CADENA_VACIA;
		loginEditarNuevo = ConstantesGenerales.CADENA_VACIA;
		usuarioHookahEditar = false;

		return "/Usuario/editar_usuario.xhtml";
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return cedula
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param cedula
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombre
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombre
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return primerApellido
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param primerApellido
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return segundoApellido
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param segundoApellido
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return login
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param login
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return contrasena
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param contrasena
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return administrador
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isAdministrador() {
		return administrador;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param administrador
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return email
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param email
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreIntegrado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreIntegrado() {
		return nombreIntegrado;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreIntegrado
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreIntegrado(String nombreIntegrado) {
		this.nombreIntegrado = nombreIntegrado;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioBean
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioBean
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Usuario getUsuarioInactivar() {
		return usuarioInactivar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioInactivar(Usuario usuarioInactivar) {
		this.usuarioInactivar = usuarioInactivar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return loginInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getLoginInactivar() {
		return loginInactivar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param loginInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setLoginInactivar(String loginInactivar) {
		this.loginInactivar = loginInactivar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreIntegradoInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreIntegradoInactivar() {
		return nombreIntegradoInactivar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreIntegradoInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreIntegradoInactivar(String nombreIntegradoInactivar) {
		this.nombreIntegradoInactivar = nombreIntegradoInactivar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estadoPanelActivarInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isEstadoPanelActivarInactivar() {
		return estadoPanelActivarInactivar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estadoPanelActivarInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstadoPanelActivarInactivar(
			boolean estadoPanelActivarInactivar) {
		this.estadoPanelActivarInactivar = estadoPanelActivarInactivar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return botonActivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isBotonActivar() {
		return botonActivar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param botonActivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setBotonActivar(boolean botonActivar) {
		this.botonActivar = botonActivar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return botonInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isBotonInactivar() {
		return botonInactivar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param botonInactivar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setBotonInactivar(boolean botonInactivar) {
		this.botonInactivar = botonInactivar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return cedulaEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getCedulaEditar() {
		return cedulaEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param cedulaEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCedulaEditar(String cedulaEditar) {
		this.cedulaEditar = cedulaEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreEditar() {
		return nombreEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreEditar(String nombreEditar) {
		this.nombreEditar = nombreEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return primerApellidoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getPrimerApellidoEditar() {
		return primerApellidoEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param primerApellidoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setPrimerApellidoEditar(String primerApellidoEditar) {
		this.primerApellidoEditar = primerApellidoEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return segundoApellidoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getSegundoApellidoEditar() {
		return segundoApellidoEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param segundoApellidoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setSegundoApellidoEditar(String segundoApellidoEditar) {
		this.segundoApellidoEditar = segundoApellidoEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return loginEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getLoginEditar() {
		return loginEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param loginEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setLoginEditar(String loginEditar) {
		this.loginEditar = loginEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return contrasenaEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getContrasenaEditar() {
		return contrasenaEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param contrasenaEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setContrasenaEditar(String contrasenaEditar) {
		this.contrasenaEditar = contrasenaEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return administradorEitar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isAdministradorEditar() {
		return administradorEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param administradorEitar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setAdministradorEditar(boolean administradorEditar) {
		this.administradorEditar = administradorEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return emailEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getEmailEditar() {
		return emailEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param emailEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEmailEditar(String emailEditar) {
		this.emailEditar = emailEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estadoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getEstadoEditar() {
		return estadoEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estadoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstadoEditar(String estadoEditar) {
		this.estadoEditar = estadoEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreIntegradoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreIntegradoEditar() {
		return nombreIntegradoEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreIntegradoEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreIntegradoEditar(String nombreIntegradoEditar) {
		this.nombreIntegradoEditar = nombreIntegradoEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Usuario getUsuarioEditar() {
		return usuarioEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioEditar(Usuario usuarioEditar) {
		this.usuarioEditar = usuarioEditar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return visibleDatosUsuario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isVisibleDatosUsuario() {
		return visibleDatosUsuario;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param visibleDatosUsuario
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setVisibleDatosUsuario(boolean visibleDatosUsuario) {
		this.visibleDatosUsuario = visibleDatosUsuario;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return loginEditarNuevo
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public String getLoginEditarNuevo() {
		return loginEditarNuevo;
	}

	/**
	 * Asigna el valor al parametro
	 * 
	 * @param loginEditarNuevo
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setLoginEditarNuevo(String loginEditarNuevo) {
		this.loginEditarNuevo = loginEditarNuevo;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaUsuarios
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaUsuarios
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioHookah
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isUsuarioHookah() {
		return usuarioHookah;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioHookah
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioHookah(boolean usuarioHookah) {
		this.usuarioHookah = usuarioHookah;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioHookahEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isUsuarioHookahEditar() {
		return usuarioHookahEditar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioHookahEditar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioHookahEditar(boolean usuarioHookahEditar) {
		this.usuarioHookahEditar = usuarioHookahEditar;
	}

}
