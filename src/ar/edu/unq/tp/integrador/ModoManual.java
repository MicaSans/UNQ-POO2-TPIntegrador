package ar.edu.unq.tp.integrador;

public class ModoManual implements Modo {

	@Override
	public void notificarPosibleInicioDeEstacionamiento(AppConductor app) {
		//En modo automático no se realiza ninguna acción
	}

	@Override
	public void notificarPosibleFinDeEstacionamiento(AppConductor app) {
		app.finalizarEstacionamiento();
	}

	@Override
	public void activarODesactivarNotificaciones(AppConductor app) {
		// TODO Auto-generated method stub
		
	}

}
