package ar.edu.unq.tp.integrador;

import java.util.ArrayList;
import java.util.List;

public class Zona {
	
	private Integer idZona;
	private Integer idInspector;
	private List<PuntoDeVenta> puntosDeVenta;
	
	public Zona(Integer idZona, Integer idInspector) {
		this.idZona = idZona;
		this.idInspector = idInspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	public void agregarPtoDeVenta(PuntoDeVenta puntoDeVenta) {
		this.puntosDeVenta.add(puntoDeVenta);
	}
	
	public Integer getIdZona() {
		return this.idZona;
	}
	
	public Integer getIdInspector() {
		return this.idInspector;
	}
	
	public List<PuntoDeVenta> getPuntosDeVenta(){
		return puntosDeVenta;
	}
}
