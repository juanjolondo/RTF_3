<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login2</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
    <body>
    <center><h1>Iniciar sesión</h1></center>
        <form action="HomeLogin.jsp" method="post">
         <table align="center">
          <tr>
           <th align="right">Id usuario:</th>
            <td><input type="text" name="txtIdUsuario" required></td>
          </tr>
          <tr>
           <th align="right">Contraseña:</th>
            <td><input type="password" name="txtPassword" required></td>
          </tr>
          <tr>
          <tr></tr>
           <td colspan="1" align="right"><input style="color: #FAFAFA; background-color: #2E2E2E; border: #000 1px solid" type="submit" value="Iniciar sesión" class=""></td>
           <form action="RegistroUsuario.jsp">
            <td colspan="2" align="right"><input style="background-color: #FAFAFA; color: #2E2E2E ;border: #2E2E2E 2px solid" type="submit" value="Registrarse" class=""></td>            
           </form>
          </tr>
          <tr>
           
          </tr>
         
         </table>
        </form>        
    </body>
</html>