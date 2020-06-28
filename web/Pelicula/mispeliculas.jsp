<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Peliculas</title>
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
                    &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Peliculas" class="navbar-brand"> Volver</a>
                </div>
                <ul class="navbar-nav">
                    <li>
                        <a href="<%=request.getContextPath()%>/Logout"
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
                <p>En esta sección se encuentra un listado de todas las películas que estas vendiendo, recuerda que la pelicula tiene que estar registrada.</p>
            
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="pelicula" items="${listPeliculas}">          
                            <tr>
                                <td><c:out value="${pelicula.id}" /></td>
                                <td><c:out value="${pelicula.getPelicula().nombre}" /></td>
                                <td><c:out value="${pelicula.precio}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>