package co.edu.unbosque.ciclo3TiendaGen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONProductos {

	private static URL url;
	//private static String sitio = "http://localhost:5000/";
	private static String sitio = "http://localhost:8080/BackTiendaBarrio-0.0.1-SNAPSHOT/";

	//agregar informacion a la tabla clientes
	
	public static ArrayList<Productos> parsingProductos(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Productos> lista = new ArrayList<Productos>();
		JSONArray productos = (JSONArray) jsonParser.parse(json);
		Iterator i = productos.iterator(); //recorrer la tabla clientes
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Productos producto = new Productos();
			producto.setCodigo_producto(Long.parseLong(innerObj.get("codigo_producto").toString())); //convertir de String a Long
			producto.setIva_compra(Long.parseLong(innerObj.get("iva_compra").toString()));
			producto.setNit_proveedor(Long.parseLong(innerObj.get("nit_proveedor").toString()));
			producto.setNombre_producto(innerObj.get("nombre_producto").toString());
			producto.setPrecio_compra(Long.parseLong(innerObj.get("precio_compra").toString()));
			producto.setPrecio_venta(Long.parseLong(innerObj.get("precio_venta").toString()));
			lista.add(producto);
		}
		return lista;
	}
	
	/**
	 * Conecta con el back-end segun los atributos definidos esn la clase y llama al metodo parsingUsuarios para 
	 * crear una lista de objetos de tipo Usuarios
	 * @return Un ArrayList de tipo Usuario
	 * @throws IOException
	 * @throws ParseException
	 */
	
	public static ArrayList<Productos> getJSON() throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "productos/listar"); //trae el metodo de Clientes.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		System.out.println(json);
		ArrayList<Productos> lista = new ArrayList<Productos>();
		lista = parsingProductos(json);
		http.disconnect();
		return lista;
	}

	public static ArrayList<Productos> getforIdJSON(String id) throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "productos/ListarId/"+id); //trae el metodo de Usuarios.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		
		String json = "[";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		json = json + "]";
		ArrayList<Productos> lista = new ArrayList<Productos>();
		lista = parsingProductos(json);
		http.disconnect();
		return lista;
	}

	
	/**
	 * Conecta con el Back-end y crea en la base de datos segun un objeto de tipo Usuarios
	 * @param usuario
	 * @return
	 * @throws IOException
	 */
	public static int postJSON(Productos productos) throws IOException {

		url = new URL(sitio + "productos/Crear");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"codigo_producto\":\"" + String.valueOf(productos.getCodigo_producto())
				+ "\",\"iva_compra\": \"" + String.valueOf(productos.getIva_compra()) 
				+ "\",\"nit_proveedor\": \"" + String.valueOf(productos.getNit_proveedor())
				+ "\",\"nombre_producto\":\"" + productos.getNombre_producto() 
				+ "\",\"precio_compra\":\""	+ String.valueOf(productos.getPrecio_compra())
				+ "\",\"precio_venta\":\""+ String.valueOf(productos.getPrecio_venta()) 
				+"\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	public static int deleteJSON(String id) throws IOException {

		url = new URL(sitio + "productos/Eliminar/"+id); //trae el metodo de Usuarios.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();


		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	public static boolean validarCSV(String nombreArchivo) {
		int contador = 0;
        char valdador;
        for (int i = 0; i < nombreArchivo.length(); i++) {
        	valdador = nombreArchivo.charAt(i);
            if (valdador == '.')
            	contador++;
        }
        
        if (contador<2) {
        	if(nombreArchivo.contains(".csv")) {
    			return true;
    		}else {
    			return false;
    		}
        }else{
        	return false;
        }
	}
	
	public static void main(String[] args) {
				
		/*ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		UsuariosJSON prueba = new UsuariosJSON();
		try {
			lista = prueba.getforIdJSON("1022420439");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error metodo");
			// TODO: handle exception
		}
		for (Usuarios user: lista) {
			System.out.println(user.getCedula_usuario());
			System.out.println(user.getNombre_usuario());
		}
		try {
			ArrayList<Productos> lista = new ArrayList<Productos>();
			lista = ProductosJSON.getJSON();
			for (Productos items: lista)
			{
				System.out.println(items.getCodigo_producto());
				System.out.println(items.getIvacompra());
				System.out.println(items.getNitproveedor());
				System.out.println(items.getNombre_producto());
				System.out.println(items.getPrecio_compra());
				System.out.println(items.getPrecio_venta());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
			// TODO: handle exception
		}
	
	
}
