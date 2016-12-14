<%@page import="models.OrderLine"%>
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
        List<ShopItem> items = cart.getShopItems();
        List<OrderLine> orderLines = cart.getOrderLines();
    %>  

    <form action="${pageContext.servletContext.contextPath}/?action=<%= request.getAttribute("name")%>&id=" method="POST">       
        <input type="hidden" name="itemid" value="<%//= cake.getItemId()%>">

       <% for(ShopItem item : items) { %>
         
            <tr>
              
                    <td><a href="#"><img src=<% item.getItemPicture(); %>></a></td>
                    <td><% item.getItemName(); %></td>
                    <td><% item.getItemPrice();%></td>
                    <td><input type="number" min="1" value=""></input></td>
                    <td><input type="submit" name="edit" value="Update Cart"></td>
                    <td><input type="submit" name="remove" value="remove"></td>
                   

            </tr>
           
       <% } %>
       

    </form>
</table>

<c:import url="footer.jsp"></c:import>