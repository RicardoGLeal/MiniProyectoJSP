<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Pelicula</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <h2 style="color:whitesmoke;"> Bienvenido <c:out value='${user}'/></h2>
                </div>
                <div>
                    &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Peliculas" class="navbar-brand"> Volver </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Logout" class="nav-link">Salir</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="Peliculas" method="post">
                        <caption>
                            <h2>
                                <c:if test="${pelicula != null}">
                                    Editar Pelicula
                                </c:if>
                                <c:if test="${pelicula == null}">
                                    Agregar Pelicula
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${pelicula != null}">
                            <input type="hidden" name="id" value="<c:out value='${pelicula.id}' />" />
                        </c:if>
                        <fieldset class="form-group">
                            <label>Nombre</label> <input type="text" value="<c:out value='${pelicula.nombre}' />" class="form-control" name="nombre" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Año</label> <input type="number" value="<c:out value='${pelicula.año}' />" class="form-control" name="ano" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Categoria</label> <input type="text" value="<c:out value='${pelicula.categoria}' />" class="form-control" name="categoria">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Director</label> <input type="text" value="<c:out value='${pelicula.director}' />" class="form-control" name="director">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Recaudacion</label> <input type="number" value="<c:out value='${pelicula.recaudacion}' />" class="form-control" name="recaudacion">
                        </fieldset>
                        <c:if test="${pelicula != null}">
                            <button name="link" type="submit" class="btn btn-success" value="Update/<c:out value='${pelicula.id}' />">Actualizar</button>
                        </c:if>
                        <c:if test="${pelicula == null}">
                            <button name="link" type="submit" class="btn btn-success" value="Insert">Guardar</button>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>