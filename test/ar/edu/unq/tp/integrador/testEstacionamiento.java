package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class testEstacionamiento {

	@Test
	void testEstaVigenteEstacionamientoApp() {
		Celular celular = new Celular();
		LocalDateTime horaInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Estacionamiento estacionamiento = new EstacionamientoApp(celular, "DDD999", horaInicio);
		celular.cargarSaldo(600);
		
		assertTrue(estacionamiento.estaVigente());
	}
	
	@Test
	void testNoEstaVigenteEstacionamientoApp() {
		Celular celular = new Celular();
		LocalDateTime horaInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Estacionamiento estacionamiento = new EstacionamientoApp(celular, "DDD999", horaInicio);
		celular.cargarSaldo(40); //le alcanzaría solo para 1 hora por lo que podría pagar hasta las 9 a.m.
		assertFalse(estacionamiento.estaVigente());
	}
	
	@Test
	void testEstaVigenteEstacionamientoCompraPuntual() {
		LocalDateTime horaInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		LocalDateTime horaFin = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0));
		Estacionamiento estacionamiento = new EstacionamientoPuntual("DDD999", horaInicio, horaFin);
		
		assertTrue(estacionamiento.estaVigente());
	}
	
	
}
