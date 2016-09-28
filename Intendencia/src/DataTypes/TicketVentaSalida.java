package DataTypes;


public class TicketVentaSalida {
	private int ticketNro;
	private float impTotal;
	private Mensaje mensaje;

	public TicketVentaSalida() {
		mensaje = new Mensaje();
		
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
	
	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}


}
