package ar.edu.unq.tp.integrador;

import java.util.Random;

public class Celular {
	String nroCelular;
	String prefijoCelular = "15";
	int saldo;
	
	public Celular() {
		this.nroCelular = generarNroDeCelular();
		this.saldo = 0;
	}
	
	public int getSaldo() {
		return this.saldo;
		
	}
	
	private String generarNroDeCelular() {
		Random random = new Random();
		int randomCelular = 10000000 + random.nextInt(90000000);

		return this.getPrefijoCelular() + randomCelular;
	}
	
	public String getPrefijoCelular() {
		return this.prefijoCelular;
	}
	
	public String getNroCelular() {
		return this.nroCelular;
	}

	public void cargarSaldo(Integer montoCarga) {
		this.saldo += montoCarga;
	}
	
	public void descontarSaldo(Integer montoADescontar) {
		this.saldo -= montoADescontar;
	}
	
}
