package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilidades.ProcesarMensaje;
import utilidades.RespuestaAnula;
import utilidades.TicketAnulSalida;





public class JPanelAnulaTicket extends JPanel {

	//private int nroDeSocio;
	private JTextField campoTicket;
	private JTextField campoCantMin;
	private JLabel mensaje; 

	//Singleton JPanelCompraTicket
	private static JPanelAnulaTicket jpanelAnulaTicket;

	public static JPanelAnulaTicket getInstance(){
		if (jpanelAnulaTicket==null) {
			jpanelAnulaTicket=new JPanelAnulaTicket();

		}
		return jpanelAnulaTicket;
	}


	private JPanelAnulaTicket() {


		setLayout(null);

		JLabel lbltitulo = new JLabel("Anular Ticket");
		lbltitulo.setBounds(32, 10, 115, 32);
		add(lbltitulo);
		

		JLabel lblTicket = new JLabel("Numero de Ticket a Anular");
		lblTicket.setBounds(32, 50, 175, 32);
		add(lblTicket);

		mensaje = new JLabel();
		mensaje.setBounds(32, 230, 532, 32);
		mensaje.setVisible(true);
		add(mensaje);


		campoTicket = new JTextField();
		campoTicket.setColumns(50);
		campoTicket.setBounds(240, 56, 324, 20);
		add(campoTicket);

		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.setBounds(391, 300, 89, 23);
		add(aceptarButton);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(292, 300, 89, 23);
		add(cancelarButton);


		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ProcesarMensaje pm = ProcesarMensaje.getProcesarMensaje();
				
				
				int nroTicket = Integer.parseInt(campoTicket.getText());
								
				if (!isNumber(campoTicket.getText())){
					mensaje.setText("Debe ingresar un valor entero para el número de Ticket");
					mensaje.setVisible(true);
				}
				
				else
				{
					if (pm.isNumber(campoTicket.getText())) {
						mensaje.setText("");
						TicketAnulSalida tas = pm.AnulaTicket(nroTicket);					
								
								
						RespuestaAnula icon = new RespuestaAnula();
						RespuestaTicket.infoAnulaTicket(campoTicket.getText(), String.valueOf(tas.getCodAnul()), tas.getFchHraAnul(), tas.getMensaje(), JOptionPane.DEFAULT_OPTION, icon);
						
						campoCantMin.setText("");
						mensaje.setText("");
					}
				}

				jpanelAnulaTicket.revalidate();
				jpanelAnulaTicket.repaint(); 
			}
		});


		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				campoTicket.setText("");
				

				mensaje.setText("");
				mensaje.setVisible(false);
				jpanelAnulaTicket.revalidate();
				jpanelAnulaTicket.repaint(); 
			}
		});
	}

	private static boolean isNumber(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}


