package ar.edu.unq.tp.integrador;

public abstract class Estado {
	
	public void driving(AppConductor appConductor) {}

	public void walking(AppConductor appConductor) {}

	public void iniciarEstacionamiento(AppConductor appConductor) {}

	public void finalizarEstacionamiento(AppConductor appConductor) {}
	
}
