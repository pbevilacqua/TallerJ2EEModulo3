package comunicacion;
import java.net.*;
// importar la libreria java.net
import java.io.*;
// importar la libreria java.io

public class socketClient {

	
	static socketClient INSTANCE = new socketClient();
//    private static class MyWrapper {
//        
//    }

    private socketClient () {}

    public static socketClient getInstance() {
        return INSTANCE;
    }
    
    
    public String enviarMensaje(String msg) {
    	//public static void main(String argv[]) {

    		
    		Socket socket;
    		String msgDelServidor = "";

    		// Declaramos un bloque try y catch para controlar la ejecución del subprograma
    		try {

    			// Instanciamos un socket con la dirección del destino y el
    			// puerto que vamos a utilizar para la comunicación
    			socket = new Socket("127.0.0.1",6000);

    			// Declaramos e instanciamos el objeto DataOutputStream
    			// que nos valdrá para enviar datos al servidor destino
    			DataOutputStream out =
    					new DataOutputStream(socket.getOutputStream());

    			// Creamos un bucle do while en el que enviamos al servidor el mensaje
    			// los datos que hemos obtenido despues de ejecutar la función
    			// "readLine" en la instancia "in"

    			
    				// enviamos el mensaje codificado en UTF
    				out.writeUTF(msg);
    				DataInputStream in = new DataInputStream(socket.getInputStream());
    								
    				msgDelServidor = in.readUTF();
    		
    				
    		}
    		// utilizamos el catch para capturar los errores que puedan surgir
    		catch (Exception e) {
    			// si existen errores los mostrará en la consola y después saldrá del
    			// programa
    			System.err.println(e.getMessage());
    			System.exit(1);
    		}
    		return msgDelServidor;
    	}
}




