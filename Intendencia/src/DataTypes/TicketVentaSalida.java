package DataTypes;

import java.sql.Date;

public class TicketVentaSalida {
	private int ticketNro;
	private float impTotal;

	public TicketVentaSalida() {
		
	}
	
	public int getTicketNro() {
		return ticketNro;
	}
	public void setTicketNro(int ticketNro) {
		this.ticketNro = ticketNro;
	}
	public float getImpTotal() {
		return impTotal;
	}
	public void setImpTotal(float impTotal) {
		this.impTotal = impTotal;
	}

}
