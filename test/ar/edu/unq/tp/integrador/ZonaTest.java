package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZonaTest {

	private SEM sem;
	private Zona zona;
	private PuntoDeVenta puntoDeVenta;
	
	@BeforeEach
	void setUp() throws Exception {
		sem = new SEM();
		zona = new Zona(7854, 948632);
		puntoDeVenta = new PuntoDeVenta("20-30659847-5", sem);
	}

	@Test
	void testRegistrarZona() {
		//Se testea que la zona se registre correctamente en la lista que tiene el SEM con sus zonas
		sem.registrarZona(zona);
		
		assertEquals(sem.getZonas().size(), 1);
		assertTrue(sem.getZonas().contains(zona));
	}
	
	@Test
	void testAgregarPtoDeVenta() {
		//Se testea que se agregue correctamente un punto de venta a la zona
		zona.agregarPtoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getPuntosDeVenta().size(), 1);
		assertTrue(zona.getPuntosDeVenta().contains(puntoDeVenta));
	}
	
	@Test
	void testIdZona() {
		assertEquals(zona.getIdZona(), 7854);
	}
	
	@Test
	void testIdInspector() {
		assertEquals(zona.getIdInspector(), 948632);
	}

}
