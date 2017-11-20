<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido al registro de usuario</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
    </head>
    <body>
        <h1>Si ya tiene una cuenta creada</h1>
        <p><a href="InicioSesion.jsp">INICIAR SESION</a></p>
        
        <p style="color: #ff0000">${sessionScope['error']}</p>
        <form action="http://localhost:8080/RTF_3/RegistroUsuario" method="post">
            <p>Nombre <input type="text" name="txtNombre"></p>
            <p>Apellido <input type="text" name="txtApellido"></p>
            <p>Usuario <input type="text" name="txtUsuario"></p>
            <p>Genero </p>
            <select name="txtGenero" selected="Masculino">
            <option >Masculino</option>
            <option>Femenino</option>
            </select>
            <p>Tipo de identificación</p>
            <p><select name="txtTipoId" selected="Cedula de ciudadania"></p>
            <option>Cedula Ciudadania</option>
            <option>Documento extranjero</option>
            </select>
            <p>Dirección</p>
            <textarea rows="" cols="" name="txtDireccion"></textarea>
            <p>Telefono <input type="text" name="txtTelefono"></p>
            <p>Identificación del usuario: <input type="text" name="txtIdUsuario">
            <p>Email: <input type="text" name="txtEmail"></p>
            <p>Confirme Email: <input type="text" name="txtEmail2"></p>
            <p>Contraseña: <input type="password" name="txtClave"></p>
            <p>Confirma contraseña: <input type="password" name="txtClave2"></p>
            <p><button type="submit">Confirmar registro</button></p>
        </form>
    </body>
</html>