<%-- 
    Document   : addUser
    Created on : Apr 10, 2015, 12:15:40 PM
    Author     : Athinodoros

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="bootstrap.min.css" rel="stylesheet">
<%@page  import="layer2.domain.bean.UserInfo" %>
<%@page  import="layer2.domain.bean.UserAuthentication" %>
<%@page  import="layer2.domain.bean.Partner" %>
<%@page  import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% ArrayList<Partner> list = (ArrayList<Partner>)request.getAttribute("partnerList"); %>
<% session.setAttribute("list", list); %>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
<% UserAuthentication userath = (UserAuthentication) request.getAttribute("userAth"); %>
<div class="form-user" style="line-height: 2em;" >
     <% if (userInfo == null) { %>
     
     <form class="lead" action="beanHandlers/userHandler.jsp"  method="POST"> </br></br>
                <a>First Name : </a></br><input type="text" name="firstname" value="" required=""/></br>
                <a>Last Name : </a></br><input type="text" name="lastname" value="" required=""/></br>
                <a>User Name : </a></br><input type="text" name="uname" value="" required=""/></br>
                <a>Password : </a></br><input type="text" name="password" value="" required=""/></br>
                <a>e-mail : </a></br><input type="text" name="email" value="" required=""/></br>
                <a>User Type : </a></br> <select name="urole" required="">
                    <option>Partner</option>
                    <option>DELL User</option>
                    <option>*region manager</option>
                </select></br>
                <a>Country : </a></br> <select name="country" required="" >
                    <option>DK</option>
                    <option>SE</option>
                    <option>FI</option>
                    <option>NO</option>
                    <option>IS</option>
                </select></br>
                <a>company : </a></br> <select name="companyName" required="" >
                    <% for (Partner singlePartner : list) { %>
                    <option> <%= singlePartner.getCompanyName() %></option>
                       <% } %>
                </select>
                </br></br>
                <input type="submit" value="Create new User" />
            </form>
            <% }else { %>
            <form class="lead" action="beanHandlers/userHandler.jsp" method="post" > </br></br>
                <a>First Name : </a></br><input type="text" name="firstname" value="<%= userInfo.getFirstname() %>" required=""/></br>
                <a>Last Name : </a></br><input type="text" name="lastname" value="<%= userInfo.getLastname()%>" required=""/></br>
                <a>User Name : </a></br><input type="text" name="uname" value="<%= userath.getUname()%>" required=""/></br>
                <a>Password : </a></br><input type="text" name="password" value="" required=""/></br>
                <a>e-mail : </a></br><input type="text" name="email" value="<%= userath.getPassword() %>" required=""/></br>
                <a>User Type : </a></br> <select name="urole" required="">
                    <option>Partner</option>
                    <option>DELL User</option>
                    <option>*region manager</option>
                </select></br>
                <a>Country : </a></br> <select name="country" required="" >
                    <option selected=""></option>
                    <option>DK</option>
                    <option>SE</option>
                    <option>FI</option>
                    <option>NO</option>
                    <option>IS</option>
                </select></br>
                <a>company : </a></br> <select name="companyName" required="" >
                    <% for (Partner singlePartner : list) { %>
                    <option><%= singlePartner.getCompanyName() %></option>
                       <% } %>
                </select>
                </br></br>
                <input type="submit" value="Create new User" />
            </form>
                <% } %>
        </div>
    