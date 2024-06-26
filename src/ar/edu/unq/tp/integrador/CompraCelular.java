package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class CompraCelular extends Compra {
	private Integer montoDeCarga;
	private String numeroDeCelular;
	
	public CompraCelular(LocalDateTime fecYHr, String nroCel, Integer montoCarga) {
		super(fecYHr);
		this.montoDeCarga = montoCarga;
		this.numeroDeCelular = nroCel;
	}
	
	public Integer getMontoDeCarga() {
		return this.montoDeCarga;
	}
	
	public String getNumeroDeCelular() {
		return this.numeroDeCelular;
	}
	
}

