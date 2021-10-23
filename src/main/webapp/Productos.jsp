<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
<title>Productos</title>
<script type="text/javascript" src="./Codigos.js"></script>
</head>
<body>

	<div class="menubox2">
	<h2>Gestión de productos</h2>
	<hr>
	<form method="post" name="formulario" enctype="multipart/form-data">
		<input type="hidden" name="nombre" value="">
		<input type="hidden" name="nombre2" value="">	
		<table id="table1" align="center">
		<tr> 
			<td><label>Cargar un archivo</label>
  			<input type="file" name="archivo" id="archivo"></td>
		</tr>
		
		<tr> 
			<td><input type="submit" value="Procesar" name="Procesar" onclick="cargarArchivo(archivo)"/></td>
		</tr>
			
		</table>
	</form>
	</div>

	<!-- VALIDACIONES -->
						<div class="card bg-transparent border-0">
							<c:choose>
								<c:when test="${error == 0}">
									<div class="card-body bg-danger rounded" >
									<h5 class=text-center>Error: datos inválidos, Revise archivo plano</h5>
								</div>
								</c:when>
								<c:when test="${error == 1}">
									<div class="card-body bg-danger rounded">
									<h5 class=text-center>Error: no se seleccionó archivo para cargar</h5>
								</div>
								</c:when>
								<c:when test="${error == 2}">
									<div class="card-body bg-success rounded">
									<h5 class=text-center>Archivo Cargado Exitosamente</h5>
								</div>
								</c:when>
								<c:when test="${error == 3}">
								<div class="card-body bg-danger rounded">
									<h5 class=text-center>Error: formato de archivo inválido</h5>
								</div>
								</c:when>
								<c:when test="${error == 4}">
								<div class="card-body bg-danger rounded">
									<h5 class=text-center>Algunos registros no se cargaron: por favor revise si añadio el nit en modulo proveedores</h5>
								</div>
								</c:when>
								<c:when test="${error == 5}">
								<div class="card-body bg-danger rounded">
									<h5 class=text-center>Algunos registros no se han cargado</h5>
									<h5 class=text-center>Los medicamentos se han cargado</h5>
									<c:if test="${registros2 != '' }">
										<h5 class=text-center>,los medicamentos con los codigos: "${registros2}" ya existen en la base de datos</h5>
									</c:if>
									<c:if test="${registros == ''}">
										<h5 class=text-center>,los demas registros se cargaron correctamente</h5>
									</c:if>
									<c:if test="${registros != ''}">
										<h5 class=text-center>,hay registros donde el proveedor no existe en base de datos porfavor verifique el modulo proveedores</h5>
										<h5 class=text-center>el proveedor ${registros} no existe</h5>
									</c:if>
								</div>
								</c:when>
							</c:choose>
						</div>
							<iframe name="null" style="display: none;"></iframe>
		</div>
		
		<form action="ControladorProductos" method = "GET">
				<table align="center">
					<tr><td><input type="submit" name="Listar" value="Listar"></td></tr>
					<tr><td><br></td></tr>
				</table>
			</form>
			
		<div class="padre">
			<table align="center">
				<thead>
					<tr>
						<th>Codigo producto</th>
						<th>IVA compra</th>
						<th>NIT proveedor</th>
						<th>Nombre producto</th>
						<th>Precio compra</th>
						<th>Precio venta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="productos" items="${lista}">
						<tr>
							<td>${productos.getCodigo_producto()}</td>
							<td>${productos.getIva_compra()}</td>
							<td>${productos.getNit_proveedor()}</td>
							<td>${productos.getNombre_producto()}</td>
							<td>${productos.getPrecio_compra()}</td>
							<td>${productos.getPrecio_venta()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>				
</body>
</html>