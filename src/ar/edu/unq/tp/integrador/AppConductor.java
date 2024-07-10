package ar.edu.unq.tp.integrador;

public class AppConductor implements MovementSensor{
	
	private SEM sem;
	private Celular celular;
	private Modo modo;
	private Boolean gpsActivado;
	
	public AppConductor(SEM sem, Celular celular) {
		this.sem = sem;
		this.celular = celular;
		this.modo = new ModoManual(); //Por defecto, comienza en modo manual
		this.gpsActivado = false; //Por defecto, el gps no se encuentra activado
	}

	public SEM getSem() {
		return sem;
	}

	public String getNumeroDeCelular() {
		return this.celular.getNroCelular();
	}

	public Modo getModo() {
		return modo;
	}

	public Boolean getGps() {
		return gpsActivado;
	}

	private void setGps(Boolean gps) {
		this.gpsActivado = gps;
	}
	
	@Override
	public void driving() {
		this.getModo().driving(this);
	}

	@Override
	public void walking() {
		this.getModo().walking(this);
	}
	
	public void iniciarEstacionamiento(String patente) {
		this.getModo().iniciarEstacionamiento(this, patente);
	}

	public void finalizarEstacionamiento(String numeroCelular) {
		this.getModo().finalizarEstacionamiento(this);
	}
	
	public void activarGPS() {
		if (!this.getGps()) {
			this.setGps(true);
		}else {
			throw new IllegalArgumentException("El GPS ya está activado."); 
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

