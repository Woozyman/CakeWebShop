<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>

<div class="container">
    <h1>Item <%= request.getParameter("id") %>- (itemName goes here)</h1><br>
    <div class="row">
        <div class="col-sm-4">(Image goes here)</div>
        <div class="col-sm-8">Name:<br>(kage)<br><br>Price:<br>(pris)<br><br>Description:<br>(beskrivelse)</div>
    </div>
    <form class="form-inline">
        <div class="form-group">
            <label for="focusedInput">Antal</label>
            <input class="form-control" id="focusedInput" type="number" value="1" min="1">
            <button type="submit" class="btn btn-default">Bestil</button>
        </div>
    </form>
</div>

<c:import url="footer.jsp"></c:import>