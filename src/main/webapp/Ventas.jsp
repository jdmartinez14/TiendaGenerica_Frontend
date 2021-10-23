<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<link rel="stylesheet" type="text/css" href="StylesTablas.css">
<title>Ventas</title>
</head>
<body>

	<div class="menubox3">
	<h2>Gestión de Ventas</h2>
	<hr>
	
	<form method="get" action="Controlador">	
	
		<input type="hidden" name="menu" value="Ventas">
		<input type="hidden" name="UsuarioActivo" value="${usuarioSeleccionado.getCedula_usuario()}">
		<table id="table2">
		<tr style="background-color:#ebebeb"> 
			<td colspan="2" width="100" style="padding-left: 10px; padding-right: 10px; padding-bottom:0px; padding-top:0px;"><input type="text" name="cedulacliente" placeholder="Cedula Cliente" value="${clienteSeleccionado.getCedula_cliente()}"></td>
  			<td width="60" style="padding-top: 10px;"><input type="submit" class="button2" name="accion" value="BuscarCli"></td>
  			<td colspan="3" width="100" style="padding-left: 10px; padding-right: 10px;"><input type="text" name="nombrecliente" value="${clienteSeleccionado.getNombre_cliente()}"/></td>
  			<td width="110" style="padding-left: 10px; padding-right: 10px;"><input type="text" name="UsuarioActivo" placeholder="Consecutivo" style="width: 75x;" value="${usuario.getCedula_usuario()}"/></td>
		</tr>
		<tr><td><br></td></tr>
		<tr style="background-color:#ebebeb">
			<td style="padding-left: 6px;">Cod Producto</td>
			<td style="padding-left: 6px;"></td>
			<td style="padding-left: 6px;">Nombre Producto</td>
			<td style="padding-left: 6px;">Precio</td>
			<td style="padding-left: 6px;">Iva</td>
			<td style="padding-left: 6px;">Cant</td>
		</tr>
		<tr style="background-color:#ebebeb"> 
  			<td width="10" style="padding-left: 10px; padding-right: 10px; padding-bottom:0px; padding-top:0px;"><input style="width: 60px;" type="text" name="codigoproducto" placeholder="Cod Producto" value="${productoSeleccionado.getCodigo_producto()}"></td>
			<td width="60" style="padding-bottom:0px; padding-top:10px;"><input type="submit" class="button2" name="accion" value="BuscarPro"></td>
			<td width="100" style="padding-left: 10px; padding-right: 1px; padding-bottom:0px; padding-top:0px;"><input style="width: 95px;" type="text" name="nombreproducto" placeholder="Nom Producto" value="${productoSeleccionado.getNombre_producto()}"/></td>
			<td width="30" style="padding-left: 10px; padding-right: 10px; padding-bottom:0px; padding-top:0px;"><input type="text" name="precioproducto" placeholder="Precio" style="width: 65px;" value="${productoSeleccionado.getPrecio_venta()}"/></td>
			<td width="30" style="padding-left: 10px; padding-right: 10px; padding-bottom:0px; padding-top:0px;"><input type="text" name="ivaproducto" placeholder="Iva" style="width: 65px;" value="${productoSeleccionado.getIva_compra()}"/></td>
			<td width="30" style="padding-left: 10px; padding-right: 10px; padding-bottom:0px; padding-top:0px;"><input type="text" name="cantidadproducto" placeholder="Cant" style="width: 55px;"/></td>
		</tr>

		<tr style="background-color:#ebebeb"> 
  			<td width="60" style="padding-bottom:0px; padding-top:10px;"><input type="submit" class="button2" name="accion" value="AgregarProducto"></td>
		</tr>

		<tr style="background-color:#ebebeb"> 
  			<td colspan="4" align="right" style="padding-right: 15px; padding-top:12px;"><label>Subtotal:</label>
			<td width="90" style="padding-left: 10px; padding-right: 10px;"><input disabled="disabled" type="text" name="txtsubtotal" placeholder="0.0" style="width: 100px;" value="${totalsubtotal}"></td>
		</tr>
		
		<tr style="background-color:#ebebeb"> 
  			<td colspan="4" align="right" style="padding-right: 15px; padding-top:12px;"><label>Total IVA:</label>
			<td width="90" style="padding-left: 10px; padding-right: 10px;"><input disabled="disabled" type="text" name="txttotaliva" placeholder="0.0" style="width: 100px;" value="${totaliva}"></td>
		</tr>
		
		<tr style="background-color:#ebebeb"> 
  			<td colspan="4" align="right" style="padding-right: 15px; padding-top:12px;"><label>Total Venta:</label>
			<td width="90" style="padding-left: 10px; padding-right: 10px;"><input disabled="disabled" type="text" name="txttotalapagar" placeholder="0.0" style="width: 100px;" value="${totalapagar}"></td>
		</tr>
		</table>
	</form>
	</div>

			
	<div>
		<div>
			<div>
				<div align="center">
				<label>Número Factura</label>
				<input type="text" name="numerofactura" value="${numerofactura}">
				</div>			
			</div>
			<div align="center">
				<table>
					<thead>
						<tr>
							<th>#</th>
							<th>Código</th>
							<th>Producto</th>
							<th>Precio</th>
							<th>Cantidad</th>
							<th>Iva</th>
							<th>Total</th>			
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="lista" items="${listaventas}">
						<tr>
							<th>${lista.getCodigo_detalle_venta()}</th>
							<th>${lista.getCodigo_producto()}</th>
							<th>${lista.getDescripcion_producto()}</th>
							<th>${lista.getPrecio_producto()}</th>
							<th>${lista.getCantidad_producto()}</th>
							<th>${lista.getIva_venta()}</th>
							<th>${lista.getValor_venta()}</th>			
						</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
							
		</div>
		
			<div align="center">
				<div>
				<a onclick="print()" href="Controlador?menu=Ventas&accion=GenerarVenta&cedulacliente=${clienteSeleccionado.getCedula_cliente()}&UsuarioActivo=${usuarioSeleccionado.getCedula_usuario()}&numerofactura=${numerofactura}">Generar Venta</a>
				<a onclick="print()" href="Controlador?menu=Ventas&accion=NuevaVenta">Nueva Venta</a>
				</div>
				
			</div>

	</div>

</body>
</html>