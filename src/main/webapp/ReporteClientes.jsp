<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Clientes"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.JSONClientes"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reporte Clientes</title>
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
</head>
<body>

<div>
    <table align="center">
    <h2><b>Reporte de Clientes</b></h2>
        <thead>
            <tr>
                <th scope="col">Cedula</th>
                <th scope="col">Teléfono</th>
                <th scope="col">Nombre</th>
                <th scope="col">Email</th>
                <th scope="col">Dirección</th>
           </tr>
        </thead>
        <tbody>
        
        	<% try { ArrayList<Clientes> lista = JSONClientes.getJSON();
            		request.setAttribute("lista", lista);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
        	%>
        	
			<% ArrayList<Clientes> lista = (ArrayList<Clientes>) request.getAttribute("lista");
			for (Clientes cliente:lista){
			%>
			<tr>
				<td><%=cliente.getCedula_cliente()%></td>
				<td><%=cliente.getTelefono_cliente()%></td>
				<td><%=cliente.getNombre_cliente()%></td>
				<td><%=cliente.getEmail_cliente()%></td>
				<td><%=cliente.getDireccion_cliente()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
	</div>		

</body>
</html>