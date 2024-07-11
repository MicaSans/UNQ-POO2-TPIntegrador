package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class CompraCelular extends Compra {
	private Integer montoDeCarga;
	private String numeroDeCelular;
	
	public CompraCelular(PuntoDeVenta puntoDeVenta, LocalDateTime fecYHr, Celular celular, Integer montoCarga) {
		super(puntoDeVenta, fecYHr);
		this.montoDeCarga = montoCarga;
		this.numeroDeCelular = celular.getNroCelular();
	}
	
	public Integer getMontoDeCarga() {
		return this.montoDeCarga;
	}
	
	public String getNumeroDeCelular() {
		return this.numeroDeCelular;
	}
	
}

