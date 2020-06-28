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
                    <h2> Bienvenido <c:out value='${user}'/></h2>
                </div>
                <div>
                    &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Main" class="navbar-brand"> Volver </a>
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
                <h3 class="text-center">Lista de Libros</h3>
                <hr>
                <p>En esta sección se encuentra un listado de todos los libros registradas por parte de todos los usuarios de esta comunidad, puedes agregar uno nuevo o puedes vender alguno de estos si cuentas con el y deseas venderlo.</p>
                <br><br>
                <div class="container text-left">
                    <form action="Libros" method="post">
                        <input name="link" type="submit" value="Agregar" class="btn btn-success">
                        <input name="link" type="submit" value="Mis libros vendidos" class="btn btn-primary">
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
                    <form action="Libros" method="post">
                        <c:forEach var="libro" items="${listLibros}">          
                            <tr>
                                <td><c:out value="${libro.id}" /></td>
                                <td><c:out value="${libro.titulo}" /></td>
                                <td><c:out value="${libro.año}" /></td>
                                <td><c:out value="${libro.autor}" /></td>
                                <td><c:out value="${libro.sinopsis}" /></td>
                                <td><c:out value="${libro.editorial}" /></td>
                                <td><button type="submit" name="link" id class="btn btn-warning" value="Edit/<c:out value='${libro.id}' />" id="<c:out value='${libro.id}' />">Editar</button>
                                <td><button type="submit" name="link" id class="btn btn-danger" value="Delete/<c:out value='${libro.id}' />" id="<c:out value='${libro.id}' />">Eliminar</button>
                                <td><button type="submit" name="link" id class="btn btn-info" value="Sell/<c:out value='${libro.id}' />" id="<c:out value='${libro.id}' />">Vender</button>
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>