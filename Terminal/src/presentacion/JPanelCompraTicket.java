package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import comunicacion.socketClient;




public class JPanelCompraTicket extends JPanel {
	
	//private int nroDeSocio;
	private JTextField campoTitulo;
	private JTextField campoMatricula;
	private JTextField campoCantMin;
	private JLabel mensaje; 
	
	//Singleton JPanelAltaSocio
	private static JPanelCompraTicket jpanelCompraTicket;

	public static JPanelCompraTicket getInstance(){
		 if (jpanelCompraTicket==null) {
			 jpanelCompraTicket=new JPanelCompraTicket();

		 }
		 return jpanelCompraTicket;
	}
	
	
	private JPanelCompraTicket() {
		
		
		setLayout(null);
		
		JLabel lbltitulo = new JLabel("Compra de Ticket");
		lbltitulo.setBounds(32, 10, 150, 32);
		add(lbltitulo);
		
		
		JLabel lblmatricula = new JLabel("Matricula");
		lblmatricula.setBounds(32, 50, 150, 32);
		add(lblmatricula);
		
		JLabel lblcantMin = new JLabel("Cantidad de Minutos");
		lblcantMin.setBounds(32, 80, 190, 32);
		add(lblcantMin);
		
		JLabel lblfchHrEst = new JLabel("Fecha/Hora Estacionamiento");
		lblfchHrEst.setBounds(32, 110, 205, 32);
		add(lblfchHrEst);
		
		
		mensaje = new JLabel();
		mensaje.setBounds(32, 230, 532, 32);
		mensaje.setVisible(true);
		add(mensaje);
		
		
		campoMatricula = new JTextField();
		campoMatricula.setColumns(50);
		campoMatricula.setBounds(240, 56, 324, 20);
		add(campoMatricula);
		
		
		campoCantMin = new JTextField();
		campoCantMin.setColumns(10);
		campoCantMin.setBounds(240, 86, 324, 20);
		add(campoCantMin);
		
		Date date = new Date();
		DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
        dateTimePicker.setBounds(240, 116, 200, 20);
	    dateTimePicker.setDate(date);
	    add(dateTimePicker);
       
		
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.setBounds(391, 300, 89, 23);
		add(aceptarButton);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(292, 300, 89, 23);
		add(cancelarButton);

		
	        aceptarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String msg;
					
					
					String matricula = campoMatricula.getText();
					String cantMin = campoCantMin.getText();
					
					
					if ((matricula.equals(null)||matricula.equals(""))||(cantMin.equals(null)||(cantMin.equals(""))))
						mensaje.setText("Los campos Matricula y Cantidad de Minutos no pueden quedar vacios");
					else {
						
						msg = "Compra|"+matricula+"|"+cantMin+"|"+dateTimePicker.getDateTimeAsString();
						socketClient sc= socketClient.getInstance();
						String msgServidor = sc.enviarMensaje(msg);
						
					
    					mensaje.setText("El mensaje recibido por el servidor es: " + msgServidor);							
						
						
					}
					mensaje.setVisible(true);
					jpanelCompraTicket.revalidate();
					jpanelCompraTicket.repaint(); 
				}
			});
			
	        
	        cancelarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//Manager_Socio manager_socio = Manager_Socio.getManagerSocio();
					campoMatricula.setText("");
					campoCantMin.setText("");
					
					mensaje.setText("");
					mensaje.setVisible(false);
					jpanelCompraTicket.revalidate();
					jpanelCompraTicket.repaint(); 
				}
			});
		
	        
		
	}
}
