package Controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;

import DataTypes.Ticket;

@ManagedBean(name="controladorJSF")
public class ControladorJSF {
    private ArrayList<Ticket> listaTickets = new ArrayList<Ticket>();
    private Date fechaDesde;
    private Date fechaHasta;
    private float importeTotal;
    private float total = 0;
    
    public ControladorJSF(){
        System.out.println("Inicio");
    	fechaHasta = new Date();
    	fechaDesde = new Date(fechaHasta.getTime() - 7 * 1000 * 60 * 60 * 24);
    	
    	
        initList();
    }
 
    private void initList(){
    	ControladorTicket ct = new ControladorTicket();
    	listaTickets = ct.listarTicket(fechaDesde, fechaHasta);
    	sumarImportes();
    	
    }
    
    public ArrayList<Ticket> getListaTicket() {
    return listaTickets;
    }
 
    public void setListaTicket(ArrayList<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public String accion(){

		initList();
		return null;
	}
	
	public float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	private void sumarImportes(){
		Iterator<Ticket> it = listaTickets.iterator();
		importeTotal = 0;
		while(it.hasNext()){
			importeTotal += it.next().getImpTotal();
		}
	}
    
    
}
