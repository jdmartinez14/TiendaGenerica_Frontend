<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.Ventas"%>
<%@ page import="co.edu.unbosque.ciclo3TiendaGen.JSONVentas"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reporte Ventas</title>
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
</head>
<body>

<div align="center">
    <table >
    <h2><b>Reporte de Ventas</b></h2>
        <thead>
            <tr>
                <th scope="col">Código Venta</th>
                <th scope="col">Cédula Cliente</th>
                <th scope="col">Cédula Usuario</th>
                <th scope="col">Iva Venta</th>
                <th scope="col">Valor Venta</th>
                <th scope="col">Total Venta</th>
           </tr>
        </thead>
        <tbody>
        
        	<% try { ArrayList<Ventas> lista = JSONVentas.getJSON();
            		request.setAttribute("lista", lista);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
        	%>
        	
			<% ArrayList<Ventas> lista = (ArrayList<Ventas>) request.getAttribute("lista");
			for (Ventas venta:lista){
			%>
			<tr>
				<td><%=venta.getCodigo_venta()%></td>
				<td><%=venta.getCedula_cliente()%></td>
				<td><%=venta.getCedula_usuario()%></td>
				<td><%=venta.getIva_venta()%></td>
				<td><%=venta.getValor_venta()%></td>
				<td><%=venta.getTotal_venta()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
	</div>		
</body>
</html>