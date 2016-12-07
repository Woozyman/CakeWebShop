<%@page import="java.util.*" %>
<%@page import="java.util.List, models.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mainpage>
    <jsp:attribute name="header">
        <jsp:include page="topUserBar.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="footer.jsp"/>
    </jsp:attribute>
  
    <jsp:body>
        <jsp:include page="mainbody.jsp"/>
    </jsp:body>
</t:mainpage>
