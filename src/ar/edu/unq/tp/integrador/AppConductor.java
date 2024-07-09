package ar.edu.unq.tp.integrador;

public class AppConductor implements MovementSensor{
	
	/**
	 * Esta clase es la que utilizan las personas que hagan uso del Sistema de Estacionamiento Medido.
	 */
	
	/**
	 * Variables de instancia
	 */
	
	/* Declaración del colaborador interno "sem". Corresponde a la clase SEM.
	 */
	private SEM sem;
	
	/* Declaración del colaborador interno "numeroDeTelefono".
	*  Hace referencia al número de télefono que tiene asociado el usuario.
	*  Es de tipo String.
	*/
	private Celular celular;
	
	/* Declaración e inicialización del colaborador interno "modo".
	 * Es una interfaz del tipo IModo.
	 * Corresponde al modo en que se encuentra la aplicación: Manual o Automático.
	 * Por defecto se inicializa en modo "ModoManual". 
	 * */
	private IModo modo = new ModoManual();
	
	/* Declaración e inicialización del colaborador interno "gps".
	 * Es del tipo Boolean.
	 * Simboliza la representación del estado on/off del gps.
	 * Por defecto se inicializa en "false". 
	 * */
	private Boolean gps = false;

	/**
	 * Getters & Setters 
	 */
	public SEM getSem() {
		return sem;
	}

	private void setSem(SEM sem) {
		this.sem = sem;
	}

	public String getNumeroDeCelular() {
		return this.celular.getNroCelular();
	}

	public IModo getModo() {
		return modo;
	}

	private void setModo(IModo modo) {
		this.modo = modo;
	}

	public Boolean getGps() {
		return gps;
	}

	private void setGps(Boolean gps) {
		this.gps = gps;
	}

	/**
	 * Constructor de clase.
	 * @param sem El SEM con el cual se comunica la appConductor.
	 * @param numeroDeCelular El número de celular que tiene registrada la appConductor.
	 */
	public AppConductor(SEM sem, Celular celular) {
		super();
		this.setSem(sem);
		this.celular = celular;
		this.setModo(this.getModo());
		this.setGps(this.getGps());
	}
	
	@Override
	public void driving() {
		// TODO completar implementacion
		// modo.ejecutarAPie();
		
	}

	@Override
	public void walking() {
		// TODO completar implementacion
		// modo.ejecutarEnVehiculo();
	}
	
	public void iniciarEstacionamiento(String patente) {
	}

	//TODO: implementar
	public void finalizarEstacionamiento(String numeroDeCelular) {
		
	}
	
	//TODO: que hace este metodo? Leer las consignas.
	public void recibirInformacion() {
		
	}
	
	public void activarGPS() {
		if (this.getGps()) {
			throw new IllegalArgumentException("El GPS ya está activado."); 
		}else {
			this.setGps(true);
		}
	}
	
	public void desactivarGPS() {
		if (this.getGps()) {
			this.setGps(false);
		}else {
			throw new IllegalArgumentException("El GPS ya está desactivado."); 
		}
	}
	
}

