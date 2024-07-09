package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class SEMTest {
	
	SEM sem;
	@Mock Celular celular;
	@Mock Infraccion infraccion;
	@Mock Zona zona;
	@Mock PuntoDeVenta puntoDeVenta;

	@BeforeEach
	void setUp() throws Exception {
		sem = new SEM();
		celular = mock(Celular.class);
		infraccion = mock(Infraccion.class);
		zona = mock(Zona.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
	}

	@Test
	void testGetPrecioPorHora() {
		//Se testea que se obtenga correctamente el precio por hora de estacionamiento ($40 actualmente)
		assertTrue(sem.getPrecioPorHora().equals(40));
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
		Infraccion infraccionNueva = sem.generarInfraccion("ARF845", zona);
		
		assertEquals(infraccionNueva.getPatente(), "ARF845");
		assertEquals(infraccionNueva.getZona(), zona);
		assertTrue(sem.getInfracciones().contains(infraccionNueva));
	}
}
