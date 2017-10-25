package co.com.inventario.action;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.inventario.constantes.ConstantesGenerales;
import co.com.inventario.ejb.BartenderBean;
import co.com.invetario.util.Utilitario;
import co.com.vuelos.entidades.Bartender;

/**
 * Clase que hace el llamado a los metodos del bean para realizar las acciones
 * en las paginas referentes a los bartender del sistema.
 * 
 * @author Deivid Arias Ramirez
 * 
 */
@Named(value = "bartenderAction")
@SessionScoped
public class BartenderAction implements Serializable {

	/**
	 * Serializacion del pojo
	 */
	private static final long serialVersionUID = 1L;

	private String bar;

	private String nombre;

	private double DrinkTix;

	private double mgrComp;

	private double cortesy;

	private double openAjustments;

	private double openPromotorComp;

	private double salidaInventario;

	/**
	 * Recurso inyectado para acceder al bean de bartender
	 * 
	 */
	@Inject
	private BartenderBean bartenderBean;

	public BartenderAction() {

	}

	/**
	 * Metodo que permite resetear los campos del formulario
	 */
	public String limpiarCampos() {

		bar = ConstantesGenerales.CADENA_VACIA;

		nombre = ConstantesGenerales.CADENA_VACIA;

		DrinkTix = 0;

		mgrComp = 0;

		cortesy = 0;

		openAjustments = 0;

		openPromotorComp = 0;

		salidaInventario = 0;

		return "/Bartender/control_bartender.xhtml";
	}

	/**
	 * Metodo que permite registrar la informacion del bartender
	 */
	public void registrarBartender() {

		Bartender bartender = new Bartender();
		bartender.setBar(bar);
		bartender.setNombre(nombre);
		bartender.setCortesy(cortesy);
		bartender.setDrinkTix(DrinkTix);
		bartender.setFecha(new Date());
		bartender.setMgrComp(mgrComp);
		bartender.setOpenAjustments(openAjustments);
		bartender.setOpenPromotorComp(openPromotorComp);
		bartender.setSalidaInventario(salidaInventario);

		bartenderBean.registrarBartender(bartender);

		// mensaje de indicando que se registro correctamente
		Utilitario.exitoDesdeCodigo("Informacion registrada!");

		// reseteamos todos los campos
		limpiarCampos();

	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return bar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public String getBar() {
		return bar;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param bar
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setBar(String bar) {
		this.bar = bar;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return DrinkTix
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getDrinkTix() {
		return DrinkTix;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param DrinkTix
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setDrinkTix(double drinkTix) {
		DrinkTix = drinkTix;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return mgrComp
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getMgrComp() {
		return mgrComp;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param mgrComp
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setMgrComp(double mgrComp) {
		this.mgrComp = mgrComp;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return cortesy
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getCortesy() {
		return cortesy;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param cortesy
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setCortesy(double cortesy) {
		this.cortesy = cortesy;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return openAjustments
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getOpenAjustments() {
		return openAjustments;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param openAjustments
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setOpenAjustments(double openAjustments) {
		this.openAjustments = openAjustments;
	}

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return openPromotorComp
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public double getOpenPromotorComp() {
		return openPromotorComp;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param openPromotorComp
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setOpenPromotorComp(double openPromotorComp) {
		this.openPromotorComp = openPromotorComp;
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
	 * @return salidaInventario
	 *
	 * @author Deivid Arias Ramirez
	 */
	public double getSalidaInventario() {
		return salidaInventario;
	}

	/**
	 * Asigna valor al parametro
	 *
	 * @param salidaInventario
	 *
	 * @author Deivid Arias Ramirez
	 */
	public void setSalidaInventario(double salidaInventario) {
		this.salidaInventario = salidaInventario;
	}
	
	

}
