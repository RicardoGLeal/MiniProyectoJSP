<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Libro</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <a href="<%=request.getContextPath()%>/Libros" class="navbar-brand"> Libros </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Libros" class="nav-link">Libros</a></li>
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
                                <c:if test="${libro != null}">
                                    Editar Libro
                                </c:if>
                                <c:if test="${libro == null}">
                                    Agregar Libro
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${libro != null}">
                            <input type="hidden" name="id" value="<c:out value='${libro.id}' />" />
                        </c:if>
                        <fieldset class="form-group">
                            <label>Título</label> <input type="text" value="<c:out value='${libro.titulo}' />" class="form-control" name="titulo" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Año</label> <input type="number" value="<c:out value='${libro.año}' />" class="form-control" name="aaa">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Autor</label> <input type="text" value="<c:out value='${libro.autor}' />" class="form-control" name="autor">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Sinopsis</label> <input type="text"value="<c:out value='${libro.sinopsis}' />" class="form-control" name="sinopsis">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Editorial</label> <input type="text"value="<c:out value='${libro.editorial}' />" class="form-control" name="editorial">
                        </fieldset>
                        <c:if test="${libro != null}">
                            <button name="link" type="submit" class="btn btn-success" value="Update/<c:out value='${libro.id}' />">Actualizar</button>
                        </c:if>
                        <c:if test="${libro == null}">
                            <button name="link" type="submit" class="btn btn-success" value="Insert">Guardar</button>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>