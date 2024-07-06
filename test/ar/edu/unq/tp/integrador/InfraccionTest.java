package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfraccionTest {

	private Infraccion infraccion;
	private Zona zona;
	
	@BeforeEach
	void setUp() throws Exception {
		zona = new Zona(894, 7452);
		infraccion = new Infraccion("OE856WI", LocalDateTime.parse("2023-10-11T19:30:00.00"), zona);
		
	}

	@Test
	void testGetPatente() {
		assertEquals(infraccion.getPatente(), "OE856WI");
	}
	
	@Test
	void testSetPatente() {
		infraccion.setPatente("APD524");
		
		assertFalse(infraccion.getPatente() == "OE856WI");
		assertEquals(infraccion.getPatente(), "APD524");
	}

	@Test
	void testGetFechaYHora() {
		assertEquals(infraccion.getFechaYHora(), LocalDateTime.parse("2023-10-11T19:30:00.00"));
	}
	
	@Test
	void testSetFechaYHora() {
		infraccion.setFechaYHora(LocalDateTime.now());
		
		assertEquals(infraccion.getFechaYHora(), LocalDateTime.now());
	}

	@Test
	void testGetZona() {
		assertEquals(infraccion.getZona(), zona);
	}
	
	@Test
	void testSetZona() {
		Zona otraZona = new Zona(985,75554);
		infraccion.setZona(otraZona);
		
		assertFalse(infraccion.getZona() == zona);
		assertEquals(infraccion.getZona(), otraZona);
	}

}
