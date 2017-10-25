package co.com.inventario.action;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.BodegaBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Bodega;

@Named(value = "bodegaAction")
@SessionScoped
public class BodegaAction implements Serializable {

	/**
	 * Serializacion del pojo
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	// tipo documento a buscar en el sistema
	private Bodega bodega;

	// listado de los tipos de documentos
	private List<Bodega> listaBodegaes;

	// indica si el tipo se guardara o se editara
	private boolean estadoBodega;

	/**
	 * Recurso inyectado para acceder al bean de tipo de documento
	 */
	@Inject
	private BodegaBean bodegaBean;

	public BodegaAction() {

	}

	/**
	 * Metodo que permite resetear los campos del formulario
	 */
	public void limpiarCampos() {

		nombre = ConstantesGenerales.CADENA_VACIA;

		listaBodegaes = bodegaBean.listarBodegas();
	}

	/**
	 * Metodo que permite almacenar una Bodega en la base de datos con los
	 * campos ingresados desde la pagina
	 * 
	 * @author Deivid Arias Ramirez
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void registrarBodega() throws UnsupportedEncodingException,
			FileNotFoundException {

		// validar que el campo codigo llegue con algun valor
		if (nombre.equals(ConstantesGenerales.CADENA_VACIA) || nombre == null) {
			Utilitario.error("El campo nombre de la Bodega es obligatorio");

			return;
		}

		// Si el estado es verdadero quiere decir que se va a modificar un tipo
		// de documento
		if (estadoBodega == true) {

			Bodega bod = bodegaBean.buscarBodega(bodega.getId());

			bod.setNombre(nombre);

			bodegaBean.editarBodega(bod);

			Utilitario.exito("Bodega Actualizada!");

			limpiarCampos();

			estadoBodega = false;

		} else {

			Bodega BodegaNuevo = new Bodega();
			BodegaNuevo.setNombre(nombre);
			bodegaBean.registrarBodega(BodegaNuevo);

			limpiarCampos();

			// mensaje exitoso del Bodega registrado
			Utilitario.exito("Bodega Registrado!");

		}

	}

	/**
	 * @author Deivid Arias Ramirez
	 * 
	 *         Metodo que permite seleccionar la bodega de la tabla el cual sera
	 *         editadao
	 * @param bodega
	 *            , el cual sera el seleccionado
	 */
	public void bodegaSeleccionado(Bodega bodegaSeleccionado) {

		// El estado pasa a ser verdadero para identificar que se va a modificar
		// una bodega
		estadoBodega = true;

		nombre = bodegaSeleccionado.getNombre();

		// bodega seleccionada es igual a la que se va a modificar
		bodega = bodegaSeleccionado;
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

		return "/Bodega/administrar_bodegas.xhtml";
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
	 * @return bodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Bodega getBodega() {
		return bodega;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param bodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return listaBodegaes
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public List<Bodega> getListaBodegaes() {
		return listaBodegaes;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param listaBodegaes
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setListaBodegaes(List<Bodega> listaBodegaes) {
		this.listaBodegaes = listaBodegaes;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return estadoBodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public boolean isEstadoBodega() {
		return estadoBodega;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param estadoBodega
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setEstadoBodega(boolean estadoBodega) {
		this.estadoBodega = estadoBodega;
	}

}
