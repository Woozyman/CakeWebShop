<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>

<form class="form-horizontal" action="${pageContext.servletContext.contextPath}/AccountController?action=login" method="POST">
         <div class="form-group">
            <label class="control-label col-sm-2" for="emailInput">Email</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" id="emailInput" placeholder="Enter a valid email address" title="email" name="Email"
                       required="required" maxlength="50" autofocus="autofocus"/>
            </div>
            </div>   
        <div class="form-group">
            <label class="control-label col-sm-2" for="passwordInput1">Password</label>
            <div class="col-sm-6">

                <input type="password" class="form-control" id="passwordInput" placeholder="Enter password, min 8 characters" title="password" name="Password"
                       type="password" required="required" maxlength="40" />
            </div>
        </div>        
        </form>
<c:import url="footer.jsp"></c:import>    

