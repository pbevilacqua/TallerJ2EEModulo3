package DataTypes;

import java.util.Date;

public class TicketAnulSalida {

	private int codAnul;
	private Date fchHraAnul;
	private Mensaje mensaje;
		
	public TicketAnulSalida() {

	}
	public int getCodAnul() {
		return codAnul;
	}
	public void setCodAnul(int codAnul) {
		this.codAnul = codAnul;
	}
	public Date getFchHraAnul() {
		return fchHraAnul;
	}
	public void setFchHraAnul(Date fchHraAnul) {
		this.fchHraAnul = fchHraAnul;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}


}
