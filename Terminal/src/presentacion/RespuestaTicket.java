package presentacion;


import javax.swing.JOptionPane;

import utilidades.*;

public class RespuestaTicket
{

 public static void infoCompraTicket(String nroticket, String importe, String fechaHora, String mensaje, int tipoMsg, RespuestaCompra icon)
 {
	 
	 String nl = System.getProperty("line.separator");
	 JOptionPane.showMessageDialog(null, "Numero de Ticket: "+ nroticket+nl+nl+
				 "Fecha/Hora Venta: "+ fechaHora+nl+nl+
				 "Importe: "+importe+nl+nl+
				 "Mensaje: "+mensaje+nl+nl, "Compra de Ticket", tipoMsg, icon);

 }
 
 public static void infoAnulaTicket(String nroticket, String codAnul, String fechaHora, String mensaje, int tipoMsg, RespuestaAnula icon)
 {
	 //mensaje = "Anula|"+tas.getCodAnul()+"|"+dFchAnul+"|"+tas.getMensaje();
	 String nl = System.getProperty("line.separator");
	 JOptionPane.showMessageDialog(null, "Numero de Ticket: "+ nroticket+nl+nl+
				 "Codigo de Anulación: "+ codAnul+nl+nl+
				 "Fecha/Hora de Anulación: "+fechaHora+nl+nl+
				 "Mensaje: "+mensaje+nl+nl, "Anulación de Ticket", tipoMsg, icon);

 }
 
  
}
 
