package co.analisis2.rtf3.entidades;

import java.time.LocalDate;
import java.util.List;

public class PedidoSuministros extends Venta {
	
	public PedidoSuministros(){
		
	}
	public PedidoSuministros(String idCliente, String descripcion, String medioPago, double precioTotal, boolean pagado,
			String consecutivo, List<Integer> cantidad, char estado, LocalDate fecha, List<String> listaProductos) {
		this.idCliente = idCliente;
		this.descripcion = descripcion;
		this.medioPago = medioPago;
		this.precioTotal = precioTotal;
		this.pagado = pagado;
		this.consecutivo = consecutivo;
		this.cantidad = cantidad;
		this.estado = estado;
		this.fecha = fecha;
		this.listaProductos = listaProductos;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public List<Integer> getCantidad() {
		return cantidad;
	}

	public void setCantidad(List<Integer> cantidad) {
		this.cantidad = cantidad;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<String> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<String> listaProductos) {
		this.listaProductos = listaProductos;
	}

}