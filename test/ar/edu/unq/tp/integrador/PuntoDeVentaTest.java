package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

class PuntoDeVentaTest {

	PuntoDeVenta puntoDeVenta;
	@Mock SEM sem;
	
	@BeforeEach
	void setUp() throws Exception {
		sem = mock(SEM.class);
		puntoDeVenta = new PuntoDeVenta("30-69857124-8", sem);
	}

	@Test
	void testGenerarCompraPuntual() {
		//Se testea que la compra puntual se registre en el SEM cuando se genera
		String patente = "OPW857";
		Integer cantidadDeHoras = 2;
		
		puntoDeVenta.generarCompraPuntual(patente, cantidadDeHoras);
		ArgumentCaptor<CompraPuntual> compraPuntual = ArgumentCaptor.forClass(CompraPuntual.class);
		
		//Verifico que la compra se registre en el SEM
		verify(sem,times(1)).registrarCompra(compraPuntual.capture());
	}
	
	@Test
	void testGenerarCompraCelular() {
		//Se testea que la compra celular se registre en el SEM cuando se genera
		Celular celular = mock(Celular.class);
		Integer monto = 200;
		
		CompraCelular compraCelular = puntoDeVenta.generarCompraCelular(celular, monto);
		
		//Verifico que la compra se registre en el SEM
		verify(sem, times(1)).registrarCompra(compraCelular);
		//Verifico que se cargue correctamente el crédito
		verify(sem, times(1)).registrarCredito(celular,monto);
	}
	
	@Test
	void testGetCuit() {
		//Se testea que se obtenga correctamente el cuit dado
		assertEquals(puntoDeVenta.getCuit(), "30-69857124-8");
	}

}
