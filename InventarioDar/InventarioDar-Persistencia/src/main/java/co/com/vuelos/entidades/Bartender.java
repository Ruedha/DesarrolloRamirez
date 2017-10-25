package co.com.vuelos.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que permite generar la tabla tipo producto en la base de datos
 * 
 * @author Deivid Arias Raramirez
 * 
 */
@Entity
@Table(name = "bartender")
public class Bartender implements Serializable {

	// serializacion de la clase
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String bar;
	private Date fecha;
	private double salidaInventario;
	private double DrinkTix;
	private double mgrComp;
	private double cortesy;
	private double openAjustments;
	private double openPromotorComp;

	/**
	 * Devuelve el valor del parametro
	 * 
	 * @return id
	 * 
	 * @author Deivid Arias Ramirez
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	 * @return fecha
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Asigna valor al parametro
	 * 
	 * @param fecha
	 * 
	 * @author Deivid Arias Ramirez
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
