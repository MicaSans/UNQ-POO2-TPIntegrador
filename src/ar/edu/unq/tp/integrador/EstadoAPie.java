package ar.edu.unq.tp.integrador;

public class EstadoAPie extends Estado {
	
	@Override
	public void walking(AppConductor appConductor) {
		appConductor.notificarPosibleInicioDeEstacionamiento();
	}
	
	@Override
	public void iniciarEstacionamiento(AppConductor appConductor) {
		appConductor.iniciarEstacionamientoSEM();
		appConductor.setEstado(new EstadoEnAuto());
	}
	
}
