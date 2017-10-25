package co.com.vuelos.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que permite generar la tabla usuario en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;

	// Identificacion unica de la entidad
	private Long id;

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

	private List<Entrada> listaEntradas;

	private List<Salida> listaSalidas;

	private String usuarioHookah;

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Id
	public Long getId() {
		return id;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return listaEntradas
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@OneToMany(mappedBy = "usuario")
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
	@OneToMany(mappedBy = "usuario")
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (administrador != other.administrador)
			return false;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;

		if (contrasena == null) {
			if (other.contrasena != null)
				return false;
		} else if (!contrasena.equals(other.contrasena))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreIntegrado == null) {
			if (other.nombreIntegrado != null)
				return false;
		} else if (!nombreIntegrado.equals(other.nombreIntegrado))
			return false;
		if (primerApellido == null) {
			if (other.primerApellido != null)
				return false;
		} else if (!primerApellido.equals(other.primerApellido))
			return false;
		if (segundoApellido == null) {
			if (other.segundoApellido != null)
				return false;
		} else if (!segundoApellido.equals(other.segundoApellido))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return id + "";
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return usuarioHookah
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getUsuarioHookah() {
		return usuarioHookah;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param usuarioHookah
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setUsuarioHookah(String usuarioHookah) {
		this.usuarioHookah = usuarioHookah;
	}

}
