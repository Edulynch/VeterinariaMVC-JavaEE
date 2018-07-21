<%-- 
    Document   : AgregarMascotas.jsp
    Created on : 18-07-2018, 1:50:21
    Author     : Eduardo Lynch Araya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Mascota</title>
        <link rel="stylesheet" type="text/css" href="RECURSOS/bootstrap/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <div class="card card-container">
                <h3 class="text-center">Agregar Mascota</h3>
                <!-- Formulario de Datos, la accion va a una pagina jsp que nos va a insertar los datos -->
                <form method="post" action="AccionesDeMascota">
                    <input type="hidden" name="accion" value="crear">
                    <div class="form-group">
                        Nombre: <input type="text" name="nomMascota" class="form-control" required autofocus/>
                    </div>
                    <div class="form-group">
                        Raza <input type="text" name="razaMascota" class="form-control" required autofocus/>
                    </div>
            </div> 
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Agregar</button>   
        </form>
    </div><!-- /card-container -->
</div><!-- /container -->
</body>
</html>
