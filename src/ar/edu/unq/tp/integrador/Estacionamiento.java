package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public abstract class Estacionamiento {
	private String patente;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	
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
	
	public Boolean estaVigente() {
		return !horaInicio.isAfter(horaFin);
	}
}
