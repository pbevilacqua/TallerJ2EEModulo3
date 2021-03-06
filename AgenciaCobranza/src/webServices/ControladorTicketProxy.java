package webServices;

public class ControladorTicketProxy implements webServices.ControladorTicket {
  private String _endpoint = null;
  private webServices.ControladorTicket controladorTicket = null;
  
  public ControladorTicketProxy() {
    _initControladorTicketProxy();
  }
  
  public ControladorTicketProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorTicketProxy();
  }
  
  private void _initControladorTicketProxy() {
    try {
      controladorTicket = (new webServices.ControladorTicketServiceLocator()).getControladorTicketPort();
      if (controladorTicket != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)controladorTicket)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)controladorTicket)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (controladorTicket != null)
      ((javax.xml.rpc.Stub)controladorTicket)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webServices.ControladorTicket getControladorTicket() {
    if (controladorTicket == null)
      _initControladorTicketProxy();
    return controladorTicket;
  }
  
  public webServices.TicketAnulSalida anulacionTicket(int arg0, int arg1) throws java.rmi.RemoteException{
    if (controladorTicket == null)
      _initControladorTicketProxy();
    return controladorTicket.anulacionTicket(arg0, arg1);
  }
  
  public webServices.TicketVentaSalida venderTicket(webServices.TicketVentaEntrada arg0) throws java.rmi.RemoteException{
    if (controladorTicket == null)
      _initControladorTicketProxy();
    return controladorTicket.venderTicket(arg0);
  }
  
  
}