package ar.edu.unq.tp.integrador;

public interface Modo {

	void driving(AppConductor appConductor);

	void walking(AppConductor appConductor);

	void iniciarEstacionamiento(AppConductor appConductor, String patente);

	void finalizarEstacionamiento(AppConductor appConductor);
		
}
