<%-- 
    Document   : formEditShopItem
    Created on : 07-12-2016, 17:11:58
    Author     : Michael
--%>

<%@page import="models.ShopItem"%>
<%if (request.getAttribute("shopItem") != null) {
        ShopItem item = (ShopItem) request.getAttribute("shopItem");
%>
<form action="#" method="POST">
    <input type="text" name="itemName" value="<%= item.getItemName()%>"/>
    <input type="text" name="itemDescription" value="<%= item.getItemDescription()%>"/>
    <input type="text" name="itemPicture" value="<%= item.getItemPicture()%>"/>
    <input type="text" name="itemPrice" value="<%= item.getItemPrice()%>"/>
    <input type="date" name="discontinuedDate" value="<%= item.getDiscontinuedDate()%>"/>
    <input type="submit" value="Opdater"/>
</form>
<%
    } else {
        out.print("<p>ingen vare er valgt så der oprettes en vare</p>");%>

<form action="#" method="POST">
    <input type="text" name="itemName" placeholder="Kagenavn"/>
    <input type="text" name="itemDescription" placeholder="Beskrivelse"/>
    <input type="text" name="itemPicture" placeholder="Billede sti"/>
    <input type="text" name="itemPrice" placeholder="Pris"/>
    <input type="submit" value="Opret"/>
</form>

<%
    }
%>