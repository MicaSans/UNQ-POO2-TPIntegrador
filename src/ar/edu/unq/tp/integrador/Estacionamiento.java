package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public abstract class Estacionamiento {
	private String patente;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	
	public Estacionamiento(String patente, LocalDateTime horaInicio) {
		this.patente = patente;
		this.horaInicio = horaInicio;
		this.horaFin = null;
	}
	
	public Estacionamiento(String patente, LocalDateTime horaInicio, LocalDateTime horaFin) {
		this.patente = patente;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public LocalDateTime getHoraFin() {
		return this.horaFin;
	}
	
	public void setHoraFin(LocalDateTime hora) {
		this.horaFin = hora;
	}
	
	public boolean estaVigente() {
		return this.horaFin.isAfter(LocalDateTime.now());
	}
	
	public abstract boolean esEstacionamientoApp();
	public abstract String getNroCelular();
}
