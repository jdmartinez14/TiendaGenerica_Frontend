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

public class JSONVentas {
	private static URL url;
	//private static String sitio = "http://localhost:5000/";
	private static String sitio = "http://localhost:8080/BackTiendaBarrio-0.0.1-SNAPSHOT/";
	
	public static ArrayList<Ventas> parsingVentas(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Ventas> lista = new ArrayList<Ventas>();
		JSONArray ventas = (JSONArray) jsonParser.parse(json);
		Iterator i = ventas.iterator(); //recorrer la tabla usuario
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Ventas venta = new Ventas();
			venta.setCodigo_venta(Long.parseLong(innerObj.get("codigo_venta").toString())); //convertir de String a Long
			venta.setCedula_cliente(Long.parseLong(innerObj.get("cedula_cliente").toString()));
			venta.setCedula_usuario(Long.parseLong(innerObj.get("cedula_usuario").toString()));
			venta.setIva_venta(Long.parseLong(innerObj.get("iva_venta").toString()));
			venta.setTotal_venta(Long.parseLong(innerObj.get("total_venta").toString()));
			venta.setValor_venta(Long.parseLong(innerObj.get("valor_venta").toString()));
			lista.add(venta);
		}
		return lista;
	}
	
	public static int postJSON(Ventas ventas) throws IOException {

		url = new URL(sitio + "ventas/guardar");
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

		String data = "{" 
				+ "\"codigo_venta\":\"" + String.valueOf(ventas.getCodigo_venta())
				+ "\",\"cedula_cliente\": \"" + String.valueOf(ventas.getCedula_cliente()) 
				+ "\",\"cedula_usuario\": \"" + String.valueOf(ventas.getCedula_usuario()) 
				+ "\",\"iva_venta\":\"" + String.valueOf(ventas.getIva_venta()) 
				+ "\",\"total_venta\":\"" + String.valueOf(ventas.getTotal_venta())
				+ "\",\"valor_venta\":\"" + String.valueOf(ventas.getValor_venta())
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	//listar la informacion
	public static ArrayList<Ventas> getJSON() throws IOException, ParseException { //devolver un listado JSON

				url = new URL(sitio + "ventas/listar"); //trae el metodo de Usuarios.API 
				HttpURLConnection http = (HttpURLConnection) url.openConnection();

				http.setRequestMethod("GET");
				http.setRequestProperty("Accept", "application/json");

				InputStream respuesta = http.getInputStream();
				byte[] inp = respuesta.readAllBytes();
				String json = "";

				for (int i = 0; i < inp.length; i++) {
					json += (char) inp[i];
				}

				ArrayList<Ventas> lista = new ArrayList<Ventas>();
				lista = parsingVentas(json);
				http.disconnect();
				return lista;
			}
}


	