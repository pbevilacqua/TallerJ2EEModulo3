<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    
    import="controladorAgencia.*"
    import="presentacion.*"
    import="DataTypes.*"
    import="capa_negocio.*" 
    import="java.util.*"
    import="java.text.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>LISTADO DE VENTAS</title>
	<link rel="stylesheet" type="text/css" href="/AgenciaCobranza/recursos_estaticos/estilos/estilos.css" media="all">
</head>

<body>
	<div class="cabezal">
		<%  Login login = (Login)request.getSession().getAttribute("login");
			if(login != null){
		%>
		<ul class="nav">
			<li><a href="/AgenciaCobranza/JSP/Inicio.jsp">Inicio</a></li>
			<li><a href="">Ventas</a>
				<ul>
					<li><a href="/AgenciaCobranza/JSP/ListarTotalesDiarios.jsp">Listar Totales Diarios</a></li>	
									
				</ul>
			</li>
			<li><a href=""><img src="/AgenciaCobranza/recursos_estaticos/imagenes/alt-usuario-icono.png" alt="Icono Usuario" class="usuario_img" /><%= login.getUsuario() %>
			</a>
				<ul>
					<li><a href="/AgenciaCobranza/ServletCerrarSesion">Cerrar Sesión</a></li>
				</ul></li>
		</ul>
		<% } %>	
	</div>

	<%
		String fd = "";
		String fh = "";
		ArrayList<Ticket> tickets = null;
		
		   Enumeration<String> paramNames = request.getParameterNames();

		   while(paramNames.hasMoreElements()) {
		      String paramName = (String)paramNames.nextElement();
		      System.out.print(paramName + " : ");
		      String paramValue = request.getParameter(paramName);
		      System.out.println(paramValue);
		   }		
		
		if(request.getMethod().equals("POST")){
			fd = (String)request.getParameter("fechaDesde");
			fh = (String)request.getParameter("fechaHasta");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			
			Date fechaDesde = formatter.parse(fd);
			Date fechaHasta = formatter.parse(fh);
			ControladorAgencia ca = new ControladorAgencia();
			tickets = ca.obtenerVentasPorFecha(fechaDesde, fechaHasta);
		}
	
	%>
	<div class="cuerpo">
		<div class="contenedor">
	
		  <fieldset style="agrupacion">
		    <legend>VENTAS</legend>
		    	<form action="/AgenciaCobranza/JSP/ListarTotalesDiarios.jsp" method="post">
		    		<div class="linea_formulario">
						<label for="txtFechaDesde" class="campo_obligatorio">Fecha Desde</label>
					    <input id="txtFechaDesde" type="datetime-local" name="fechaDesde" size="60" value="<%= fd %>" required="required">	  
					</div>
					<div class="linea_formulario">
						<label for="txtFechaHasta">Fecha Hasta</label>
					    <input id="txtFechaHasta" type="datetime-local" name="fechaHasta" size="60" value="<%= fh %>" required="required">
					</div>
					<div class="linea_formulario">
						<input type="submit" value="Consultar">
					</div>
					<div class="contenedorListado">
						<table class="listados">
							
							
							<%	
					  if (tickets != null){
						  %>
						  <tr></tr>
						  <tr>
							<th>Ticket</th>
							<th>Matricula</th>
							<th>Fecha Hora Venta</th>
							<th>Fecha Hora Estacionamiento</th>
							<th>Cantidad de Minutos</th>
							<th>Importe Total</th>
							<th>Codigo de Anulación</th>
							<th>Fecha Hora Anulación</th>
							<th>Número de Terminal</th>

						</tr>
						  <%	
						  Iterator<Ticket> it = tickets.iterator();
						  
						  while(it.hasNext()){
								Ticket ticket = (Ticket)it.next();	
								
								DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
								String fhv = "";
								String fhe = "";
								String fha = "";
								
								if (ticket.getFchHraVenta() != null){
									fhv = df.format(ticket.getFchHraVenta());
								}
								if (ticket.getFchHraEst() != null){
									fhe = df.format(ticket.getFchHraEst());
								}
								if  (ticket.getFchHraAnul() != null){
								    fha = df.format(ticket.getFchHraAnul());
								}
								
							%>
							<tr>
								<td><%= ticket.getTicketNro()%></td>
								<td><%= ticket.getMatricula()%></td>
								<td><%= fhv%></td>
								<td><%= fhe%></td>
								<td><%= ticket.getCantMin()%></td>
								<td><%= ticket.getImpTotal()%></td>
								<td><%= ticket.getCodAnul()%></td>
								<td><%= fha%></td>
								<td><%= ticket.getTerminalNro() %></td>
							</tr>

							<%
							}
					  	} 
				  	%>
						</table>
					</div>
				</form>
		    </fieldset>
		</div>
	</div>
	<div class="pie_pagina">
		<label class="copyright">Copyright © 2016 | Curso Antel JEE - Patricia Bevilacqua - Gonzalo Martinez</ a></label>
	</div>	
</body>
</html>