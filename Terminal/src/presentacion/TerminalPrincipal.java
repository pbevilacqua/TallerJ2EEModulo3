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
   
    
    public TerminalPrincipal(){
 
        JFrame jfM = new JFrame("Terminal Agencia Cobranza");  
        jfM.setLayout(null);
        jp_principal = JPanelPrincipal.getInstance();
        jp_principal.setBounds(10, 10, 780, 680);
        jfM.add(jp_principal); 
        jfM.setLocation(100, 50);
        jfM.setResizable(true);
        jfM.setVisible(true);
        jfM.setSize(800, 700);
        jfM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
    	
		TerminalPrincipal tp = new TerminalPrincipal();
	}




}
    
    