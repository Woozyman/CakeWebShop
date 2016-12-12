<%@page import="java.sql.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.ShopItem"%>
<c:import url="header.jsp"></c:import>
<%
    ShopItem item = (ShopItem) request.getAttribute("shopItem");
    if (item != null) {

%>
<form action="/CakeWebShop/ShopItemController?action=updateitem&id=<%= item.getItemId()%>" method="POST">
    <input type="text" name="itemName" value="<%= item.getItemName()%>"/>
    <input type="text" name="itemDescription" value="<%= item.getItemDescription()%>"/>
    <input type="text" name="itemPicture" value="<%= item.getItemPicture()%>"/>
    <input type="text" name="itemPrice" value="<%= item.getItemPrice()%>"/>
    <input type="date" name="discontinuedDate" value="<% try {%>
           <%= item.getDiscontinuedDate() %><%
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
           %>"/>
    <input type="submit" name="updateItem" value="Update"/>
</form>
<%
} else {
    out.print("<p>ingen vare er valgt så der oprettes en vare</p>");%>

<form action="/CakeWebShop/ShopItemController?action=create" method="POST">
    <input type="text" name="itemName" placeholder="Kagenavn"/>
    <input type="text" name="itemDescription" placeholder="Beskrivelse"/>
    <input type="text" name="itemPicture" placeholder="Billede sti"/>
    <input type="text" name="itemPrice" placeholder="Pris"/>
    <input type="submit" name="create" value="Create"/>
</form>

<%
    }
%>
<c:import url="footer.jsp"></c:import>