package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class PuntoDeVenta {
	
	private String cuit;
	private SEM sem;
	
	public PuntoDeVenta(String cuit, SEM sem) {
		this.cuit = cuit;
		this.sem = sem;
	}
	
	public String getCuit() {
		return this.cuit;
	}
	
	public void generarCompraPuntual(String patente, Integer cantHoras) {
		LocalDateTime horaInicio = LocalDateTime.now();
		LocalDateTime horaFin = horaInicio.plusHours(cantHoras);
		CompraPuntual nuevaCompraPuntual = new CompraPuntual(this, horaInicio, patente, cantHoras);
		this.sem.registrarCompra(nuevaCompraPuntual);
		this.sem.iniciarEstacionamiento(patente, horaInicio, horaFin);
	}
	
	public CompraCelular generarCompraCelular(Celular celular, Integer monto) {
		LocalDateTime fechaYHora = LocalDateTime.now();
		CompraCelular nuevaCompraCelular = new CompraCelular(this, fechaYHora, celular, monto);
		this.sem.registrarCompra(nuevaCompraCelular);
		this.sem.registrarCredito(celular, monto);
		return nuevaCompraCelular;
	}
}
