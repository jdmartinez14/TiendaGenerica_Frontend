<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Proveedores"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
<title>Proveedores</title>
</head>
<body>

	<div class="menubox2">
	<h2>Gestión de Proveedores</h2>
	<hr>
	<form method="get" action="Controlador">
	
		<input type="hidden" name="menu" value="Proveedores">		
		<table id="table1" align="center">
		<tr> 
			<td colspan=2><label id="icon" for="name"><img src="./images/driver-license.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="nit_proveedor" placeholder="NIT Proveedor" value="${usuarioSeleccionado.getNit_proveedor()}" required/></td>
  		
			<td colspan=2><label id="icon" for="name"><img src="./images/phone-call.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="telefono_proveedor" placeholder="Teléfono" value="${usuarioSeleccionado.getTelefono_proveedor()}" required/></td>
		</tr>
		
		<tr> 
			<td colspan=2><label id="icon" for="name"><img src="./images/id-card.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="nombre_proveedor" placeholder="Nombre Proveedor" value="${usuarioSeleccionado.getNombre_proveedor()}" required/></td>
			
			<td colspan=2><label id="icon" for="name"><img src="./images/buildings.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="ciudad_proveedor" placeholder="Ciudad" value="${usuarioSeleccionado.getCiudad_proveedor()}" required/></td>
		</tr>
		
		<tr> 
			<td colspan=2><label id="icon" for="name"><img src="./images/navigator.png" width="17px" height="17px"><i class="icon-user"></i></label>
  			<input type="text" name="direccion_proveedor" placeholder="Dirección" value="${usuarioSeleccionado.getDireccion_proveedor()}" required/></td>
		</tr>
	
		<tr>
			<td style="padding-top:10px;"><input type="submit" class="button" name="accion" value="Crear"></td>
  			<td style="padding-top:10px;"><input type="submit" class="button" name="accion" value="Actualizar"></td>
  			<td style="padding-top:10px;"><input type="submit" class="button" name="accion" value="Eliminar"></td>
		</tr>	
		
		</table>
			
	</form>
					
	</div>
	
</body>
</html>