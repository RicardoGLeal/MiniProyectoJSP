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
                    <a href="<%=request.getContextPath()%>/Controller" class="navbar-brand"> Bienvenido</a>
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
                <h3 class="text-center">Venta de Multimedia</h3>

                <p style="text-align: center">Bienvenido! <c:out value='${user}'/>  Has iniciado sesion correctamente</p>

                <hr>
                <div class="container text-left">
                    <form action="Main" method="post">
                        <input style="width: 50%" name="link" type="submit" value="Peliculas" class="btn btn-success">
                    </form>
                    <form action="Main" method="post">
                        <input style="width: 50%" name="link" type="submit" value="Videojuegos" class="btn btn-success">
                    </form>
                    <form action="Main" method="post">
                        <input style="width: 50%" name="link" type="submit" value="Libros" class="btn btn-success">
                    </form>
                </div>
                <br>
            </div>
        </div>
    </body>
</html>