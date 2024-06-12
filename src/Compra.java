import java.time.LocalDateTime;

public class Compra {
	private Integer numeroDeControl;
	private LocalDateTime fechaYHora;
	private PuntoDeVenta ptoDeVenta;
	
	public Compra(Integer nroControl, LocalDateTime fecYHr, PuntoDeVenta ptoVenta) {
		this.numeroDeControl = nroControl;
		this.fechaYHora = fecYHr;
		this.ptoDeVenta = ptoVenta;
	}
	
	
}
