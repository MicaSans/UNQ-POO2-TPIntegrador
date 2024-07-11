package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SEM {
	
	private final LocalDateTime horarioInicio = LocalDateTime.now().with(LocalTime.of(20, 0));
	private final LocalDateTime horarioFin = LocalDateTime.now().with(LocalTime.of(7, 0));
	private final Integer precioPorHora = 40;
	private List<Compra> compras;
	private List<Estacionamiento> estacionamientos;
	private List<Zona> zonas;
	private List<Infraccion> infracciones;
	private List<Alertable> suscriptoresDeAlertas;
	
	public SEM() {
        this.compras = new ArrayList<Compra>();
        this.estacionamientos = new ArrayList<Estacionamiento>();
        this.zonas = new ArrayList<Zona>();
        this.infracciones = new ArrayList<Infraccion>();
        this.suscriptoresDeAlertas = new ArrayList<Alertable>();
    }
	
	public LocalDateTime getHorarioInicio() {
		return horarioInicio;
	}
	
	public LocalDateTime getHorarioFin() {
		return horarioFin;
	}
	
	public int getPrecioPorHora() {
		//Se obtiene el precio por hora de estacionamiento (actualmente $40.-)
		return precioPorHora;
	}

	public int saldoParaElHorario(LocalDateTime horaInicio, LocalDateTime horarioFin) {
		return (horarioFin.getHour() - horaInicio.getHour()) * precioPorHora;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	
	public List<Zona> getZonas() {
		//Se obtiene la lista con las zonas registradas en el SEM
		return zonas;
	}
	
	public List<Infraccion> getInfracciones() {
		//Se obtiene la lista con las infracciones registradas en el SEM
		return infracciones;
	}
	
	private <T> void registrarElemento(List<T> lista, T elemento, String mensajeError) {
        if (lista.stream().anyMatch(e -> e.equals(elemento))) {
        	throw new IllegalArgumentException(mensajeError);
        } else {
            lista.add(elemento);
        }
    }

    public void registrarZona(Zona zona) {
    	//Se registra la zona dada a la lista de zonas del SEM y en caso que ya esté registrada se lanza una excepción
        registrarElemento(zonas, zona, "La zona ya está registrada.");
    }

    public void registrarCompra(Compra compra) {
        registrarElemento(compras, compra, "La compra ya está registrada.");
    }

    public void registrarEstacionamiento(Estacionamiento estacionamiento) {
        registrarElemento(estacionamientos, estacionamiento, "El estacionamiento ya está registrado.");
    }
    
    public void registrarInfraccion(Infraccion infraccion) {
    	//Se registra la infracción dada a la lista de infracciones del SEM y en caso que ya esté registrada se lanza una excepción
        registrarElemento(infracciones, infraccion, "La infracción ya está registrada.");
    }
    
	public void registrarCredito(Celular celular, Integer credito) {
		celular.cargarSaldo(credito);
		this.alertarRecargaDeCredito();
	}
	
	public void finalizarEstacionamientosVigentes() {
		this.estacionamientos.stream().filter(e -> e.estaVigente()).forEach(e -> e.setHoraFin(LocalDateTime.now()));
	}
	
	public void iniciarEstacionamientoApp(String patente, AppConductor appConductor) {
		Estacionamiento estacionamientoApp = new EstacionamientoApp(appConductor.getCelular(), patente, horarioInicio);
		this.registrarEstacionamiento(estacionamientoApp);
		this.alertarInicioEstacionamiento();
	}
	
	public void iniciarEstacionamientoPtoVta(String patente, Integer cantidadDeHoras) {
		Estacionamiento estacionamientoPuntual = new EstacionamientoPuntual(patente, horarioInicio, horarioFin);
		this.registrarEstacionamiento(estacionamientoPuntual);
		this.alertarInicioEstacionamiento();
	}
	
	/*
	 * Finaliza el Estacionamiento de un numero de celular en concreto.
	 * Es un método que nos sugirió Butti, para cuando se cree una instancia de EstacionamientoApp,
	 * supongamos que se llama a este método para ponerle un horario de fin de estacionamiento.
	 */
	//TODO: revisar
	public void finalizarEstacionamientoDeApp(String celular) {
		List<Estacionamiento> estacionamientosApp = this.estacionamientos.stream().filter(e -> e.esEstacionamientoApp()).toList();
		Optional<Estacionamiento> estacionamientoBuscado = estacionamientosApp.stream().filter(e -> e.getNroCelular().equals(celular)).findFirst();
		Estacionamiento estacionamientoEncontrado = estacionamientoBuscado.get();
		estacionamientoEncontrado.setHoraFin(LocalDateTime.now());
		this.alertarFinEstacionamiento();
	}
	
	private Boolean tieneEstacionamientoVigente(String patente) {
		Estacionamiento estacionamiento = null;
		for(Estacionamiento e : this.getEstacionamientos()) {
			if(e.getPatente().equals(patente)) {
				estacionamiento = e;
			}
		}
		return estacionamiento.estaVigente();
	}
	
	public Infraccion generarInfraccion(String patente, Zona zona, String idInspector) {
		//Se genera una nueva infracción, registrándola en la lista de infracciones
		Infraccion nuevaInfraccion = new Infraccion(patente, LocalDateTime.now(), zona, idInspector);
		this.registrarInfraccion(nuevaInfraccion);
		return nuevaInfraccion;
	}
	
	public void addSuscriptorDeAlerta(Alertable suscriptor) {
		this.suscriptoresDeAlertas.add(suscriptor);
	}
	
	public void removeSuscriptorDeAlerta(Alertable suscriptor) {
		this.suscriptoresDeAlertas.remove(suscriptor);
	}
	
	private void alertarInicioEstacionamiento() {
		suscriptoresDeAlertas.stream().forEach(s -> s.inicioEstacionamiento());
	}
	
	private void alertarFinEstacionamiento() {
		suscriptoresDeAlertas.stream().forEach(s -> s.finEstacionamiento());
	}
	
	private void alertarRecargaDeCredito() {
		suscriptoresDeAlertas.stream().forEach(s -> s.recargaDeCredito());
	}
}	
