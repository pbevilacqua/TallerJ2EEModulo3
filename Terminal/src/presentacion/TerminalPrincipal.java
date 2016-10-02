package presentacion;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TerminalPrincipal {
 
    JPanel jp_principal;
    JButton jb_compraTicketb;   
    JToolBar tb;
    ImageIcon jb_compraTicket;
    public JPanel jp_compra_ticket;
    
    
    public TerminalPrincipal(){
 
        JFrame jfM = new JFrame("Terminal Agencia Cobranza");  
        jfM.setLayout(null);
        bordJP(); //invoca el metodo que contiene el panel
        jp_principal.setBounds(10, 10, 780, 680);
        jfM.add(jp_principal); 
        jfM.setLocation(100, 50);
        jfM.setResizable(true);
        jfM.setVisible(true);
        jfM.setSize(800, 700);
        jfM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void bordJP(){
    	jp_principal = new JPanel(new BorderLayout(2, 3));
   	
        jb_compraTicket= new ImageIcon("iconos\\parking.png");
        
        jb_compraTicketb = new JButton(jb_compraTicket);

 
        tb = new JToolBar();
        tb.add(jb_compraTicketb);

        
        jp_principal.add(tb, BorderLayout.NORTH);
        jp_principal.setVisible(true);
        
        
        
        // ActionListener CompraTicket
        jb_compraTicketb.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent event) {

            	jp_principal.removeAll();
            	jp_principal.setLayout(new BorderLayout(2, 3));
            	jp_principal.add(tb, BorderLayout.NORTH); //boton al panel norte
            	
            	jp_compra_ticket = JPanelCompraTicket.getInstance();//Singleton del panel de Compra Ticket
            	jp_principal.add(jp_compra_ticket);
            	

        		jp_principal.revalidate();
        		jp_principal.repaint();
        		
            }

        });
        jp_principal.setVisible(true);   
        
   
        
    }
 

	public static void main(String[] args) {
    	
		TerminalPrincipal tp = new TerminalPrincipal();
	}




}
    
    