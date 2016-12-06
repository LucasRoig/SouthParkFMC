<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- -------------------------------------------------------------------
----------------------------NAV-----------------------------------------
----------------------------------------------------------------------->
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">South Park FMC</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home <span class="sr-only">(current)</span></a></li>
                    <li><a href="/season/read">Saisons</a></li>
                    <li><a href="/character/readAll">Personnages</a></li>
                    <li><a href="/tag/readAll">Tags</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right ">
                    <c:if test="${(empty sessionScope.user)}">
                        <li><a href="/authentication/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        </c:if>
                        <c:if test="${(!empty sessionScope.user)}">
                        <li> <a href="/authentication/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                        </c:if>
                </ul>
                <form class="nav navbar-nav navbar-form navbar-right" action="/episode/search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="text">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </div>
                    </div>
                </form>


            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>
<!----------------------------------------------------------------------
------------------------------------------------------------------------
----------------------------------------------------------------------->
