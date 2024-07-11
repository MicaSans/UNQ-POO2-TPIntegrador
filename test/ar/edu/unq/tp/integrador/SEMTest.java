package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class SEMTest {
	
	SEM sem;
	@Mock Celular celular;
	@Mock Infraccion infraccion;
	@Mock Zona zona;
	@Mock PuntoDeVenta puntoDeVenta;
	@Mock Compra compra;
	@Mock Estacionamiento estacionamiento;
	@Mock AppConductor appConductor;

	@BeforeEach
	void setUp() throws Exception {
		sem = new SEM();
		celular = mock(Celular.class);
		infraccion = mock(Infraccion.class);
		zona = mock(Zona.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
		compra = mock(Compra.class);
		estacionamiento = mock(Estacionamiento.class);
		appConductor = mock(AppConductor.class);
	}

	@Test
	void testGetPrecioPorHora() {
		//Se testea que se obtenga correctamente el precio por hora de estacionamiento ($40 actualmente)
		assertTrue(sem.getPrecioPorHora() == 40);
	}

	@Test
	void testGetZonas() {
		//Se testea que se obtenga correctamente la lista de zonas con las que se agreguen
		assertEquals(sem.getZonas().size(), 0);
		
		sem.registrarZona(zona);
		Zona otraZona = mock(Zona.class);
		sem.registrarZona(otraZona);
		
		assertEquals(sem.getZonas().size(), 2);
		assertTrue(sem.getZonas().contains(zona));
		assertTrue(sem.getZonas().contains(otraZona));
	}

	@Test
	void testGetInfracciones() {
		//Se testea que se obtenga correctamente la lista de infracciones con las que se agreguen
		assertEquals(sem.getInfracciones().size(), 0);
		
		sem.registrarInfraccion(infraccion);
		Infraccion otraInfraccion = mock(Infraccion.class);
		sem.registrarInfraccion(otraInfraccion);
		
		assertEquals(sem.getInfracciones().size(), 2);
		assertTrue(sem.getInfracciones().contains(infraccion));
		assertTrue(sem.getInfracciones().contains(otraInfraccion));
	}
	
	@Test
	void testRegistrarZona() {
		//Se testea que la zona se registre correctamente en la lista que tiene el SEM con sus zonas
		sem.registrarZona(zona);
		
		assertEquals(sem.getZonas().size(), 1);
		assertTrue(sem.getZonas().contains(zona));
	}
	
	@Test
	void testRegistrarMismaZonaDosVeces() {
		//Se testea que se lance la excepción adecuada cuando se intente registrar nuevamente una zona ya registrada en el SEM
		sem.registrarZona(zona);
		
		assertThrows(IllegalArgumentException.class, () -> sem.registrarZona(zona), "La zona ya está registrada.");
	}
	
	@Test
	void testRegistrarInfraccion() {
		//Se testea que la infraccion se registre correctamente en la lista de infracciones que tiene el SEM
		sem.registrarInfraccion(infraccion);
		
		assertEquals(sem.getInfracciones().size(), 1);
		assertTrue(sem.getInfracciones().contains(infraccion));
	}
	
	@Test
	void testRegistrarMismaInfraccionDosVeces() {
		//Se testea que se lance la excepción adecuada cuando se intente registrar nuevamente una infraccion ya registrada en el SEM
		sem.registrarInfraccion(infraccion);
		
		assertThrows(IllegalArgumentException.class, () -> sem.registrarInfraccion(infraccion), "La infracción ya está registrada.");
	}
	
	@Test
	void testGenerarInfraccion() {
		//Se testea que se genere correctamente una infracción y al hacerlo se incluya en la lista de infracciones del SEM como corresponde
		Infraccion infraccionNueva = sem.generarInfraccion("ARF845", zona, "8597");
		
		assertEquals(infraccionNueva.getPatente(), "ARF845");
		assertEquals(infraccionNueva.getZona(), zona);
		assertEquals(infraccionNueva.getIdInspector(), "8597");
		assertTrue(sem.getInfracciones().contains(infraccionNueva));
	}
	
	@Test
	void testGetHorarioInicio() {
		assertEquals(LocalTime.of(7, 0), sem.getHoraInicioEstacionamientoMedido());
	}
	
	@Test
	void testGetHorarioFin() {
		assertEquals(LocalTime.of(20, 0), sem.getHoraFinEstacionamientoMedido());
	}
	
	@Test
	void testGetCompras() {
		//Se testea que se obtenga correctamente la lista de compras con las que se agreguen
		assertEquals(sem.getCompras().size(), 0);
				
		sem.registrarCompra(compra);
		Compra otraCompra = mock(Compra.class);
		sem.registrarCompra(otraCompra);
				
		assertEquals(sem.getCompras().size(), 2);
		assertTrue(sem.getCompras().contains(compra));
		assertTrue(sem.getCompras().contains(otraCompra));
	}
	
	@Test
	void testGetEstacionamientos() {
		//Se testea que se obtenga correctamente la lista de estacionamientos con los que se agreguen
		assertEquals(sem.getEstacionamientos().size(), 0);
				
		sem.registrarEstacionamiento(estacionamiento);
		Estacionamiento otroEstacionamiento = mock(Estacionamiento.class);
		sem.registrarEstacionamiento(otroEstacionamiento);
				
		assertEquals(sem.getEstacionamientos().size(), 2);
		assertTrue(sem.getEstacionamientos().contains(estacionamiento));
		assertTrue(sem.getEstacionamientos().contains(otroEstacionamiento));
	}
	
	@Test
	void testRegistrarCompra() {
		//Se testea que la compra se registre correctamente en la lista que tiene el SEM con sus compras
		sem.registrarCompra(compra);
		
		assertEquals(sem.getCompras().size(), 1);
		assertTrue(sem.getCompras().contains(compra));
	}
	
	@Test
	void testRegistrarMismaCompraDosVeces() {
		//Se testea que se lance la excepción adecuada cuando se intente registrar nuevamente una compra ya registrada en el SEM
		sem.registrarCompra(compra);
		
		assertThrows(IllegalArgumentException.class, () -> sem.registrarCompra(compra), "La compra ya está registrada.");
	}
	
	@Test
	void testRegistrarEstacionamiento() {
		//Se testea que el estacionamiento se registre correctamente en la lista que tiene el SEM con sus estacionamientos
		sem.registrarEstacionamiento(estacionamiento);
		
		assertEquals(sem.getEstacionamientos().size(), 1);
		assertTrue(sem.getEstacionamientos().contains(estacionamiento));
	}
	
	@Test
	void testRegistrarMismoEstacionamientoDosVeces() {
		//Se testea que se lance la excepción adecuada cuando se intente registrar nuevamente un estacionamiento ya registrado en el SEM
		sem.registrarEstacionamiento(estacionamiento);
		
		assertThrows(IllegalArgumentException.class, () -> sem.registrarEstacionamiento(estacionamiento), "El estacionamiento ya está registrada.");
	}
	
	@Test
	void testRegistrarCredito() {
		when(celular.getSaldo()).thenReturn(0);
		sem.registrarCredito(celular, 500);
		
		verify(celular).cargarSaldo(500);
	}
	
	@Test
	void testFinalizarEstacionamientosVigentes() {
		Estacionamiento otroEstacionamiento = mock(Estacionamiento.class);
		
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(otroEstacionamiento.estaVigente()).thenReturn(false);
		
		sem.registrarEstacionamiento(estacionamiento);
		sem.registrarEstacionamiento(otroEstacionamiento);
		sem.finalizarEstacionamientosVigentes();
		
		verify(estacionamiento).setHoraFin(any(LocalDateTime.class));
		verify(otroEstacionamiento, never()).setHoraFin(any(LocalDateTime.class));
		
	}
	
	@Test
	void testIniciarEstacionamientoApp() {
		
	}
	
	@Test
	void testIniciarEstacionamientoPtoVta() {
		
	}
	
	@Test
	void testFinalizarEstacionamientoDeApp() {
		
	}
	
	@Test
	void testTieneEstacionamientoVigente() {
		sem.registrarEstacionamiento(estacionamiento);
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.getPatente()).thenReturn("IEK841");
		
		assertTrue(sem.tieneEstacionamientoVigente(estacionamiento.getPatente()));
	}
	
	@Test
	void testAddSuscriptorDeAlerta() {
		
	}
	
	@Test
	void testRemoveSuscriptorDeAlerta() {
		
	}
	
	@Test
	void testAlertarInicioEstacionamiento() {
		
	}
	
	@Test
	void testAlertarFinEstacionamiento() {
		
	}
	
	@Test
	void testAlertarRecargaDeCredito() {
		
	}
}
