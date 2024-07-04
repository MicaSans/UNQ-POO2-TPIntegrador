package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class testEstacionamiento {

	@Test
	void testEstaVigenteEstacionamientoApp() {
		LocalDateTime horaInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		LocalDateTime horaFin = LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0));
		Estacionamiento estacionamiento = new EstacionamientoApp("1155227788", "DDD999", horaInicio);
		
		estacionamiento.setHoraFin(horaFin);
		
		assertTrue(estacionamiento.estaVigente());
	}
	
	@Test
	void testEstaVigenteEstacionamientoCompraPuntual() {
		LocalDateTime horaInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		LocalDateTime horaFin = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0));
		Estacionamiento estacionamiento = new EstacionamientoPuntual("DDD999", horaInicio, horaFin);
		
		assertTrue(estacionamiento.estaVigente());
	}
	
	
}
