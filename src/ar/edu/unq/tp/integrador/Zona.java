package ar.edu.unq.tp.integrador;

import java.util.ArrayList;
import java.util.List;

public class Zona {
	
	private Integer idZona;
	private Integer idInspector;
	private List<PuntoDeVenta> puntosDeVenta;
	
	public Zona(Integer unIdZona, Integer unIdInspector) {
		this.idZona = unIdZona;
		this.idInspector = unIdInspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	public void agregarPtoDeVenta(PuntoDeVenta unPuntoDeVenta) {
		this.puntosDeVenta.add(unPuntoDeVenta);
	}
	
	public Integer getIdZona() {
		return this.idZona;
	}
	
	public Integer getIdInspector() {
		return this.idInspector;
	}

}
