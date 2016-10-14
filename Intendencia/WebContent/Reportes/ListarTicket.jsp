<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tickets Vendidos</title>

</head>

<body>
	<f:view>
		<h:form>
			<h:messages  />
			<h:dataTable value="#{controladorJSF.listaTicket}" var="item">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Número" />
					</f:facet>
					<h:outputText value="#{item.ticketNro}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Agencia" />
					</f:facet>
					<h:outputText value="#{item.agenciaNro}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Matricula" />
					</f:facet>
					<h:outputText value="#{item.matricula}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Estacionamiento" />
					</f:facet>
					<h:outputText value="#{item.fchHraEst}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Venta" />
					</f:facet>
					<h:outputText value="#{item.fchHraVenta}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Anulacion" />
					</f:facet>
					<h:outputText value="#{item.codAnul}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Fecha Anulacion" />
					</f:facet>
					<h:outputText value="#{item.fchHraVenta}" />
				</h:column>
			</h:dataTable>


<%-- 			<h:commandButton value="Aceptar" --%>
<%-- 				action="#{personaManagedBean.accion()}" /> --%>

			<%-- 				<h:outputText value="nombre" /> --%>
			<%-- 				<h:inputText value=" " /> --%>
			<%-- 				<h:outputText value="apellido" /> --%>
			<%-- 				<h:inputText value=" " /> --%>
			<%-- 				<h:outputText value="direccion" /> --%>
			<%-- 				<h:inputText value=" " /> --%>
			<%-- 				<h:commandButton value="Aceptar" action = "cualquiera"/> --%>

		</h:form>
	</f:view>
</body>
</html>