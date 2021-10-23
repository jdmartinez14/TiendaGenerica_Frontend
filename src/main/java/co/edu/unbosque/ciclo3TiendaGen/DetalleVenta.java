package co.edu.unbosque.ciclo3TiendaGen;

public class DetalleVenta {
	
	private Long codigo_detalle_venta;
	private Integer cantidad_producto;
	private Long codigo_producto;
	private Long codigo_venta;
	private Long iva_venta;
	private Long total_venta;
	private Long valor_venta;
	private String descripcion_producto;
	private Long precio_producto;
	
	public Long getCodigo_detalle_venta() {
		return codigo_detalle_venta;
	}
	public void setCodigo_detalle_venta(Long codigo_detalle_venta) {
		this.codigo_detalle_venta = codigo_detalle_venta;
	}
	public Integer getCantidad_producto() {
		return cantidad_producto;
	}
	public void setCantidad_producto(Integer cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}
	public Long getCodigo_producto() {
		return codigo_producto;
	}
	public void setCodigo_producto(Long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	public Long getCodigo_venta() {
		return codigo_venta;
	}
	public void setCodigo_venta(Long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}
	public Long getIva_venta() {
		return iva_venta;
	}
	public void setIva_venta(Long iva_venta) {
		this.iva_venta = iva_venta;
	}
	public Long getTotal_venta() {
		return total_venta;
	}
	public void setTotal_venta(Long total_venta) {
		this.total_venta = total_venta;
	}
	public Long getValor_venta() {
		return valor_venta;
	}
	public void setValor_venta(Long valor_venta) {
		this.valor_venta = valor_venta;
	}
	
	public String getDescripcion_producto() {
		return descripcion_producto;
	}
	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}
	
	public Long getPrecio_producto() {
		return precio_producto;
	}
	public void setPrecio_producto(Long precio_producto) {
		this.precio_producto = precio_producto;
	}

}
