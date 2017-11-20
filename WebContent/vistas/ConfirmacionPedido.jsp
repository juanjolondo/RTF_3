<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmar Pedido</title>
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
                 %>https://www.wiziq.com/online-class/4360007-ids-415-analisis-y-dise%C3%B1o-sistemas-ii
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
              <td colspan="1"><% out.print(aleatorio*6754); %>
            </tr>
            <tr>
              <td colspan="3"><b>Seleccine forma de pago</b></td><td colspan="3">
              <td colspan="1">
            </tr>
             <td colspan="3"><b>Forma de pago</b> </td><td colspan="3">
             <td colspan="1"> <% request.getParameter("txtFormaPago"); %> </td>
             
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
              <td colspan="1"><%out.print("Valor total a pagar");%>
            </tr>
            <tr> 
             <form action="HomeLogin.jsp">
             <td><input type="submit" style="Position:absolute; left:50%; top:75%" Value="Aceptar"/></td>
             </form>
             </tr>
            </table>
            
</body>
</html>