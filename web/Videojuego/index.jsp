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
                    <a href="<%=request.getContextPath()%>/Main" class="navbar-brand"> Videojuegos <c:out value='${user}'/></a>
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
                <h3 class="text-center">Lista de Videojuegos</h3>
                <hr>
                <p>En esta sección se encuentra un listado de todos los videojuegos registrados por parte de todos los usuarios de esta comunidad, puedes agregar uno nuevo o puedes vender alguno de estos si cuentas con el y deseas venderlo.</p>
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
                            <th>Desarrollador</th>
                            <th>Distribuidora</th>
                            <th>Clasificacion</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="Controller" method="post">
                        <c:forEach var="direction" items="${listVideojuegos}">          
                            <tr>
                                <td><c:out value="${videojuego.id}" /></td>
                                <td><c:out value="${videojuego.titulo}" /></td>
                                <td><c:out value="${videojuego.año}" /></td>
                                <td><c:out value="${videojuego.desarrollador}" /></td>
                                <td><c:out value="${videojuego.distribuidora}" /></td>
                                <td><c:out value="${videojuego.clasificacion}" /></td>
                                <td><button type="submit" name="link" id class="btn btn-success" value="Edit/<c:out value='${videojuego.id}' />" id="<c:out value='${videojuego.id}' />">Editar</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        <td><button type="submit" name="link" id class="btn btn-success" value="Delete/<c:out value='${videojuego.id}' />" id="<c:out value='${videojuego.id}' />">Eliminar</button>
                                            </tr>
                                        </c:forEach>
                                        </form>
                                        </tbody>
                                        </table>
                                        </div>
                                        </div>
                                        </body>
                                        </html>