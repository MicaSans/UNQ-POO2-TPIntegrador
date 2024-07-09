package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class EstacionamientoApp extends Estacionamiento {
	private Celular celular;
	
	public EstacionamientoApp(Celular celular, String patente, LocalDateTime horaInicio) {
		super(patente, horaInicio);
		this.celular = celular;
	}
	
	@Override
	public Boolean estaVigente() {
		return this.getHoraFin() == null && this.maxHoraDeFinQuePodriaAbonar().isAfter(LocalDateTime.now());
	}
	
	private LocalDateTime maxHoraDeFinQuePodriaAbonar() {
		int maxCantHorasSegunSaldo = this.celular.getSaldo() / 40;
		LocalDateTime maxHoraDeFin = this.getHoraInicio().plusHours(maxCantHorasSegunSaldo);
		return maxHoraDeFin;
	}
}
