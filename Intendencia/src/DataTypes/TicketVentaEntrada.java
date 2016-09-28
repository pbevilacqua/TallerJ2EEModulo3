package DataTypes;

import java.sql.Date;

public class TicketVentaEntrada {
	private String matricula;
	private Date fchHraVenta;
	private Date fchHraEst;
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
	public Date getFchHraVenta() {
		return fchHraVenta;
	}
	public void setFchHraVenta(Date fchHraVenta) {
		this.fchHraVenta = fchHraVenta;
	}
	public Date getFchHraEst() {
		return fchHraEst;
	}
	public void setFchHraEst(Date fchHraEst) {
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
