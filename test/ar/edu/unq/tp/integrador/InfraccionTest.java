package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InfraccionTest {

	Infraccion infraccion;
	@Mock Zona zona;
	
	@BeforeEach
	void setUp() throws Exception {
		zona = mock(Zona.class);
		infraccion = new Infraccion("OE856WI", LocalDateTime.parse("2023-10-11T19:30:00.00"), zona);
	}

	@Test
	void testGetPatente() {
		//Se testea que se obtenga correctamente la patente dada
		assertEquals(infraccion.getPatente(), "OE856WI");
	}
	
	@Test
	void testSetPatente() {
		//Se testea que se cree correctamente la patente dada
		infraccion.setPatente("APD524");
		
		assertFalse(infraccion.getPatente().equals("OE856WI"));
		assertEquals(infraccion.getPatente(), "APD524");
	}

	@Test
	void testGetFechaYHora() {
		//Se testea que se obtenga correctamente la fecha y hora dada
		assertEquals(infraccion.getFechaYHora(), LocalDateTime.parse("2023-10-11T19:30:00.00"));
	}
	
	@Test
	void testSetFechaYHora() {
		//Se testea que se cree correctamente la fecha y hora actual
		LocalDateTime nuevaFecha = LocalDateTime.now();
		infraccion.setFechaYHora(nuevaFecha);
		
		assertEquals(infraccion.getFechaYHora(), nuevaFecha);
	}

	@Test
	void testGetZona() {
		//Se testea que se obtenga correctamente la zona dada
		assertEquals(infraccion.getZona(), zona);
	}
	
	@Test
	void testSetZona() {
		//Se testea que se cree correctamente la zona dada
		Zona otraZona = mock(Zona.class);
		infraccion.setZona(otraZona);
		
		assertFalse(infraccion.getZona().equals(zona));
		assertEquals(infraccion.getZona(), otraZona);
	}

}