package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoDeVentaTest {

	private PuntoDeVenta puntoDeVenta;
	private SEM sem;
	
	@BeforeEach
	void setUp() throws Exception {
		sem = new SEM();
		puntoDeVenta = new PuntoDeVenta("30-69857124-8", sem);
	}

	@Test
	void testGenerarCompraPuntual() {
		//Se testea que la compra puntual se registre en el SEM cuando se genera
		String patente = "OPW857";
		Integer cantidadDeHoras = 2;
		CompraPuntual compraPuntual = puntoDeVenta.generarCompraPuntual(patente, cantidadDeHoras);
		
		assertEquals(sem.getCompras().size(), 1);
		assertTrue(sem.getCompras().contains(compraPuntual));
	}
	
	@Test
	void testGenerarCompraCelular() {
		String celular = "15-5489-7852";
		Integer monto = 200;
		CompraCelular compraCelular = puntoDeVenta.generarCompraCelular(celular, monto);
		
		assertEquals(sem.getCompras().size(), 1);
		assertTrue(sem.getCompras().contains(compraCelular));
	}
	
	@Test
	void testGetCuit() {
		assertEquals(puntoDeVenta.getCuit(), "30-69857124-8");
	}

}
