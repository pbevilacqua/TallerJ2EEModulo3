package Servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataTypes.Login;
import DataTypes.Mensaje;
import DataTypes.TipoMensaje;
import DataTypes.Usuario;
import controladorAgencia.ControladorAgencia;

/**
 * Servlet implementation class ServletLogin
 */
//@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String,String> usrPass;
    /**
     * @see HttpServlet#HttpServlet()
     */
  
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		usrPass = new HashMap<String,String>();
		Enumeration<String> paramNames = config.getInitParameterNames();
		
		while(paramNames.hasMoreElements()){
			String name = paramNames.nextElement();
			String value = config.getInitParameter(name);
			usrPass.put(name, value);
		}
		//Iterator itr = config.getInitParameterNames();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		
		Usuario user = new Usuario();
		user.setUser(usuario);
		user.setPassword(pass);
		String texto;
		
		ControladorAgencia ca = new ControladorAgencia();
		
		//if (usrPass.containsKey(usuario) && usrPass.get(usuario).equals(pass)){
		try {
			if (ca.existeUsuario(usuario, pass)){
				if(ca.usuarioAdministrador(usuario)){
				HttpSession session = request.getSession(true); 
					Login login = new Login();
					login.setUsuario(usuario);
					session.setAttribute("login", login);
					// texto = "<html><body><h1>Bienvenid@ " +
					// usuario+"!!</h1></body></html>";
					request.getRequestDispatcher("/JSP/ListarTotalesDiarios.jsp").forward(request, response);
				}
				else
				{
					Mensaje msg = new Mensaje("Usuario no es Administrador!!", TipoMensaje.ERROR);				
					request.setAttribute("mensaje", msg);				
					request.getRequestDispatcher("/JSP/Mensajes.jsp").forward(request, response);
				}
				
			}
			else
			{
				Mensaje msg = new Mensaje("Usuario y/o contraseña incorrecta!!", TipoMensaje.ERROR);				
				request.setAttribute("mensaje", msg);				
				request.getRequestDispatcher("/JSP/Mensajes.jsp").forward(request, response);	
				//response.getWriter().print(texto);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
