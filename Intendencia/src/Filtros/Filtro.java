package Filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DataTypes.Login;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/Filtro")
public class Filtro implements Filter {

	/**
	 * Default constructor. 
	 */
	public Filtro() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest htsr = (HttpServletRequest) request;
		HttpSession session = htsr.getSession(); 
		DataTypes.Login login = (Login)session.getAttribute("login");
		if (login != null){
			chain.doFilter(request, response);	
		}
		else{
			//Vuelvo al login
			request.getRequestDispatcher("/Login/Login.xhtml").forward(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
