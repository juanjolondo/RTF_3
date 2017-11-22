<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Realizar pedido</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
       <div class="jumbotron container-fluid">
      		<div class="text-center">
        		<h3 class="text">BIENVENIDO A LA SECCIÓN SOLICITAR PEDIDOS</h3>
      		</div>
      		<div class="col-sm-12 text-center">
        		<p>
          			Para realizar un pedido correctamente, por favor verificar los datos ingresados antes de confirmar el pedido.
       	 		</p>
      		</div>
    	</div><br /><br />
    	
    	<form name="frmRegistroPedidos" action="http://localhost:8080/RTF_3/vistas/ConfirmacionPedido.jsp" method="post" >
    	<%
         Date dNow = new Date();
         SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
         String currentDate = ft.format(dNow);
         %>
        <p>Fecha <%=currentDate%></p>
                
    	 <div>
    	  <br/>
    	   <label for="IdCliente">Identificación:</label>
    	   <input type="text" id="IdCliente" name="txtIdCliente"/>    	 
    	 </div>
    	 <div>
    	  <br/>
    	   <label for="descripcion">Descripción del pedido:</label>
    	   <textarea id="descripcion" name="txtDescripcion"></textarea>
    	 </div>
    	 <div>
    	 <br/>
    	 Seleccione el tipo de flor: <br/>
    	 
         <select name="Flores" name="txtTipoFlor">
          <option value="Orquidea" selected="selected">Orquidea</option>
          <option value="Rosa">Rosa</option>
         </select>
      	  <br/>
      	 <dir></dir>
    	 <select name="txtFormaPago">
            <option value="Tarjeta debito" selected="selected">Tarjeta debito</option>
            <option value="Efectivo">Efectivo </option> 
            </select>
          </div>
          <dir></dir>
          <div> 
    	   <label for="CantidadFlor">Cantidad</label>
    	   <input type="text" id="CantidadFlor" name="txtCantidadFlor"/>    	 
    	  </div>   	 	 
      	 Selecciona el color de tinturado:<br/>
    	 <input name="color" type="color" value="#f3f3f3" name="txtColor"/> <br/>
    	 
    	 <div class="button">
    	  <br/>
    	   <button type="submit">Pasar a confirmación del pedido solicitado</button>
    	 </div>
    	 
    	 <dir>
    	 </dir>

</body>
</html>