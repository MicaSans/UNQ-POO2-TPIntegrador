package ar.edu.unq.tp.integrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class AppConductorTest {
	
	AppConductor appConductor;
	SEM sem;
	Celular celular;
	String patente;
	Modo modoManual;
	Modo modoAutomatico;
	Estado estadoEnAuto;
	Estado estadoAPie;
	
	@BeforeEach
	public void setup() {
		sem = mock(SEM.class);
		celular = mock(Celular.class);
		modoManual = mock(ModoManual.class);
		modoAutomatico = mock(ModoAutomatico.class);
		estadoEnAuto = mock(EstadoEnAuto.class);
		estadoAPie = mock(EstadoAPie.class);
		patente = "LLL888";
		appConductor = new AppConductor(sem, celular, patente);
	}
	
	@Test
	void testConductorIniciaEstacionamientoManualmente() {
		appConductor.iniciarEstacionamiento();
		when(celular.getSaldo()).thenReturn(600);
		
		verify(sem, times(1)).iniciarEstacionamiento(patente, celular);
	}
	
	@Test
	void testConductorFinalizaEstacionamientoManualmente() {
		appConductor.iniciarEstacionamiento();
		appConductor.finalizarEstacionamiento();
		
		verify(sem, times(1)).finalizarEstacionamiento(appConductor.getNumeroDeCelular());
	}
	
	@Test
	void testEstablecerModoDeUso() {
		appConductor.setModo(modoAutomatico);
		
		assertEquals(appConductor.getModo(), modoAutomatico);
	}
	
	@Test
	void testVerificarSiLasAlertasEstanActivadas() {
		assertEquals(appConductor.getAlertaDesplazamientoActiva(), true);
	}
	
	@Test
	void testPosibleInicioDeEstacionamientoConAppEnModoAutomatico() {
		appConductor.setModo(modoAutomatico);
		appConductor.notificarPosibleInicioDeEstacionamiento();
		
		verify(modoAutomatico).notificarPosibleInicioDeEstacionamiento(appConductor);
		
	}
	
	@Test
	void testPosibleFinDeEstacionamientoConAppEnModoAutomatico() {
		appConductor.setModo(modoAutomatico);
		appConductor.notificarPosibleFinDeEstacionamiento();
		
		verify(modoAutomatico).notificarPosibleFinDeEstacionamiento(appConductor);
	}
	
	@Test
	void testPosibleInicioDeEstacionamientoConAppEnModoManual() {
		appConductor.setModo(modoManual);
		appConductor.notificarPosibleInicioDeEstacionamiento();
		
		verify(modoManual).notificarPosibleInicioDeEstacionamiento(appConductor);
		
	}
	
	@Test
	void testPosibleFinDeEstacionamientoConAppEnModoManual() {
		appConductor.setModo(modoManual);
		appConductor.notificarPosibleFinDeEstacionamiento();
		
		
		verify(modoManual).notificarPosibleFinDeEstacionamiento(appConductor);
	}
	
	@Test
	void testDesactivarNotificacionesEnModoManual() {
		appConductor.activarODesactivarNotificaciones();
		
		assertFalse(appConductor.getAlertaDesplazamientoActiva()); //como inicia en modo manual y con alertas activadas, debería cambiar a false al llamar a activarODesactivarNotificaciones()
	}
	
	@Test
	void testActivarODesactivarNotificacionesEnModoAutomaticoNoHaceNada() {
		appConductor.setModo(modoAutomatico);
		appConductor.activarODesactivarNotificaciones();
		
		assertTrue(appConductor.getAlertaDesplazamientoActiva()); //verifico si es true porque inicialmente comienzan en true
	}
	
	
	@Test
	void testObtenerPatente() {
		assertEquals(appConductor.getPatente(), patente);
	}
	
	@Test
	void testIniciarEstacionamientoEnSEM() {
		appConductor.iniciarEstacionamientoSEM();
		
		verify(sem, times(1)).iniciarEstacionamiento(patente, celular);
	}
	
	@Test
	void testFinalizarEstacionamientoEnSEM() {
		appConductor.finalizarEstacionamientoSEM();
		
		verify(sem, times(1)).finalizarEstacionamiento(appConductor.getNumeroDeCelular());
	}
	
	@Test
	void testEnvioMensajeWalkingConAppEnEstadoAPieYModoManual() {
		//por default está en estado a pie
		appConductor.setModo(modoManual);
		appConductor.walking();
		
		verify(modoManual, times(1)).notificarPosibleInicioDeEstacionamiento(appConductor);
	}
	

}
