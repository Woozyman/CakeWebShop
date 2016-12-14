<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %> <!-- Remember page directive to Use types in jsp. -->
<%@page import="models.Cart" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CakeWebShop</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home.jsp">CakeWebshop</a>
        </div>
        <% Cart cart = (Cart) session.getAttribute("cart"); %>
        <%if (session.getAttribute("userObj") != null) {
                User user = (User) session.getAttribute("userObj");
                if (user.getFirstname().equals("admin")) {
// admin goes here%>        
        <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/AccountController?action=logout" method="POST">
            <div class="form-group">
                <button type="submit" value="logout" class="btn btn-warning navbar-btn"><span class="glyphicon glyphicon-log-out"></span> Log out: <% out.println(user.getFirstname());%></button>
            </div>
        </form>
        <% } else {
// Normal user goes here %>
        <form method="post" action="${pageContext.servletContext.contextPath}/RouteController?action=showCart">
            <button  class="btn btn-success navbar-btn navbar-right" ><span class="glyphicon glyphicon-shopping-cart"></span> Indkøbskurv (<%out.println(cart.getItemsCount());%>)</button>
        </form> 

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
                <input type="text" name="email" class="form-control" placeholder="E-mail">
                <input type="text" name="password" class="form-control" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Login</button> 
        </form>
<% //her starter loginknapper %>
        <form class="navbar-form navbar-left" action="${pageContext.servletContext.contextPath}/AccountController?action=login" method="POST">
            <div class="form-group">
                <input type="hidden" name="email" class="form-control" placeholder="E-mail" value="fclante@gmail.com">
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
<% //her slutter loginknapper %>
        <button type="submit" class="btn btn-success navbar-btn navbar-right" action="${pageContext.servletContext.contextPath}/CartController?action=showCart" method="post"><span class="glyphicon glyphicon-shopping-cart"></span> Indkøbskurv (<%out.print(cart.getItemsCount());%>)</button>
        <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/formRegistration.jsp" method="post">
            <button type="submit" class="btn navbar-btn"><span class="glyphicon glyphicon-user"></span> Register</button>
        </form>
        <% } %>     
    </div>
</nav>