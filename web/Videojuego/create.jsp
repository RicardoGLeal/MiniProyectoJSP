<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Videojuegos</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <a href="<%=request.getContextPath()%>/Videojuegos" class="navbar-brand"> Videojuegos </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Videojuegos" class="nav-link">Videojuegos</a></li>
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
                                <c:if test="${videojuego != null}">
                                    Editar Videojuego
                                </c:if>
                                <c:if test="${videojuego == null}">
                                    Agregar Videojuego
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${videojuego != null}">
                            <input type="hidden" name="id" value="<c:out value='${videojuego.id}' />" />
                        </c:if>
                        <fieldset class="form-group">
                            <label>Título</label> <input type="text" value="<c:out value='${videojuego.titulo}' />" class="form-control" name="titulo" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Año</label> <input type="number" value="<c:out value='${videojuego.año}' />" class="form-control" name="ano">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Desarrollador</label> <input type="text" value="<c:out value='${videojuego.desarrollador}' />" class="form-control" name="desarrollador">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Distribuidora</label> <input type="text"value="<c:out value='${videojuego.distribuidora}' />" class="form-control" name="distribuidora">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Clasificacion</label> <input type="text"value="<c:out value='${videojuego.clasificacion}' />" class="form-control" name="clasificacion">
                        </fieldset>
                        <c:if test="${videojuego != null}">
                            <button name="link" type="submit" class="btn btn-success" value="Update/<c:out value='${videojuego.id}' />">Actualizar</button>
                        </c:if>
                        <c:if test="${videojuego == null}">
                            <button name="link" type="submit" class="btn btn-success" value="Insert">Guardar</button>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>