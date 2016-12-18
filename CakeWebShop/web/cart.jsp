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
            <th>Price</th>
            <th>Number</th>
            <th>Update Number</th>
            <th>Remove</th>
        </tr>

      
      
    <% /* Her skal logikken hente linierne fra orders hvor "orderInShoppingCart" == 1 */
        List<ShopItem> items = (List<ShopItem>) session.getAttribute("shopItems");
        OrderLineMapper orm = new OrderLineMapper();
        int orderId = (int) session.getAttribute("orderId");
        
        for(ShopItem item : items) { %>
         
            <tr>  
                    <td><a href="#"><img width="200" src="${pageContext.servletContext.contextPath}/<%=item.getItemPicture()%>"></a></td>
                    <td><%=item.getItemName()%></td>
                    <td><%=item.getItemPrice()%></td>
                    <td><input type="number" min="1" value="<%= orm.getItemCount(item.getItemId(), orderId) %>"></input></td>
                    <td><a href="${pageContext.servletContext.contextPath}/CartController?action=update"> Update </a></td>
                    <td><a href="${pageContext.servletContext.contextPath}/CartController?action=remove"> Remove </a></td>
            </tr>
           
       <% } %>
       

  
</table>
<a href="${pageContext.servletContext.contextPath}/checkOut.jsp" class="btn btn-success" role="button">Pay</a>
<c:import url="footer.jsp"></c:import>
