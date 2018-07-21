<%-- 
    Document   : ListaMascotas
    Created on : 18-07-2018, 1:16:05
    Author     : Eduardo Lynch Araya
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Mascotas</title>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <!-- Con esa clase centramos el contenido --> 
            <div class="col-xs-1 text-center">
                <h1>Listando Datos de Macota</h1>
                <br/>
                <!-- Usamos una clase de Boostrap para las tablas--> 
                <table class="table">
                    <tr>
                        <td><b>ID</b></td>
                        <td><b>Nombre</b></td>
                        <td><b>Raza</b></td>
                        <td><b>Fecha Registro</b></td>              
                    </tr>
                    <c:forEach var="mascota" items="${listaMascotas}">
                        <tr>
                            <!-- aca obtenemos los campos sengun el nombre y 
                                 el tipo que declaramos en la base de Datos -->
                            <td><c:out value="${mascota.idMascota}"/></td>  
                            <td><c:out value="${mascota.nombre}"/></td>
                            <td><c:out value="${mascota.raza}"/></td>
                            <td><c:out value="${mascota.fechaRegistro}"/> </td>
                            <td>
                                <!-- Aca declaramos la pagina para ir a eliminar el registro -->
                                <!-- se pasa como parametro el id de la persona --> 
                                <a href="AccionesDeMascota?accion=borrar&idMascota=<c:out value="${mascota.idMascota}"/>">
                                    <img src="imagenes/eliminar.png" width="36" height="36" />
                                </a>
                                <!-- Aca declaramos la pagina para ir a Modificar el registro -->
                                <!-- se pasa como parametro el id de la persona --> 
                                <a href="AccionesDeMascota?accion=cargar&idMascota=<c:out value="${mascota.idMascota}"/>">
                                    <img src="imagenes/modificar.png" width="36" height="36" />
                                </a>
                            </td>                              
                        </tr>
                    </c:forEach>
                </table>
                <!-- Para agregar Mascotas --> 
                <form action="AgregarMascotas.jsp">
                    <input type="submit" value="¡Agregar Mascota!" class="btn btn-success" />       
                </form> 
            </div>
        </div>
    </body>
</html>
