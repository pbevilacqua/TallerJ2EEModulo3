package Controladores;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DataTypes.Login;

@ManagedBean(name="controladorLogin")
public class controladorLogin {
	
	private String usuario;
	private String contraseña;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	public void controlCarga() throws IOException, ServletException{
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if(request.getMethod().equals("GET")){
			request.getSession(true);
		}
	}	
	
	public String accion(){
		ControladorTicket ct = new ControladorTicket();
		if (ct.existeUsuario(usuario, contraseña)){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); 
			Login login = new Login();
			login.setUsuario(usuario);
			session.setAttribute("login", login);
			return "/Reportes/ListarTicket.xhtml";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contraseña incorrectos", ""));
			//contraseña = null;
			return null;
		}
	}
}
