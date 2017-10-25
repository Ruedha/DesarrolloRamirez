package co.com.inventario.action;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.TipoProductoBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.TipoProducto;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a los tipos de productos del sistema.
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "tipoProductoAction")
@SessionScoped
public class TipoProductoAction implements Serializable {

	/**
	 * Serializacion del pojo
	 */
	private static final long serialVersionUID = 1L;

	// campo descripcion del formulario de tipo de documento
	private String descripcion;

	private String codigo;

	// tipo documento a buscar en el sistema
	private TipoProducto tipoProducto;

	// listado de los tipos de documentos
	private List<TipoProducto> listatipoProductos;

	// indica si el tipo se guardara o se editara
	private boolean estadotipoProducto;

	/**
	 * Recurso inyectado para acceder al bean de tipo de documento
	 */
	@Inject
	private TipoProductoBean tipoProductoBean;

	public TipoProductoAction() {

	}

	/**
	 * Metodo que permite resetear los campos del formulario
	 */
	public void limpiarCampos() {
		descripcion = ConstantesGenerales.CADENA_VACIA;

		codigo = ConstantesGenerales.CADENA_VACIA;

		listatipoProductos = tipoProductoBean.listartipoProducto();
	}

	/**
	 * Metodo que permite almacenar un area en la base de datos con los campos
	 * ingresados desde la pagina
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void registrartipoProducto() {

		// validar que el campo codigo llegue con algun valor
		if (descripcion.equals(ConstantesGenerales.CADENA_VACIA)
				|| descripcion == null) {
			Utilitario
					.error(ConstantesGenerales.MENSAJE_ERROR_TIPO_DOC_DESCRIPCION);

			return;
		}

		// validar que el campo codigo llegue con algun valor
		if (codigo.equals(ConstantesGenerales.CADENA_VACIA) || codigo == null) {
			Utilitario.error("El campo codigo es obligatorio");

			return;
		}

		// Si el estado es verdadero quiere decir que se va a modificar un tipo
		// de documento
		if (estadotipoProducto == true) {

			TipoProducto tipoDoc = tipoProductoBean
					.buscarTipoProducto(tipoProducto.getId());

			tipoDoc.setDescripcion(descripcion);
			tipoDoc.setCodigo(codigo);
			tipoProductoBean.editarTipoProducto(tipoDoc);

			Utilitario.exito("Marca Actualizada!");

			limpiarCampos();

			estadotipoProducto = false;

		} else {

			TipoProducto tipoProductoNuevo = new TipoProducto();
			tipoProductoNuevo.setDescripcion(descripcion);
			tipoProductoNuevo.setCodigo(codigo);

			tipoProductoBean.registrarTipoProducto(tipoProductoNuevo);

			limpiarCampos();

			// mensaje exitoso del area registrada
			Utilitario.exito("Marca Registrada!");

		}

	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Metodo que permite seleccionar el tipo de documento de la tabla
	 *         el cual sera editadao
	 * @param tipoProducto
	 *            , el cual sera el seleccionado
	 */
	public void tipoProductoSeleccionado(TipoProducto tipoProductoSeleccionado) {

		// El estado pasa a ser verdadero para identificar que se va a modificar
		// un area
		estadotipoProducto = true;

		descripcion = tipoProductoSeleccionado.getDescripcion();
		codigo = tipoProductoSeleccionado.getCodigo();

		// area seleccionada es igual a la que se va a modificar
		tipoProducto = tipoProductoSeleccionado;
	}

	/**
	 * Metodo que permite redirigir hacia la administracion de los tipos de
	 * documentos
	 * 
	 * @author Deivid Arias Ramirez
	 * 
	 * @return retorna la pagina a la cual se va a redirigir
	 */
	public String redirigir() {

		// Cada vez que se ingrese a esta url se limpian todos los campos
		limpiarCampos();

		return "/TipoProducto/administrar_tipos.xhtml";
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return tipoProducto
	 */
	public TipoProducto gettipoProducto() {
		return tipoProducto;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param tipoProducto
	 */
	public void settipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return listatipoProductos
	 */
	public List<TipoProducto> getListatipoProductos() {
		return listatipoProductos;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param listatipoProductos
	 */
	public void setListatipoProductos(List<TipoProducto> listatipoProductos) {
		this.listatipoProductos = listatipoProductos;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Devuelve el valor del parametro
	 * 
	 * @return estadotipoProducto
	 */
	public boolean isEstadotipoProducto() {
		return estadotipoProducto;
	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Asigna valor al parametro
	 * 
	 * @param estadotipoProducto
	 */
	public void setEstadotipoProducto(boolean estadotipoProducto) {
		this.estadotipoProducto = estadotipoProducto;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return codigo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param codigo
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return tipoProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param tipoProducto
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

}
