import java.time.LocalDateTime;

public class CompraCelular extends Compra {
	private Integer montoDeCarga;
	private Integer numeroDeCelular;
	
	public CompraCelular(Integer nroControl, LocalDateTime fecYHr, PuntoDeVenta ptoVenta, Integer montoCarga, Integer nroCel) {
		super(nroControl, fecYHr, ptoVenta);
		this.montoDeCarga = montoCarga;
		this.numeroDeCelular = nroCel;
	}
	
	public Integer getMontoDeCarga() {
		return this.montoDeCarga;
	}
	
	public Integer getNumeroDeCelular() {
		return this.numeroDeCelular;
	}
	
}
