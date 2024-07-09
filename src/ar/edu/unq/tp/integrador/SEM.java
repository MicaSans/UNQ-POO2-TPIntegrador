package ar.edu.unq.tp.integrador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SEM {
	
	private HashMap<Celular, EstacionamientoApp> mapCelularConEstacionamiento;
	private HashMap<Celular, Integer> mapCelularConCredito;
	private final LocalDateTime horarioInicio = LocalDateTime.now().with(LocalTime.of(20, 0));
	private final LocalDateTime horarioFin = LocalDateTime.now().with(LocalTime.of(7, 0));
	private final Integer precioPorHora = 40;
	private List<Compra> compras;
	private List<Estacionamiento> estacionamientos;
	private List<Zona> zonas;
	private List<Infraccion> infracciones;
	
	public SEM() {
        this.mapCelularConEstacionamiento = new HashMap<Celular, EstacionamientoApp>();
        this.mapCelularConCredito = new HashMap<Celular, Integer>();
        this.compras = new ArrayList<Compra>();
        this.estacionamientos = new ArrayList<Estacionamiento>();
        this.zonas = new ArrayList<Zona>();
        this.infracciones = new ArrayList<Infraccion>();
    }
	
	// Getters
	
	public HashMap<Celular, EstacionamientoApp> getMapCelularConEstacionamiento() {
		return mapCelularConEstacionamiento;
	}
	
	public HashMap<Celular, Integer> getMapCelularConCredito() {
		return mapCelularConCredito;
	}

	public void setMapCelularConCredito(HashMap<Celular, Integer> mapCelularConCredito) {
		this.mapCelularConCredito = mapCelularConCredito;
	}

	public LocalDateTime getHorarioInicio() {
		return horarioInicio;
	}
	
	public LocalDateTime getHorarioFin() {
		return horarioFin;
	}
	
	public Integer getPrecioPorHora() {
		return precioPorHora;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	
	public List<Zona> getZonas() {
		return zonas;
	}
	
	public List<Infraccion> getInfracciones() {
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
        registrarElemento(zonas, zona, "La zona ya está registrada.");
    }

    public void registrarCompra(Compra compra) {
        registrarElemento(compras, compra, "La compra ya está registrada.");
    }

    public void registrarEstacionamiento(Estacionamiento estacionamiento) {
        registrarElemento(estacionamientos, estacionamiento, "El estacionamiento ya está registrado.");
    }
    
    public void registrarInfraccion(Infraccion infraccion) {
        registrarElemento(infracciones, infraccion, "La infracción ya está registrado.");
    }
    
    //TODO preguntar
	public void registrarCredito(Celular numeroCelular, Integer credito) {
		this.mapCelularConCredito.put(numeroCelular, credito);
	}
	
	public void finalizarEstacionamientosVigentes() {
	//TODO: finaliza todos los estacionamiemtos vigentes.
		// debería tener una exepción para saber si es la hora fin de estacionamiento medido?
	}
	
	public void iniciarEstacionamiento(String patente, Celular celular) {
		if (this.tieneCreditoSuficiente(celular)) {
			
		}else {
			throw new IllegalArgumentException("No tiene saldo suficiente para iniciar el estacionamiento.");
		}
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
	
	//TODO: Implementar segun requerimiento. Seguramente precise una exepcion.
	private Boolean tieneEstacionamientoVigente(String patente) {
		Estacionamiento estacionamiento = null;
		for(Estacionamiento e : this.getEstacionamientos()) {
			if(e.getPatente().equals(patente)) {
				estacionamiento = e;
			}
		}
		return estacionamiento.estaVigente();
	}
	
	public Infraccion generarInfraccion(String patente, Zona unaZona) {
		Infraccion nuevaInfraccion = new Infraccion(patente, LocalDateTime.now(), unaZona);
		this.registrarInfraccion(nuevaInfraccion);
		return nuevaInfraccion;
	}
	
	public Integer informarSaldoDe(String numeroDeCelular) {
		for(Celular celular : mapCelularConCredito.keySet()) {
			if(celular.getNroCelular().equals(numeroDeCelular)) {
				return mapCelularConCredito.get(celular);
			}
		}
		return null; //Retorna null en caso que no se encuentre el numero de celular indicado
	}

	public void cargarCreditoDe(Celular celular, Integer credito) {
		this.getMapCelularConCredito().put(celular, credito);
		celular.cargarSaldo(credito);
	}
}	
