<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuario encontrado</title>
<link rel="stylesheet" type="text/css" href="/css/general.css">
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
			<fieldset class="lista" id="myAnimeList">
				<legend class="legend1">
					Lista de anime de "<c:out value="${param.searchPerfilQuery}" />"
				</legend>
				<div class="scroll reescalarImagen">
					<c:choose>
						<c:when test="${not empty requestScope.perfilInfo}">
							<c:forEach items="${requestScope.perfilInfo}" var="anime">
									<img src="<c:out value="${anime.seriesImage}"/>" /><br />
									<span><b>Título:</b> <c:out value="${anime.seriesTitle}"/></span><br />
									<span><b>Episodios:</b>  <c:out value="${anime.seriesEpisodes}"/></span><br />
									<span><b>Fecha de comienzo en verla:</b>  <c:out value="${anime.myStartDate}"/></span><br />
									<span><b>Fecha de fin en acabarla:</b>  <c:out value="${anime.myFinishDate}"/></span><br />
									<c:if test="${anime.myStatus == '1'}"><span><b>Estado:</b> viendo</span><br /></c:if>
									<c:if test="${anime.myStatus == '2'}"><span><b>Estado:</b> completado</span><br /></c:if>
									<c:if test="${anime.myStatus == '3'}"><span><b>Estado:</b> en pausa</span><br /></c:if>
									<c:if test="${anime.myStatus == '4'}"><span><b>Estado:</b> abandonado</span><br /></c:if>
									<c:if test="${anime.myStatus == '6'}"><span><b>Estado:</b> pendiente</span><br /></c:if>
									<span><b>Episodios vistos:</b>  <c:out value="${anime.myWatchedEpisodes}"/></span><br />
									<span><b>Mi puntuación:</b>  <c:out value="${anime.myScore}"/></span><br />
									<br /><br />
									<hr class="lineas">
							</c:forEach>
						</c:when>
						<c:otherwise>
							<span>No hay resultados.</span>
						</c:otherwise>
					</c:choose>
				</div>
			</fieldset>
		</div>
	</div>
</body>


</html>