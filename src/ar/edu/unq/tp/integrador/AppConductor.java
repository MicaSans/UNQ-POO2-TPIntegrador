package ar.edu.unq.tp.integrador;

public class AppConductor implements MovementSensor {
	
	private SEM sem;
	private Celular celular;
	private Modo modo;
	private Estado estado;
	private Boolean notificacionesActivas;
	private Boolean gpsActivado;
	
	public AppConductor(SEM sem, Celular celular) {
		this.sem = sem;
		this.celular = celular;
		this.modo = new ModoManual(); //Por defecto, comienza en modo manual
		this.estado = new EstadoAPie();
		this.notificacionesActivas = true;
		this.gpsActivado = false; //Por defecto, el gps no se encuentra activado
	}

	public String getNumeroDeCelular() {
		return this.celular.getNroCelular();
	}
	
	public Celular getCelular() {
		return this.celular;
	}

	public SEM getSem() {
		return sem;
	}

	public Modo getModo() {
		return modo;
	}
	
	public Estado getEstado() {
		return this.estado;
	}

	public boolean getGps() {
		return gpsActivado;
	}

	private void setGps(boolean gps) {
		this.gpsActivado = gps;
	}
	
	public int getCreditoDisponible() {
		return this.celular.getSaldo();
	}
	
	@Override
	public void driving() {
		this.getEstado().driving(this);
	}

	@Override
	public void walking() {
		this.getEstado().walking(this);
	}
	
	public void iniciarEstacionamiento(String patente) {
		this.estado.iniciarEstacionamiento(this, patente);
		this.sem.iniciarEstacionamiento(patente, this.getCelular()); 
	}
	
	public void finalizarEstacionamiento() {
		this.sem.finalizarEstacionamiento(this.getNumeroDeCelular());
	}
	
	public void notificarPosibleInicioDeEstacionamiento() {
		this.modo.notificarPosibleInicioDeEstacionamiento(this);
	}
	
	public void notificarPosibleFinDeEstacionamiento() {
		this.modo.notificarPosibleFinDeEstacionamiento(this);
	}
	
	public void activarODesactivarNotificaciones() {
		this.modo.activarODesactivarNotificaciones(this);
	}
	
	//TODO: revisar
	public void consultarSaldoDisponible() {
		System.out.println("Su saldo actual es $" + getCreditoDisponible());
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

