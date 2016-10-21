<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="DataTypes.*"
	import="capa_compartida.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agencia de Cobranza</title>
<link rel="stylesheet" type="text/css"
	href="/TallerJ2EE2016/recursos_estaticos/estilos/estilos.css"
	media="all">
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
					<li><a href="/AgenciaCobranza/JSP/ListarTotalesDiarios.jsp">Listar
							Totales Diarios</a></li>

				</ul></li>
			<li><a href=""><img
					src="/AgenciaCobranza/recursos_estaticos/imagenes/alt-usuario-icono.png"
					alt="Icono Usuario" class="usuario_img" /><%= login.getUsuario() %>
			</a>
				<ul>
					<li><a href="/AgenciaCobranza/ServletCerrarSesion">Cerrar
							Sesión</a></li>
				</ul></li>
		</ul>
		<% } %>
	</div>
	<div class="cuerpo">
		<div class="contenedor">
			<div class="msg_contenedor">
				<%
			Mensaje msg = (Mensaje) request.getAttribute("mensaje");

			if(msg != null){
				switch(msg.getTipo()){
					case ERROR:
			%>
				<div class="msg_error">
					<div style="float: left">
						<img src="/TallerJ2EE2016/recursos_estaticos/imagenes/error.png" />
					</div>
					<div style="float: left">
						<h2>Error</h2>
						<label><%= msg.getMensaje() %></label>
					</div>
				</div>
				<%
						break;
					case EXITO:
			%>
				<div class="msg_exito">
					<div style="float: left">
						<img src="/TallerJ2EE2016/recursos_estaticos/imagenes/success.png" />
					</div>
					<div style="float: left">
						<h2>Éxito</h2>
						<label><%= msg.getMensaje() %></label>
					</div>
				</div>
				<%						
						break;
					case INFORMACION:
			%>
				<div class="msg_info">
					<div style="float: left">
						<img src="/TallerJ2EE2016/recursos_estaticos/imagenes/info.png" />
					</div>
					<div style="float: left">
						<h2>Información</h2>
						<label><%= msg.getMensaje() %></label>
					</div>
				</div>
				<%						
						break;						
				}
			}
			%>
			</div>
		</div>
	</div>
	<div class="pie_pagina">
		<label class="copyright">Copyright © 2016 | Curso Antel JEE - Patricia Bevilacqua - Gonzalo Martinez</ a></label>
	</div>
</body>

</html>