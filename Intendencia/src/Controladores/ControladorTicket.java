package Controladores;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

import DataTypes.Mensaje;
import DataTypes.Ticket;
import DataTypes.TicketAnulSalida;
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
			
			Date date = tve.getFchHraEst().getTime();
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			ticket.setFchHraEst(sqldate);
			
			date = tve.getFchHraVenta().getTime();
			sqldate = new java.sql.Date(date.getTime());
			ticket.setFchHraVenta(sqldate);

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
	
	@WebMethod
	public TicketAnulSalida anulacionTicket(int nroTicket, int nroAgencia){
		TicketAnulSalida tas = new TicketAnulSalida();
		

		ControladorDB cdb = ControladorDB.getControladorDB();
		Ticket ticket = new Ticket();
		ticket.setAgenciaNro(nroAgencia);
		ticket.setTicketNro(nroTicket);
		
		Mensaje mensaje = new Mensaje();
		
		cdb.anularTicket(ticket, mensaje);
		
		if (ticket.getCodAnul() > 0){
			tas.setCodAnul(ticket.getCodAnul());
			tas.setFchHraAnul(new Date(ticket.getFchHraAnul().getTime()));
		}
		tas.setMensaje(mensaje);
		
		return tas;
	}

}
