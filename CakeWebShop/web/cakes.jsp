<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>
<% ShopItem item = (ShopItem) request.getAttribute("item"); %>
<div class="container">
    <h1> <%= item.getItemName() %></h1><br>
    <div class="row">
        <div class="col-sm-4"><img width="350" src="${pageContext.servletContext.contextPath}/<%= item.getItemPicture() %>"/></div>
        <div class="col-sm-8">Name:<br><%= item.getItemName() %><br><br>Price:<br><%= item.getItemPrice()  %><br><br>Description:<br><%= item.getItemDescription() %></div>
    </div>
    <form class="form-inline" action="/CartController?action=addToCart&numOfItems=${numOfItems}">
        <div class="form-group">
            <label for="focusedInput">Antal</label>
            <input class="form-control" id="focusedInput" name="numOfItems" type="number" value="1" min="1">
            <button type="submit" class="btn btn-default">Bestil</button>
        </div>
    </form>
</div>

<c:import url="footer.jsp"></c:import>
