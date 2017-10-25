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
import co.com.inventario.ejb.EmpleadoBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Empleado;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a los proveedores del sistema.
 * 
 * @author Santiago Rueda Ramirez
 * 
 */
@Named(value = "empleadoAction")
@SessionScoped
public class EmpleadoAction implements Serializable{

	/**
	 * Serializacion del pojo
	 */
	private static final long serialVersionUID = 1L;

	private String nombreCompleto;

	private String numeroContacto;

	private String direccion;

	// tipo documento a buscar en el sistema
	private Empleado empleado;

	// listado de los tipos de documentos
	private List<Empleado> listaEmpleados;

	// indica si el tipo se guardara o se editara
	private boolean estadoEmpleado;

	/**
	 * Recurso inyectado para acceder al bean de tipo de documento
	 */
	@Inject
	private EmpleadoBean empleadoBean;

	public EmpleadoAction() {

	}

	/**
	 * Metodo que permite resetear los campos del formulario
	 */
	public void limpiarCampos() {

		nombreCompleto = ConstantesGenerales.CADENA_VACIA;
		numeroContacto = ConstantesGenerales.CADENA_VACIA;
		direccion = ConstantesGenerales.CADENA_VACIA;

		listaEmpleados = empleadoBean.listarEmpleados();
	}

	/**
	 * Metodo que permite almacenar un empleado en la base de datos con los
	 * campos ingresados desde la pagina
	 * 
	 * @author Santiago Rueda Ramirez
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void registrarEmpleado() throws UnsupportedEncodingException,
			FileNotFoundException {

		// validar que el campo codigo llegue con algun valor
		if (nombreCompleto.equals(ConstantesGenerales.CADENA_VACIA) || nombreCompleto == null) {
			Utilitario.error("El campo nombre del empleado es obligatorio");

			return;
		}

		// Si el estado es verdadero quiere decir que se va a modificar un tipo
		// de documento
		if (estadoEmpleado == true) {

			Empleado emple = empleadoBean.buscarEmpleado(empleado.getId());

			emple.setNombreCompleto(nombreCompleto);
			emple.setNumeroContacto(numeroContacto);
			emple.setDireccion(direccion);

			empleadoBean.editarEmpleado(emple);

			Utilitario.exito("Empleado Actualizado!");

			limpiarCampos();

			estadoEmpleado = false;

		} else {

			Empleado empleadoNuevo = new Empleado();
			empleadoNuevo.setNombreCompleto(nombreCompleto);
			empleadoNuevo.setNumeroContacto(numeroContacto);
			empleadoNuevo.setDireccion(direccion);

			empleadoBean.registrarEmpleado(empleadoNuevo);

			String idEmpleado = empleadoNuevo.getId().toString();

			
			File directorio = new File(System.getenv("JBOSS_HOME")
					+ "\\standalone\\deployments\\empleado.war\\Empleado\\" + idEmpleado);
			
			
			directorio.mkdirs();

			limpiarCampos();

			// mensaje exitoso del empleado registrado
			Utilitario.exito("Empleado Registrado!");

		}

	}

	/**
	 * @author Santiago Rueda Ramirez
	 * 
	 *         Metodo que permite seleccionar el empleado de la tabla el cual
	 *         sera editadao
	 * @param empleado
	 *            , el cual sera el seleccionado
	 */
	public void empleadoSeleccionado(Empleado empleadoSeleccionado) {

		// El estado pasa a ser verdadero para identificar que se va a modificar
		// un area
		estadoEmpleado = true;

		nombreCompleto = empleadoSeleccionado.getNombreCompleto();
		numeroContacto = empleadoSeleccionado.getNumeroContacto();
		direccion = empleadoSeleccionado.getDireccion();

		// area seleccionada es igual a la que se va a modificar
		empleado = empleadoSeleccionado;
	}

	/**
	 * Metodo que permite redirigir hacia la administracion de los empleados
	 * 
	 * @author Santiago Rueda Ramirez
	 * 
	 * @return retorna la pagina a la cual se va a redirigir
	 */
	public String redirigir() {

		// Cada vez que se ingrese a esta url se limpian todos los campos
		limpiarCampos();

		return "/Empleado/administrar_empleados.xhtml";
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return nombre
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param nombre
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return empleado
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param proveedor
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setProveedor(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaEmpleados
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaEmpleados
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estadoEmpleado
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public boolean isEstadoEmpleado() {
		return estadoEmpleado;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estadoEmpleado
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setEstadoEmpleado(boolean estadoEmpleado) {
		this.estadoEmpleado = estadoEmpleado;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return empleadoBean
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public EmpleadoBean getEmpleadoBean() {
		return empleadoBean;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param empleadoBean
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setEmpleadoBean(EmpleadoBean empleadoBean) {
		this.empleadoBean = empleadoBean;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return numeroContacto
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public String getNumeroContacto() {
		return numeroContacto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param numeroContacto
	 * 
	 * @author Santiago Rueda Ramirez
	 */
	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
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
