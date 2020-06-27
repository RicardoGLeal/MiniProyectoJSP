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
                    <a href="<%=request.getContextPath()%>/Main" class="navbar-brand"> Películas</a>
                </div>
                <ul class="navbar-nav">
                    <li>
                        <a href="<%=request.getContextPath()%>/"
                           class="nav-link">Salir
                        </a>
                    </li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <div class="container">
                <h3 class="text-center">Lista de Peliculas</h3>
                <hr>
                <p>En esta sección se encuentra un listado de todas las películas registradas por parte de todos los usuarios de esta comunidad, puedes agregar una nueva o puedes vender alguna de estas si cuentas con ella y deseas venderla.</p>
                <br><br>
                <div class="container text-left">
                    <form action="Peliculas" method="post">
                        <input name="link" type="submit" value="Agregar" class="btn btn-success">
                        <input name="link" type="submit" value="Mis peliculas vendidas" class="btn btn-primary">
                    </form>
                </div>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Año</th>
                            <th>Categoria</th>
                            <th>Director</th>
                            <th>Recaudacion</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="Peliculas" method="post">
                        <c:forEach var="pelicula" items="${listPeliculas}">          
                            <tr>
                                <td><c:out value="${pelicula.id}" /></td>
                                <td><c:out value="${pelicula.nombre}" /></td>
                                <td><c:out value="${pelicula.año}" /></td>
                                <td><c:out value="${pelicula.categoria}" /></td>
                                <td><c:out value="${pelicula.director}" /></td>
                                <td><c:out value="${pelicula.recaudacion}" /></td>
                                <td><button type="submit" name="link" id class="btn btn-warning" value="Edit/<c:out value='${pelicula.id}' />" id="<c:out value='${pelicula.id}' />">Editar</button>
                                <td><button type="submit" name="link" id class="btn btn-danger" value="Delete/<c:out value='${pelicula.id}' />" id="<c:out value='${pelicula.id}' />">Eliminar</button>
                                <td><button type="submit" name="link" id class="btn btn-info" value="Sell/<c:out value='${pelicula.id}' />" id="<c:out value='${pelicula.id}' />">Vender</button>
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>