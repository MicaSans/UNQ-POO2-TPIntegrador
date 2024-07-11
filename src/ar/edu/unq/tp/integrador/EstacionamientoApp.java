package ar.edu.unq.tp.integrador;

import java.time.Duration;
import java.time.LocalDateTime;

public class EstacionamientoApp extends Estacionamiento {
	private Celular celular;
	
	public EstacionamientoApp(Celular celular, String patente, LocalDateTime horaInicio) {
		super(patente, horaInicio);
		this.celular = celular;
	}
	
	@Override
	public boolean estaVigente() {
		return this.getHoraFin() == null && this.maxHoraDeFinQuePodriaAbonar().isAfter(LocalDateTime.now());
	}
	
	private LocalDateTime maxHoraDeFinQuePodriaAbonar() {
		int maxCantHorasSegunSaldo = this.celular.getSaldo() / 40;
		LocalDateTime maxHoraDeFin = this.getHoraInicio().plusHours(maxCantHorasSegunSaldo);
		return maxHoraDeFin;
	}
	
	@Override
	public boolean esEstacionamientoApp() {
		return true;
	}
	
	@Override
	public String getNroCelular() {
		return this.celular.getNroCelular();
	}
	
	@Override
	public void cobrarEstacionamiento(Integer monto) {
        this.getCelular().descontarSaldo(monto);
	}

	public Celular getCelular() {
		return this.celular;
	}
}
