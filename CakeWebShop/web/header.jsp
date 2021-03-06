<%@page import="models.User" %> <!-- Remember page directive to Use types in jsp. -->
<%@page import="models.Cart" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CakeWebShop</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/index.jsp">CakeWebshop</a>
                </div>
                <% Cart cart = (Cart) session.getAttribute("cart"); %>
                <%if (session.getAttribute("userObj") != null) {
                        User user = (User) session.getAttribute("userObj");
                        if (user.getFirstname().equals("admin")) {
        // admin goes here%>        
                <div class="navbar-nav navbar-right">
                    <a href="upload.jsp" class="btn btn-warning navbar-btn"><span class="glyphicon glyphicon-plus"></span> Create New Shop Item</a>
                </div>
                <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/AccountController?action=logout" method="POST">
                    <div class="form-group">
                        <button type="submit" value="logout" class="btn btn-warning navbar-btn"><span class="glyphicon glyphicon-log-out"></span> Log out: <% out.println(user.getFirstname());%></button>
                    </div>
                </form>
                <% } else if (user.getFirstname().equals("baker")) {
// Baker user goes here %>
                <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/AccountController?action=logout" method="POST">
                    <div class="form-group">
                        <button type="submit" value="logout" class="btn btn-warning navbar-btn"><span class="glyphicon glyphicon-log-out"></span> Log out: <% out.println(user.getFirstname());%></button>
                    </div>
                </form>                
                <%} else {
// Normal user goes here %>
                <a href="CartController?action=showCart" class="btn btn-success navbar-btn navbar-right" ><span class="glyphicon glyphicon-shopping-cart"></span> Indkøbskurv (<%out.println(cart.getItemsCount());%>)</a>
                <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/AccountController?action=logout" method="POST">
                    <div class="form-group">
                        <button type="submit" value="logout" class="btn btn-warning navbar-btn"><span class="glyphicon glyphicon-log-out"></span> Log out: <% out.print(user.getFirstname()); %></button>
                    </div>
                </form>
                <% }
                } else {
        // Guest goes here %> 
                <form class="navbar-form navbar-left" action="${pageContext.servletContext.contextPath}/AccountController?action=login" method="POST">
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="E-mail">
                        <input type="password" name="password" class="form-control" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Login</button> 
                </form>
                <% //her starter loginknapper %>
                <form class="navbar-form navbar-left" action="${pageContext.servletContext.contextPath}/AccountController?action=login" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="email" class="form-control" placeholder="E-mail" value="frey@cakewebshop.com">
                        <input type="hidden" name="password" class="form-control" placeholder="Password" value="pass123">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Login Frey</button> 
                </form>
                <form class="navbar-form navbar-left" action="${pageContext.servletContext.contextPath}/AccountController?action=login" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="email" class="form-control" placeholder="E-mail" value="admin@cakewebshop.com">
                        <input type="hidden" name="password" class="form-control" placeholder="Password" value="admin123">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Login Admin</button> 
                </form>
                <form class="navbar-form navbar-left" action="${pageContext.servletContext.contextPath}/AccountController?action=login" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="email" class="form-control" placeholder="E-mail" value="baker@cakewebshop.com">
                        <input type="hidden" name="password" class="form-control" placeholder="Password" value="baker123">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Login Baker</button> 
                </form>
                <% //her slutter loginknapper %>

                <a href="CartController?action=showCart" class="btn btn-success navbar-btn navbar-right" ><span class="glyphicon glyphicon-shopping-cart"></span> Indkøbskurv (<%out.println(cart.getItemsCount());%>)</a>

                <a href="formRegistration.jsp" class="btn navbar-btn"><span class="glyphicon glyphicon-user"></span> Register</a>

                <% } %>     
            </div>
        </nav>
