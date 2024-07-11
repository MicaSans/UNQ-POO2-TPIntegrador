package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppConductorTest {
	AppConductor appConductor;
	SEM sem;
	Celular celular;
	String patente;
	
	@BeforeEach
	public void setup() {
		sem = mock(SEM.class);
		celular = mock(Celular.class);
		appConductor = new AppConductor(sem, celular);
		patente = "LLL888";
	}
	
	@Test
	void testIniciarEstacionamiento() {
		appConductor.iniciarEstacionamiento(patente);
	}
	
	@Test
	void testFinalizarEstacionamiento() {
		appConductor.iniciarEstacionamiento(patente);
		
	}

}
