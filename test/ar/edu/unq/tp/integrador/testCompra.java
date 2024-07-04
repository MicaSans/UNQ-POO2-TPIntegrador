package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class testCompra {

	@Test
	void testGetFechaYHoraCompraPuntual() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Compra compraPuntual = new CompraPuntual(horaDeCompra, "DDD999", 4);
		
		assertEquals(horaDeCompra, compraPuntual.getFechaYHora());
	}
	
	@Test
	void testGetFechaYHoraCompraCelular() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Compra compraCelular = new CompraCelular(horaDeCompra, "1155531425", 1000);
		
		assertEquals(horaDeCompra, compraCelular.getFechaYHora());
	}
	
	@Test
	void testGetCantidadDeHorasCompraPuntual() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		CompraPuntual compraPuntual = new CompraPuntual(horaDeCompra, "DDD999", 4);
		
		assertEquals(horaDeCompra, compraPuntual.getCantidadDeHoras());
	}

}