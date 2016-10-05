package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilidades.*;





public class JPanelAnulaTicket extends JPanel {

	//private int nroDeSocio;
	private JTextField campoMatricula;
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

		JLabel lbltitulo = new JLabel("Ticket a anular");
		lbltitulo.setBounds(32, 10, 150, 32);
		add(lbltitulo);

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


				if ((matricula.equals(null)||matricula.equals(""))||(cantMin.equals(null)||(cantMin.equals("")))){
					mensaje.setText("Los campos Matricula y Cantidad de Minutos no pueden quedar vacios");
					mensaje.setVisible(true);
				}
				else{
					if (!isNumber(cantMin)){
						mensaje.setText("Debe ingresar un valor entero para la cantidad de minutos");
						mensaje.setVisible(true);
					}
					else {
						if (isNumber(cantMin)) {
							mensaje.setText("");
							ProcesarMensaje pm = ProcesarMensaje.getProcesarMensaje();
							TicketVentaSalida tvs = pm.CompraTicket(matricula, cantMin, dateTimePicker.getDateTimeAsString());
							RespuestaIcono icon = new RespuestaIcono();
							RespuestaCompraTicket.infoCompraTicket(tvs.getTicketNro(), tvs.getImpTotal(), tvs.getFecha(), tvs.getMensaje(), JOptionPane.DEFAULT_OPTION, icon);
							campoMatricula.setText("");
							campoCantMin.setText("");
							mensaje.setText("");
						}
					}
				}
				
				jpanelAnulaTicket.revalidate();
				jpanelAnulaTicket.repaint(); 
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

