<%-- 
    Document   : topUserBar
    Created on : 03-12-2016, 08:23:53
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %> <!- Remember page directive to Use types in jsp. -->

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <form class="navbar-form navbar-left" action="Login" method="POST">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="User name">
                <input type="text" class="form-control" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default">Log in</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <%if(request.getAttribute("user")!=null){
                User user = (User)request.getAttribute("user");
                out.print("<li><a>Logged in as: "+user.getFirstname()+"</a></li>");
            }%>
        </ul>
    </div>
</nav>
