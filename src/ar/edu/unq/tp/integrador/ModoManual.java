package ar.edu.unq.tp.integrador;

public class ModoManual implements Modo {

	@Override
	public void driving(AppConductor appConductor) {
		//En modo manual no se realiza ninguna acción
	}

	@Override
	public void walking(AppConductor appConductor) {
		//En modo manual no se realiza ninguna acción
	}
	
	//TODO: revisar
	@Override
	public void iniciarEstacionamiento(AppConductor appConductor, String patente) {
		appConductor.iniciarEstacionamiento(patente);
	}

	@Override
	public void finalizarEstacionamiento(AppConductor appConductor) {
		appConductor.getSem().finalizarEstacionamiento(appConductor.getNumeroDeCelular());
	}

}
