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
                    <h2 style="color:whitesmoke;"> Bienvenido <c:out value='${user}'/></h2>
                </div>
                <div>
                    &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Main" class="navbar-brand"> Volver</a>
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
                <h3 class="text-center">Lista de Videojuegos</h3>
                <hr>
                <p>En esta sección se encuentra un listado de todas los videojuegos registrados por parte de todos los usuarios de esta comunidad, puedes agregar uno nuevo o puedes vender alguno de estos si cuentas con el y deseas venderlo.</p>
                <br><br>
                <div class="container text-left">
                    <form action="Videojuegos" method="post">
                        <input name="link" type="submit" value="Agregar" class="btn btn-success">
                        <input name="link" type="submit" value="Mis videojuegos vendidos" class="btn btn-primary">
                    </form>
                </div>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Título</th>
                            <th>Año</th>
                            <th>Desarrollador</th>
                            <th>Distribuidora</th>
                            <th>Clasificación</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="Videojuegos" method="post">
                        <c:forEach var="videojuego" items="${listVideojuegos}">          
                            <tr>
                                <td><c:out value="${videojuego.id}" /></td>
                                <td><c:out value="${videojuego.titulo}" /></td>
                                <td><c:out value="${videojuego.año}" /></td>
                                <td><c:out value="${videojuego.desarrollador}" /></td>
                                <td><c:out value="${videojuego.distribuidora}" /></td>
                                <td><c:out value="${videojuego.clasificacion}" /></td>
                                <td><button type="submit" name="link" id class="btn btn-warning" value="Edit/<c:out value='${videojuego.id}' />" id="<c:out value='${videojuego.id}' />">Editar</button>
                                <td><button type="submit" name="link" id class="btn btn-danger" value="Delete/<c:out value='${videojuego.id}' />" id="<c:out value='${videojuego.id}' />">Eliminar</button>
                                <td><button type="submit" name="link" id class="btn btn-info" value="Sell/<c:out value='${videojuego.id}' />" id="<c:out value='${videojuego.id}' />">Vender</button>
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>