package DataTypes;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

public class TicketVentaEntrada {
	private String matricula;
	private XMLGregorianCalendar fchHraVenta;
	private XMLGregorianCalendar fchHraEst;
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
	
	public int getAgenciaNro() {
		return agenciaNro;
	}

	public void setAgenciaNro(int agenciaNro) {
		this.agenciaNro = agenciaNro;
	}
	
	

}
