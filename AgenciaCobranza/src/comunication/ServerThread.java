package comunication;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.*;

import DataTypes.*;
import webServices.ControladorTicketProxy;
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
        				//tve.setFchHraEst(comando);
        				tve.setFchHraEst("2016-10-01T23:00:00");
        				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        				Date fecha = new Date();
        				//tve.setFchHraVenta(df.format(fecha));	
        				tve.setFchHraVenta("2016-10-02T15:22:22");
        				ControladorTicketProxy ctp = new ControladorTicketProxy();
        				TicketVentaSalida tvs = new TicketVentaSalida();
        				tvs = ctp.venderTicket(tve);

        				System.out.println("Info del ticket");
        				System.out.println(tvs.getTicketNro());
        				System.out.println(tvs.getImpTotal());
        				System.out.println(tvs.getMensaje().getCodigo());
        				System.out.println(tvs.getMensaje().getMensaje());
        				
        				mensaje = "Compra|"+tvs.getTicketNro()+"|"+tvs.getImpTotal()+"|"+tve.getFchHraVenta()+"|"+tvs.getMensaje().getCodigo()+"|"+tvs.getMensaje().getMensaje();
        				

        				// Si el codigo de error del mensaje es ok grabar el ticket en la base de datos de la agencia.
        				if (tvs.getMensaje().getCodigo() == 0){
        					System.out.println("DEBO GUARDAR EN BASE DE DATOS");
        					TicketAgencia ta = new TicketAgencia();
        					//TicketNro,Matricula,FchHraVenta,FchHraEst,CantMin,ImpTotal,TerminalNro
        				}
        				
        				
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
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }
}