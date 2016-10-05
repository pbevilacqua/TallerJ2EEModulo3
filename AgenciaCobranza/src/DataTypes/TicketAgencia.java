package DataTypes;

import javax.xml.datatype.XMLGregorianCalendar;


public class TicketAgencia {
	private int TicketNro;
	private String matricula;
	private XMLGregorianCalendar fchHraVenta;
	private XMLGregorianCalendar fchHraEst;
	private int cantMin;
	private float ImpTotal;
	private int TerminalNro;

	public TicketAgencia() {
		
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public XMLGregorianCalendar getFchHraVenta() {
		return fchHraVenta;
	}
	public void setFchHraVenta(XMLGregorianCalendar fchHraVenta) {
		this.fchHraVenta = fchHraVenta;
	}
	public XMLGregorianCalendar getFchHraEst() {
		return fchHraEst;
	}
	public void setFchHraEst(XMLGregorianCalendar fchHraEst) {
		this.fchHraEst = fchHraEst;
	}
	public int getCantMin() {
		return cantMin;
	}
	public void setCantMin(int cantMin) {
		this.cantMin = cantMin;
	}
	
	public float getImpTotal() {
		return ImpTotal;
	}

	public void setImpTotal(float impTotal) {
		ImpTotal = impTotal;
	}

	public int getTerminalNro() {
		return TerminalNro;
	}

	public void setTerminalNro(int terminalNro) {
		TerminalNro = terminalNro;
	}

	public int getTicketNro() {
		return TicketNro;
	}

	public void setTicketNro(int ticketNro) {
		TicketNro = ticketNro;
	}

}
