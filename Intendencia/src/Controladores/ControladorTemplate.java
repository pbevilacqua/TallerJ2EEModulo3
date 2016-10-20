package Controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="controladorTemplate")
public class ControladorTemplate {
	
	
	
	public String salir(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 			
		return "/Login/Login.xhtml?faces-redirect=true";
	}
}
