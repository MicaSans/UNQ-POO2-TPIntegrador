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
	
	public CompraPuntual generarCompraPuntual(String patente, Integer cantHoras) {
		LocalDateTime fechaYHora = LocalDateTime.now();
		CompraPuntual nuevaCompraPuntual = new CompraPuntual(fechaYHora, patente, cantHoras);
		this.sem.registrarCompra(nuevaCompraPuntual);
		return nuevaCompraPuntual;
	}
	
	public CompraCelular generarCompraCelular(Celular celular, Integer monto) {
		LocalDateTime fechaYHora = LocalDateTime.now();
		CompraCelular nuevaCompraCelular = new CompraCelular(fechaYHora, celular, monto);
		this.sem.registrarCompra(nuevaCompraCelular);
		celular.cargarSaldo(monto);
		return nuevaCompraCelular;
	}
}
