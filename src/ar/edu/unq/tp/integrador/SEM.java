package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	// TODO codigo repetido en el registro de zona, estacionamiento, compra e infraccion
	/*
	public void registrarZona(Zona zona) {
		if (!this.estaLaZonaRegistrada(zona)) {
			zonas.add(zona);
		}else {
			throw new IllegalArgumentException("La zona ya está registrada.");
		}
			
	}

	private Boolean estaLaZonaRegistrada(Zona zona) {
		return zonas.stream().anyMatch(z -> z.equals(zona));
	}
	
	public void registrarCompra(Compra compra) {
		if (!this.estaLaCompraRegistrada(compra)) {
			compras.add(compra);
		}else {
			throw new IllegalArgumentException("La compra ya está registrada.");
		}
	}

	private Boolean estaLaCompraRegistrada(Compra compra) {
		return compras.stream().anyMatch(c -> c.equals(compra));
	}
	
	private void registrarEstacionamiento(Estacionamiento estacionamiento) {
		if (!this.estaElEstacionamientoRegistrado(estacionamiento)) {
			estacionamientos.add(estacionamiento);
		}else {
			throw new IllegalArgumentException("El estacionamiento ya está registrada.");
		}
	}

	private Boolean estaElEstacionamientoRegistrado(Estacionamiento estacionamiento) {
		return estacionamientos.stream().anyMatch(e -> e.equals(estacionamiento));
	}
	private void registrarInfraccion(Infraccion infraccion) {
		if (!this.estaLaInfraccionRegistrada(infraccion)) {
			infracciones.add(infraccion);
		}else {
			throw new IllegalArgumentException("La infraccion ya está registrada.");
		}
	}

	private boolean estaLaInfraccionRegistrada(Infraccion infraccion) {
		return infracciones.stream().anyMatch(i -> i.equals(infraccion));
	}*/
	
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
    
	public void registrarCredito(Celular numeroCelular, Integer credito) {
	}
	
	public void finalizarEstacionamientosVigentes() {
	//TODO: finaliza todos los estacionamiemtos vigentes.
		// debería tener una exepción para saber si es la hora fin de estacionamiento medido?
	}
	
	public String iniciarEstacionamiento(String patente, Celular celular) {
		if (this.tieneCreditoSuficiente(celular)) {
			LocalDateTime horaInicio = LocalDateTime.now();
			Estacionamiento estacionamiento = new EstacionamientoApp(celular, patente, horaInicio);
			this.registrarEstacionamiento(estacionamiento);
			this.alertarInicioEstacionamiento();
			return "Hora inicio: " + horaInicio + " Hora máxima de fin: " + this.maxHoraFinQuePuedeAbonar(celular, horaInicio);
		}else {
			throw new IllegalArgumentException("No tiene saldo suficiente para iniciar el estacionamiento.");
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
	
	
	public void iniciarEstacionamientoPtoVta(String patente, Integer cantidadDeHoras) {
		//Leer consignas tp.
	}
	
	
	/*
	 * Finaliza el Estacionamiento de un numero de celular en concreto.
	 * Es un método que nos sugirió Butti, para cuando se cree una instancia de EstacionamientoApp,
	 * supongamos que se llama a este método para ponerle un horario de fin de estacionamiento.
	 */
	public void finalizarEstacionamientoDeApp(String numeroDeCelular) {
		
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
}	
