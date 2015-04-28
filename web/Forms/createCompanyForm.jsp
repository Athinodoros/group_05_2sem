<%-- 
    Document   : createCompanyForm
    Created on : Apr 13, 2015, 10:32:39 AM
    Author     : Athinodoros
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
--%>
    <%@page import="layer2.domain.interfaces.NamingConv" %>
    <link href="bootstrap.min.css" rel="stylesheet">

    
    <div>
        <h1>Register a company</h1>
        <br>
        <form class="lead" action="UIServlet" method="POST">
            Company Name:<input type="text" name="companyName" value="" required="">
            <br>
            Company ID: <input type="number" name="companyID" value="" required="">
            <br>
            <input name="command" value=<%=NamingConv.CREATECOMPANY%> hidden/>
            <input type="submit" value="Save" />
        </form> 
        
            
   </div>