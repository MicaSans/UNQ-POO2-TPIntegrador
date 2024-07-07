package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class CompraCelular extends Compra {
	private Integer montoDeCarga;
	private Celular numeroDeCelular;
	
	public CompraCelular(LocalDateTime fecYHr, Celular nroCel, Integer montoCarga) {
		super(fecYHr);
		this.montoDeCarga = montoCarga;
		this.numeroDeCelular = nroCel;
	}
	
	public Integer getMontoDeCarga() {
		return this.montoDeCarga;
	}
	
	public Celular getNumeroDeCelular() {
		return this.numeroDeCelular;
	}
	
}

