package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;

public class EstacionamientoPuntual extends Estacionamiento {

	public EstacionamientoPuntual(String patente, LocalDateTime horaInicio, LocalDateTime horaFin) {
		super(patente, horaInicio, horaFin);
	}

	@Override
	public boolean esEstacionamientoApp() {
		return false;
	}

	//TODO: revisar
	@Override
	public String getNroCelular() {
		return null;
	}

	@Override
	protected void cobrarEstacionamiento(Integer precioHoras) {}

}
