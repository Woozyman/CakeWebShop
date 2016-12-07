<%-- 
    Document   : mainbody
    Created on : 07-12-2016, 14:42:32
    Author     : Michael
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<table class="table table-striped">
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <%List<ShopItem> cakes = (ArrayList) session.getAttribute("cakeList");
                for (ShopItem cake : cakes) {
            %>
                <tr>
                    <td><a href="#"><img src="<%= cake.getItemPicture()%>"></a></td>
                    <td><%= cake.getItemName()%></td>
                    <td><%= cake.getItemPrice()%></td>
                </tr>
            <%
                    }
            %>

        </table>
