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
                <p>En esta sección se encuentra un listado de todos los libros que estas vendiendo, recuerda que el libro tiene que estar registrado.</p>
            
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="libro" items="${listLibros}">          
                            <tr>
                                <td><c:out value="${libro.id}" /></td>
                                <td><c:out value="${libro.getLibri().titulo}" /></td>
                                <td><c:out value="${libro.precio}" /></td>
                            </tr>
                        </c:forEach>>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>