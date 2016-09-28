package WebServiceClient;

public class ControladorTicketProxy implements WebServiceClient.ControladorTicket {
  private String _endpoint = null;
  private WebServiceClient.ControladorTicket controladorTicket = null;
  
  public ControladorTicketProxy() {
    _initControladorTicketProxy();
  }
  
  public ControladorTicketProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorTicketProxy();
  }
  
  private void _initControladorTicketProxy() {
    try {
      controladorTicket = (new WebServiceClient.ControladorTicketServiceLocator()).getControladorTicketPort();
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
  
  public WebServiceClient.ControladorTicket getControladorTicket() {
    if (controladorTicket == null)
      _initControladorTicketProxy();
    return controladorTicket;
  }
  
  public WebServiceClient.TicketVentaSalida venderTicket(WebServiceClient.TicketVentaEntrada arg0) throws java.rmi.RemoteException{
    if (controladorTicket == null)
      _initControladorTicketProxy();
    return controladorTicket.venderTicket(arg0);
  }
  
  
}