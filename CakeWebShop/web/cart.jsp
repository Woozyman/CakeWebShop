<%@page import="dataaccess.CartHelper"%>
<%@page import="models.OrderLineMapper"%>
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
            <th>Number</th>
            <th>Price</th>
            <th>Total</th>
            <th>Update Number</th>
            <th>Remove</th>
        </tr>



    <% /* Her skal logikken hente linierne fra orders hvor "orderInShoppingCart" == 1 */
        User user = (User) session.getAttribute("userObj");
        CartHelper carthelper = new CartHelper();
        List<ShopItem> items = (List<ShopItem>) session.getAttribute("shopItems");
        OrderLineMapper orm = new OrderLineMapper();
        int orderId = (int) session.getAttribute("orderId");
        double total = 0;
        for (ShopItem item : items) {
            total = total + (orm.getItemCount(item.getItemId(), orderId) * item.getItemPrice());
    %>
    
    <form  method="POST">  
        <tr>  
            <td><a href="#"><img width="200" src="${pageContext.servletContext.contextPath}/<%=item.getItemPicture()%>"></a></td>
            <td><%=item.getItemName()%></td>
            <% if(user != null) {%>
            <td><input type="number" name="numOfItems" min="1" value="<%= orm.getItemCount(item.getItemId(), orderId)%>"></input></td>
            <% }else{ %>
            <td><input type="number" name="numOfItems" min="1" value="<%= carthelper.getItemCountFromSessionCart(item.getItemId(), session) %>"></input></td>
            <%}%>
            <td><%=item.getItemPrice()%></td>
            <td><%=orm.getItemCount(item.getItemId(), orderId) * item.getItemPrice()%></td>
            <td><button type="submit" formaction="${pageContext.servletContext.contextPath}/CartController?action=update&id=<%= item.getItemId() %>">Update</button></td>
            <td><button type="submit" formaction="${pageContext.servletContext.contextPath}/CartController?action=remove&id=<%= item.getItemId() %>">Remove</button></td>
        </tr>
    </form>
    <% }%>
    <td></td>
    <td></td>
    <td></td>
    <td>Total all:</td>
    <td><%=total%></td>
    <td><a href="${pageContext.servletContext.contextPath}/CartController?action=checkout" class="btn btn-success" role="button">Pay Order</a></td>
    <td></td>


</table>

<c:import url="footer.jsp"></c:import>
