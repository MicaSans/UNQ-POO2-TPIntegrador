package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class Infraccion {

	private String patente;
	private LocalDateTime fechaYHora;
	private Zona zona;
	
	public Infraccion(String patente, LocalDateTime fechaYHora, Zona zona) {
		this.patente = patente;
		this.fechaYHora = fechaYHora;
		this.zona = zona;
	}

	//Getters y setters
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
}
