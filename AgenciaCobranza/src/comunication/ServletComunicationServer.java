package comunication;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletComunicationServer
 */
@WebServlet(name="/ServletComunicationServer",loadOnStartup=1)
public class ServletComunicationServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComunicationServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        ((MainThread) new MainThread()).start();
//        try {
//            ss = new ServerSocket(6000);
//            System.out.println("\t[OK]");
//            int idSession = 0;
//            while (true) {
//                Socket socket;
//                socket = ss.accept();
//                System.out.println("Nueva conexión entrante: "+socket);
//                ((ServerThread) new ServerThread(socket, idSession)).start();
//                idSession++;
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(ServletComunicationServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
		
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
