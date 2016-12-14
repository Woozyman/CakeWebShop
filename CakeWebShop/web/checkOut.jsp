<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>

<div class="container">
    <h1>CheckOut/payment</h1><br>
    <p>Ammount: (Beløbet som skal trækkes kommer her)</p>
    <p>heraf moms (25%): (beløb ovenfor /0.2 )</p>
</div>

<c:import url="footer.jsp"></c:import>