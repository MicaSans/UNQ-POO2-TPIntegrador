package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class CompraPuntual extends Compra {
	private String patente;
	private Integer cantidadDeHoras;
	
	public CompraPuntual(LocalDateTime fecYHr, String patente, Integer cantHs) {
		super(fecYHr);
		this.patente = patente;
		this.cantidadDeHoras = cantHs;
	}
	
	public Integer getCantidadDeHoras() {
		return this.cantidadDeHoras;
	}
}

