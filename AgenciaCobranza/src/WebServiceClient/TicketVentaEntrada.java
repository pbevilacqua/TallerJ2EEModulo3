/**
 * TicketVentaEntrada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WebServiceClient;

public class TicketVentaEntrada  implements java.io.Serializable {
    private int agenciaNro;

    private int cantMin;

    private WebServiceClient.Date fchHraEst;

    private WebServiceClient.Date fchHraVenta;

    private java.lang.String matricula;

    public TicketVentaEntrada() {
    }

    public TicketVentaEntrada(
           int agenciaNro,
           int cantMin,
           WebServiceClient.Date fchHraEst,
           WebServiceClient.Date fchHraVenta,
           java.lang.String matricula) {
           this.agenciaNro = agenciaNro;
           this.cantMin = cantMin;
           this.fchHraEst = fchHraEst;
           this.fchHraVenta = fchHraVenta;
           this.matricula = matricula;
    }


    /**
     * Gets the agenciaNro value for this TicketVentaEntrada.
     * 
     * @return agenciaNro
     */
    public int getAgenciaNro() {
        return agenciaNro;
    }


    /**
     * Sets the agenciaNro value for this TicketVentaEntrada.
     * 
     * @param agenciaNro
     */
    public void setAgenciaNro(int agenciaNro) {
        this.agenciaNro = agenciaNro;
    }


    /**
     * Gets the cantMin value for this TicketVentaEntrada.
     * 
     * @return cantMin
     */
    public int getCantMin() {
        return cantMin;
    }


    /**
     * Sets the cantMin value for this TicketVentaEntrada.
     * 
     * @param cantMin
     */
    public void setCantMin(int cantMin) {
        this.cantMin = cantMin;
    }


    /**
     * Gets the fchHraEst value for this TicketVentaEntrada.
     * 
     * @return fchHraEst
     */
    public WebServiceClient.Date getFchHraEst() {
        return fchHraEst;
    }


    /**
     * Sets the fchHraEst value for this TicketVentaEntrada.
     * 
     * @param fchHraEst
     */
    public void setFchHraEst(WebServiceClient.Date fchHraEst) {
        this.fchHraEst = fchHraEst;
    }


    /**
     * Gets the fchHraVenta value for this TicketVentaEntrada.
     * 
     * @return fchHraVenta
     */
    public WebServiceClient.Date getFchHraVenta() {
        return fchHraVenta;
    }


    /**
     * Sets the fchHraVenta value for this TicketVentaEntrada.
     * 
     * @param fchHraVenta
     */
    public void setFchHraVenta(WebServiceClient.Date fchHraVenta) {
        this.fchHraVenta = fchHraVenta;
    }


    /**
     * Gets the matricula value for this TicketVentaEntrada.
     * 
     * @return matricula
     */
    public java.lang.String getMatricula() {
        return matricula;
    }


    /**
     * Sets the matricula value for this TicketVentaEntrada.
     * 
     * @param matricula
     */
    public void setMatricula(java.lang.String matricula) {
        this.matricula = matricula;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TicketVentaEntrada)) return false;
        TicketVentaEntrada other = (TicketVentaEntrada) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.agenciaNro == other.getAgenciaNro() &&
            this.cantMin == other.getCantMin() &&
            ((this.fchHraEst==null && other.getFchHraEst()==null) || 
             (this.fchHraEst!=null &&
              this.fchHraEst.equals(other.getFchHraEst()))) &&
            ((this.fchHraVenta==null && other.getFchHraVenta()==null) || 
             (this.fchHraVenta!=null &&
              this.fchHraVenta.equals(other.getFchHraVenta()))) &&
            ((this.matricula==null && other.getMatricula()==null) || 
             (this.matricula!=null &&
              this.matricula.equals(other.getMatricula())));
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
        _hashCode += getAgenciaNro();
        _hashCode += getCantMin();
        if (getFchHraEst() != null) {
            _hashCode += getFchHraEst().hashCode();
        }
        if (getFchHraVenta() != null) {
            _hashCode += getFchHraVenta().hashCode();
        }
        if (getMatricula() != null) {
            _hashCode += getMatricula().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TicketVentaEntrada.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "ticketVentaEntrada"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agenciaNro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agenciaNro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantMin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantMin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fchHraEst");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fchHraEst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fchHraVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fchHraVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://Controladores/", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matricula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matricula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
