/**
 * 
 */
package co.com.inventario.constantes;

/**
 * Clase que implementa las constantes generales de la aplicacion
 * 
 * @author Deivid Arias Ramirez
 * 
 */
public class ConstantesGenerales {

	/**
	 * indica el mensaje de exito que se va mostrar cuando se registre un
	 * usuario
	 */
	public static final String USUARIO_MENSAJE_BIENVENIDO = "Bienvenido ";

	/**
	 * indica el mensaje de exito que se va mostrar cuando se registre un
	 * usuario
	 */
	public static final String USUARIO_MENSAJE_ERROR_LOGIN = "El login ya existe en el sistema, utilice otro. ";
	/**
	 * indica el mensaje de exito que se va mostrar cuando se registre un
	 * usuario
	 */
	public static final String USUARIO_MENSAJE_LOGIN_INVALIDO = "login o password invalidos ";

	/**
	 * Indica una cadena vacia
	 */
	public static final String CADENA_VACIA = "";

	/**
	 * Indica una la ruta de la pagina de bienvenida
	 */
	public static final String PAGINA_BIENVENIDA = "/layout/inicio.html?faces-redirect=true";

	/**
	 * Indica la pagina de inicio de la aplicacion
	 */
	public static final String PAGINA_INICIO = "/layout/inicio.html?faces-redirect=true";

	/**
	 * Indica una la ruta de la pagina para registrar un usuario
	 */
	public static final String USUARIO_URL_REGISTRAR_USUARIO = "/Usuario/registrar_usuario.html?faces-redirect=true";

	/**
	 * Indica una la ruta de la pagina de inicio de sesion
	 */
	public static final String USUARIO_URL_LOGIN = "/Usuario/login.html?faces-redirect=true";

	/**
	 * Indica una la ruta de la pagina de inicio de sesion
	 */
	public static final String USUARIO_MENSAJE_ERROR_PASSWORD = "Los password no coinciden, por favor verifique";

	/**
	 * Indica una la ruta de la pagina de inicio de sesion
	 */
	public static final String USUARIO_MENSAJE_REGISTRAR = "Usuario registrado!, por favor inicie sesion";

	/**
	 * Indica una la ruta de la pagina de consulta de horarios
	 */
	public static final String VUELO_URL_CONSULTA_HORARIO = "/Consultas/consulta_horario.html?faces-redirect=true";

	/**
	 * Indica una la ruta de la pagina de consulta por tarifas
	 */
	public static final String VUELO_URL_CONSULTA_TARIFA = "/Consultas/consulta_tarifas.html?faces-redirect=true";
	/**
	 * Indica una la ruta de la pagina de consulta por tarifas
	 */
	public static final String VUELO_URL_CONSULTA_ESTADO = "/Consultas/consulta_estado.html?faces-redirect=true";
	/**
	 * Indica una la ruta de la pagina de consulta de horarios
	 */
	public static final String VUELO_MENSAJE_ERROR_SESION = "Debe iniciar sesion en el sistema o registrarse si es primera vez";

	/**
	 * Indica el mensaje de error a mostrar cuando ya haya realizado una reserva
	 * en esa fecha
	 */
	public static final String RESERVA_ERROR_FECHA = "Ya realizó una reserva para esa fecha y hora";

	/**
	 * Indica el mensaje de error a mostrar cuando ya haya realizado una reserva
	 * en esa fecha
	 */
	public static final String RESERVA_ERROR_EDAD = "Debe ser mayor a 18";

	/**
	 * url de las reservas por usuario
	 */
	public static final String VUELO_URL_MIS_RESERVAS = "/Reserva/mis_reservas.html?faces-redirect=true";

	/**
	 * indica el mensaje de exito que se va mostrar cuando se registre un
	 * usuario
	 */
	public static final String MENSAJE_EXITO_USUARIO_REGISTRAR = "El usuario ha sido registrado";

	/**
	 * indica el mensaje de exito que se va mostrar cuando se registre un
	 * cliente
	 */
	public static final String MENSAJE_EXITO_CLIENTE_REGISTRAR = "El cliente ha sido registrado";

	/**
	 * Indica el mensaje de error cuando no se ingrese el codigo del area
	 */
	public static final String MENSAJE_ERROR_AREA_CODIGO = "El campo codigo es obligatorio";

	/**
	 * Indica una cadena vacia
	 */
	public static final String ESPACIO_BLANCO = " ";

	/**
	 * Indica que el usuario estara activo en el sistema
	 */
	public static final String ESTADO_ACTIVO_USUARIO = "ACTIVO";

	/**
	 * Indica que el usuario estara inactivo en el sistema
	 */
	public static final String ESTADO_INACTIVO_USUARIO = "INACTIVO";

	/**
	 * Indica que el mensaje de error cuando no se encuentra un usuario
	 */
	public static final String MENSAJE_ERROR_BUSCAR_USUARIO_EDITAR = "Usuario no encontrado";

	/**
	 * Indica el mensaje de error cuando no se encuentra el cliente
	 */
	public static final String MENSAJE_ERROR_BUSCAR_CLIENTE_EDITAR = "El Paciente no se encontro";

	/**
	 * Indica el mensaje de exito cuando se edita el usuario
	 */
	public static final String MENSAJE_EXITO_USUARIO_EDITAR = "La informacion del usuario ha sido actualizada.";

	/**
	 * Indica el mensaje de exito cuando se edita el cliente
	 */
	public static final String MENSAJE_EXITO_CLIENTE_EDITAR = "La informacion del cliente ha sido actualizada.";

	/**
	 * Indica el texto que se mostrara en el boton
	 */
	public static final String TEXTO_BOTON_ACTIVAR = "Activar";

	/**
	 * Indica el texto que se mostrara en el boton
	 */
	public static final String TEXTO_BOTON_INACTIVAR = "Inactivar";

	/**
	 * Indica la url del inicio de sesion
	 */
	public static final String URL_INICIAR_SESION = "/Sesion/iniciar_sesion.xhtml";
	/**
	 * Indica el mensaje de error de login o password
	 */
	public static final String ERROR_LOGIN_PASSWORD = "Login o password invalido";

	/**
	 * Indica el mensaje de exito cuando se crea la ciudad
	 */
	public static final String MENSAJE_EXITO_CIUDAD_CREAR = "La ciudad ha sido creada";

	/**
	 * Indica el mensaje de exito cuando se crea el departamento
	 */
	public static final String MENSAJE_EXITO_DEPARTAMENTO_CREAR = "el departamento ha sido creado";

	/**
	 * Url de la pagina de inicio
	 */
	public static final String URL_INICIO = "/layout/inicio.html?faces-redirect=true";

	/**
	 * Si el usuario esta inactivo no lo dejara iniciar sesion
	 */
	public static final String ERROR_LOGIN_INACTIVO = "El usuario esta inactivo en el sistema, contactese con el administrador";

	/**
	 * Si el usuario esta inactivo no lo dejara iniciar sesion
	 */
	public static final String MENSAJE_SESION_INICIADA = "Sesion iniciada correctamente";

	/**
	 * indica el error cuando el usuario vaya a cambiar el password
	 */
	public static final String ERROR_CAMBIO_PASSWORD = "Los password no coinciden, verifique nuevamente";

	/**
	 * indica el error cuando no se ingresa la descripcion
	 */
	public static final String MENSAJE_ERROR_SUCURSA_DESCRIPCION = "El campo descripcion es obligatorio";

	/**
	 * Indica el error cuando no hay departamentos creados
	 */
	public static final String ERROR_DEPARTAMENTOS_NO_CREADOS = "No hay departamentos creados";

	/**
	 * Indica el error cuando no hay sucursales creadas
	 */
	public static final String ERROR_SUCURSALES_NO_CREADAS = "No hay sucursales creadas";

	/**
	 * Indica el error cuando la ciudad no se encontro
	 */
	public static final String MENSAJE_ERROR_BUSCAR_CIUDAD_EDITAR = "Ciudad no encontrada";

	/**
	 * Mensaje que indica que la ciudad ha sido editada con exito
	 */
	public static final String MENSAJE_EXITO_EDITAR_CIUDAD = "La informacion de la ciudad ha sido actualizada";

	/**
	 * Mensaje que indica que se debe seleccionar una sucursal
	 */
	public static final String MENSAJE_SELECCION_SUCURSAL = "Se debe seleccionar la sucursal";

	/**
	 * Mensaje que informa que el usuario ha sido activado
	 */
	public static final String MENSAJE_USUARIO_ACTIVADO = "El usuario ha sido activado";

	/**
	 * Mensaje que informa que el usuario se ha inactivado
	 */
	public static final String MENSAJE_USUARIO_INACTIVADO = "El usuario ha sido inactivado";

	/**
	 * Indica un error cuanod el campo esta vacio
	 */
	public static final String ERROR_CAMPO_VACIO = "El campo no puede estar vacio";

	/**
	 * Mensaje que indica que la sucursal ha sido editada correctamente
	 */
	public static final String MENSAJE_SUCURSAL_EDITADA = "La sucursal ha sido editada correctamente";

	/**
	 * Mensaje que indica que el departamento ha sido editado correctamente
	 */
	public static final String MENSAJE_DEPARTAMENTOL_EDITADO = "El departamento ha sido editada correctamente";

	/**
	 * Indica el error cuando no hay ciudades o sucursales creadas
	 */
	public static final String ERROR_SUCURSALES_CIUDADES_NO_CREADAS = "No hay ciudades o sucursales creadas";

	/**
	 * Indica el error cuando no hay ciudades o sucursales o tipo de
	 * identificacion creadas
	 */
	public static final String ERROR_SUCURSALES_CIUDADES_TIPOIDENTIFICACION_NO_CREADAS = "No hay ciudades o sucursales o tipo de identificacion creadas";

	/**
	 * Indica el error cuando no hay ciudades o sucursales o el tipo de
	 * identificacion no han sido seleccionadas
	 */
	public static final String ERROR_SUCURSALES_CIUDADES_TIPOIDENTIFICACION_NO_SELECCIONADAS = "No hay ciudades o sucursales o tipos de identificacion seleccionadas";

	/**
	 * Indica el error cuando no se ha seleccionado la sucursal
	 */
	public static final String ERROR_SUCURSAL_NO_SELECCIONADA = "Se debe seleccionar la sucursal";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String ERROR_CIUDAD_NO_SELECCIONADA = "Se debe seleccionar la ciudad";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_EXITO_EDITAR_LICENCIA = "Licencia configurada correctamente.";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_VALIDACION_EDITAR_LICENCIA = "Los valores deben ser mayor a 0.";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_EXITO_LICENCIA_REGISTRAR = "Licencia registrada.";

	/**
	 * Indica el error cuando no se ha seleccionado el tipo de identificacion
	 */
	public static final String ERROR_TIPOIDENTIFICACION_NO_SELECCIONADO = "Se debe seleccionar un tipo de identificacion";

	/**
	 * Indica el error cuando on se ha seleccioado la fecha de expedicion
	 */
	public static final String ERROR_FECHAEXPEDICION_NO_SELECCIONADA = "Se debe asignar una fecha de expedicion";

	/**
	 * Indica el error cuando on se ha seleccioado la fecha de nacimiento
	 */
	public static final String ERROR_FECHANACIMIENTO_NO_SELECCIONADA = "Se debe asignar una fecha de nacimiento";

	/**
	 * Indica que la licencia se debe ingresar para la venta
	 */
	public static final String VALIDACION_VENTA_LICENCIA = "- Se debe seleccionar la licencia";

	/**
	 * Indica que la licencia se debe ingresar para la venta
	 */
	public static final String URL_LICENCIA_VENDER_CONTADO = "/VentaLicencia/vender_licencia_contado.html?faces-redirect=true";

	/**
	 * Mensaje de error cuando se digita mal la cedula o el cliente no existe
	 */
	public static final String VALIDACION_CLIENTE_VENDER_LICENCIA = "El cliente no existe o la cedula esta incorrecta";

	/**
	 * Mensaje de error cuando se digita mal la cedula o el cliente no existe
	 */
	public static final String PAGO_CONTADO = "CONTADO";

	/**
	 * Mensaje de error cuando se digita mal la cedula o el cliente no existe
	 */
	public static final String MENSAJE_EXITO_VENTA = "Venta realizada.";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_VALIDACION_EDITAR_TECNO = "El valor debe ser mayor a 0 y el vehiculo no puede ser nulo.";

	/**
	 * Indica el mensaje de exito cuando se persista la tecnicomecanica
	 */
	public static final String MENSAJE_EXITO_EDITAR_TECNO = "Tecnicomecanica configurada correctamente.";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_EXITO_TECNO_REGISTRAR = "Tecnicomecanica registrada.";

	/**
	 * Indica el mensaje de exito cuando se persista el seguro
	 */
	public static final String MENSAJE_EXITO_EDITAR_SEGURO = "Seguro configurado correctamente.";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_VALIDACION_REGISTRAR_SEGURO = "El valor debe ser mayor a 0 y el cilindraje no puede ser nulo.";

	/**
	 * Indica el error cuando no se ha seleccionado la ciudad
	 */
	public static final String MENSAJE_EXITO_SEGURO_REGISTRAR = "Seguro registrada.";

	/**
	 * Indica que seguro se debe ingresar para la venta
	 */
	public static final String URL_SEGURO_VENDER_CONTADO = "/VentaSeguro/vender_seguro_contado.html?faces-redirect=true";

	/**
	 * Indica que seguro se debe ingresar para la venta
	 */
	public static final String URL_TECNO_VENDER_CONTADO = "/VentaTecno/vender_tecno_contado.html?faces-redirect=true";

	/**
	 * Redirecciona a la pagina de lista de clientes
	 */
	public static final String URL_LISTADO_CLIENTES = "/Cliente/listado_clientes.xhtml";

	/**
	 * Indica el error cuando ya existe un cleinte con la isma identificacion en
	 * el sistema
	 */
	public static final String ERROR_CLIENTE_EXISTENTE = "Ya existe un paciente con esa identificación";

	/**
	 * Indica el error cuando ya existe un cleinte con la isma identificacion en
	 * el sistema
	 */
	public static final String ERROR_PACIENTE_NO_EXISTENTE = "Paciente no encontrado, verifique su cedula.";

	/**
	 * indica la url del egreso
	 */
	public static final String URL_EGRESOS = "/Egreso/administrar_egreso.xhtml";

	/**
	 * indica la url del egreso
	 */
	public static final String MENSAJE_EXITO_PRODUCTO_REGISTRAR = "Operacion Exitosa";

	/**
	 * Indica el mensaje de error cuando no se ingrese el codigo del area
	 */
	public static final String MENSAJE_ERROR_TIPO_DOC_DESCRIPCION = "El campo descripcion es obligatorio";

}
