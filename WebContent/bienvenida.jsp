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
	<div>
		<c:set var="usuarioLogueado"
			value="${sessionScope.usuarioLogueado}" scope="page" />
		<div>

			<c:choose>
				<c:when test="${empty usuarioLogueado}">
					<a href="vistas/login.jsp">Iniciar sesión</a>
					<a href="vistas/insertar.jsp">Darse de alta en el sistema</a>
					<p>
					<p><b>Usuario</b> anónimo</p>
				</c:when>
				<c:otherwise>
					<a href="vistas/editar.jsp">Ver mis datos personales</a>
					<p>
						<b>Usuario</b>
						<c:out value="${usuarioLogueado.correoElectronico}" />
					</p>
					<form
						action="${pageContext.request.contextPath}/ControladorUsuario"
						method="post">
						<input type="submit" name="intruccion" value="Cerrar sesión">
					</form>
				</c:otherwise>
			</c:choose>

		</div>

		<h1>Bienvenido/a a la página principal.</h1>
	</div>
</body>
</html>