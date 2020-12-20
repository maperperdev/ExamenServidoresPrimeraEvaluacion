<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Iniciar sesión</h1>

	<form action="${pageContext.request.contextPath}/ServletUsuarioLogin"
		method="post">
		<table>
			<tr>
				<td><label for="nombre">Correo electrónico</label></td>
				<td><input type="text" name="correoElectronico"
					value="${sessionScope.usuarioLogueado.correoElectronico}" /></td>
			</tr>

			<tr>
				<td><label for="nombre">Contraseña</label></td>
				<td><input type="password" name="contrasenia" /></td>
			</tr>

		</table>

		<input type="submit" value="Ingresar">

	</form>
	
	
	<c:set var="listaErroresLogin" value="${sessionScope.listaErroresLogin}" scope="page" />
	<c:choose>
		<c:when test="${not empty listaErroresLogin}">
			<c:forEach var="error" items="${listaErroresLogin}">
				<p>
					<c:out value="${error}"/>
				</p>
			</c:forEach>
		</c:when>
	</c:choose>
</body>
</html>