package co.analisis2.rtf3.dominio;

public class Producto {
	private String nombre;
	private String idProducto;
	private double precio;
	private String color;
	private int existencias;
	
	public Producto(){
		
	}
	public Producto(String nombre, String idProducto, double precio, String color, int existencias) {
		this.nombre = nombre;
		this.idProducto = idProducto;
		this.precio = precio;
		this.color = color;
		this.existencias = existencias;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getExistencias() {
		return existencias;
	}
	
	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

}