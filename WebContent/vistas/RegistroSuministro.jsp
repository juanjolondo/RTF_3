<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Suministros</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
<div class="jumbotron container-fluid">
      		<div class="text-center">
        		<h3 class="text">REGISTRAR SUMINISTROS</h3>
      		</div>
      		<div class="col-sm-12 text-center">
        		<p>
          			Por favor sr/sra administrador@ ingresé correctamete los datos y verifiquelos.
       	 		</p>
      		</div>
    	</div><br />
    	<br />
        
    	 <div>
    	  <br/>
    	   <label for="NombreProducto">Nombre del producto:</label>
    	   <input type="text" id="NombreProducto"/>    	 
    	 </div>
    	 <div>
    	  <br/>
    	   <label for="IdProducto">Id del producto:</label>
    	   <input type="text" id="IdProducto"/>
    	 </div>
    	 <br>
    	  <label for="PrecioDocenaProducto">Precio ramos del producto(En pesos) $:</label>
    	  <input type="text" id="PrecioDocenaProducto"/>
    	 <br/>
    	 
    	 <div>
    	 <br/>
    	  <label for="CantidadAlmacenada">Cantidad disponible: </label>
    	  <input type="text">
    	 </div>
    	 <div class="button">
    	  <br/>
    	   <button type="submit">Registrar producto</button>
    	 </div>

</body>
</html>