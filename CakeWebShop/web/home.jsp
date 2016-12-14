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
    <form action="${pageContext.servletContext.contextPath}/RouteController?action=edit&id=<%= cake.getItemId()%>" method="POST">       
        <input type="hidden" name="itemid" value="<%= cake.getItemId()%>">
        <tr>
            <td><a href="${pageContext.servletContext.contextPath}/RouteController?action=details&id=<%=cake.getItemId()%>"><img src="${pageContext.servletContext.contextPath}<%= cake.getItemPicture()%>"></a></td>
            <td><%= cake.getItemName()%></td>
            <td><%= cake.getItemPrice()%></td>
            <td><a href="${pageContext.servletContext.contextPath}/RouteController?action=details&id=<%=cake.getItemId()%>" class="btn btn-default" role="button">Details</a>
            <td><input type="submit" name="edit" value="Rediger vare"></td>
        </tr>
    </form>
    <%          }
        } else {
//almindelig User goes here
        List<ShopItem> cakes = new ArrayList();
        cakes = (ArrayList) session.getAttribute("cakeList");
        for (ShopItem cake : cakes) {%>
    </tr>
    <tr>
        <td><a href="${pageContext.servletContext.contextPath}/RouteController?action=details&id=<%=cake.getItemId()%>"><img src="${pageContext.servletContext.contextPath}<%= cake.getItemPicture()%>"></a></td>
        <td><%= cake.getItemName()%></td>
        <td><%= cake.getItemPrice()%></td>
        <td><a href="${pageContext.servletContext.contextPath}/RouteController?action=details&id=<%=cake.getItemId()%>" class="btn btn-default" role="button">Details</a>
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
        <td><a href="${pageContext.servletContext.contextPath}/RouteController?action=details&id=<%=cake.getItemId()%>"><img src="<%= cake.getItemPicture()%>"></a></td>
        <td><%= cake.getItemName()%></td>
        <td><%= cake.getItemPrice()%></td>
        <td><a href="${pageContext.servletContext.contextPath}/RouteController?action=details&id=<%=cake.getItemId()%>" class="btn btn-default" role="button">Details</a>
    </tr>
    <%      }
        }%>
</table>
<c:import url="footer.jsp"></c:import>