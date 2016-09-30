package comunicacion;
//importar la libreria java.io
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import Controladores.ControladorTicketProxy;
import Controladores.TicketVentaEntrada;
import Controladores.TicketVentaSalida;

// declaramos la clase servidortcp

public class socketServer {
	
	public TicketVentaEntrada crearMensajeCompraTicket(String msg){
		TicketVentaEntrada tve = new TicketVentaEntrada();
		
		
		
		return tve;
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
	

	// método principal main de la clase
	public static void main(String argv[]) {

		// declaramos un objeto ServerSocket para realizar la comunicación
		ServerSocket socket;
		// creamos una varible boolean con el valor a false
		boolean fin = false;

		// Declaramos un bloque try y catch para controlar la ejecución del subprograma
		try {

			socketServer ss = new socketServer();
			String s = ss.getConfigPropertyValue("idAgencia");
			
			// Instanciamos un ServerSocket con la dirección del destino y el
			// puerto que vamos a utilizar para la comunicación

			socket = new ServerSocket(6000);

			// Creamos un socket_cli al que le pasamos el contenido del objeto socket después
			// de ejecutar la función accept que nos permitirá aceptar conexiones de clientes
						
				Socket socket_cli = socket.accept();

				// Declaramos e instanciamos el objeto DataInputStream
				// que nos valdrá para recibir datos del cliente

				DataInputStream in =
						new DataInputStream(socket_cli.getInputStream());
				
				DataOutputStream out =
    					new DataOutputStream(socket_cli.getOutputStream());

				
				do {
					String mensaje ="";
					mensaje = in.readUTF();
					if (mensaje!= null){
						StringTokenizer st = new StringTokenizer(mensaje,"|");
						String comando =  st.nextToken();
						if (comando.equals("Login")){
							//Crea mensaje de login
							System.out.println("Comienza con Login");
							
						}
						else {
	
							if (comando.equals("Compra")){
								TicketVentaEntrada tve = new TicketVentaEntrada(); 
								tve.setAgenciaNro(1);
								comando =  st.nextToken();
								tve.setMatricula(comando);
								comando =  st.nextToken();
								tve.setCantMin(Integer.parseInt(comando));
								
								comando =  st.nextToken();
								tve.setFchHraEst(comando);
								SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
								Date fecha = new Date();
								tve.setFchHraVenta(df.format(fecha));							
								ControladorTicketProxy ctp = new ControladorTicketProxy();
								TicketVentaSalida tvs = new TicketVentaSalida();
								tvs = ctp.venderTicket(tve);
								
								System.out.println("Info del ticket");
								System.out.println(tvs.getImpTotal());
								System.out.println(tvs.getTicketNro());
								System.out.println(tvs.getMensaje());
							}
							else 
							{
								if (comando.equals("Anula")){
									//Crea mensaje anula ticket
									System.out.println("Comienza con Anula");
								}
							}
						}
					}

					out.writeUTF(mensaje);
					System.out.println(mensaje);
				} while (1>0);
				
			
			
			
		}
		// utilizamos el catch para capturar los errores que puedan surgir
		catch (Exception e) {

			// si existen errores los mostrará en la consola y después saldrá del
			// programa
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}