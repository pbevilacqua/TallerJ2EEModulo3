package comunicacion;
import java.net.*;
import java.util.logging.*;
import java.io.*;


public class socketClient {

	
	static socketClient INSTANCE = new socketClient();


    private socketClient () {}

    public static socketClient getInstance() {
        return INSTANCE;
    }
    
    
    public String enviarMensaje(String msg) {
    	   		
    		Socket socket;
    		String msgDelServidor = "";

    		try {

    			socket = new Socket("127.0.0.1",6000);

    			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    			
    			out.writeUTF(msg);
    			DataInputStream in = new DataInputStream(socket.getInputStream());

    			msgDelServidor = in.readUTF();
    		
    				
    		}
		     catch (IOException ex) {
		        Logger.getLogger(socketClient.class.getName()).log(Level.SEVERE, null, ex);
		    }
    		catch (Exception e) {
    			System.err.println(e.getMessage());
    			System.exit(1);
    		}
    		return msgDelServidor;
    	}
}




