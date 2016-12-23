<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>
    
<p></p>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/AccountController?action=register" method="POST">
    <fieldset>
        <legend>Email and password</legend>
        <div class="form-group">
            <label class="control-label col-sm-2" for="emailInput">Email</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" id="emailInput" placeholder="Enter a valid email address" title="email" name="Email"
                       required="required" maxlength="50" autofocus="autofocus"/>
                 <span class="error">${errors.Email}</span>
            </div>
        </div>   
        <div class="form-group">
            <label class="control-label col-sm-2" for="passwordInput1">Password</label>
            <div class="col-sm-6">

                <input type="password" class="form-control" id="passwordInput" placeholder="Enter password, min 8 characters" title="password" name="Password"
                       type="password" required="required" maxlength="40" />
                 <span class="error">${errors.Password}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="passwordInput2">Repeat Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="passwordInput" placeholder="Reenter password, make sure it's the same!" title="reenter password" 
                       name="Password2" required="required" maxlength="40"/>
                 <span class="error">${errors.Password2}</span>
            </div>
        </div>

    </fieldset>
    <br />
    <fieldset>
        <Legend>Your details</Legend>
        <div class="form-group">
            <input type="hidden" name="origin" value="register" />

            <label class="control-label col-sm-2" for="firstNameInput">First Name</label>
            <div class="col-sm-6">
                <input id="firstNameInput" placeholder="Firstname" title="First name" name="FirstName" type="text" required="required" autofocus="autofocus" 
                       class="form-control" maxlength="30" />
                 <span class="error">${errors.FirstName}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="lastNameInput">Last Name</label>
            <div class="col-sm-6">
                <input id="lastNameInput" placeholder="Lastname" title="Last name" name="LastName" type="text" required="required"  
                       class="form-control" maxlength="30" />
                 <span class="error">${errors.LastName}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="phoneInput">Phone Number</label>
            <div class="col-sm-6">
                <input id="phoneInput" placeholder="Enter a 8 digit phone number" title="Phone number" name="PhoneNumber" type="text" required="required"  
                       class="form-control" maxlength="8" minlength="8" />
                <span class="error">${errors.PhoneNumber}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="address">Address</label>
            <div class="col-sm-6">
                <input id="addressInput" placeholder="Streetname and number" title="Address" name="Address" type="text" required="required"  
                       class="form-control" maxlength="50" />
                 <span class="error">${errors.Address}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="zipInput">Zip</label>
            <div class="col-sm-6">
                <input id="zipInput" placeholder="Enter zip code"name="Zip" title="Zip code" type="text" required="required"  
                       class="form-control" maxlength="4" minlength="4"/> 
                 <span class="error">${errors.Zip}</span>
            </div>
        </div>
        </div>
        <br>
        <div class="col-sm-2"></div>
        <div class="col-sm-2">
            <input type="submit" class="btn btn-success" value="Register" />
        </div>
    </fieldset>
</form>
<c:import url="footer.jsp"></c:import>
