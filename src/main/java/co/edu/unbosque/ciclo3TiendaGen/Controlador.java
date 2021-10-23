package co.edu.unbosque.ciclo3TiendaGen;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        public Controlador() {
          super();
        }
        
                
        //************* Variables Generales *************************
        
        long subtotal = 0, totalapagar = 0;
        long codproducto = 0, precio = 0, iva= 0, valor_iva = 0, subtotaliva = 0, acusubtotal = 0;
        long numfact = 0;
        long item = 0;
        int cant = 0;
        String descripcion, cedulacliente;
        List<DetalleVenta> listaventas = new ArrayList<>();
        Usuarios usuarios = new Usuarios();
        DetalleVenta detalle_venta = new DetalleVenta();
        
        //*****
        
        public void buscarCliente(Long id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	try {
        		ArrayList<Clientes> listac = JSONClientes.getJSON();
        		for (Clientes clientes:listac) {
        			if (clientes.getCedula_cliente()==(id)) {
        				request.setAttribute("clienteSeleccionado", clientes); 
        				}				
        		}
        	}catch (Exception e) {
        			e.printStackTrace();
        			}
        }
        
        public void buscarProducto(Long cod, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	try {
        		ArrayList<Productos> listap = JSONProductos.getJSON();
        		for (Productos productos:listap) {
        			if (productos.getCodigo_producto()==(cod)) {
        				request.setAttribute("productoSeleccionado", productos); }
        		}
        	}catch (Exception e) {
        			e.printStackTrace();
        			}
        }
        
        public void buscarFactura(String numFact, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	if (numFact==null) {
        		numfact = Integer.parseInt(numFact)+1;
        	}else {
        		numfact = Integer.parseInt(numFact)+1;
        	}
        	request.setAttribute("numerofactura", numfact);   	
        }
        
        public void grabarDetalle(Long numFact, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	for (int i=0; i<listaventas.size(); i++ ) {
        		detalle_venta = new DetalleVenta();
        		detalle_venta.setCodigo_detalle_venta(Long.valueOf(i+1));
        		detalle_venta.setCodigo_venta(numFact);
        		detalle_venta.setCodigo_producto(listaventas.get(i).getCodigo_producto());
        		detalle_venta.setCantidad_producto(listaventas.get(i).getCantidad_producto());
        		detalle_venta.setTotal_venta(listaventas.get(i).getTotal_venta());
        		detalle_venta.setValor_venta(listaventas.get(i).getValor_venta());
        		detalle_venta.setIva_venta(listaventas.get(i).getIva_venta());
        		detalle_venta.setDescripcion_producto(listaventas.get(i).getDescripcion_producto());
        		
        		int respuesta = 0;
        		try {
        			respuesta = JSONDetalleVenta.postJSON(detalle_venta);
        			PrintWriter write = response.getWriter();
        			if (respuesta == 200 ) {
        				System.out.println("Registros grabados detalle ventas");
        			}else {
        				write.println("Error Detalle Venta " + respuesta);
        			}
        			write.close();
        		}catch (Exception e) {
        			e.printStackTrace();
        			}
        		
        	}
        }
        
        public void factura(HttpServletRequest request, HttpServletResponse response) {
        	try {
        		ArrayList<Ventas> lista = JSONVentas.getJSON();
        		int numFac= lista.size();
        		numFac=numFac+1;
        		request.setAttribute("numerofactura", numFac);
        		//System.out.println(numFac);
        		//request.getRequestDispatcher("/JSP/ventas.jsp").forward(request, response);
        	} catch (Exception e){
        		e.printStackTrace();
        	}
        }
        
             
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
        	String menu = request.getParameter("menu");
        	String accion = request.getParameter("accion");

         
        
        	switch (menu) {
        		case "Principal":
        			request.getRequestDispatcher("/Principal.jsp").forward(request, response);
        			break;
        		case "Usuarios":
        			if (accion.equals("Listar")) {
            			try {
            				ArrayList<Usuarios> lista = TestJSON.getJSON();
            				request.setAttribute("lista", lista);
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
            		}else if(accion.equals("Crear")) {
    				     Usuarios usuario = new Usuarios();
    				     usuario.setCedula_usuario(Long.parseLong(request.getParameter("cedula_usuario")));
    				     usuario.setNombre_usuario(request.getParameter("nombre_usuario"));
    				     usuario.setEmail_usuario(request.getParameter("email_usuario"));
    				     usuario.setUsuario(request.getParameter("usuario"));
    				     usuario.setPassword_usuario(request.getParameter("password_usuario"));
    							
    				     int respuesta=0;
    				     try {
    				    	 respuesta = TestJSON.postJSON(usuario);
    				    	 PrintWriter write = response.getWriter();
    				    	 if (respuesta==200) {
    				    		 request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
    				    	 } else {
    			   				write.println("Error: " +  respuesta);
    				    	 }
    			   			write.close();
    				     } catch (Exception e) {
    				    	 e.printStackTrace();
    				     }
            		}else if(accion.equals("Actualizar")) {
    				     Usuarios usuario = new Usuarios();
    			         usuario.setCedula_usuario(Long.parseLong(request.getParameter("cedula_usuario")));
    				     usuario.setNombre_usuario(request.getParameter("nombre_usuario"));
    				     usuario.setEmail_usuario(request.getParameter("email_usuario"));
    					 usuario.setUsuario(request.getParameter("usuario"));
    					 usuario.setPassword_usuario(request.getParameter("password_usuario"));
    					
    					 int respuesta=0;
    					 try {
    						 respuesta = TestJSON.putJSON(usuario,usuario.getCedula_usuario());
    						 PrintWriter write = response.getWriter();
    							
    						 if (respuesta==200) {
    							 request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
    						 } else {
    							 write.println("Error: " +  respuesta);
    						 }
    						 	write.close();
    					 } catch (Exception e) {
    						 e.printStackTrace();
    					 }
            		}else if(accion.equals("Cargar")) {
            			Long id= Long.parseLong(request.getParameter("id"));
            			try {
            				ArrayList<Usuarios> lista1 = TestJSON.getJSON();
            				System.out.println("Parametro: " + id);						
            				for (Usuarios usuarios:lista1){
            					if (usuarios.getCedula_usuario()==id) {
            						request.setAttribute("usuarioSeleccionado", usuarios);
            						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);	
            					}
            				}
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
            		}else if(accion.equals("Eliminar")) {
            			Long id= Long.parseLong(request.getParameter("id"));			
            			int respuesta=0;
            			try {
            				respuesta = TestJSON.deleteJSON(id);
            				PrintWriter write = response.getWriter();
            				if (respuesta==200) {
            					request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
            				} else {
            					write.println("Error: " +  respuesta);
            				}
            				write.close();
            			} catch (Exception e) {
            				e.printStackTrace();
            			}	
            		}
        			request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
        			break;
		     	case "Clientes":
		     		if (accion.equals("Listar")) {
            			try {
            				ArrayList<Clientes> lista = JSONClientes.getJSON();
            				request.setAttribute("lista", lista);
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
            		}else if(accion.equals("Crear")) {
     				     Clientes cliente = new Clientes();
      				     cliente.setCedula_cliente(Long.parseLong(request.getParameter("cedula_cliente")));
      				     cliente.setDireccion_cliente(request.getParameter("direccion_cliente"));
      				     cliente.setEmail_cliente(request.getParameter("email_cliente"));
      				     cliente.setNombre_cliente(request.getParameter("nombre_cliente"));
      				     cliente.setTelefono_cliente(request.getParameter("telefono_cliente"));
      							
      				     int respuesta=0;
      				     try {
      				    	 respuesta = JSONClientes.postJSON(cliente);
      				    	 PrintWriter write = response.getWriter();
      				    	 if (respuesta==200) {
      				    		 request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
      				    	 } else {
      			   				write.println("Error: " +  respuesta);
      				    	 }
      			   			write.close();
      				     } catch (Exception e) {
      				    	 e.printStackTrace();
      				     }
              		}else if(accion.equals("Eliminar")) {
            			Long id= Long.parseLong(request.getParameter("id"));			
            			int respuesta=0;
            			try {
            				respuesta = JSONClientes.deleteJSON(id);
            				PrintWriter write = response.getWriter();
            				if (respuesta==200) {
            					request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
            				} else {
            					write.println("Error: " +  respuesta);
            				}
            				write.close();
            			} catch (Exception e) {
            				e.printStackTrace();
            			}	
            		}
		     		request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
		     		break;
		     	case "Proveedores":	
		     		if (accion.equals("Listar")) {
            			try {
            				ArrayList<Proveedores> lista = JSONProveedores.getJSON();
            				request.setAttribute("lista", lista);
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
            		}
		     		else if(accion.equals("Crear")) {
     				     Proveedores proveedor = new Proveedores();
     				     proveedor.setNit_proveedor(Long.parseLong(request.getParameter("nit_proveedor")));
     				     proveedor.setTelefono_proveedor(request.getParameter("telefono_proveedor"));
     				   	 proveedor.setNombre_proveedor(request.getParameter("nombre_proveedor"));
     				   	 proveedor.setCiudad_proveedor(request.getParameter("ciudad_proveedor"));
     				 	 proveedor.setDireccion_proveedor(request.getParameter("direccion_proveedor"));
      							
      				     int respuesta=0;
      				     try {
      				    	 respuesta = JSONProveedores.postJSON(proveedor);
      				    	 PrintWriter write = response.getWriter();
      				    	 if (respuesta==200) {
      				    		 request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
      				    	 } else {
      			   				write.println("Error: " +  respuesta);
      				    	 }
      			   			write.close();
      				     } catch (Exception e) {
      				    	 e.printStackTrace();
      				     }
              		}else if(accion.equals("Eliminar")) {
            			Long id= Long.parseLong(request.getParameter("id"));			
            			int respuesta=0;
            			try {
            				respuesta = JSONProveedores.deleteJSON(id);
            				PrintWriter write = response.getWriter();
            				if (respuesta==200) {
            					request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
            				} else {
            					write.println("Error: " +  respuesta);
            				}
            				write.close();
            			} catch (Exception e) {
            				e.printStackTrace();
            			}	
            		}
		     		request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
		     		break;
		     	case "Productos":
		     		request.getRequestDispatcher("/Productos.jsp").forward(request, response);
		     		break;
		     	case "Ventas":
		     		
		     		this.factura(request, response);
		     	 	String cedula_usuario_activo = request.getParameter("UsuarioActivo");
		        	usuarios.setCedula_usuario(Long.parseLong(cedula_usuario_activo));
		        	request.setAttribute("usuarioSeleccionado", usuarios);
		        	
		     		//request.setAttribute("usuarioSeleccionado", usuarios);
		     		//request.setAttribute("numerofactura", numfact);
		     		
		     		if (accion.equals("BuscarCli")) {
		     			String id=request.getParameter("cedulacliente");
		     			this.buscarCliente(Long.parseLong(id), request, response);
            		}else if (accion.equals("BuscarPro")) {
            			String id=request.getParameter("cedulacliente");
		     			this.buscarCliente(Long.parseLong(id), request, response);
		     			
		     			String cod=request.getParameter("codigoproducto");
		     			this.buscarProducto(Long.parseLong(cod), request, response);
		     			
            		}else if (accion.equals("AgregarProducto")) {
            			try {
            				String id=request.getParameter("cedulacliente");
    		     			this.buscarCliente(Long.parseLong(id), request, response);
    		     			
    		     			detalle_venta = new DetalleVenta();
    		     			item++;
    		     			totalapagar=0;
    		     			acusubtotal=0;
    		     			subtotaliva=0;
    		     			codproducto=Long.parseLong(request.getParameter("codigoproducto"));
    		     			descripcion=request.getParameter("nombreproducto");	
    		     			precio=Long.parseLong(request.getParameter("precioproducto"));	
    		     			cant=Integer.parseInt(request.getParameter("cantidadproducto"));		
    		     			iva=Long.parseLong(request.getParameter("ivaproducto"));
    		     			subtotal=(precio*cant);
    		     			valor_iva=subtotal*iva/100;
    		     		
    		     			detalle_venta.setCodigo_detalle_venta(item);
    		     			detalle_venta.setCodigo_producto(codproducto);
    		     			detalle_venta.setDescripcion_producto(descripcion);
    		     			detalle_venta.setPrecio_producto(precio);
    		     			detalle_venta.setCantidad_producto(cant);
    		     			detalle_venta.setCodigo_venta(numfact);
    		     			detalle_venta.setIva_venta(valor_iva);
    		     			detalle_venta.setValor_venta(subtotal);
    		     			listaventas.add(detalle_venta);
    		     	
    		     			
    		     			for (int i=0; i<listaventas.size();i++) {
    		     				acusubtotal += listaventas.get(i).getValor_venta();
    		     				subtotaliva += listaventas.get(i).getIva_venta();
    		     			}
    		     			totalapagar = acusubtotal + subtotaliva;
    		     			detalle_venta.setTotal_venta(totalapagar);
    		     			
    		     			request.setAttribute("listaventas", listaventas);
    		     			request.setAttribute("totalsubtotal", acusubtotal);
    		     			request.setAttribute("totaliva", subtotaliva);
    		     			request.setAttribute("totalapagar", totalapagar);
            			
            			}catch (Exception e) {
            				e.printStackTrace();
            			}
            			
            		}else if(accion.equals("GenerarVenta")) {
            			            			
            			cedulacliente = request.getParameter("cedulacliente");
            			String numFact = request.getParameter("numerofactura");
            			
            			Ventas ventas = new Ventas();
            			ventas.setCodigo_venta(Long.parseLong(numFact));
            			ventas.setCedula_cliente(Long.parseLong(cedulacliente));
            			ventas.setCedula_usuario(usuarios.getCedula_usuario());
            			ventas.setIva_venta(subtotaliva);
            			ventas.setValor_venta(acusubtotal);
            			ventas.setTotal_venta(totalapagar);
            			            			
            			int respuesta = 0;
            			try {
            				respuesta = JSONVentas.postJSON(ventas);
            				PrintWriter write = response.getWriter();
            				write.println("Resultado respuesa " + respuesta);
            				System.out.println("Resultado de respuesta " + respuesta);
            				
            				if (respuesta == 200) {
            					System.out.println("Grabación éxitosa " + respuesta);
            					this.grabarDetalle(ventas.getCodigo_venta(), request, response);
            				}else {
            					write.println("Error ventas");	
            				}
            				write.close();
            			}catch (Exception e) {
            				e.printStackTrace();
            			}           			
            		}
            		
            		//else {
            			//String factura = "0";
            			//String factura = String.valueOf(request.getAttribute("numerofactura"));
            			//this.buscarFactura(factura, request, response);
            		//}
		     		
		     		request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
		     		break;
		     	case "Reportes":
		     		request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
		     		break;
				}
	}
}

