<%-- 
    Document   : loginOrRegister
    Created on : 19-Dec-2016, 21:19:51
    Author     : freyb
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"></c:import>

    <div class="container">
        <h1>Login or Check Out </h1><br>
        <form class="navbar-form navbar-left" method="POST">
            <div class="form-group">
                <input type="email" name="email" class="form-control" placeholder="E-mail">
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <button type="submit" formaction="${pageContext.servletContext.contextPath}/AccountController?action=login" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Login</button>
            <button type="submit" formaction="${pageContext.servletContext.contextPath}/AccountController?action=register" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Register</button> 
        </form>
    </div>

<c:import url="footer.jsp"></c:import>