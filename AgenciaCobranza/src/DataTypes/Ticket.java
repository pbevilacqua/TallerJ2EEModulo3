package DataTypes;

import java.sql.Date;

public class Ticket {
	private int ticketNro;
	private String matricula;
	private Date fchHraVenta;
	private Date fchHraEst;
	private int cantMin;
	private float impTotal;
	private int codAnul;
	private int fchHraAnul;
	private int terminalNro;

	public Ticket() {
		
	}
	
	public int getTicketNro() {
		return ticketNro;
	}
	public void setTicketNro(int ticketNro) {
		this.ticketNro = ticketNro;
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
	public float getImpTotal() {
		return impTotal;
	}
	public void setImpTotal(float impTotal) {
		this.impTotal = impTotal;
	}
	public int getCodAnul() {
		return codAnul;
	}
	public void setCodAnul(int codAnul) {
		this.codAnul = codAnul;
	}
	public int getFchHraAnul() {
		return fchHraAnul;
	}
	public void setFchHraAnul(int fchHraAnul) {
		this.fchHraAnul = fchHraAnul;
	}
	
	public int getTerminalNro() {
		return terminalNro;
	}

	public void setTerminalNro(int agenciaNro) {
		this.terminalNro = agenciaNro;
	}
	
	

}
