<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Libros</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <a href="<%=request.getContextPath()%>/Peliculas" class="navbar-brand"> Libros </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Peliculas" class="nav-link"> Libros </a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="Libros" method="post">
                        <caption>
                            <h2>
                                Vender Libro
                            </h2>

                            <br>
                        </caption>

                        <c:if test="${libro != null}">
                            <input type="hidden" name="id" value="<c:out value='${libro.id}' />" />
                        </c:if>
                      <fieldset class="form-group">
                          <label>Titulo</label> <input type="text" value="<c:out value='${libro.titulo}' />" class="form-control" name="titulo" required="required" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Año</label> <input type="number" value="<c:out value='${libro.año}' />" class="form-control" name="ano" required="required" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Editorial</label> <input type="text" value="<c:out value='${libro.editorial}' />" class="form-control" name="editorial" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Autor</label> <input type="text" value="<c:out value='${libro.autor}' />" class="form-control" name="autor" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Sinopsis</label> <input type="text" value="<c:out value='${libro.sinopsis}' />" class="form-control" name="sinopsis" disabled="true">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Precio: </label> <input type="number" class="form-control" name="precio" required="required">
                        </fieldset>
                        <button name="link" type="submit" class="btn btn-success" value="Sold/<c:out value='${libro.id}' />">Vender</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
