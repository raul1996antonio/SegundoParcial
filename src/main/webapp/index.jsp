<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>
<% ArrayList<Producto> prod = (ArrayList<Producto>) request.getAttribute("prods");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
    </head>

    <body>
        <table border="2">
            <TH>
                <h2>SEGUNDO PARCIAL TEM-742</h2>
                <h4>NOMBRE: RAUL ANTONIO RAMIREZ</h4>
                <h4>CARNET: 10062212 L.P.</h4>
            </TH>
        </table>

        <h2>Gestion de Productos</h2>
        <p><a href="Inicio?action=add">Nuevo producto</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${prod}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.descripcion}</td>
                    <td>${item.cantidad}</td>
                    <td>${item.precio}</td>
                    <td>${item.categoria}</td>
                    <td>
                        <a href="Inicio?action=edit&id=${item.id}">Editar</a>
                    </td>
                    <td>
                        <a href="Inicio?action=delete&id=${item.id}" onclick="return(confirm('¿Estas seguro de eliminar?'))">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        
                
        </table>
        
    </body>
</html>
