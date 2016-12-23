<%@page import="models.ShopItemMapper"%>
<%@page import="models.OrderLineMapper"%>
<%@page import="models.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if (session.getAttribute("firstVisit") == null) { %>
<jsp:forward page="/HomeController?action=home"/>
<%} %>

<c:import url="header.jsp"></c:import>
    <table class="table table-striped">
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Price</th>
            <th>Details</th>
            <%if (session.getAttribute("userObj") != null) {
                    User user = (User) session.getAttribute("userObj");
                    if (user.getFirstname().equals("admin")) {
                        //Admin goes here
                        List<ShopItem> cakes = new ArrayList();
                        cakes = (ArrayList) session.getAttribute("cakeList");
                        out.print("<th>Edit</th>");
                        for (ShopItem cake : cakes) {
            %>
    </tr>
    <form action="${pageContext.servletContext.contextPath}/ShopItemController?action=edit&id=<%= cake.getItemId()%>" method="POST">       
        <input type="hidden" name="itemid" value="<%= cake.getItemId()%>">
        <tr>
            <td><a href="${pageContext.servletContext.contextPath}/ShopItemController?action=details&id=<%=cake.getItemId()%>"><img width="150" src="${pageContext.servletContext.contextPath}/<%= cake.getItemPicture()%>"></a></td>
            <td><%= cake.getItemName()%></td>
            <td><%= cake.getItemPrice()%></td>
            <td><a href="${pageContext.servletContext.contextPath}/ShopItemController?action=details&id=<%=cake.getItemId()%>" class="btn btn-default" role="button">Details</a>
            <td><input type="submit" name="edit" value="Rediger vare"></td>
        </tr>
    </form>
    <%          }
    } else if (user.getFirstname().equals("baker")) {
//Baker goes here
        OrderLineMapper lineMapper = new OrderLineMapper();
        List<OrderLine> orderLines = lineMapper.getInCompleteOrderLines();
        ShopItemMapper sim = new ShopItemMapper();
//        List<ShopItem> shopItems = sim.mapShopItemsToOrderLines(orderLines);
        for (OrderLine lineItem : orderLines) { %>    

    <form action="${pageContext.servletContext.contextPath}/ShopItemController?action=markasbaked&orderlineid=<%= lineItem.getOrderLineId() %>&orderid=<%= lineItem.getOrderId() %>" method="post">
        <tr>
            <td><a href="#"><img width="200" src="${pageContext.servletContext.contextPath}/<%= sim.getItem(lineItem.getShopItemId()).getItemPicture() %>"></a></td>
            <td><%= sim.getItem(lineItem.getShopItemId()).getItemName() %></td>
            <td><%= sim.getItem(lineItem.getShopItemId()).getItemPrice() * lineItem.getNumberOfItems() %></td>
            <td><%= sim.getItem(lineItem.getShopItemId()).getItemDescription() %></td>
            <td><input type="submit" value="Mark as Baked"></td>
        </tr>

    </form>
    <%} %>
   <% } else {
        //almindelig User goes here
        List<ShopItem> cakes = new ArrayList();
        cakes = (ArrayList) session.getAttribute("cakeList");
        for (ShopItem cake : cakes) {%>

    <tr>      
        <td><a href="${pageContext.servletContext.contextPath}/ShopItemController?action=details&id=<%=cake.getItemId()%>"><img width="150" src="${pageContext.servletContext.contextPath}/<%= cake.getItemPicture()%>"></a></td>
        <td><%= cake.getItemName()%></td>
        <td><%= cake.getItemPrice()%></td>
        <td><a href="${pageContext.servletContext.contextPath}/ShopItemController?action=details&id=<%=cake.getItemId()%>" class="btn btn-default" role="button">Details</a>
    </tr>
    <%      }
        }
    } else {
//Guest goes here
        List<ShopItem> cakes = new ArrayList();
        cakes = (ArrayList) session.getAttribute("cakeList");
        for (ShopItem cake : cakes) {%>
</tr>
<tr>
    <td><a href="${pageContext.servletContext.contextPath}/ShopItemController?action=details&id=<%=cake.getItemId()%>"><img width="150" src="${pageContext.servletContext.contextPath}/<%= cake.getItemPicture()%>"></a></td>
    <td><%= cake.getItemName()%></td>
    <td><%= cake.getItemPrice()%></td>
    <td><a href="${pageContext.servletContext.contextPath}/ShopItemController?action=details&id=<%=cake.getItemId()%>" class="btn btn-default" role="button">Details</a>
</tr>
<%      }
    }%>
</table><br><br>
<c:import url="footer.jsp"></c:import>
