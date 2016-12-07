<%-- 
    Document   : index
    Created on : 18-Nov-2016, 19:44:16
    Author     : freyb
--%>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@page import="java.util.List, models.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="topUserBar.jsp"%>
        <h1>Kager</h1>
        <p>See the best cakes in the world.</p>
    </body>
</html>
=======
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainpage>
    <jsp:attribute name="header">
        <jsp:include page="topUserBar.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="footer.jsp"/>
    </jsp:attribute>
  
    <jsp:body>
        
    </jsp:body>
</t:mainpage>
>>>>>>> origin/master
