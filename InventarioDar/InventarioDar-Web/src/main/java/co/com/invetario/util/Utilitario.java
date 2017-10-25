package co.com.invetario.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que contiene las utilidades generales para las paginas del sistema
 * 
 * @author Deivid ARias Ramirez
 * 
 */
public class Utilitario {

	/**
	 * Metodo que indica un mensaje de error en pantalla
	 * 
	 * @param mensaje
	 *            de error a mostrar en pantalla
	 */
	public static void error(String mensaje) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
								mensaje));
	}

	/**
	 * Metodo que indica un mensaje de error en pantalla cuando se muestra desde
	 * el pojo
	 * 
	 * @param mensaje
	 *            de error a mostrar en pantalla
	 */
	public static void errorDesdeCodigo(String mensaje) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje,
								mensaje));
	}

	/**
	 * Metodo que permite mostrar un mensaje en pantalla indicando que la
	 * operacion se realizo correctamente
	 */
	public static void exito(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", mensaje));
	}

	/**
	 * Metodo que permite mostrar un mensaje en pantalla indicando que la
	 * operacion se realizo correctamente cuando se muestra desde el pojo
	 */
	public static void exitoDesdeCodigo(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
	}
}
