package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class CompraTest {
	
	@Mock PuntoDeVenta puntoDeVenta;

	@BeforeEach
	void setUp() throws Exception{
		puntoDeVenta = mock(PuntoDeVenta.class);
	}
	
	@Test
	void testGetFechaYHoraCompraPuntual() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Compra compraPuntual = new CompraPuntual(puntoDeVenta, horaDeCompra, "DDD999", 4);
		
		assertEquals(horaDeCompra, compraPuntual.getFechaYHora());
	}
	
	@Test
	void testGetFechaYHoraCompraCelular() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Celular celular = new Celular();
		Compra compraCelular = new CompraCelular(puntoDeVenta, horaDeCompra, celular, 1000);
		
		assertEquals(horaDeCompra, compraCelular.getFechaYHora());
	}
	
	@Test
	void testGetCantidadDeHorasCompraPuntual() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		CompraPuntual compraPuntual = new CompraPuntual(puntoDeVenta, horaDeCompra, "DDD999", 4);
		
		assertEquals(4, compraPuntual.getCantidadDeHoras());
	}
	
	@Test
	void testGetMontoDeCargaCompraCelular() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Celular celular = new Celular();
		CompraCelular compraCelular = new CompraCelular(puntoDeVenta, horaDeCompra, celular, 1000);
		
		assertEquals(1000, compraCelular.getMontoDeCarga());
	}
	
	@Test
	void testGetNroDeCelular() {
		LocalDateTime horaDeCompra = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
		Celular celular = new Celular();
		CompraCelular compraCelular = new CompraCelular(puntoDeVenta, horaDeCompra, celular, 1000);
		
		assertEquals(celular.getNroCelular(), compraCelular.getNumeroDeCelular());
	}

	@Test
	void testGetPuntoDeVenta() {
		CompraCelular compra = new CompraCelular(puntoDeVenta, LocalDateTime.now(), new Celular(), 500);
		assertEquals(compra.getPuntoDeVenta(), puntoDeVenta);
	}
	
	@Test
	void testGetNumeroDeControl() {
		CompraCelular compra = new CompraCelular(puntoDeVenta, LocalDateTime.now(), new Celular(), 9400);
		assertEquals(5, compra.getNumeroControl());
	}
}
