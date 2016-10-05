package controladorAgencia;

import DataTypes.Ticket;
import DataTypes.TicketAgencia;
//import DataTypes.TicketVentaSalida;
import Persistencia.ControladorDB;

public class ControladorAgencia {


	public void venderTicket(TicketAgencia ta) {

		try {
			ControladorDB cdb = ControladorDB.getControladorDB();
			Ticket ticket = new Ticket();
			ticket.setTicketNro(ta.getTicketNro());
			ticket.setMatricula(ta.getMatricula());
			ticket.setFchHraVenta(new java.sql.Date(ta.getFchHraVenta().getTime().getTime()));				
			ticket.setFchHraEst(new java.sql.Date(ta.getFchHraEst().getTime().getTime()));
			ticket.setCantMin(ta.getCantMin());
			ticket.setImpTotal(ta.getImpTotal());
			ticket.setTerminalNro(ta.getTerminalNro());

			cdb.reservaTicket(ticket);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

