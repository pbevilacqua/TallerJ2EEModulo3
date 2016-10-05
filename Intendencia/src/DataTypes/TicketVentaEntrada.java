package DataTypes;

import java.util.GregorianCalendar;


public class TicketVentaEntrada {
	private String matricula;
	private GregorianCalendar fchHraVenta;
	private GregorianCalendar fchHraEst;
	private int cantMin;
	private int agenciaNro;

	public TicketVentaEntrada() {
		
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public GregorianCalendar getFchHraVenta() {
		return fchHraVenta;
	}
	public void setFchHraVenta(GregorianCalendar fchHraVenta) {
		this.fchHraVenta = fchHraVenta;
	}
	public GregorianCalendar getFchHraEst() {
		return fchHraEst;
	}
	public void setFchHraEst(GregorianCalendar fchHraEst) {
		this.fchHraEst = fchHraEst;
	}
	public int getCantMin() {
		return cantMin;
	}
	public void setCantMin(int cantMin) {
		this.cantMin = cantMin;
	}
	
	public int getAgenciaNro() {
		return agenciaNro;
	}

	public void setAgenciaNro(int agenciaNro) {
		this.agenciaNro = agenciaNro;
	}
	
	

}
