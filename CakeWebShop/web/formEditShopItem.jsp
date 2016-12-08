<%-- 
    Document   : formEditShopItem
    Created on : 07-12-2016, 17:11:58
    Author     : Michael
--%>

<%@page import="models.ShopItem"%>

<%
    ShopItem item = (ShopItem) request.getAttribute("shopItem");
    if (item != null) {
        
%>
<form action="/CakeWebShop?action=edit" method="POST">
    <input type="text" name="itemName" value="<%= item.getItemName()%>"/>
    <input type="text" name="itemDescription" value="<%= item.getItemDescription()%>"/>
    <input type="text" name="itemPicture" value="<%= item.getItemPicture()%>"/>
    <input type="text" name="itemPrice" value="<%= item.getItemPrice()%>"/>
    <input type="date" name="discontinuedDate" value="<%= item.getDiscontinuedDate()%>"/>
    <input type="submit" name="edit" value="Update"/>
</form>
<%
    } else {
        out.print("<p>ingen vare er valgt så der oprettes en vare</p>");%>

<form action="/CakeWebShop?action=create" method="POST">
    <input type="text" name="itemName" placeholder="Kagenavn"/>
    <input type="text" name="itemDescription" placeholder="Beskrivelse"/>
    <input type="text" name="itemPicture" placeholder="Billede sti"/>
    <input type="text" name="itemPrice" placeholder="Pris"/>
    <input type="submit" name="create" value="Create"/>
</form>

<%
    }
%>