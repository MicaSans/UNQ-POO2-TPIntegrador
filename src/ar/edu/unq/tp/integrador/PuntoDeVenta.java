package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;
import java.util.List;

public class PuntoDeVenta {
	
	private String cuit;
	private SEM sem;
	
	public PuntoDeVenta(String unCuit) {
		this.cuit = unCuit;
	}
	
	public String getCuit() {
		return this.cuit;
	}
	
	public CompraPuntual generarCompraPuntual(String unaPatente, Integer unaCantidadDeHoras) {
		LocalDateTime fechaYHoraActual = LocalDateTime.now();
		CompraPuntual nuevaCompraPuntual = new CompraPuntual(unaPatente, fechaYHoraActual, unaCantidadDeHoras);
		this.sem.registrarCompra(nuevaCompraPuntual);
		return nuevaCompraPuntual;
	}
	
	public CompraCelular generarCompraCelular(String unCelular, Integer unImporte) {
		CompraCelular nuevaCompraCelular = new CompraCelular(unCelular, unImporte);
		this.sem.registrarCompra(nuevaCompraCelular);
		this.sem.cargarCreditoDe(unCelular, unImporte);
	}

}
