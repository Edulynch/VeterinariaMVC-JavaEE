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
        <title>Agregar Ficha</title>
        <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <h3 class="text-center">Agregar Ficha</h3>
            <!-- Formulario de Datos, la accion va a una pagina jsp que nos va a insertar los datos -->
            <form method="post" action="AccionesDeFicha">
                <input type="hidden" name="accion" value="crear">
                <div class="form-group">
                    Titulo <input type="text" name="titulo" class="form-control" required autofocus/>
                </div>
                <div class="form-group">
                    Mascota <input type="text" name="idMascota" class="form-control" required/>
                </div>
                <div class="form-group">
                    <label for="motvioFicha">Motivo</label>
                    <textarea class="form-control" id="motvioFicha" name="motivo" rows="3"></textarea>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Agregar</button>   
            </form>
        </div><!-- /card-container -->
    </div><!-- /container -->
</body>
</html>
