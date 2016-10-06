package comunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainThread extends Thread {
    private Socket socket;
   // private DataOutputStream out;
 //   private DataInputStream in;
    //private int idSession;
    
    
    
    
    public MainThread(/*Socket socket, int id*/) {
//        this.socket = socket;
//       // this.idSession = id;
//        try {
//        	
//        	
//            out = new DataOutputStream(socket.getOutputStream());
//            in = new DataInputStream(socket.getInputStream());
//            
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    
    
    
    
//    public void desconnectar() {
//        try {
//            socket.close();
//        } catch (IOException ex) {
//            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    @Override
    public void run() {
    	
    	ServerSocket ss;
    	
    	try {
            ss = new ServerSocket(6000);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ((ServerThread) new ServerThread(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}