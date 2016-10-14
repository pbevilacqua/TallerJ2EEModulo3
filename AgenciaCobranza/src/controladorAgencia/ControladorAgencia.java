package controladorAgencia;

import java.util.ArrayList;
import java.util.Date;
import DataTypes.Ticket;
import DataTypes.TicketAgencia;
import Persistencia.ControladorDB;


public class ControladorAgencia {


	public void anularTicket(TicketAgencia ta) {

		try {
			ControladorDB cdb = ControladorDB.getControladorDB();
			Ticket ticket = new Ticket();
			ticket.setTicketNro(ta.getTicketNro());
			ticket.setCodAnul(ta.getCodAnul());
			ticket.setFchHraAnul(ta.getFchHraAnul().getTime());
			cdb.anularTicket(ticket);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
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

	public boolean existeTicket(int ticketNro){
		boolean resultado = false;
		try {
			ControladorDB cdb = ControladorDB.getControladorDB();
			if (cdb.existeTicket(ticketNro)){
				resultado = true;			
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;

	}
	
	public ArrayList<Ticket> obtenerVentasPorFecha(Date fechaDesde, Date fechaHasta ) {
		ControladorDB cdb = ControladorDB.getControladorDB();
		return cdb.obtenerVentasPorFecha(new java.sql.Date(fechaDesde.getTime()), new java.sql.Date(fechaHasta.getTime()));

	}

}

