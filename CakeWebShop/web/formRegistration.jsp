<%-- 
    Document   : formRegistration
    Created on : 10-12-2016, 16:13:48
    Author     : Jens
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Styles/styles.css" rel="stylesheet" type="text/css"/>
        <title>Registration Form JSP</title>
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
        
        <form action="/AccountController?action=register" method="POST">
            <fieldset class="box">
                <legend>Email and password</legend>
                <div>
                    <p>
                        <label class="textlabel" for="emailInput">Email</label>
                        <input id="emailInput" placeholder="Enter valid email address" title="email" name="Email"   type="email"
                               required="required" class="textinput" maxlength="50" autofocus="autofocus"/>                                                      
                    </p>   
                    <p>
                        <label class="textlabel" for="passwordInput1">Password</label>
                        <input id="passwordInput" placeholder="Enter password, min 8 characters" title="password" name="Password1"
                               type="password" required="required" class="textinput" maxlength="40" />
                    </p>
                      <p>
                        <label class="textlabel" for="passwordInput2">Password</label>
                        <input id="passwordInput" placeholder="Reenter password, make sure it´s the same!" title="reenter password" 
                               name="Password2" type="password" required="required" class="textinput" maxlength="40"/>
                    </p>
                </div>                
            </fieldset>
            <br />
            <fieldset class="box">
                <Legend>Your details</Legend>
                <div>
                        <input type="hidden" name="origin" value="register" />
                    <p>
                        <label class="textlabel" for="firstNameInput">First Name</label>
                        <input id="firstNameInput" placeholder="Firstname" title="First name" name="FirstName" type="text" required="required" autofocus="autofocus" 
                               class="textinput" maxlength="30" />                         
                    </p>
                     <p>
                        <label class="textlabel" for="lastNameInput">Last Name</label>
                        <input id="lastNameInput" placeholder="Lastname" title="Last name" name="LastName" type="text" required="required"  
                               class="textinput" maxlength="30" />                         
                    </p>
                     <p>
                        <label class="textlabel" for="phoneInput">Phone Number</label>
                        <input id="phoneInput" placeholder="Enter a 8 digit phone number" title="Phone number" name="PhoneNumber" type="phone" required="required"  
                               class="textinput" maxlength="8" />                         
                    </p>
                     <p>
                        <label class="textlabel" for="address">Address</label>
                        <input id="addressInput" placeholder="Streetname and number" title="Address" name="Address" type="text" required="required"  
                               class="textinput" maxlength="50" />                         
                    </p>
                     <p>
                        <label class="textlabel" for="zipInput">Zip</label>
                        <input id="zipInput" placeholder="Enter zip code"name="Zip" title="Zip code" type="text" required="required"  
                               class="textinput" maxlength="4" />                         
                    </p>
                </div>
            </fieldset>
            <input type="submit" class="submitbutton" value="Register"  
            
          
        </form>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
