<%@page import="java.util.ArrayList"%>
<%@page import="models.ShopItem"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>

<%if (session.getAttribute("userObj") != null) {
    User user = (User) session.getAttribute("userObj");%>
<div class="container">
    <h1>CheckOut/payment</h1><br>
    <p>Ammount: (Beløbet som skal trækkes kommer her)</p>
    <p>heraf moms (25%): (beløb ovenfor /0.2 )</p>
    <form class="form-horizontal" action="#">
        <fieldset>
            <legend>Payment <img width="300" src="http://rk-retail.dk/wp-content/uploads/2015/12/kreditkort_logo_danmark.jpg"/></legend>
            <div class="form-group">
                <label class="col-sm-3 control-label">Name on Card</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="name" placeholder="<% out.println(user.getFirstname()+" "+user.getLastname()); %>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Card Number</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="cardnumber" placeholder="4571 1234 5678 9012">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" >Expiration Date</label>
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-xs-3">
                            <select class="form-control col-sm-2" name="expireMonth">
                                <option value="01">Jan (01)</option>
                                <option value="02">Feb (02)</option>
                                <option value="03">Mar (03)</option>
                                <option value="04">Apr (04)</option>
                                <option value="05">Maj (05)</option>
                                <option value="06">Juni (06)</option>
                                <option value="07">Juli (07)</option>
                                <option value="08">Aug (08)</option>
                                <option value="09">Sep (09)</option>
                                <option value="10">Oct (10)</option>
                                <option value="11">Nov (11)</option>
                                <option value="12">Dec (12)</option>
                            </select>
                        </div>
                        <div class="col-xs-3">
                            <select class="form-control" name="expireYear">
                                <option value="16">2016</option>
                                <option value="17">2017</option>
                                <option value="18">2018</option>
                                <option value="19">2019</option>
                                <option value="20">2020</option>
                                <option value="21">2021</option>
                                <option value="22">2022</option>
                                <option value="23">2023</option>
                                <option value="24">2024</option>
                                <option value="25">2025</option>
                                <option value="26">2026</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Card CVV</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="cvv" placeholder="123">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-success">Pay Now</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<% } else {out.print("Du er ikke logget ind og kan derfor ikke chekke ud");}%>
<c:import url="footer.jsp"></c:import>