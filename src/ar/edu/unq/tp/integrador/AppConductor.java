package ar.edu.unq.tp.integrador;

import java.time.Duration;
import java.time.LocalDateTime;

public class AppConductor implements MovementSensor{
	
	private SEM sem;
	private Celular celular;
	private Modo modo;
	private Boolean gpsActivado;
	private LocalDateTime horaInicioEstacionamiento;
	
	public AppConductor(SEM sem, Celular celular) {
		this.sem = sem;
		this.celular = celular;
		this.modo = new ModoManual(); //Por defecto, comienza en modo manual
		this.gpsActivado = false; //Por defecto, el gps no se encuentra activado
	}
	
	public Celular getCelular() {
		return this.celular;
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
	
	public int getCreditoDisponible() {
		return this.celular.getSaldo();
	}
	
	public boolean haySaldoSuficiente() {
		int creditoNecesario = sem.saldoParaElHorario(LocalDateTime.now(), sem.getHorarioFin());
		return getCreditoDisponible() >= creditoNecesario;
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
		if(haySaldoSuficiente()) {
			this.getModo().iniciarEstacionamiento(this, patente);
			System.out.println("Estacionamiento iniciado: su hora de inicio es " + LocalDateTime.now() + " y su hora máxima de estacionamiento es " + calcularHoraMaximaEstacionamiento());
			this.setHoraInicioEstacionamiento(LocalDateTime.now());
		}else {
			System.out.println("Saldo insuficiente. Estacionamiento no permitido");
		}
	}
	
	private void setHoraInicioEstacionamiento(LocalDateTime hora) {
		this.horaInicioEstacionamiento = hora;
	}

	public LocalDateTime getHoraInicioEstacionamiento() {
		return this.horaInicioEstacionamiento;
	}

	private LocalDateTime calcularHoraMaximaEstacionamiento() {
		int saldoDisponible = this.getCreditoDisponible();
		int costoPorHora = sem.getPrecioPorHora();
		int horasPosibles = saldoDisponible / costoPorHora;
		LocalDateTime horaInicio = LocalDateTime.now();
		LocalDateTime horaMaximaEstacionamiento = horaInicio.plusHours(horasPosibles);
		if(horaMaximaEstacionamiento.isAfter(sem.getHorarioFin())) {
			horaMaximaEstacionamiento = sem.getHorarioFin();
		}
		return horaMaximaEstacionamiento;
	}
	
	private int obtenerImporteADescontar(LocalDateTime horario) {
		Duration duracionEstacionamiento = Duration.between(horaInicioEstacionamiento, horario);
		int horasTranscurridas = (int) duracionEstacionamiento.toHours();
		return horasTranscurridas * sem.getPrecioPorHora();
	}
	
	private void calcularSaldoADescontar(LocalDateTime horario) {
		this.descontarCredito(obtenerImporteADescontar(horario));
	}

	private void descontarCredito(Integer monto) {
		celular.descontarSaldo(monto);
	}

	public void finalizarEstacionamiento(String numeroCelular) {
		this.getModo().finalizarEstacionamiento(this);
		this.calcularSaldoADescontar(LocalDateTime.now());
		System.out.println("Estacionamiento finalizado: su hora inicial fue " + this.horaInicioEstacionamiento + ", su hora de finalización " + LocalDateTime.now() + ", la duración del estacionamiento fue de " + Duration.between(horaInicioEstacionamiento, LocalDateTime.now()) + " y el importe debitado de su crédito fue $" + this.obtenerImporteADescontar(LocalDateTime.now()));
	}
	
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

