<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Usuarios"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Clientes"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Proveedores"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reportes</title>
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
</head>
<body>

<div class="menubox2">
	<h2>Gestión de Reportes</h2>
	<hr>
	
	<form method="get" action="Controlador">
	<input type="hidden" name="menu" value="Reportes">
	<table id="table1">
		<tr>
			<td width="100" style="padding-left: 10px; padding-right: 10px;"><a href="http://localhost:8080/Ciclo3TiendaGen/ReporteUsuarios.jsp" class="button" target="_blank">Usuarios</a></td>
  			<td width="100" style="padding-left: 10px; padding-right: 10px;"><a href="http://localhost:8080/Ciclo3TiendaGen/ReporteClientes.jsp" class="button" target="_blank">Clientes</a></td>
		</tr>
		<tr> 
			<td width="100" style="padding-left: 10px; padding-right: 10px;"><a href="http://localhost:8080/Ciclo3TiendaGen/ReporteProveedores.jsp" class="button" target="_blank">Proveedores</a></td>
  			<td width="100" style="padding-left: 10px; padding-right: 10px;"><a href="http://localhost:8080/Ciclo3TiendaGen/ReporteVentas.jsp" class="button" target="_blank">Ventas</a></td>
		</tr>
	</form>	
	</table>
</div>
	

</body>
</html>