<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Proveedores"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.JSONProveedores"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
<title>Reporte de Proveedores</title>
</head>
<body>

	<div>
    <table align="center">
    <h2><b>Reporte de Proveedores</b></h2>
        <thead>
            <tr>
                <th scope="col">NIT Prov.</th>
                <th scope="col">Telefono</th>
                <th scope="col">Nombre proveedor</th>
                <th scope="col">Ciudad</th>
                <th scope="col">Dirección</th>
           </tr>
        </thead>
        <tbody>
        
        	<% try { ArrayList<Proveedores> lista = JSONProveedores.getJSON();
            	request.setAttribute("lista", lista);
            } catch (Exception e) {
            		e.printStackTrace();
            }%>
        
            <% ArrayList<Proveedores> lista = (ArrayList<Proveedores>) request.getAttribute("lista");
			for (Proveedores proveedor:lista){
			%>
			<tr>
				<td><%=proveedor.getNit_proveedor()%></td>
				<td><%=proveedor.getTelefono_proveedor()%></td>
				<td><%=proveedor.getNombre_proveedor()%></td>
				<td><%=proveedor.getCiudad_proveedor()%></td>
				<td><%=proveedor.getDireccion_proveedor()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
	</div>		
	
</body>
</html>