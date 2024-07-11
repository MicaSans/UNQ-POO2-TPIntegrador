package ar.edu.unq.tp.integrador;

public class ModoAutomatico implements Modo {

	@Override
	public void notificarPosibleInicioDeEstacionamiento(AppConductor appConductor) {
		appConductor.iniciarEstacionamientoSEM();
		System.out.println("Notificación: Se inicio el estacionamiento de forma automatica.");
	}

	@Override
	public void notificarPosibleFinDeEstacionamiento(AppConductor appConductor) {
		appConductor.finalizarEstacionamientoSEM();
	}

	@Override
	public void activarODesactivarNotificaciones(AppConductor app) {}

}
