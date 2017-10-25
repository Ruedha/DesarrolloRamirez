package co.com.inventario.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.ProveedorBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Proveedor;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a los proveedores del sistema.
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "proveedorAction")
@SessionScoped
public class ProveedorAction implements Serializable {

	/**
	 * Serializacion del pojo
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	private String numeroContacto;

	private String nombreContacto;

	private String direccion;

	// tipo documento a buscar en el sistema
	private Proveedor proveedor;

	// listado de los tipos de documentos
	private List<Proveedor> listaProveedores;

	// indica si el tipo se guardara o se editara
	private boolean estadoProveedor;

	/**
	 * Recurso inyectado para acceder al bean de tipo de documento
	 */
	@Inject
	private ProveedorBean proveedorBean;

	public ProveedorAction() {

	}

	/**
	 * Metodo que permite resetear los campos del formulario
	 */
	public void limpiarCampos() {

		nombre = ConstantesGenerales.CADENA_VACIA;
		numeroContacto = ConstantesGenerales.CADENA_VACIA;
		nombreContacto = ConstantesGenerales.CADENA_VACIA;
		direccion = ConstantesGenerales.CADENA_VACIA;

		listaProveedores = proveedorBean.listarProveedores();
	}

	/**
	 * Metodo que permite almacenar un proveedor en la base de datos con los
	 * campos ingresados desde la pagina
	 * 
	 * @author Deivid Arias Ramirez
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void registrarProveedor() throws UnsupportedEncodingException,
			FileNotFoundException {

		// validar que el campo codigo llegue con algun valor
		if (nombre.equals(ConstantesGenerales.CADENA_VACIA) || nombre == null) {
			Utilitario.error("El campo nombre del proveedor es obligatorio");

			return;
		}

		// Si el estado es verdadero quiere decir que se va a modificar un tipo
		// de documento
		if (estadoProveedor == true) {

			Proveedor provee = proveedorBean.buscarProveedor(proveedor.getId());

			provee.setNombre(nombre);
			provee.setNumeroContacto(numeroContacto);
			provee.setNombreContacto(nombreContacto);
			provee.setDireccion(direccion);

			proveedorBean.editarProveedor(provee);

			Utilitario.exito("Proveedor Actualizado!");

			limpiarCampos();

			estadoProveedor = false;

		} else {

			Proveedor proveedorNuevo = new Proveedor();
			proveedorNuevo.setNombre(nombre);
			proveedorNuevo.setNumeroContacto(numeroContacto);
			proveedorNuevo.setNombreContacto(nombreContacto);
			proveedorNuevo.setDireccion(direccion);

			proveedorBean.registrarProveedor(proveedorNuevo);

			String idProveedor = proveedorNuevo.getId().toString();

//			File directorio = new File(System.getenv("JBOSS_HOME")
//					+ "\\Proveedor\\" + idProveedor);
			
			File directorio = new File(System.getenv("JBOSS_HOME")
					+ "\\standalone\\deployments\\proveedor.war\\Proveedor\\" + idProveedor);
			
			
			directorio.mkdirs();

			limpiarCampos();

			// mensaje exitoso del proveedor registrado
			Utilitario.exito("Proveedor Registrado!");

		}

	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Metodo que permite seleccionar el proveedor de la tabla el cual
	 *         sera editadao
	 * @param proveedor
	 *            , el cual sera el seleccionado
	 */
	public void proveedorSeleccionado(Proveedor proveedorSeleccionado) {

		// El estado pasa a ser verdadero para identificar que se va a modificar
		// un area
		estadoProveedor = true;

		nombre = proveedorSeleccionado.getNombre();
		numeroContacto = proveedorSeleccionado.getNumeroContacto();
		nombreContacto = proveedorSeleccionado.getNombreContacto();
		direccion = proveedorSeleccionado.getDireccion();

		// area seleccionada es igual a la que se va a modificar
		proveedor = proveedorSeleccionado;
	}

	/**
	 * Metodo que permite redirigir hacia la administracion de los proveedores
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 * @return retorna la pagina a la cual se va a redirigir
	 */
	public String redirigir() {

		// Cada vez que se ingrese a esta url se limpian todos los campos
		limpiarCampos();

		return "/Proveedor/administrar_proveedores.xhtml";
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
	 * @return proveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param proveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaProveedores
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaProveedores
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estadoProveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isEstadoProveedor() {
		return estadoProveedor;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estadoProveedor
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstadoProveedor(boolean estadoProveedor) {
		this.estadoProveedor = estadoProveedor;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return proveedorBean
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public ProveedorBean getProveedorBean() {
		return proveedorBean;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param proveedorBean
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setProveedorBean(ProveedorBean proveedorBean) {
		this.proveedorBean = proveedorBean;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return numeroContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNumeroContacto() {
		return numeroContacto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param numeroContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombreContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombreContacto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return direccion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param direccion
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
