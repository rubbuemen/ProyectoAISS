<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/css/general.css">
	<title>Login MyAnimeList</title>
</head>
<body>
<div class='contenedor'>
	<div class='col-100 tablet-100 movil-100 alinearCentro reescalarImagen'>
		<img class="logoLista" src='/images/Logo.png'>
	</div>
</div>
<div class='clear'></div>
<div class='contenedor'>
	<div class='col-100 tablet-100 movil-100 alinearCentro'>
		<form id="loginForm" action="/index" method="post">
			<label for="usuario">Ingrese nombre de usuario:</label><br />
				<input type="text" name="usuario"><br />
			<label for="clave">Ingrese contraseña:</label><br />
				<input type="password" name="clave"><br /><br />
			<input type="hidden" name="sesion" value ="OK" />
			<input type="hidden" name="searchQuery" value ="${param.searchQuery}" />
			<input type="submit" name="loginBtn" title="Iniciar sesión" value="Iniciar sesión">
		</form>
	</div>
</div>
</body>
</html>