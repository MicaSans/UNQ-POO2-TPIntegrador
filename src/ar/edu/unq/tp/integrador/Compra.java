package ar.edu.unq.tp.integrador;
import java.time.LocalDateTime;

public class Compra {
	private static int contadorCompras = 0;
	private int numeroControl;
	private PuntoDeVenta puntoDeVenta;
	private LocalDateTime fechaYHora;
	
	public Compra(PuntoDeVenta puntoDeVenta, LocalDateTime fecYHr) {
		this.numeroControl = ++contadorCompras;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHora = fecYHr;
	}
	
	public LocalDateTime getFechaYHora() {
		return this.fechaYHora;
	}
	
	public PuntoDeVenta getPuntoDeVenta() {
		return this.puntoDeVenta;
	}

	public int getNumeroControl() {
		return numeroControl;
	}

}

