<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.Date" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<title>Mi tiendita de barrio</title>
</head>
<body>

<h1 align="center">mi tiendita de barrio</h1>

<h6 align="center"> Hoy es:
<%= new java.util.Date() %>
</h6>

	<div class="testbox">
	<h2>Registro</h2>
	<hr>
	<form method="get" action="./DemoServlet">	
		<label id="icon" for="name"><img src="./images/user.png" width="17px" height="17px"><i class="icon-user"></i></label>
  		<input type="text" name="nombre" placeholder="Nombre"/>
		<label id="icon" for="name"><img src="./images/padlock.png" width="17px" height="17px"><i class="icon-shield"></i></label>
  		<input type="password" name="password" placeholder="Contraseña"/>
  		<p><input type="submit" name="accion" value="Enviar" class="button"></p>
	</form>
	</div>
	
</body>
</html>