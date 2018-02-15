<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Animes encontrados</title>
<link rel="stylesheet" type="text/css" href="/css/general.css">
</head>
<body>
	<div class='contenedor'>
		<div class='col-100 tablet-100 movil-100 alinearCentro reescalarImagen'>
			<img class="logoLista" src='/images/Logo.png'>
		</div>
	</div>
	<div class='clear'></div>
	<c:if test="${not empty requestScope.added}">
		<div class='contenedor'>
			<div class='col-100 tablet-100 movil-100 alinearCentro'>
				<span class="realizadaAccion">Se ha añadido correctamente el anime a tu lista.</span>
			</div>
		</div>
	<br /><br />
	</c:if>
	<c:if test="${not empty requestScope.noAdded}">
		<div class='contenedor'>
			<div class='col-100 tablet-100 movil-100 alinearCentro'>
				<span class="realizadaAccionFail">El anime que quieres añadir ya está en tu lista.</span>
			</div>
		</div>
	<br /><br />
	</c:if>
	<c:if test="${not empty requestScope.updated}">
		<div class='contenedor'>
			<div class='col-100 tablet-100 movil-100 alinearCentro'>
				<span class="realizadaAccion">Se ha actualizado correctamente el anime de tu lista.</span>
			</div>
		</div>
	<br /><br />
	</c:if>
	<c:if test="${not empty requestScope.noUpdated}">
		<div class='contenedor'>
			<div class='col-100 tablet-100 movil-100 alinearCentro'>
				<span class="realizadaAccionFail">El anime que quieres actualizar no está en tu lista.</span>
			</div>
		</div>
	<br /><br />
	</c:if>
	<c:if test="${not empty requestScope.deleted}">
		<div class='contenedor'>
			<div class='col-100 tablet-100 movil-100 alinearCentro'>
				<span class="realizadaAccion">Se ha eliminado correctamente el anime de tu lista.</span>
			</div>
		</div>
	<br /><br />
	</c:if>
	<c:if test="${not empty requestScope.noDeleted}">
		<div class='contenedor'>
			<div class='col-100 tablet-100 movil-100 alinearCentro'>
				<span class="realizadaAccionFail">El anime que quieres eliminar no está en tu lista.</span>
			</div>
		</div>
	<br /><br />
	</c:if>
	
	<div class='clear'></div>
	<div class='contenedor'>
		<div class='col-100 tablet-100 movil-100 alinearCentro'>
			<fieldset class="lista" id="myAnimeList">
				<legend class="legend1">
					Títulos relacionados con "<c:out value="${param.searchQuery}" />"
				</legend>
				<div class="scroll reescalarImagen">
					<c:choose>
						<c:when test="${not empty requestScope.informacion}">
							<c:forEach items="${requestScope.informacion}" var="info">
								<img src="<c:out value="${info.image}"/>" /><br /><br />
								<form method="post" class="listaForm" action="/selectedAnimeController">
									<input type="submit" class="tituloEnviar" name="titulo" value="<c:out value="${info.title}"/>" /><br /><br />
									<input type='hidden' name='usuario' value="<c:out value="${param.usuario}"/>" />
									<input type='hidden' name='clave' value="<c:out value="${param.clave}"/>" />
									<input type='hidden' name='searchQuery' value="<c:out value="${param.searchQuery}"/>" />
									<c:if test="${param.sesion == 'OK'}">
										<label for="puntuacion" class="labelsListaAnime">Puntuación</label>
											<input type="number" class="score" name="score" min="1" max="10" value="1" />
										<label for="episodios" class="labelsListaAnime">Episodios vistos</label>
											<input type="number" class="episodes" name="episodes" min="1" max="<c:out value="${info.episodes}"/>" />
										<label for="estado" class="labelsListaAnime">Estado</label>
											<select class="status" name="status">
												<option value="1">Viendo</option>
												<option value="2">Completado</option>
												<option value="3">En pausa</option>
												<option value="4">Abandonado</option>
												<option value="6">Pendiente</option>
											</select>
										<input type='hidden' name='idAnime' value="<c:out value="${info.id}"/>" />
										<input type='hidden' name='sesion' value="<c:out value="${param.sesion}"/>" />
										<input type="submit" class='add' name="addAnime" value="Añadir anime" onclick='this.form.action="/addAnimeController"'/>
										<input type="submit" class='update' name="updateAnime" value="Actualizar anime" onclick='this.form.action="/updateAnimeController"'/>
										<input type="submit" class='delete' name="deleteAnime" value="Eliminar anime" onclick='this.form.action="/deleteAnimeController"'/>
									</c:if>
									<br/><br>
									<hr class="lineas">
								</form>
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