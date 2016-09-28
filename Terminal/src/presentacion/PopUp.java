package presentacion;


import javax.swing.JOptionPane;

public class PopUp
{

 public static void infoBox(String infoMessage, String titleBar, int tipoMsg)
 {
 JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, tipoMsg);
 }
}
 
