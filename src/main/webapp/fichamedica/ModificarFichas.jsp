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
        <title>Modificar Ficha</title>  
        <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.min.css">
        <link href="css/Style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h3 class="text-center">Modificar Ficha</h3>
            <!-- Formulario de Datos, la accion va a una pagina jsp que nos va a insertar los datos -->
            <form method="post" action="AccionesDeFicha">
                <input type="hidden" name="accion" value="editar">
                <input type="hidden" name="idFicha" value="${ficha.idFicha}" />
                <input type="hidden" name="idUsuario" value="${ficha.idUsuario}" />
                <div class="form-group">
                    Titulo <input type="text" name="titulo" class="form-control" value="${ficha.titulo}" required autofocus/>
                </div>
                <div class="form-group">
                    idMascota <input type="text" name="idMascota" class="form-control" value="${ficha.idMascota}" required/>
                    <br /> 
                </div> 
                <div class="form-group">
                    <label for="motivoFicha">Motivo</label>
                    <textarea class="form-control" id="motivoFicha" name="motivo" rows="3">${ficha.motivo}</textarea>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Actualizar</button>   
            </form>
        </div><!-- /card-container -->
    </div><!-- /container -->
</body>
</html>
