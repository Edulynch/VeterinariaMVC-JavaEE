<%-- 
    Document   : ListaMascotas
    Created on : 18-07-2018, 1:16:05
    Author     : Eduardo Lynch Araya
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- imports function tags from JSTL, prefix "fn"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Fichas</title>
        <link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <!-- Con esa clase centramos el contenido --> 
            <div class="col-xs-1 text-center">
                <h1>Fichas de <%= session.getAttribute("Nombre") + " " + session.getAttribute("Apellido")%></h1>
                <h1>(<%= session.getAttribute("Rol")%>)</h1>
                <br/>
                <!-- Usamos una clase de Boostrap para las tablas--> 
                <table class="table">
                    <tr>
                        <td><b>ID</b></td>
                        <td><b>Titulo</b></td>
                        <td><b>Mascota</b></td>
                        <td><b>Motivo</b></td>
                        <td><b>Fecha Registro</b></td>              
                        <td><b>Opciones</b></td>
                    </tr>
                    <c:forEach var="ficha" items="${listaFichas}">
                        <tr>
                            <!-- aca obtenemos los campos sengun el nombre y 
                                 el tipo que declaramos en la base de Datos -->
                            <td><c:out value="${ficha.idFicha}"/></td>  
                            <c:set var="shortTitulo" value="${fn:substring(ficha.titulo, 0, 20)}" />
                            <td><c:out value="${shortTitulo}"/>...</td>
                            <td><c:out value="${ficha.idMascota}"/></td>
                            <c:set var="shortMotivo" value="${fn:substring(ficha.motivo, 0, 20)}" />
                            <td><c:out value="${shortMotivo}"/>... </td>
                            <td><c:out value="${ficha.fechaRegistro}"/> </td>
                            <td>
                                <!-- Aca declaramos la pagina para ir a eliminar el registro -->
                                <!-- se pasa como parametro el id de la persona --> 
                                <a href="AccionesDeFicha?accion=borrar&idFicha=<c:out value="${ficha.idFicha}"/>" style="text-decoration:none">
                                    <img src="assets/imagenes/eliminar.png" width="36" height="36" />
                                </a>
                                <!-- Aca declaramos la pagina para ir a Modificar el registro -->
                                <!-- se pasa como parametro el id de la persona --> 
                                <a href="AccionesDeFicha?accion=cargar&idFicha=<c:out value="${ficha.idFicha}"/>" style="text-decoration:none">
                                    <img src="assets/imagenes/modificar.png" width="36" height="36" />
                                </a>
                            </td>                              
                        </tr>
                    </c:forEach>
                </table>
                <!-- Para agregar Mascotas --> 
                <form action="AccionesDeFicha">
                    <input type="hidden" name="accion" value="agregar">
                    <input type="submit" value="¡Agregar Ficha!" class="btn btn-success" />       
                </form> 
            </div>
        </div>
        <script src="https://unpkg.com/ionicons@4.2.4/dist/ionicons.js"></script>
    </body>
</html>
