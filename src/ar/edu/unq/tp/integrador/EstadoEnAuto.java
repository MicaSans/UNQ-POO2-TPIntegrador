package ar.edu.unq.tp.integrador;

public class EstadoEnAuto extends Estado {
	
	public void driving(AppConductor appConductor) {
		appConductor.notificarPosibleFinDeEstacionamiento();
	}

	public void finalizarEstacionamiento(AppConductor appConductor) {
		appConductor.finalizarEstacionamientoSEM();
		appConductor.setEstado(new EstadoAPie());
	}
	
}
