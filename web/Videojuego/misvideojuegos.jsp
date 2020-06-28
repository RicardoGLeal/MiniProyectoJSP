<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD VIDEOJUEGOS</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <a href="<%=request.getContextPath()%>/Controller" class="navbar-brand"> Videojuegos</a>
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
                <h3 class="text-center">Lista de Videojuegos</h3>
                <hr>
                <p>En esta sección se encuentra un listado de todos los videojuegos que estas vendiendo, recuerda que el videojuego tiene que estar registrado.</p>
            
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="videojuego" items="${listVideojuegos}">          
                            <tr>
                                <td><c:out value="${videojuego.id}" /></td>
                                <td><c:out value="${videojuego.getVideojuego().titulo}" /></td>
                                <td><c:out value="${videojuego.precio}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>