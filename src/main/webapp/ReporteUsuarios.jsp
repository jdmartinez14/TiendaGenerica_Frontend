<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Usuarios"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.TestJSON"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reporte de Usuarios</title>
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
</head>
<body>

<div>

    <table align="center">
    <h2><b>Reporte de Usuarios</b></h2>
        <thead class="table1">
            <tr>
                <th scope="col">Cedula</th>
                <th scope="col">Nombre</th>
                <th scope="col">Email</th>
                <th scope="col">Usuario</th>
                <th scope="col">Password</th>
           </tr>
        </thead>
        <tbody>
        
        	<% try { ArrayList<Usuarios> lista = TestJSON.getJSON();
            			request.setAttribute("lista", lista);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
        	%>
        	
            <% ArrayList<Usuarios> lista= (ArrayList<Usuarios>) request.getAttribute("lista");
			for (Usuarios usuario:lista){
			%>
			<tr>
				<td><%=usuario.getCedula_usuario()%></td>
				<td><%=usuario.getNombre_usuario()%></td>
				<td><%=usuario.getEmail_usuario()%></td>
				<td><%=usuario.getUsuario()%></td>
				<td><%=usuario.getPassword_usuario()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>
</body>
</html>