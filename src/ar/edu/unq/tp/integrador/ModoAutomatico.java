package ar.edu.unq.tp.integrador;

public class ModoAutomatico implements Modo {

	@Override
	public void driving(AppConductor appConductor) {
		//En modo automático no se realiza ninguna acción
	}

	@Override
	public void walking(AppConductor appConductor) {
		appConductor.finalizarEstacionamiento(appConductor.getNumeroDeCelular());
	}

	@Override
	public void iniciarEstacionamiento(AppConductor appConductor, String patente) {
		//En modo automático no se realiza ninguna acción
	}

	@Override
	public void finalizarEstacionamiento(AppConductor appConductor) {
		appConductor.finalizarEstacionamiento(appConductor.getNumeroDeCelular());
	}

}
