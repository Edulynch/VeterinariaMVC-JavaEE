<%-- 
    Document   : Registrado
    Created on : 19-07-2018, 23:18:24
    Author     : Eduardo Lynch Araya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>My Login Page &mdash; Bootstrap 4 Login Page Snippet</title>
        <link rel="stylesheet" type="text/css" href="RECURSOS/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="RECURSOS/css/my-login.css">
    </head>
    <body class="my-login-page">
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center align-items-center h-100">
                    <div class="card-wrapper">
                        <div class="brand">
                            <img src="RECURSOS/img/logo.jpg">
                        </div>
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Forgot Password</h4>
                                <form method="POST">

                                    <div class="form-group">
                                        <label for="email">E-Mail Address</label>
                                        <input id="email" type="email" class="form-control" name="email" value="" required autofocus>
                                        <div class="form-text text-muted">
                                            By clicking "Reset Password" we will send a password reset link
                                        </div>
                                    </div>

                                    <div class="form-group no-margin">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Reset Password
                                        </button>
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
    </body>
</html>