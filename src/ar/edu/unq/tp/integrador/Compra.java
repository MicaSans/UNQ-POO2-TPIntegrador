package ar.edu.unq.tp.integrador;
import java.time.LocalDateTime;

public class Compra {
	private LocalDateTime fechaYHora;
	
	public Compra(LocalDateTime fecYHr) {
		this.fechaYHora = fecYHr;
	}
	
	public LocalDateTime getFechaYHora() {
		return this.fechaYHora;
	}
}

