package Controladores;

import javax.jws.WebMethod;
import javax.jws.WebService;

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
			ticket.setFchHraEst(tve.getFchHraEst());
			ticket.setFchHraVenta(tve.getFchHraVenta());
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
