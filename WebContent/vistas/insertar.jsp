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
	<a href="insertar.jsp">Iniciar sesión</a>
	<a href="login.jsp">Darse de alta en el sistema</a>

	<h1>Registrarme en el sistema</h1>

	<form action="${pageContext.request.contextPath}/ServletUsuarioInsertar" method="post">
		<table>
			<tr>
				<td><label for="nombre">Nombre</label></td>
				<td><input type="text" name="nombre"
					value="${requestScope.nombre}" /></td>
			</tr>
			<tr>
				<td><label for="apellidos">Apellidos</label></td>
				<td><input type="text" name="apellido"
					value="${requestScope.apellido}" /></td>
			</tr>
						<tr>
				<td><label for="fechaNac">Fecha de nacimiento</label></td>
				<td><input type="text" name="fechaNacimiento" placeholder="dd/mm/aaaa"
					value="${requestScope.fechaNacimiento}" /></td>
			</tr>
						<tr>
				<td><label for="correoElectronico">Correo electrónico</label></td>
				<td><input type="text" name="correoElectronico"
					value="${requestScope.correoElectronico}" /></td>
			</tr>
						<tr>
				<td><label for="nombre">Contraseña</label></td>
				<td><input type="password" name="contrasenia"
					value="${requestScope.contrasenia}" /></td>
			</tr>
						<tr>
				<td><label for="nombre">Repita contraseña</label></td>
				<td><input type="password" name="contraseniaRepetida"
					value="${requestScope.contrasenaRep}" /></td>
			</tr>
		</table>
		<input type="submit" value="Registrarme" name="instruccion">
	</form>
	
	<c:set var="listaErrores" value="${sessionScope.listaErrores}" scope="page" />
	<c:choose>
		<c:when test="${not empty listaErrores}">
			<c:forEach var="error" items="${listaErrores}">
				<p>
					<c:out value="${error}"/>
				</p>
			</c:forEach>
		</c:when>
	</c:choose>
</body>
</html>