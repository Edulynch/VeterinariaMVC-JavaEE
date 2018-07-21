<%-- 
    Document   : Registro
    Created on : 20-07-2018, 1:48:10
    Author     : Eduardo Lynch Araya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Registro &mdash; Veterinaria</title>
        <link rel="stylesheet" type="text/css" href="RECURSOS/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="RECURSOS/css/my-login.css">
    </head>
    <body class="my-login-page">
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center h-100">
                    <div class="card-wrapper">
                        <div class="brand">
                            <img src="RECURSOS/img/logo.jpg">
                        </div>
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Registro</h4>
                                <form method="post" action="AccionesDeUsuario">
                                    <input type="hidden" name="accion" value="crear">
                                    <div class="form-group">
                                        <label for="name">Nombre</label>
                                        <input id="name" type="text" placeholder="Usuario..." class="form-control" name="nomUsuario" minlength="3" maxlength="35" pattern="[A-Za-z]+" title="Caracteres Numericos no son aceptados." required autofocus>
                                    </div>

                                    <div class="form-group">
                                        <label for="name">Apellido</label>
                                        <input id="name" type="text" placeholder="Ejemplo..." class="form-control" name="apeUsuario" minlength="3" maxlength="35" pattern="[A-Za-z]+" title="Caracteres Numericos no son aceptados."1 required autofocus>
                                    </div>

                                    <div class="form-group">
                                        <label for="email">E-Mail</label>
                                        <input id="email" type="email" placeholder="usuario@ejemplo.com" class="form-control" name="emailUsuario" minlength="6" maxlength="255" required>
                                    </div>

                                    <fieldset>
                                        <label>¿Eres Veterinario?</label><br />
                                        <label class="custom-control custom-checkbox">
                                            <input type="radio" class="custom-control-input" name="roleUsuario" value="PACIENTE" checked>
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">Paciente</span>
                                        </label>
                                        <label class="custom-control custom-checkbox">
                                            <input type="radio" class="custom-control-input" name="roleUsuario" value="MEDICO">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">Medico</span>
                                        </label>
                                    </fieldset>

                                    <div class="form-group">
                                        <label for="password">Contraseña</label>
                                        <input id="password" type="password" placeholder="********************" class="form-control" name="passUsuario" minlength="6" maxlength="20" required data-eye>
                                    </div>

                                    <div class="form-group no-margin">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Registrame
                                        </button>
                                    </div>
                                    <div class="margin-top20 text-center">
                                        ¿Ya tienes una cuenta? <a href="<%= request.getContextPath() + "/"%>">Login</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="footer">
                            Copyright &copy; 2018 &mdash; Eduardo Lynch 
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="RECURSOS/js/jquery.min.js"></script>
        <script src="RECURSOS/bootstrap/js/bootstrap.min.js"></script>
        <script src="RECURSOS/js/my-login.js"></script>
    </body>
</html>