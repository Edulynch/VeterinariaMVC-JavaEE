<%-- 
    Document   : ModificarMascotas
    Created on : 18-07-2018, 1:42:54
    Author     : Eduardo Lynch Araya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Mascota</title>  
        <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.min.css">
        <link href="css/Style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="card card-container">
                <h3 class="text-center">Modificar Mascota</h3>
                <!-- Formulario de Datos, la accion va a una pagina jsp que nos va a insertar los datos -->
                <form method="post" action="AccionesDeMascota">
                    <input type="hidden" name="accion" value="editar">
                    <input type="hidden" name="idMascota" value="${mascota.idMascota}" />
                    <input type="hidden" name="idUsuario" value="${mascota.idUsuario}" />
                    <div class="form-group">
                        Nombre: <input type="text" name="nomMascota" class="form-control" value="${mascota.nombre}" required autofocus/>
                    </div>
                    <div class="form-group">
                        Raza <input type="text" name="razaMascota" class="form-control" value="${mascota.raza}" required/>
                        <br /> 
                    </div> 
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Actualizar</button>   
                </form>
            </div><!-- /card-container -->
        </div><!-- /container -->
    </body>
</html>
