package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class EstacionamientoTest {
	Celular celular;
	
	@BeforeEach
	public void setUp() {
		celular = mock(Celular.class);
	}

	@Test
	void testEstaVigenteEstacionamientoApp() {
		LocalDateTime horaInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Estacionamiento estacionamiento = new EstacionamientoApp(celular, "DDD999", horaInicio);
		when(celular.getSaldo()).thenReturn(600);
		
		assertTrue(estacionamiento.estaVigente());
	}
	
	@Test
	void testNoEstaVigenteEstacionamientoApp() {
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
