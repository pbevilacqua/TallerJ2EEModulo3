package Controladores;

import java.util.GregorianCalendar;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import DataTypes.Ticket;
import DataTypes.TicketVentaEntrada;
import DataTypes.TicketVentaSalida;
import Persistencia.ControladorDB;

@WebService
public class ControladorTicket {

	@WebMethod
	public TicketVentaSalida venderTicket(TicketVentaEntrada tve) {

		TicketVentaSalida tvs = new TicketVentaSalida();
		try {
			ControladorDB cdb = ControladorDB.getControladorDB();
			Ticket ticket = new Ticket();
			ticket.setAgenciaNro(tve.getAgenciaNro());
			ticket.setCantMin(tve.getCantMin());
			
			
			ticket.setFchHraEst(new java.sql.Date(tve.getFchHraEst().toGregorianCalendar().getTime().getTime()));
			ticket.setFchHraVenta(new java.sql.Date(tve.getFchHraVenta().toGregorianCalendar().getTime().getTime()));

			ticket.setMatricula(tve.getMatricula());
			
			float impTotal =  1000;
			ticket.setImpTotal(impTotal);

			cdb.reservaTicket(ticket);

			tvs.setImpTotal(ticket.getImpTotal());
			tvs.setTicketNro(ticket.getTicketNro());
			tvs.getMensaje().setCodigo(0);
			tvs.getMensaje().setMensaje("Ticket vendido con exito");
			
			return tvs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tvs;
	}

}
