    <%-- 
    Document   : mainbody
    Created on : 07-12-2016, 14:42:32
    Author     : Michael
--%>

<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<table class="table table-striped">
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <%if(session.getAttribute("userObj")!=null){
                    User user = (User)session.getAttribute("userObj");
                    if(user.getFirstname().equals("admin")){
                        List<ShopItem> cakes = new ArrayList();
                        cakes = (ArrayList) session.getAttribute("cakeList");
                        for (ShopItem cake : cakes) {%>
                            <form action="/CakeWebShop/RouteController?action=edit&id=<%= cake.getItemId()%>" method="POST">       
                                <input type="hidden" name="itemid" value="<%= cake.getItemId()%>">
                                <tr>
                                    <td><a href="#"><img src="<%= cake.getItemPicture()%>"></a></td>
                                    <td><%= cake.getItemName()%></td>
                                    <td><%= cake.getItemPrice()%></td>
                                    <td><input type="submit" name="edit" value="Rediger vare"></td>
                                </tr>
                            </form>
            <%          }
                    }
            }else{
                List<ShopItem> cakes = new ArrayList();
                cakes = (ArrayList) session.getAttribute("cakeList");
                for (ShopItem cake : cakes) {%>
                    <tr>
                        <td><a href="#"><img src="<%= cake.getItemPicture()%>"></a></td>
                        <td><%= cake.getItemName()%></td>
                        <td><%= cake.getItemPrice()%></td>
                    </tr>
            <%
                }
            }
            %>

        </table>

            
            
            
            
            
            
            
            
            

            
            
            
            
            
            
<%-- 
fra git

<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<table class="table table-striped">
    <tr>
        <th>Image</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <%if (session.getAttribute("userObj") != null) {
            User user = (User) session.getAttribute("userObj");
            if (user.getFirstname().equals("admin")) {
                List<ShopItem> cakes = new ArrayList();
                cakes = (ArrayList) session.getAttribute("cakeList");
                for (ShopItem cake : cakes) {
    %>
    <tr>
        <td>
            <form action="/CakeWebShop/RouteController?action=edit&id=<%= cake.getItemId()%>" method="POST">       
                <a href="#"><img src="<%= cake.getItemPicture()%>"></a>
                    <%= cake.getItemName()%>
                    <%= cake.getItemPrice()%>
                <input type="submit" value="Rediger vare">
            </form>
        </td>
    </tr>
    <%          }
        }
    } else {
        List<ShopItem> cakes = new ArrayList();
        cakes = (ArrayList) session.getAttribute("cakeList");
        for (ShopItem cake : cakes) {%>

    <tr>
        <td><a href="#"><img src="<%= cake.getItemPicture()%>"></a></td>
        <td><%= cake.getItemName()%></td>
        <td><%= cake.getItemPrice()%></td>
    </tr>
    <%
            }
        }
    %>

</table>
    
    
    
    
    
    













    Document   : mainbody
    Created on : 07-12-2016, 14:42:32
    Author     : Michael


<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<table class="table table-striped">
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <%if(session.getAttribute("userObj")!=null){
                    User user = (User)session.getAttribute("userObj");
                    if(user.getFirstname().equals("admin")){
                        List<ShopItem> cakes = new ArrayList();
                        cakes = (ArrayList) session.getAttribute("cakeList");
                        for (ShopItem cake : cakes) {
            %>
                            <form action="/RouteController" method="POST"> 
                                <input type="hidden" name="itemid" value="<%= cake.getItemId()%>">
                                <tr>
                                    <td><a href="#"><img src="<%= cake.getItemPicture()%>"></a></td>
                                    <td><%= cake.getItemName()%></td>
                                    <td><%= cake.getItemPrice()%></td>
                                    <td><input type="submit" name="edit" value="Rediger vare"></td>
                                </tr>
                            </form>
            <%          }
                    }
            }else{
                List<ShopItem> cakes = new ArrayList();
                cakes = (ArrayList) session.getAttribute("cakeList");
                for (ShopItem cake : cakes) {
    %>
    <tr>
        <td>
            <form action="/CakeWebShop/RouteController?action=edit&id=<%= cake.getItemId()%>" method="POST">       
                <a href="#"><img src="<%= cake.getItemPicture()%>"></a>
                    <%= cake.getItemName()%>
                    <%= cake.getItemPrice()%>
                <input type="submit" value="Rediger vare">
            </form>
        </td>
    </tr>
    <%          }
        }
    } else {
        List<ShopItem> cakes = new ArrayList();
        cakes = (ArrayList) session.getAttribute("cakeList");
        for (ShopItem cake : cakes) {%>

    <tr>
        <td><a href="#"><img src="<%= cake.getItemPicture()%>"></a></td>
        <td><%= cake.getItemName()%></td>
        <td><%= cake.getItemPrice()%></td>
    </tr>
    <%
            }
        }
    %>

</table>

--%>