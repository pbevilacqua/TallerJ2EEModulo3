package Controladores;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;

import DataTypes.Ticket;

@ManagedBean
public class ControladorJSF {
    private ArrayList<Ticket> listaTickets = new ArrayList<Ticket>();
    
    public ControladorJSF(){
        System.out.println("Inicio");
        initList();
    }
 
    private void initList(){
    	ControladorTicket ct = new ControladorTicket();
    	Date fechaDesde = new Date(0);
    	Date fechaHasta = new Date();
    	listaTickets = ct.listarTicket(fechaDesde, fechaHasta);
    	
    }
    
    public ArrayList<Ticket> getListaTicket() {
    return listaTickets;
    }
 
    public void setListaTicket(ArrayList<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }
}
