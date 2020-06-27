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
                    <a href="<%=request.getContextPath()%>/Controller" class="navbar-brand"> Libros</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Controller"
                           class="nav-link"><c:out value='${user}'/></a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <div class="container">
                <h3 class="text-center">Lista de Libros</h3>
                <hr>
                <p>En esta sección se encuentra un listado de todos los libros registrados por parte de todos los usuarios de esta comunidad, puedes agregar uno nuevo o puedes vender alguno de estos si cuentas con el y deseas venderlo.</p>
                <br><br>
                <div class="container text-left">
                    <form action="Videojuegos" method="post">
                        <input name="link" type="submit" value="Recargar" class="btn btn-success">
                        <input name="link" type="submit" value="Agregar" class="btn btn-success">
                    </form>
                </div>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Titulo</th>
                            <th>Año</th>
                            <th>Autor</th>
                            <th>Sinopsis</th>
                            <th>Editorial</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="Controller" method="post">
                        <c:forEach var="direction" items="${listLibros}">          
                            <tr>
                                <td><c:out value="${libro.id}" /></td>
                                <td><c:out value="${libro.titulo}" /></td>
                                <td><c:out value="${libro.año}" /></td>
                                <td><c:out value="${libro.autor}" /></td>
                                <td><c:out value="${libro.sinopsis}" /></td>
                                <td><c:out value="${libro.editorial}" /></td>
                                <td><button type="submit" name="link" id class="btn btn-success" value="Edit/<c:out value='${libro.id}' />" id="<c:out value='${libro.id}' />">Editar</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        <td><button type="submit" name="link" id class="btn btn-success" value="Delete/<c:out value='${libro.id}' />" id="<c:out value='${libro.id}' />">Eliminar</button>
                                            </tr>
                                        </c:forEach>
                                        </form>
                                        </tbody>
                                        </table>
                                        </div>
                                        </div>
                                        </body>
                                        </html>