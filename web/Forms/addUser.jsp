<%-- 
    Document   : addUser
    Created on : Apr 10, 2015, 12:15:40 PM
    Author     : Athinodoros

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="bootstrap.min.css" rel="stylesheet">


<div class="form-user" style="line-height: 2em;">
            <form class="lead" action="beanHandlers/userHandler.jsp" ></br></br>
                <a>First Name : </a></br><input type="text" name="firstName" value="" required=""/></br>
                <a>Last Name : </a></br><input type="text" name="lastName" value="" required=""/></br>
                <a>User Name : </a></br><input type="text" name="userName" value="" required=""/></br>
                <a>Password : </a></br><input type="text" name="password" value="" required=""/></br>
                <a>e-mail : </a></br><input type="text" name="eMail" value="" required=""/></br>
                <a>User Type : </a></br> <select name="role" required="">
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
                </select>
                <a>company : </a></br> <select name="companyName" required="" >
                    <option>DK</option>
                    <option>SE</option>
                    <option>FI</option>
                    <option>NO</option>
                    <option>IS</option>
                </select>
                </br></br>
                <input type="submit" value="Create new User" />
            </form>
        </div>
    