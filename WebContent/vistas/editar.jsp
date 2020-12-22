<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	 window.addEventListener("load", start, false);
	 function start() {
	const contraseniaNuevaTabla = document.getElementById("contraseniaNuevaTabla");
	contraseniaNuevaTabla.style.visibility = "hidden";
	 for (const element of document.querySelectorAll('input[type="radio"]')) {
	 element.addEventListener("change", (event) => {
	 if (event.target.checked && event.target.value == "NO") {
	  contraseniaNuevaTabla.style.visibility = "hidden";
	 } else {
		 contraseniaNuevaTabla.style.visibility = "visible";
	 }
	 }, false);
	 }
	
	 } 
</script>
</head>
<body>

	<c:set var="usuarioLogueado" value="${sessionScope.usuarioLogueado}"
		scope="page" />
	<c:set var="fechaFomatoNormal" value="" />

	<c:forEach var="item"
		items="${fn:split(usuarioLogueado.fechaNacimiento,'-')}">
		<c:set var="fechaFomatoNormal" value="/${item}${fechaFomatoNormal}" />
	</c:forEach>
	<c:set var="fechaFomatoNormal"
		value="${fn:substring(fechaFomatoNormal, 1, 11)}" />

	<form action="${pageContext.request.contextPath}/ServletUsuarioEditar"
		method="post">
		<table>
			<tr>
				<td><label for="nombre">Nombre</label></td>
				<td><input type="text" name="nombre"
					value="${usuarioLogueado.nombre}" /></td>
			</tr>
			<tr>
				<td><label for="apellidos">Apellidos</label></td>
				<td><input type="text" name="apellido"
					value="${usuarioLogueado.apellido}" /></td>
			</tr>
			<tr>
				<td><label for="fechaNac">Fecha de nacimiento</label></td>
				<td><input type="text" name="fechaNacimiento"
					placeholder="dd/mm/aaaa" value="${fechaFomatoNormal}" /></td>
			</tr>
			<tr>
				<td><label for="correoElectronico">Correo electrónico</label></td>
				<td><input type="text" name="correoElectronico"
					value="${usuarioLogueado.correoElectronico}" /></td>
			</tr>
			<tr>
				<td>Cambiar contraseña</td>
				<td><label>Sí</label><input type="radio" name="eleccion"
					value="SI"> <label>No</label><input type="radio"
					name="eleccion" value="NO" checked></td>
			</tr>
			<table id="contraseniaNuevaTabla">
				<tr>
					<td><label for="ContraseniaAntigua">Contraseña Antigua</label></td>
					<td><input type="text" name="contraseniaAntigua" /></td>
				</tr>
				<tr>
					<td><label for="contraseniaNueva">Introduzca una
							contraseña nueva</label></td>
					<td><input type="text" name="contraseniaNueva" /></td>
				</tr>
				<tr>
					<td><label for="contraseniaNuevaRepetida">Repita nueva
							contraseña</label></td>
					<td><input type="text" name="contraseniaNuevaRepetida" /></td>
				</tr>

			</table>

		</table>
		<input type="submit" value="Guardar Datos">
	</form>

	<c:set var="listaErrores" value="${sessionScope.listaErrores}"
		scope="page" />
	<c:choose>
		<c:when test="${not empty listaErrores}">
			<div>
				<c:forEach var="error" items="${listaErrores}">
					<p>
						<c:out value="${error}" />
					</p>
				</c:forEach>
			</div>
		</c:when>
	</c:choose>

</body>
</html>