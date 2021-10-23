<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<title>Menú Mi Tiendita de Barrio</title>
</head>
<body>

	<div class="menubox">
	<h1 align="center">Menú Principal</h1>
	<table align="center">
		<tr>
			<td><p>Bienvenido ${usuario.getNombre_usuario()}</td>
			<td><p>Hoy es: <%= new java.util.Date() %></td>
		</tr>
	</table>
			
	<hr>
		<a href="Controlador?menu=Reportes" class="button" target="myFrame">Reportes</a>
		<a href="Controlador?menu=Ventas&accion=default&UsuarioActivo=${usuario.getCedula_usuario()}" class="button" target="myFrame">Ventas</a>
  		<a href="Controlador?menu=Productos" class="button" target="myFrame">Productos</a>
  		<a href="Controlador?menu=Proveedores&accion=Listar" class="button" target="myFrame">Proveedores</a>
  		<a href="Controlador?menu=Clientes&accion=Listar" class="button" target="myFrame">Clientes</a>
  		<a href="Controlador?menu=Usuarios&accion=Listar" class="button" target="myFrame">Usuarios</a>
  		
	</div>
	
	<div class="embed-container">
		<iframe name="myFrame">
		</iframe>
	</div>
</body>
</html>