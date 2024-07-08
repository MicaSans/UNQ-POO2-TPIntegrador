package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ZonaTest {

	Zona zona;
	@Mock PuntoDeVenta puntoDeVenta;
	
	@BeforeEach
	void setUp() throws Exception {
		zona = new Zona(7854, 948632);
		puntoDeVenta = mock(PuntoDeVenta.class);
	}
	
	@Test
	void testAgregarPtoDeVenta() {
		//Se testea que se agregue correctamente un punto de venta a la zona
		zona.agregarPtoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getPuntosDeVenta().size(), 1);
		assertTrue(zona.getPuntosDeVenta().contains(puntoDeVenta));
	}
	
	@Test
	void testGetIdZona() {
		//Se testea que se obtenga el IdZona correcto
		assertEquals(zona.getIdZona(), 7854);
	}
	
	@Test
	void testGetIdInspector() {
		//Se testea que se obtenga el IdInspector correcto
		assertEquals(zona.getIdInspector(), 948632);
	}

	@Test
	void testGetPuntosDeVenta() {
		//Se testea que se obtenga la lista de puntos de venta con los que agregue correctamente
		assertEquals(zona.getPuntosDeVenta().size(), 0);
		
		zona.agregarPtoDeVenta(puntoDeVenta);
		PuntoDeVenta otroPuntoDeVenta = mock(PuntoDeVenta.class);
		zona.agregarPtoDeVenta(otroPuntoDeVenta);
		
		assertEquals(zona.getPuntosDeVenta().size(), 2);
		assertTrue(zona.getPuntosDeVenta().contains(puntoDeVenta));
		assertTrue(zona.getPuntosDeVenta().contains(otroPuntoDeVenta));
	}
}
