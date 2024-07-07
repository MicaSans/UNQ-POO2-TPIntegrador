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

		// Genera un número aleatorio de 8 dígitos
		int randomCelular = 10000000 + random.nextInt(90000000);

		// Concatenar "11" al inicio del número aleatorio generado
		return this.getPrefijoCelular() + randomCelular;
	}
	
	public String getPrefijoCelular() {
		return this.prefijoCelular;
	}
	
	public String getNroCelular() {
		return this.nroCelular;
	}

	public void cargarSaldo(int montoCarga) {
		this.saldo += montoCarga;
	}
	
}
