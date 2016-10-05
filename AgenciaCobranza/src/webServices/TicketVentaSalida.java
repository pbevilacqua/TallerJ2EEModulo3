/**
 * TicketVentaSalida.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webServices;

public class TicketVentaSalida  implements java.io.Serializable {
    private float impTotal;

    private webServices.Mensaje mensaje;

    private int ticketNro;

    public TicketVentaSalida() {
    }

    public TicketVentaSalida(
           float impTotal,
           webServices.Mensaje mensaje,
           int ticketNro) {
           this.impTotal = impTotal;
           this.mensaje = mensaje;
           this.ticketNro = ticketNro;
    }


    /**
     * Gets the impTotal value for this TicketVentaSalida.
     * 
     * @return impTotal
     */
    public float getImpTotal() {
        return impTotal;
    }


    /**
     * Sets the impTotal value for this TicketVentaSalida.
     * 
     * @param impTotal
     */
    public void setImpTotal(float impTotal) {
        this.impTotal = impTotal;
    }


    /**
     * Gets the mensaje value for this TicketVentaSalida.
     * 
     * @return mensaje
     */
    public webServices.Mensaje getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this TicketVentaSalida.
     * 
     * @param mensaje
     */
    public void setMensaje(webServices.Mensaje mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the ticketNro value for this TicketVentaSalida.
     * 
     * @return ticketNro
     */
    public int getTicketNro() {
        return ticketNro;
    }


    /**
     * Sets the ticketNro value for this TicketVentaSalida.
     * 
     * @param ticketNro
     */
    public void setTicketNro(int ticketNro) {
        this.ticketNro = ticketNro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TicketVentaSalida)) return false;
        TicketVentaSalida other = (TicketVentaSalida) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.impTotal == other.getImpTotal() &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            this.ticketNro == other.getTicketNro();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Float(getImpTotal()).hashCode();
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        _hashCode += getTicketNro();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TicketVentaSalida.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "ticketVentaSalida"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("impTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "impTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "mensaje"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ticketNro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ticketNro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
