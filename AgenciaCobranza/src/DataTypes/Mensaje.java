package DataTypes;

public class Mensaje {
	
	private String mensaje;
	private TipoMensaje tipo;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public TipoMensaje getTipo() {
		return tipo;
	}
	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}
	
	public Mensaje (String mensaje, TipoMensaje tipo){
		this.mensaje = mensaje;
		this.tipo = tipo;
	}
}
