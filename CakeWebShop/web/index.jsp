<%@page import="java.util.*" %>
<%@page import="java.util.List, models.ShopItem" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainpage>
    <jsp:attribute name="header">
        <jsp:include page="topUserBar.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="mainbody">
        <jsp:include page="mainbody"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="footer.jsp"/>
    </jsp:attribute>
  
    <jsp:body>
       
    </jsp:body>
</t:mainpage>
