<%@page import="com.emergentes.modelo.Producto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Producto"%>
<%Producto prod = (Producto) request.getAttribute("prod");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${prod.id == 0}">
                Nuevo Registro
            </c:if>

                <c:if test="${prod.id != 0}">
                Editar Registro
            </c:if>

        </h1>
        <form action="Inicio" method="POST">

            <input type="hidden" name="id" value="${prod.id}"> 

            <table border="1">
                <tr>
                    <td>Descripcion:</td>
                    <td><input type="text" name="descripcion" value="${prod.descripcion}"></td>
                </tr>
                <tr>
                    <td>Cantidad:</td>
                    <td><input type="number" name="cantidad" value="${prod.cantidad}"></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input type="text" name="precio" value="${prod.precio}"></td>
                </tr>
                <tr>
                    <td>Categoria:</td>
                    <td><input type="text" name="categoria" value="${prod.categoria}"></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>

                
            </table>
        </form>

    </body>
</html>
