<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css" media="screen">
</style>
<head>
        
    </head>
    <body>
        <h1><b>INICIAR SESIÓN</b></h1>
        <p><a href="RegistroUsuario.jsp">REGISTRARSE</a></p>
        Por favor ingresa correctamente sus datos, en caso de dificultad "aldoR7@gmail.com"
        <p style="color: #ff0000">${sessionScope['error']}</p>
        <form action="HomeLogin.jsp" method="post">
            <p> Identificación del usuario: <input type="text" name="txtIdUsuario" required></p>
            <p> Contraseña: <input type="password" name="txtPassword" required></p>
            <p><input type="submit" value="Entrar"></p>
        </form>
    </body>
</html>