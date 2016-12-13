<%@page import="models.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>

    <table class="table table-striped">
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Price</th>
            <th>Number</th>
            <th>Update Number</th>
            <th>Remove</th>
        </tr>
    <% /* Her skal logikken hente linierne fra orders hvor "orderInShoppingCart" == 1 */
       Cart cart = (Cart) session.getAttribute("cart");
    %>
    <form action="${pageContext.servletContext.contextPath}/?action=<%= request.getAttribute("name") %>&id=" method="POST">       
        <input type="hidden" name="itemid" value="<%//= cake.getItemId()%>">
        <tr>
            <td><a href="#"><img src="(image goes here)<%//= cake.getItemPicture()%>"></a></td>
            <td>(kage)<%//= cake.getItemName()%></td>
            <td>(pris)<%//= cake.getItemPrice()%></td>
            <td><input type="number" min="1" value="1<%//= cake.getItemNumber()%>"></input></td>
            <td><input type="submit" name="edit" value="Update Cart"></td>
            <td><input type="submit" name="remove" value="remove"></td>
        </tr>
    </form>
</table>

<c:import url="footer.jsp"></c:import>