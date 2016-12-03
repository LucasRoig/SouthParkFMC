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

        <link rel="stylesheet" type="text/css" media="all" href="/css/selectize.bootstrap3.css">
        <link  rel="stylesheet" type="text/css" href="/css/main.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="/js/selectize.min.js"></script>
        <script src="/js/autosize.js"></script>
        <script src="/js/readCharacter.js"></script>
    </head>
    <body>
        <%@include file="/header.jsp" %>
        <!--
        <div class="container">
            <div class="btn-group btn-group-justified">
                <div class="btn-group">
                    <!-- Modifier btn-default pour que ce soit moins moche -->
        <!--
                    <button type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        Ep Précédent
                    </button>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-static ">Ep Courant</button>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-default">Ep Suivant
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </button>
                </div>
            </div>
        </div>
        -->
        <div class="container">
            <center><h1>${character.characterName}</h1></center>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#background">Background</a></li>
                <li><a data-toggle="tab" href="#apparition">Apparitions</a></li>
            </ul>

            <div class="tab-content">
                <%@include file="characterView/character-background.jsp" %>
                <%@include file="characterView/character-apparitions.jsp" %>
            </div>
        </div>
    </body>
</html>
