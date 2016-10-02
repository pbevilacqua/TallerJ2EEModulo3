/**
 * TicketVentaSalida.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package utilidades;

public class TicketVentaSalida  {
	private String ticketNro;
	
    private String impTotal;
    
    private String fecha;

    private String mensaje;

    

    public TicketVentaSalida() {
    }

    public TicketVentaSalida(
           String impTotal,
           String mensaje,
           String ticketNro) {
           this.impTotal = impTotal;
           this.mensaje = mensaje;
           this.ticketNro = ticketNro;
    }

    
    public String getTicketNro() {
        return ticketNro;
    }


    public void setTicketNro(String ticketNro) {
        this.ticketNro = ticketNro;
    }
    

    
    public String getImpTotal() {
        return impTotal;
    }


   
    public void setImpTotal(String impTotal) {
        this.impTotal = impTotal;
    }


    public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
   
    public String getMensaje() {
        return mensaje;
    }


    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    } 


	
   
}
