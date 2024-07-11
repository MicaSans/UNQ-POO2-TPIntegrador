package ar.edu.unq.tp.integrador;

public class AppConductor implements MovementSensor {
	
	private SEM sem;
	private Celular celular;
	private String patente;
	private Modo modo;
	private Estado estado;
	private Boolean alertaDesplazamientoActiva;
	
	public AppConductor(SEM sem, Celular celular, String patente) {
		this.sem = sem;
		this.celular = celular;
		this.modo = new ModoManual();
		this.estado = new EstadoAPie();
		this.patente = patente;
		this.alertaDesplazamientoActiva = true;
	}

	public String getNumeroDeCelular() {
		return this.celular.getNroCelular();
	}
	
	public Celular getCelular() {
		return this.celular;
	}
	
	public String getPatente() {
		return patente;
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

	public int getCreditoDisponible() {
		return this.celular.getSaldo();
	}
	
	public Boolean getAlertaDesplazamientoActiva() {
		return alertaDesplazamientoActiva;
	}
	
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void setAlertaDesplazamientoActiva() {
		alertaDesplazamientoActiva = !alertaDesplazamientoActiva;
	}
	
	public void iniciarEstacionamiento() {
		this.estado.iniciarEstacionamiento(this);
	}
	
	public void iniciarEstacionamientoSEM() {
		this.sem.iniciarEstacionamiento(this.getPatente(), this.getCelular());
	}
	
	public void finalizarEstacionamiento() {
		this.estado.finalizarEstacionamiento(this);
	}
	
	public void finalizarEstacionamientoSEM() {
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
	
	@Override
	public void driving() {
		this.getEstado().driving(this);
	}

	@Override
	public void walking() {
		this.getEstado().walking(this);
	}
	
}

