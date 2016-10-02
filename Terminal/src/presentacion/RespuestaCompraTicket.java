package presentacion;


import javax.swing.JOptionPane;

import utilidades.RespuestaIcono;

public class RespuestaCompraTicket
{

 public static void infoCompraTicket(String ticket, String importe, String fechaHora, String mensaje, int tipoMsg, RespuestaIcono icon)
 {
	 
	 String nl = System.getProperty("line.separator");
	 JOptionPane.showMessageDialog(null, "Numero de Ticket: "+ ticket+nl+nl+
				 "Fecha/Hora Venta: "+ fechaHora+nl+nl+
				 "Importe: "+importe+nl+nl+
				 "Mensaje: "+mensaje+nl+nl, "Compra de Ticket", tipoMsg, icon);

 }
 
  
}
 
