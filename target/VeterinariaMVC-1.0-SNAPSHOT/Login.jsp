<%-- 
    Document   : index
    Created on : 18-07-2018, 1:14:29
    Author     : Eduardo Lynch Araya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Login &mdash; Veterinaria</title>
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
                                <h4 class="card-title">Login</h4>

                                <form method="post" action="AccionesDeUsuario">
                                    <input type="hidden" name="accion" value="login">

                                    <div class="form-group">
                                        <label for="email">E-Mail
                                            <input id="email" placeholder="usuario@ejemplo.com" type="email" class="form-control" name="emailUsuario" value="" required autofocus>
                                            </div>

                                            <div class="form-group">
                                                <label for="password">Contraseña
                                                </label>
                                                <input id="password" placeholder="********************" type="password" class="form-control" name="passUsuario" required data-eye>
                                            </div>

                                            <div class="form-group no-margin">
                                                <button type="submit" class="btn btn-primary btn-block">
                                                    Iniciar Sesion
                                                </button>
                                            </div>
                                            <div class="margin-top20 text-center">
                                                ¿No tienes una cuenta? <a href="AccionesDeUsuario?accion=registrar">Registrate</a>
                                            </div>
                                            </form>
                                    </div>
                            </div>
                            <div class="footer">
                                Copyright &copy; 2017 &mdash; Eduardo Lynch 
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