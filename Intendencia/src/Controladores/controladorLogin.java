package Controladores;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.password.Password;

import DataTypes.Login;

@ManagedBean(name="controladorLogin")
public class controladorLogin {
	
	private String usuario;
	private String contrase�a;
	private Password password;
	public Password getPassword() {
		return password;
	}
	public void setPassword(Password password) {
		this.password = password;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	public String accion(){
		ControladorTicket ct = new ControladorTicket();
		if (ct.existeUsuario(usuario, contrase�a)){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); 
			Login login = new Login();
			login.setUsuario(usuario);
			session.setAttribute("login", login);
			return "/Reportes/ListarTicket.xhtml";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contrase�a incorrectos", ""));
			//contrase�a = null;
			return null;
		}
	}
	
	
}
