<%-- 
    Document   : login
    Created on : 3 déc. 2016, 07:26:23
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="/css/bootstrap-theme.min.css">

        <link  rel="stylesheet" type="text/css" href="/css/main.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="col-md-offset-3 col-md-6">
                <div class="panel panel-default ">
                    <div class="panel-heading">
                        Login
                    </div>
                    <div class="panel-body">
                        <form action="/authentication/login" method="POST">
                            <div class="form-group">
                                <label class="control-label">Username</label>
                                <input class="form-control" type="text" name="username">
                            </div>
                            <div class="form-group">
                                <label class="control-label">Password</label>
                                <input class="form-control" type="text" name="password">
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Valider</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>


</html>
