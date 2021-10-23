<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Usuarios"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.TestJSON"%>
<%@ page import="java.util.ArrayList"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
<title>Usuarios</title>
</head>
<body>

	<div class="menubox2">
	<h2>Gestión de usuarios</h2>
	<hr>
	<form method="get" action="Controlador">
	
		<input type="hidden" name="menu" value="Usuarios">	
		<table id="table1" align="center">
		<tr> 
			<td colspan=2><label id="icon" for="name"><img src="./images/driver-license.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="cedula_usuario" placeholder="Cédula Usuario" value="${usuarioSeleccionado.getCedula_usuario()}" required/></td>
  		
			<td colspan=2><label id="icon" for="name"><img src="./images/user.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="usuario" placeholder="Usuario" value="${usuarioSeleccionado.getUsuario()}" required/></td>
		</tr>
		
		<tr> 
			<td colspan=2><label id="icon" for="name"><img src="./images/id-card.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="nombre_usuario" placeholder="Nombre Completo" value="${usuarioSeleccionado.getNombre_usuario()}" required/></td>
			
			<td colspan=2><label id="icon" for="name"><img src="./images/padlock.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="password" name="password_usuario" placeholder="Contraseña" value="${usuarioSeleccionado.getPassword_usuario()}" required/></td>
		</tr>
		
		<tr> 
			<td colspan=2><label id="icon" for="name"><img src="./images/mail.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="email_usuario" placeholder="Email" value="${usuarioSeleccionado.getEmail_usuario()}" required/></td>
		</tr>
		
		<tr>
			<td style="padding-top:10px;"><input type="submit" class="button" name="accion" value="Crear"></td>
			<td style="padding-top:10px;"><input type="submit" class="button" name="accion" value="Actualizar"></td>
		</tr>	
		
		</table>
			
	</form>
		
	</div>
	
</body>
</html>