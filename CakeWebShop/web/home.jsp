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
    <%if (session.getAttribute("userObj") != null) {
            User user = (User) session.getAttribute("userObj");
            if (user.getFirstname().equals("admin")) {
                List<ShopItem> cakes = new ArrayList();
                cakes = (ArrayList) session.getAttribute("cakeList");
                out.print("<th>Edit</th>");
                for (ShopItem cake : cakes) {
    %>
        </tr>
    <form action="/CakeWebShop/RouteController?action=edit&id=<%= cake.getItemId()%>" method="POST">       
        <input type="hidden" name="itemid" value="<%= cake.getItemId()%>">
        <tr>
            <td><a href="/cakes.jsp?id=<%=cake.getItemId()%>"><img src="<%= cake.getItemPicture()%>"></a></td>
            <td><%= cake.getItemName()%></td>
            <td><%= cake.getItemPrice()%></td>
            <td><input type="submit" name="edit" value="Rediger vare"></td>
        </tr>
    </form>
    <%          }
        }
    } else {
        List<ShopItem> cakes = new ArrayList();
        cakes = (ArrayList) session.getAttribute("cakeList");
        for (ShopItem cake : cakes) {%>
    </tr>
    <tr>
        <td><a href="/cakes.jsp?id=<%=cake.getItemId() %>"><img src="<%= cake.getItemPicture()%>"></a></td>
        <td><%= cake.getItemName()%></td>
        <td><%= cake.getItemPrice()%></td>
    </tr>
    <%
            }
        }
    %>

</table>

<c:import url="footer.jsp"></c:import>