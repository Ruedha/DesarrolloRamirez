package co.com.inventario.action;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.UsuarioBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Usuario;

/**
 * @author Deivid Arias Ramirez
 * 
 *         Clase que controla la sesion de la aplicacion
 * 
 */
@Named(value = "sesionAction")
@SessionScoped
public class SesionAction implements Serializable {

	/**
	 * identificacion de la serializacion de la clase
	 */
	private static final long serialVersionUID = 1L;

	// login con el que el usuario inicia sesion
	private String login;

	// Password con el que el usuario inicia sesion
	private String password;

	// indica el usuario que queda en sesion en la aplicacion
	@Inject
	private Usuario usuarioSesion;

	// indicar el campo de login a recuperar el password
	private String loginRecuperar;

	// es el campo de login en el formulario de cambiar Password
	private String loginCambiar;

	// es el campo que corresponde al password que se envio por correo
	// electornico
	private String passwordEnviado;

	// password nuevo asignado por el usuario
	private String passwordNuevo;

	// confirmacion del nuevo password
	private String confirmarPassword;

	// indica el primer password que el usuario asignara
	private String passwordNuevoPrimeraVez;

	// indica la confimacion del nuevo password
	private String confirmarPasswordNuevoPrimeraVez;

	// bandera para controlar la visibilidad del menu de gestion documental
	private boolean menuUsuarios;

	// bandera para controlar la visibilidad del menu de configuraciones
	private boolean menuConfiguraciones;

	private boolean menuAdministrador;

	private boolean menuNoAdministrador;

	/**
	 * Permite acceder al bean del usuario alojado en el contexto del servidor
	 */
	@Inject
	private UsuarioBean usuarioBean;

	/**
	 * Constructor por defecto del Action
	 */
	public SesionAction() {

	}

	/**
	 * Metodo que permite inicializar las banderas para controlarlas dependiendo
	 * de las areas que tenga asignadas el usuario
	 */
	public void validarOpcionesDelMenu(Usuario usuarioSesion) {

		menuUsuarios = false;
		menuAdministrador = false;

		if (usuarioSesion.isAdministrador() == true) {
			menuUsuarios = true;
			menuAdministrador = true;

		} else {
			// Ojooooo! quitar esto cuando se implemente la seguridad verdadera
			// de los administradores
			menuUsuarios = true;
			menuAdministrador = true;
		}

	}

	/**
	 * Metodo que permite iniciar sesion en el sistema
	 * 
	 * @return pagina a redirigir
	 */
	public String iniciarSesion() {

		try {
			usuarioSesion = usuarioBean.buscarUsuarioXloginYContrasena(login,
					password);

			if (usuarioSesion != null) {

				// si el login y la contrasena son iguales entonces debe
				// cambiarla
				if (usuarioSesion.getContrasena().equals(
						usuarioSesion.getLogin())) {

					return "/Sesion/primer_inicio_sesion.xthml";

				}

				// si el usuario esta activo en el sistema iniciara sesion
				// correctamente.
				if (usuarioSesion.getEstado().equals(
						ConstantesGenerales.ESTADO_ACTIVO_USUARIO)) {

					return ConstantesGenerales.URL_INICIO;

				} else {

					Utilitario.error(ConstantesGenerales.ERROR_LOGIN_INACTIVO);

				}

			}

		} catch (Exception e) {

			Utilitario.error(ConstantesGenerales.ERROR_LOGIN_PASSWORD);
		}

		return ConstantesGenerales.CADENA_VACIA;
	}

	/**
	 * Metodo que permite cambiar el password cuando el usuario va iniciar
	 * sesion por primera vez
	 * 
	 * @return devuelve la redireccion a la pagina deseada
	 */
	public String cambiarPasswordPrimeraVez() {

		if (passwordNuevoPrimeraVez.equals(confirmarPasswordNuevoPrimeraVez)) {
			usuarioSesion.setContrasena(passwordNuevoPrimeraVez);
			usuarioBean.editarUsuario(usuarioSesion);

			Utilitario.exito(ConstantesGenerales.MENSAJE_SESION_INICIADA);

			return ConstantesGenerales.URL_INICIO;

		} else {

			Utilitario.error(ConstantesGenerales.ERROR_CAMBIO_PASSWORD);

			return ConstantesGenerales.CADENA_VACIA;
		}
	}

	/**
	 * Metodo que permite cerrar la sesion del sistema
	 * 
	 * @return pagina url a la cual se redirecciona cuando se cierre sesion
	 * 
	 */
	public String logout() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);

		if (session != null) {

			session.invalidate(); // Cierre de sesion
		}
		return ConstantesGenerales.URL_INICIAR_SESION;

	}

	public void iniciarRecuperarPassword() {

		loginRecuperar = ConstantesGenerales.CADENA_VACIA;
	}

	/**
	 * Metodo que permite redirigir hacia el formulario de iniciar sesion
	 * 
	 * @return pagina a redireccionar
	 */
	public String redirigir() {

		login = ConstantesGenerales.CADENA_VACIA;
		password = ConstantesGenerales.CADENA_VACIA;

		return ConstantesGenerales.URL_INICIAR_SESION;
	}

	/**
	 * Metodo que permite inicializar los campos del formulario de cambiar el
	 * password
	 */
	public void iniciarCambiarPassword() {

		loginCambiar = ConstantesGenerales.CADENA_VACIA;
		passwordEnviado = ConstantesGenerales.CADENA_VACIA;
		passwordNuevo = ConstantesGenerales.CADENA_VACIA;
		confirmarPassword = ConstantesGenerales.CADENA_VACIA;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return usuarioSesion
	 */
	public Usuario getUsuarioSesion() {
		return usuarioSesion;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param usuarioSesion
	 */
	public void setUsuarioSesion(Usuario usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return loginRecuperar
	 */
	public String getLoginRecuperar() {
		return loginRecuperar;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param loginRecuperar
	 */
	public void setLoginRecuperar(String loginRecuperar) {
		this.loginRecuperar = loginRecuperar;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return loginCambiar
	 */
	public String getLoginCambiar() {
		return loginCambiar;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param loginCambiar
	 */
	public void setLoginCambiar(String loginCambiar) {
		this.loginCambiar = loginCambiar;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return passwordEnviado
	 */
	public String getPasswordEnviado() {
		return passwordEnviado;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param passwordEnviado
	 */
	public void setPasswordEnviado(String passwordEnviado) {
		this.passwordEnviado = passwordEnviado;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return passwordNuevo
	 */
	public String getPasswordNuevo() {
		return passwordNuevo;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param passwordNuevo
	 */
	public void setPasswordNuevo(String passwordNuevo) {
		this.passwordNuevo = passwordNuevo;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return confirmarPassword
	 */
	public String getConfirmarPassword() {
		return confirmarPassword;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param confirmarPassword
	 */
	public void setConfirmarPassword(String confirmarPassword) {
		this.confirmarPassword = confirmarPassword;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return passwordNuevoPrimeraVez
	 */
	public String getPasswordNuevoPrimeraVez() {
		return passwordNuevoPrimeraVez;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param passwordNuevoPrimeraVez
	 */
	public void setPasswordNuevoPrimeraVez(String passwordNuevoPrimeraVez) {
		this.passwordNuevoPrimeraVez = passwordNuevoPrimeraVez;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return confirmarPasswordNuevoPrimeraVez
	 */
	public String getConfirmarPasswordNuevoPrimeraVez() {
		return confirmarPasswordNuevoPrimeraVez;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param confirmarPasswordNuevoPrimeraVez
	 */
	public void setConfirmarPasswordNuevoPrimeraVez(
			String confirmarPasswordNuevoPrimeraVez) {
		this.confirmarPasswordNuevoPrimeraVez = confirmarPasswordNuevoPrimeraVez;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return menuGestionDocumental
	 */
	public boolean isMenuGestionDocumental() {
		return menuUsuarios;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param menuGestionDocumental
	 */
	public void setMenuGestionDocumental(boolean menuGestionDocumental) {
		this.menuUsuarios = menuGestionDocumental;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return menuConfiguraciones
	 */
	public boolean isMenuConfiguraciones() {
		return menuConfiguraciones;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param menuConfiguraciones
	 */
	public void setMenuConfiguraciones(boolean menuConfiguraciones) {
		this.menuConfiguraciones = menuConfiguraciones;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return menuUsuarios
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isMenuUsuarios() {
		return menuUsuarios;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param menuUsuarios
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setMenuUsuarios(boolean menuUsuarios) {
		this.menuUsuarios = menuUsuarios;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return menuAdministrador
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isMenuAdministrador() {
		return menuAdministrador;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param menuAdministrador
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setMenuAdministrador(boolean menuAdministrador) {
		this.menuAdministrador = menuAdministrador;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return menuNoAdministrador
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isMenuNoAdministrador() {
		return menuNoAdministrador;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param menuNoAdministrador
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setMenuNoAdministrador(boolean menuNoAdministrador) {
		this.menuNoAdministrador = menuNoAdministrador;
	}

	
}
