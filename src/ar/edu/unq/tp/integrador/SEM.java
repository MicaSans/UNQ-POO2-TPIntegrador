package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SEM {
	
	private final LocalTime horaInicioEstacionamientoMedido = LocalTime.of(07, 0);
	private final LocalTime horaFinEstacionamientoMedido = LocalTime.of(20, 0);
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
	
	// Getters

	public LocalTime getHoraInicioEstacionamientoMedido() {
		return horaInicioEstacionamientoMedido;
	}
	
	public LocalTime getHoraFinEstacionamientoMedido() {
		return horaFinEstacionamientoMedido;
	}
	
	public Integer getPrecioPorHora() {
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
		this.estacionamientos.stream()
		.filter(e -> e.estaVigente())
		.forEach(e -> e.setHoraFin(LocalDateTime.now().with(this.getHoraFinEstacionamientoMedido())));
	}
	
	/**
	 * Inicia un estacionamiento solicitado desde la app del conductor
	 * 
	 * @patente la patente del vehiculo estacionado
	 * @celular el celular desde el cual se solicita el inicio del estacionamiento
	 */
	public void iniciarEstacionamiento(String patente, Celular celular) {
		if (this.tieneCreditoSuficiente(celular)) {
			LocalDateTime horaInicio = LocalDateTime.now();
			Estacionamiento estacionamiento = new EstacionamientoApp(celular, patente, horaInicio);
			this.registrarEstacionamiento(estacionamiento);
			this.alertarInicioEstacionamiento();
			System.out.println( "Hora inicio: " + horaInicio + " Hora máxima de fin: " + this.maxHoraFinQuePuedeAbonar(celular, horaInicio));
		}else {
			throw new IllegalArgumentException("Saldo insuficiente. Estacionamiento no permitido");
		}
	}
	
	private LocalTime maxHoraFinQuePuedeAbonar(Celular celular, LocalDateTime horaInicio) {
		int maxCantHorasSegunSaldo = celular.getSaldo() / this.getPrecioPorHora();
		LocalDateTime maxHoraDeFin = horaInicio.plusHours(maxCantHorasSegunSaldo);
		return maxHoraDeFin.toLocalTime();
	}
	
	private Boolean tieneCreditoSuficiente(Celular celular) {
		return celular.getSaldo() >= this.getPrecioPorHora();
	}
	
	/**
	 * Inicia un estacionamiento por compra puntual
	 * 
	 * @patente la patente del vehiculo estacionado
	 * @horaInicio la hora de inicio del estacionamiento
	 * @horaFin la hora de fin del estacionamiento
	 */
	public void iniciarEstacionamiento(String patente, LocalDateTime horaInicio, LocalDateTime horaFin) {
		Estacionamiento estacionamientoPuntual = new EstacionamientoPuntual(patente, horaInicio, horaFin);
		this.registrarEstacionamiento(estacionamientoPuntual);
		this.alertarInicioEstacionamiento();
	}
	
	/*
	 * Finaliza el Estacionamiento asociado al numero de celular pasado por parámetro.
	 * 
	 * @celular el número de celular desde el cuál se inicio el estacinamiento
	 */
	//TODO: revisar
	public void finalizarEstacionamiento(String celular) {
		Estacionamiento estacionamientoApp = buscarEstacionamientoApp(celular);
		estacionamientoApp.setHoraFin(LocalDateTime.now());
		estacionamientoApp.cobrarEstacionamiento(this.getPrecioPorHora());
		this.alertarFinEstacionamiento();
	}
	
	private Estacionamiento buscarEstacionamientoApp(String celular) {
		List<Estacionamiento> estacionamientosApp = this.estacionamientos.stream().filter(e -> e instanceof EstacionamientoApp).toList();
		Optional<Estacionamiento> estacionamientoBuscado = estacionamientosApp.stream().filter(e -> e.getNroCelular().equals(celular)).findFirst();
		
		return estacionamientoBuscado.get();
	}
	
	public Boolean tieneEstacionamientoVigente(String patente) {
		Estacionamiento estacionamiento = null;
		for(Estacionamiento e : this.getEstacionamientos()) {
			if(e.getPatente().equals(patente)) {
				estacionamiento = e;
			}	
		}
		return estacionamiento.estaVigente();
	}
	
	public Infraccion generarInfraccion(String patente, Zona zona) {
		//Se genera una nueva infracción, registrándola en la lista de infracciones
		Infraccion nuevaInfraccion = new Infraccion(patente, LocalDateTime.now(), zona);
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
