package comunicacion;
import java.net.*;
//importar la libreria java.net

import java.io.*;
//importar la libreria java.io

// declaramos la clase servidortcp

public class socketServer {

	// m�todo principal main de la clase
	public static void main(String argv[]) {

		// declaramos un objeto ServerSocket para realizar la comunicaci�n
		ServerSocket socket;
		// creamos una varible boolean con el valor a false
		boolean fin = false;

		// Declaramos un bloque try y catch para controlar la ejecuci�n del subprograma
		try {

			// Instanciamos un ServerSocket con la direcci�n del destino y el
			// puerto que vamos a utilizar para la comunicaci�n

			socket = new ServerSocket(6000);

			// Creamos un socket_cli al que le pasamos el contenido del objeto socket despu�s
			// de ejecutar la funci�n accept que nos permitir� aceptar conexiones de clientes
						
				Socket socket_cli = socket.accept();

				// Declaramos e instanciamos el objeto DataInputStream
				// que nos valdr� para recibir datos del cliente

				DataInputStream in =
						new DataInputStream(socket_cli.getInputStream());
				
				DataOutputStream out =
    					new DataOutputStream(socket_cli.getOutputStream());

				// Creamos un bucle do while en el que recogemos el mensaje
				// que nos ha enviado el cliente y despu�s lo mostramos
				// por consola

				do {
					String mensaje ="";
					mensaje = in.readUTF();
					out.writeUTF(mensaje);
					System.out.println(mensaje);
				} while (1>0);
				
			
			
			
		}
		// utilizamos el catch para capturar los errores que puedan surgir
		catch (Exception e) {

			// si existen errores los mostrar� en la consola y despu�s saldr� del
			// programa
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}