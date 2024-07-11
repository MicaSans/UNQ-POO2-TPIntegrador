package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CelularTest {
	Celular celular;
	
	@BeforeEach
	void setUp() throws Exception {
		celular = new Celular();
	}

	@Test
	void testGetSaldo() {
		assertEquals(celular.getSaldo(), 0);
	}

	@Test
	void testGetPrefijoCelular() {
		assertEquals(celular.getPrefijoCelular(), "15");
	}
	
	@Test
	void testCargarSaldo() {
		celular.cargarSaldo(500);
		
		assertEquals(celular.getSaldo(), 500);
	}
	
	@Test
	void testDescontarSaldo() {
		celular.cargarSaldo(500);
		celular.descontarSaldo(100);
		
		assertEquals(celular.getSaldo(), 400);
	}
	
	@Test
	void testGetNroCelular() {
		assertEquals(celular.getNroCelular(), celular.nroCelular);
	}
}
