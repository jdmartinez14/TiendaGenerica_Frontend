package co.edu.unbosque.ciclo3TiendaGen;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.csvreader.CsvReader;

import co.edu.unbosque.ciclo3TiendaGen.Proveedores;
import co.edu.unbosque.ciclo3TiendaGen.JSONProveedores;

@WebServlet("/ControladorProductos")
@MultipartConfig

public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	public ControladorProductos() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String Listar = request.getParameter("Listar");
		if (Listar != null) {
			try {
				ArrayList<Productos> lista = JSONProductos.getJSON();
				request.setAttribute("lista", lista);
				request.getRequestDispatcher("Productos.jsp").forward(request, response);
			} catch (Exception e) {
				out.println("Catch :(");
				// TODO: handle exception
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		PrintWriter out = response.getWriter();
		System.out.println("Entro Sevlet");
		String nomb = request.getParameter("nombre");
		Part arch = request.getPart("archivo");
		String process = request.getParameter("Procesar");
		InputStream is = arch.getInputStream();
		Charset charset = Charset.forName("UTF-8");
		
		if (process != null) {
			if(is.available() ==0) {
				is.close();
				System.out.println("Entro error sin archivo");
				request.setAttribute("error", 1);//error no se ha seleccionado el archivo
				request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			}else {
				if(JSONProductos.validarCSV(nomb)) {
					try {
						String registros = "";
						boolean reg_no_cargados = false;
						CsvReader leerproducto = new CsvReader(is, charset);
						leerproducto.readHeaders();
						List<Productos> productos = new ArrayList<Productos>(); // Lista donde se guardara los datos
						while(leerproducto.readRecord()) {
							String codigo = leerproducto.get(0);
							String iva = leerproducto.get(1);
							String nit = leerproducto.get(2);
							String nombre = leerproducto.get(3);
							String compra = leerproducto.get(4);
							String venta = leerproducto.get(5);
							
							//añade lo leido a una lista tipo productios
						productos.add(new Productos(Long.parseLong(codigo), Long.parseLong(iva), Long.parseLong(nit), nombre, Long.parseLong(compra), Long.parseLong(venta)));
						}
						is.close();
						System.out.println("Entro en boton");
						ArrayList<Productos> listafinal = new ArrayList<Productos>();
						try {
							ArrayList<Proveedores> lista = JSONProveedores.getJSON();
							for (Proveedores suplier:lista) {
								for(Productos item:productos) {
									if(suplier.getNit_proveedor() != item.getNit_proveedor()) { // el código estaba == y se cambió por !=
										System.out.println("Entro for if");
										listafinal.add(item);
									}else {
										registros = String.valueOf(item.getNit_proveedor())+",";
										reg_no_cargados =  true;
									}
								}
							}
						} catch (Exception e) {
							System.out.println("Entro catch");
							out.println("Catch :(");
							// TODO: handle exception
						}
						String registros2 = "";
						int ban= 3;
						int validacion = 0;
						for(Productos item:listafinal) {
							ArrayList<Productos> listaproductos = JSONProductos.getJSON();
							for(Productos item2:listaproductos) {
								if(item.getCodigo_producto()!=item2.getCodigo_producto()) {
									validacion = JSONProductos.postJSON(item);
									if(validacion==200) {
										ban = (ban * 0) + 2;
									}
								}else {
									registros2 = String.valueOf(item.getCodigo_producto())+",";
								}
							}		
						}
						if(validacion == 200) {
							System.out.println("carga existosa");
							request.setAttribute("error", 2); //carga exitosa de csv
							request.getRequestDispatcher("/Productos.jsp").forward(request, response);
						}else {
							System.out.println("los registros no se cargaron");
							request.setAttribute("error", 4); //Registros no se cargaron
							request.getRequestDispatcher("/Productos.jsp").forward(request, response);
						}
					}catch(Exception e){
						System.out.println("Entro datos invalidos");
						request.setAttribute("error", 0);//error datos leidos invalidos
						request.getRequestDispatcher("/Productos.jsp").forward(request, response);
					}finally {
						is.close();
					}
				}else {
					is.close();
					System.out.println("Entro error formato");
					request.setAttribute("error", 3);//error datos leidos invalidos
					request.getRequestDispatcher("Productos.jsp").forward(request, response);
					//request.getRequestDispatcher("/ValidacionProductos?validar=3&error=3").forward(request, response);
				}
			}		
		}
	}
			
		
}

