package comunication;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.*;

import DataTypes.*;
import controladorAgencia.ControladorAgencia;
import webServices.ControladorTicketProxy;
import webServices.TicketAnulSalida;
import webServices.TicketVentaEntrada;
import webServices.TicketVentaSalida;

public class ServerThread extends Thread {
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	//private int idSession;




	public ServerThread(Socket socket, int id) {
		this.socket = socket;
		// this.idSession = id;
		try {


			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());



		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	public String getConfigPropertyValue(String propStr) throws IOException{
		InputStream inputStream = null;
		String result = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			//inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			inputStream = classloader.getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			result = prop.getProperty(propStr);
		}
		catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if(inputStream != null)
				inputStream.close();
		}

		return result;
	}


	public void desconnectar() {
		try {
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@Override
	public void run() {
		String mensaje = "";
		try {
			mensaje = in.readUTF();

			if (mensaje!= null){
				StringTokenizer st = new StringTokenizer(mensaje,"|");
				String comando =  st.nextToken();
				if (comando.equals("Login")){
					
					
					String usuario =  st.nextToken();
					String contraseña =  st.nextToken();
					ControladorAgencia ca = new ControladorAgencia();
					if (ca.existeUsuario(usuario, contraseña)){
						
						if (ca.usuarioTerminal(usuario)){
							
							mensaje = "OK";
						}
						else{
							mensaje = "Error|Usuario no habilitado para ingresar a la terminal";
						}
						
					}
					else{
						mensaje = "Error|Usuario y/o contraseña incorrectos";
					}
					
					//Crea mensaje de login
					System.out.println("Comienza con Login");

				}
				else {

					if (comando.equals("Compra")){
						TicketVentaEntrada tve = new TicketVentaEntrada(); 
						//tve.setAgenciaNro(Integer.parseInt(this.getConfigPropertyValue("idAgencia")));    
						tve.setAgenciaNro(1);   
						comando =  st.nextToken();
						tve.setMatricula(comando);
						comando =  st.nextToken();
						tve.setCantMin(Integer.parseInt(comando));

						comando =  st.nextToken();
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						Date parsed = df.parse(comando);
						System.out.println("parsed date: " + parsed);

						GregorianCalendar gCFchEst = new GregorianCalendar(); //Calendar.getInstance();
						gCFchEst.setTime(parsed);


						tve.setFchHraEst(gCFchEst);

						GregorianCalendar gCFchVen = new GregorianCalendar();
						tve.setFchHraVenta(gCFchVen);	
						Date dFchVen = gCFchVen.getTime();

						ControladorTicketProxy ctp = new ControladorTicketProxy();
						TicketVentaSalida tvs = new TicketVentaSalida();
						tvs = ctp.venderTicket(tve);

						System.out.println("Info del ticket");
						System.out.println(tvs.getTicketNro());
						System.out.println(tvs.getImpTotal());
						System.out.println(tvs.getMensaje().getCodigo());
						System.out.println(tvs.getMensaje().getMensaje());

						mensaje = "Compra|"+tvs.getTicketNro()+"|"+tvs.getImpTotal()+"|"+dFchVen+"|"+tvs.getMensaje().getCodigo()+"|"+tvs.getMensaje().getMensaje();


						// Si el codigo de error del mensaje es ok grabar el ticket en la base de datos de la agencia.
						if (tvs.getMensaje().getCodigo() == 0){
							TicketAgencia ta = new TicketAgencia();
							ta.setTicketNro(tvs.getTicketNro());
							ta.setMatricula(tve.getMatricula());
							ta.setFchHraVenta(gCFchVen);
							ta.setFchHraEst(gCFchEst);
							ta.setCantMin(tve.getCantMin());
							ta.setImpTotal(tvs.getImpTotal());
							ta.setTerminalNro(1);

							ControladorAgencia ca = new ControladorAgencia();
							ca.venderTicket(ta);

						}


					}
					else 
					{
						if (comando.equals("Anula")){
							//Crea mensaje anula ticket
							//TicketVentaEntrada tve = new TicketVentaEntrada(); 
							//tve.setAgenciaNro(Integer.parseInt(this.getConfigPropertyValue("idAgencia")));    
							//tve.setAgenciaNro(1);   
							comando =  st.nextToken();
							ControladorAgencia ca = new ControladorAgencia();
							TicketAgencia ta = new TicketAgencia();
							int nroTicket = Integer.parseInt(comando);
							if (ca.existeTicket(nroTicket)){

								ControladorTicketProxy ctp = new ControladorTicketProxy();
								TicketAnulSalida tas = new TicketAnulSalida();
								//tas = ctp.anulacionTicket(nroTicket, Integer.parseInt(this.getConfigPropertyValue("idAgencia")));
								tas = ctp.anulacionTicket(nroTicket, 1);

								Calendar gCFchHraAnul = tas.getFchHraAnul();
								Date dFchAnul = gCFchHraAnul.getTime();

								mensaje = "Anula|"+tas.getCodAnul()+"|"+dFchAnul+"|"+tas.getMensaje().getMensaje();

								if (tas.getCodAnul() != 0) {

									// Si en la intendencia se anulo ok anulo el ticket en la agencia
									ta.setTicketNro(Integer.parseInt(comando));
									ta.setCodAnul(tas.getCodAnul()); 
									ta.setFchHraAnul((GregorianCalendar)tas.getFchHraAnul());
									ca.anularTicket(ta);

								}	

							}
							else {
								mensaje = "Error|"+"El ticket "+nroTicket+" no fue vendido por esta agencia";
							}

						} 						
					}
				}
			}

			out.writeUTF(mensaje);
			System.out.println(mensaje);
		} catch (IOException | ParseException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
		desconnectar();
	}
}