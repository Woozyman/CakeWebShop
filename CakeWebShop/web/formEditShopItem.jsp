<%-- 
    Document   : formEditShopItem
    Created on : 07-12-2016, 17:11:58
    Author     : Michael
--%>

<%if(response.getAttribute("shopItem")!=null){
                Item item = (Item)response.getAttribute("shopItem");
%>
<form action="#" method="POST">
    <input type="text" name="itemName" value="<%= item.getItemName %>"/>
    <input type="text" name="itemDescription" value="<%= item.getItemDescription %>"/>
    <input type="text" name="itemPicture" value="<%= item.getItemPicture %>"/>
    <input type="text" name="itemPrice" value="<%= item.getItemPrice %>"/>
    <input type="date" name="discontinuedDate" value="<%= item.getDiscontinuedDate %>"/>
    <input type="submit" value="Opdater"/>
</form>
<%
    } else {
out.print("<p>ingen vare er valgt</p>");
}
%>