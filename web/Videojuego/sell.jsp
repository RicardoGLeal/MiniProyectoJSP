<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Direcciones</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <a href="<%=request.getContextPath()%>/Peliculas" class="navbar-brand"> Videojuegos </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Peliculas" class="nav-link"> Videojuegos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="Videojuegos" method="post">
                        <caption>
                            <h2>
                                Vender Videojuego
                            </h2>

                            <br>
                        </caption>

                        <c:if test="${videojuego != null}">
                            <input type="hidden" name="id" value="<c:out value='${videojuego.id}' />" />
                        </c:if>
                      <fieldset class="form-group">
                          <label>Titulo</label> <input type="text" value="<c:out value='${videojuego.titulo}' />" class="form-control" name="nombre" required="required" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Año</label> <input type="number" value="<c:out value='${videojuego.año}' />" class="form-control" name="ano" required="required" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Desarrollador</label> <input type="text" value="<c:out value='${videojuego.desarrollador}' />" class="form-control" name="categoria" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Distribuidora</label> <input type="text" value="<c:out value='${videojuego.distribuidora}' />" class="form-control" name="director" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Clasificación</label> <input type="text" value="<c:out value='${videojuego.clasificacion}' />" class="form-control" name="recaudacion" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Precio: </label> <input type="number" class="form-control" name="precio" required="required">
                        </fieldset>
                        <button name="link" type="submit" class="btn btn-success" value="Sold/<c:out value='${videojuego.id}' />">Vender</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>