package co.edu.unbosque.ciclo3TiendaGen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection; //esta libreria es para realizar la conexion
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONClientes {
	private static URL url;
	//private static String sitio = "http://localhost:5000/";
	private static String sitio = "http://localhost:8080/BackTiendaBarrio-0.0.1-SNAPSHOT/";

	//agregar informacion a la tabla clientes
	
	public static ArrayList<Clientes> parsingClientes(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Clientes> lista = new ArrayList<Clientes>();
		JSONArray clientes = (JSONArray) jsonParser.parse(json);
		Iterator i = clientes.iterator(); //recorrer la tabla clientes
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Clientes cliente = new Clientes();
			cliente.setCedula_cliente(Long.parseLong(innerObj.get("cedula_cliente").toString())); //convertir de String a Long
			cliente.setDireccion_cliente(innerObj.get("direccion_cliente").toString());
			cliente.setEmail_cliente(innerObj.get("email_cliente").toString());
			cliente.setNombre_cliente(innerObj.get("nombre_cliente").toString());
			cliente.setTelefono_cliente(innerObj.get("telefono_cliente").toString());
			lista.add(cliente);
		}
		return lista;
	}
	
	
	//listar la informacion
	public static ArrayList<Clientes> getJSON() throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "clientes/listar"); //trae el metodo de Clientes.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Clientes> lista = new ArrayList<Clientes>();
		lista = parsingClientes(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON(Clientes cliente) throws IOException {

		url = new URL(sitio + "clientes/guardar");
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

		String data = "{" + "\"cedula_cliente\":\"" + String.valueOf(cliente.getCedula_cliente())
				+ "\",\"telefono_cliente\": \"" + cliente.getTelefono_cliente() 
				+ "\",\"email_cliente\": \"" + cliente.getEmail_cliente() 
				+ "\",\"nombre_cliente\":\"" + cliente.getNombre_cliente() 
				+ "\",\"direccion_cliente\":\""	+ cliente.getDireccion_cliente()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON(Usuarios usuario, Long id) throws IOException {
				
		url = new URL(sitio+"usuarios/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
		try {
		  http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
		  e.printStackTrace();
		}
		
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"cedula_usuario\":\""+ id
				+"\",\"email_usuario\": \""+usuario.getEmail_usuario()
				+"\",\"nombre_usuario\": \""+usuario.getNombre_usuario()
				+"\",\"password_usuario\":\""+usuario.getPassword_usuario()
				+"\",\"usuario\":\""+usuario.getUsuario()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	public static int deleteJSON(Long id) throws IOException {
		
		url = new URL(sitio+"clientes/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
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
	

}
