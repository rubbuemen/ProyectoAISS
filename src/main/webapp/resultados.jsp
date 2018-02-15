<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Animes encontrados</title>
	<link rel="stylesheet" type="text/css" href="/css/general.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script type="text/javascript" src="/js/scripts.js"></script>
</head>
<body class="reescalarImagen bodyResult">
<div class="posicionarIzquierda">
	<img class="logoSuccess" src='/images/LogoPequenio.png'>
	<form class="logear <c:if test="${param.sesion == 'OK'}">noDisplay</c:if>" method="post" action="loginMyAnimeList.jsp">
		<span>¿No has iniciado sesión?</span>
		<input type="hidden" name="searchQuery" value="${param.searchQuery}">
		<button type="submit" value="logeate">Inicia sesión</button>
	</form>
</div>
<br />
<div class="posicionarDerecha centrarMovil">
	<form id='formNuevaBusqueda' action='/tagsSearchController' method='post'>
		<label for="searchQuery" class="nuevaBusqueda">Nueva búsqueda: </label>
			<input type='text' placeholder='Busca un anime' name='searchQuery' required />
		<input type="hidden" name="searchQuery" value="${param.searchQuery}">
		<input type="hidden" name="usuario" value="<c:out value="${param.usuario}"/>">
		<input type="hidden" name="clave" value="<c:out value="${param.clave}"/>">
		<input type='hidden' name='sesion' value="<c:out value="${param.sesion}"/>" />
	</form>
</div>
<div class="clear"></div>
<br /><br />
<div class='contenedor'>
	<div class='col-100 tablet-100 movil-100 alinearCentro'>
		<h1 class="titulo"><c:out value="${info.title}" /></h1><br />
		<div id="slider" >
			<a href="#" class="control_next">&gt;</a> <a href="#" class="control_prev">&lt;</a>
			<ul>
				<li>
					<div class="ANN">
						<c:choose>
							<c:when test="${not empty param.searchQuery}">
								<c:forEach begin="0" end="0" items="${requestScope.noticias}" var="noticia">
									<c:forEach items="${noticia.ann.anime.news}" var="new">
										<a target="_blank" href="<c:out value="${new.href}"/>"><c:out value="${new.cite}" /></a>
										<span>(<c:out value="${new.datetime}" />)</span><br /><br />
										<hr class="lineasResultados">
									</c:forEach>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span>No hay resultados.</span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				<li>
					<div class="Tumblr">
						<c:choose>
							<c:when test="${not empty param.searchQuery}">
								<c:forEach items="${requestScope.fotos.response}" var="respuesta">
									<c:forEach items="${respuesta.photos}" var="foto">
										<img src="<c:out value="${foto.original_size.url}"/>" />
									</c:forEach>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span>No hay resultados.</span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				<li>
					<div class="Youtube">
						<c:choose>
							<c:when test="${not empty param.searchQuery}">
								<c:forEach items="${requestScope.videos.items}" var="item">
									<iframe src="https://www.youtube.com/embed/<c:out value="${item.id.videoId}"/>"></iframe>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span>No hay resultados.</span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				<li>
					<div class="Aliexpress">
						<c:choose>
							<c:when test="${not empty param.searchQuery}">
								<c:forEach items="${requestScope.productos.result.products}" var="producto">
									<a target="_blank" href="<c:out value="${producto.productUrl}"/>">${producto.productTitle}</a><br />
									<img src="<c:out value="${producto.imageUrl}"/>" />
									<br /><hr class="lineasResultados">
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span>No hay resultados.</span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				<li>
					<div class="MAL">
						<c:choose>
							<c:when test="${not empty param.searchQuery}">
								<c:forEach items="${requestScope.informacion}" var="info">
									<c:if test="${info.title == param.titulo}">
										<span class="sinop">Sinopsis</span><br><br>
										<p class="negro">${info.synopsis}</p><br>
										<hr class="lineasResultados">
										<p class="negro"><span class="tit">Título alternativo</span><br><br> ${info.english}</p><br>
										<hr class="lineasResultados">
										<p class="negro"><span class="eps">Número de episodios</span><br><br> ${info.episodes}</p><br>
										<hr class="lineasResultados">
										<p class="negro"><span class="tip">Tipo</span><br><br> ${info.type}</p>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span>No hay resultados.</span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<video id="video" autoplay="autoplay" muted loop>
		<source src="/videos/amv.mp4" type="video/mp4"></source>
	</video>
</div>
</body>
</html>