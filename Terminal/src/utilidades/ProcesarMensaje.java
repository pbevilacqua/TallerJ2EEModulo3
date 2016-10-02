package utilidades;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import comunicacion.socketClient;


public class ProcesarMensaje {
	
	private static ProcesarMensaje procesarMensaje;
	
	private ProcesarMensaje(){
	}
	
	
	public static ProcesarMensaje getProcesarMensaje(){
		 if (procesarMensaje==null) {
			 procesarMensaje=new ProcesarMensaje();
		 }
		 return procesarMensaje;
	}

	public TicketVentaSalida CompraTicket(String matricula, String cantMin, String fecha){
		TicketVentaSalida tvs = new TicketVentaSalida();
		String msg;
		
		msg = "Compra|"+matricula+"|"+cantMin+"|"+fecha;
		socketClient sc= socketClient.getInstance();
		String mensaje = sc.enviarMensaje(msg);
		
		//mensaje = "Compra|"+tvs.getTicketNro()+"|"+tvs.getImpTotal()+"|"+"tvs.getFecha()"+"|"+tvs.getMensaje();
		
		  try {
	        	if (mensaje!= null){
	        		StringTokenizer st = new StringTokenizer(mensaje,"|");
	        		String comando =  st.nextToken();
	        		if (comando.equals("Compra")){
	        			
	        				comando =  st.nextToken();
	        				tvs.setTicketNro(comando);
	        				comando =  st.nextToken();
	        				tvs.setImpTotal(comando);
	        				comando =  st.nextToken();
	        				tvs.setFecha(comando);
	        				comando =  st.nextToken();
	        				tvs.setMensaje(comando);

	        				System.out.println("Info de la respuesta de compra ticket");
	        				System.out.println(tvs.getTicketNro());
	        				System.out.println(tvs.getImpTotal());
	        				System.out.println(tvs.getFecha());
	        				System.out.println(tvs.getMensaje());   
	        				
	        			}	        			
	        		}	        	
	        } catch (Exception ex) {
	            Logger.getLogger(ProcesarMensaje.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
		return tvs;

	}
}
