<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Confirmacion</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
  <table align="center">
            <tr>
             <td colspan="10"  bgcolor="#FF9900" ><center> <b>Factura de Compra</b> </center> </td>
            </tr>
            <tr>
                <td colspan="3"> <b>Fecha</b></td><td colspan="3">
                 <%
                 Date dNow = new Date();
                 SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
                 String currentDate = ft.format(dNow);
                 %>
                <td colspan="1"> <%=currentDate%> </td>
                
            </tr>
            <tr>
                <td colspan="3"><b>NIT o ID</b> </td><td colspan="3">
                <td colspan="1"> <% request.getParameter("txtIdCliente"); %> </td>
            </tr>
            <tr>
              <td colspan="3"><b>Consecutivo de compra</b></td><td colspan="3">
              <%
              long aleatorio = Math.round(Math.random()*987698978);
              %>
              <td colspan="1"><% out.print(aleatorio*67); %>
            </tr>
            <tr>
             <td colspan="3"><b>Forma de pago</b> </td><td colspan="3">
             <td colspan="1"> <% request.getParameter("txtFormaPago"); %> </td>
            </tr>
            <h4>Si selecciona EFECTIVO debe llevar la factura, para empezar a realizar su pedido.</h4>
            <tr>
              <td colspan="3"><b>Observaciones</b></td><td colspan="3">
              <td colspan="1"><% request.getParameter("txtColor");
                                 request.getParameter("txtTipoFlor");
                                 request.getParameter("txtCantidadFlor");
                               %>
            </tr>
            <tr>
              <td colspan="3"><b>Valor ramos x 20 de flor (En pesos) $:</b></td><td colspan="3">
              <td colspan="1"><%out.print("$ "+120000);%>
            </tr>
            <tr>
              <td colspan="3"><b>Valor total a pagar</b></td><td colspan="3">
              <td colspan="1"><%out.print("240.000");%>
            </tr>
            <tr> 
             <form action="HomeLogin.jsp">
             <td><input type="submit" style="Position:absolute; left:50%; top:45%" Value="Enviar solicitud"/></td>
             </form>
             </tr>
            </table>
            
</body>
</html>