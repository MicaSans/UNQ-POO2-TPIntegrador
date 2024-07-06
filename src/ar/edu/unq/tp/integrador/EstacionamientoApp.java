package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class EstacionamientoApp extends Estacionamiento {
	private String numeroDeCelular;
	
	public EstacionamientoApp(String nroCelular, String patente,LocalDateTime horaInicio) {
		super(patente, horaInicio);
		this.numeroDeCelular = nroCelular;
	}
	
	@Override
	public void finalizarEstacionamiento() {
		if(estaVigente()) {
			setHoraFin(LocalDateTime.now());
		}
	}
}
