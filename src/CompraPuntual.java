import java.time.LocalDateTime;

public class CompraPuntual extends Compra {
	private Integer cantidadDeHoras;
	
	public CompraPuntual(Integer nroControl, LocalDateTime fecYHr, PuntoDeVenta ptoVenta, Integer cantHs) {
		super(nroControl, fecYHr, ptoVenta);
		this.cantidadDeHoras = cantHs;
	}
	
	public Integer getCantidadDeHoras() {
		return this.cantidadDeHoras;
	}
}
