package ar.edu.unq.tp.integrador;

public interface Modo {

	public void notificarPosibleInicioDeEstacionamiento(AppConductor app);
	
	public void notificarPosibleFinDeEstacionamiento(AppConductor app);
	
	public void activarODesactivarNotificaciones(AppConductor app);
		
}
