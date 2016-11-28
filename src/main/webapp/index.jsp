<%-- 
    Document   : index
    Created on : 24 nov. 2016, 16:45:32
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

        <link  rel="stylesheet" type="text/css" href="/css/main.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    </head>
<body>
<%@include file="header.jsp" %>
        <div class="container">
            <div class="jumbotron text-center">
                <h1>South Park FMC</h1><form class="">
                <div class="input-group">
                    <input type="text" class="form-control input-lg" placeholder="Search">
                            <span class="input-group-btn">
                                <button class="btn btn-default btn-lg" type="submit">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                </div>
            </form>
            </div>
        </div>
    </body>
</html>
