package co.edu.unbosque.ciclo3TiendaGen;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSONDetalleVenta {
	
	private static URL url;
	//private static String sitio = "http://localhost:5000/";
	private static String sitio = "http://localhost:8080/BackTiendaBarrio-0.0.1-SNAPSHOT/";
	
	public static int postJSON(DetalleVenta detalle_ventas) throws IOException {

		url = new URL(sitio + "detalle_ventas/guardar");
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
				+ "\"codigo_detalle_venta\":\"" + String.valueOf(detalle_ventas.getCodigo_detalle_venta())
				+ "\",\"cantidad_producto\": \"" + detalle_ventas.getCantidad_producto() 
				+ "\",\"codigo_producto\": \"" + detalle_ventas.getCodigo_producto() 
				+ "\",\"codigo_venta\":\"" + detalle_ventas.getCodigo_venta() 
				+ "\",\"iva_venta\":\"" + detalle_ventas.getIva_venta()
				+ "\",\"total_venta\":\"" + detalle_ventas.getTotal_venta()
				+ "\",\"valor_venta\":\"" + detalle_ventas.getValor_venta()
				+ "\",\"descripcion_producto\":\"" + detalle_ventas.getDescripcion_producto()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

}
