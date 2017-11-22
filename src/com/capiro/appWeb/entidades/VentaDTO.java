package com.capiro.appWeb.entidades;

import java.time.LocalDate;
import java.util.List;

public abstract class VentaDTO {
	protected String idCliente;
	protected String descripcion;
	protected String medioPago;
	protected double precioTotal;
	protected boolean pagado;
	protected String consecutivo;
	protected List<Integer> cantidad;
	protected char estado;
	protected LocalDate fecha;
	protected List<String> listaProductos;
	
}
