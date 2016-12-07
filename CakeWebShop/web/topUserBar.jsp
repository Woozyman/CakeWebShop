<%-- 
    Document   : topUserBar
    Created on : 03-12-2016, 08:23:53
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %> <!-- Remember page directive to Use types in jsp. -->

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="nav navbar-nav navbar-right">
            <%if(session.getAttribute("userObj")!=null){
                User user = (User)session.getAttribute("userObj");
                out.print("<p>Logged in as: "+user.getFirstname()+"</p>");%>
            <form action='Login' method='POST'>
            <input type='hidden' name='origin' value='logout'>
            <input type='submit' value='Logout'>
            <% }else{ %>
            
        <form class="navbar-form navbar-left" action="Login" method="POST">
            <div class="form-group">
                <input type="text" name="email" class="form-control" placeholder="E-mail" value="admin@cakewebshop.com">
                <input type="text" name="password" class="form-control" placeholder="Password" value="admin123">
            </div>
            <button type="submit" class="btn btn-default">Log in</button>
        </form>
            <%
                out.print("<p>Welcome visitor</p>");
            }
            %>
        </div>
    </div>
</nav>
