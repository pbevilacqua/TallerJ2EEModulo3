package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import utilidades.ProcesarMensaje;


public class JPanelPrincipal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6352974965470748715L;

	//Login
	private JTextField campoUsuario;
	private JTextField campoContraseña;
	private JLabel mensaje; 

	//Gestión Tickets
	JButton jb_compraTicket, jb_anulaTicket;   
	JToolBar tb;
	ImageIcon imi_compraTicket, imi_anulaTicket;
	public JPanel jp_compra_ticket, jp_anula_ticket;

	//Singleton jpanelLogin
	private static JPanelPrincipal jpanelPrincipal;

	public static JPanelPrincipal getInstance(){
		if (jpanelPrincipal==null) {
			jpanelPrincipal=new JPanelPrincipal();

		}
		return jpanelPrincipal;
	}


	private JPanelPrincipal() {
		this.crearVentanaLogin();
	}

	private void crearVentanaLogin(){
		setLayout(null);


		JLabel lblusuario = new JLabel("Usuario");
		lblusuario.setBounds(32, 50, 150, 32);
		add(lblusuario);

		JLabel lblcontraseña = new JLabel("Contraseña");
		lblcontraseña.setBounds(32, 80, 190, 32);
		add(lblcontraseña);

		mensaje = new JLabel();
		mensaje.setBounds(32, 230, 532, 32);
		mensaje.setVisible(true);
		add(mensaje);


		campoUsuario = new JTextField();
		campoUsuario.setColumns(50);
		campoUsuario.setBounds(240, 56, 324, 20);
		add(campoUsuario);


		campoContraseña = new JTextField();
		campoContraseña.setColumns(10);
		campoContraseña.setBounds(240, 86, 324, 20);
		add(campoContraseña);


		JButton aceptarButton = new JButton("Login");
		aceptarButton.setBounds(391, 300, 89, 23);
		add(aceptarButton);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(292, 300, 89, 23);
		add(cancelarButton);


		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String msg;
				ProcesarMensaje pm = ProcesarMensaje.getProcesarMensaje();			


				String usuario = campoUsuario.getText();
				String contraseña = campoContraseña.getText();


				if ((usuario.equals(null)||usuario.equals(""))||(contraseña.equals(null)||(contraseña.equals("")))){
					mensaje.setText("Debe ingresar usuario y contraseña");
					mensaje.setVisible(true);
				}
				else{
					msg = pm.Login(usuario, contraseña);

					if(msg != null){
						switch (msg){
						case "OK":
							crearVentanaGestionTicket();
							break;
						default:
							//Tiro el mensaje que venga por el socket
						}
					}
					else{
						//Error de comunicación: Error en el sistema, vuelva a intentarlo mas tarde.
					}
				}

				jpanelPrincipal.revalidate();
				jpanelPrincipal.repaint(); 
			}
		});


		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Manager_Socio manager_socio = Manager_Socio.getManagerSocio();
				campoUsuario.setText("");
				campoContraseña.setText("");

				mensaje.setText("");
				mensaje.setVisible(false);
				jpanelPrincipal.revalidate();
				jpanelPrincipal.repaint(); 
			}
		});
	}

	private void crearVentanaGestionTicket(){
		removeAll();
		setLayout(new BorderLayout(2, 3));


		imi_compraTicket= new ImageIcon("iconos\\parking.png");
		imi_anulaTicket = new ImageIcon("iconos\\anularTicket.png");

		jb_compraTicket = new JButton(imi_compraTicket);
		jb_anulaTicket = new JButton(imi_anulaTicket);


		tb = new JToolBar();
		tb.add(jb_compraTicket);
		tb.add(jb_anulaTicket);

		add(tb, BorderLayout.NORTH);
		setVisible(true);


		// ActionListener CompraTicket
		jb_compraTicket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				jpanelPrincipal.removeAll();
				jpanelPrincipal.setLayout(new BorderLayout(2, 3));
				jpanelPrincipal.add(tb, BorderLayout.NORTH); //boton al panel norte

				jp_compra_ticket = JPanelCompraTicket.getInstance();//Singleton del panel de Compra Ticket
				jpanelPrincipal.add(jp_compra_ticket);


				jpanelPrincipal.revalidate();
				jpanelPrincipal.repaint();

			}

		});
		jpanelPrincipal.setVisible(true);    


		// ActionListener AnulaTicket
		jb_anulaTicket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				jpanelPrincipal.removeAll();
				jpanelPrincipal.setLayout(new BorderLayout(2, 3));
				jpanelPrincipal.add(tb, BorderLayout.NORTH); //boton al panel norte

				jp_anula_ticket = JPanelAnulaTicket.getInstance();//Singleton del panel de Anula Ticket
				jpanelPrincipal.add(jp_anula_ticket);


				jpanelPrincipal.revalidate();
				jpanelPrincipal.repaint();

			}

		});
		jpanelPrincipal.setVisible(true);  
	}


}


