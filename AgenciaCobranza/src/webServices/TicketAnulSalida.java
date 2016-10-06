/**
 * TicketAnulSalida.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webServices;

public class TicketAnulSalida  implements java.io.Serializable {
    private int codAnul;

    private java.util.Calendar fchHraAnul;

    private webServices.Mensaje mensaje;

    public TicketAnulSalida() {
    }

    public TicketAnulSalida(
           int codAnul,
           java.util.Calendar fchHraAnul,
           webServices.Mensaje mensaje) {
           this.codAnul = codAnul;
           this.fchHraAnul = fchHraAnul;
           this.mensaje = mensaje;
    }


    /**
     * Gets the codAnul value for this TicketAnulSalida.
     * 
     * @return codAnul
     */
    public int getCodAnul() {
        return codAnul;
    }


    /**
     * Sets the codAnul value for this TicketAnulSalida.
     * 
     * @param codAnul
     */
    public void setCodAnul(int codAnul) {
        this.codAnul = codAnul;
    }


    /**
     * Gets the fchHraAnul value for this TicketAnulSalida.
     * 
     * @return fchHraAnul
     */
    public java.util.Calendar getFchHraAnul() {
        return fchHraAnul;
    }


    /**
     * Sets the fchHraAnul value for this TicketAnulSalida.
     * 
     * @param fchHraAnul
     */
    public void setFchHraAnul(java.util.Calendar fchHraAnul) {
        this.fchHraAnul = fchHraAnul;
    }


    /**
     * Gets the mensaje value for this TicketAnulSalida.
     * 
     * @return mensaje
     */
    public webServices.Mensaje getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this TicketAnulSalida.
     * 
     * @param mensaje
     */
    public void setMensaje(webServices.Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TicketAnulSalida)) return false;
        TicketAnulSalida other = (TicketAnulSalida) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codAnul == other.getCodAnul() &&
            ((this.fchHraAnul==null && other.getFchHraAnul()==null) || 
             (this.fchHraAnul!=null &&
              this.fchHraAnul.equals(other.getFchHraAnul()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
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
        _hashCode += getCodAnul();
        if (getFchHraAnul() != null) {
            _hashCode += getFchHraAnul().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TicketAnulSalida.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "ticketAnulSalida"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codAnul");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codAnul"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fchHraAnul");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fchHraAnul"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "mensaje"));
        elemField.setMinOccurs(0);
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
