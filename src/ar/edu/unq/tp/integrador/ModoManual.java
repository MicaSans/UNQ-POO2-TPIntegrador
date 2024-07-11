package ar.edu.unq.tp.integrador;

public class ModoManual implements Modo {

	@Override
	public void notificarPosibleInicioDeEstacionamiento(AppConductor appConductor) {
		if(appConductor.getAlertaDesplazamientoActiva()) {
			System.out.println("Notificación: debe iniciar el estacionamiento.");
		}
	}

	@Override
	public void notificarPosibleFinDeEstacionamiento(AppConductor appConductor) {
		if(appConductor.getAlertaDesplazamientoActiva()) {
			System.out.println("Notificación: debe finalizar el estacionamiento.");
		}
	}

	@Override
	public void activarODesactivarNotificaciones(AppConductor appConductor) {
		appConductor.setAlertaDesplazamientoActiva();
	}

}
