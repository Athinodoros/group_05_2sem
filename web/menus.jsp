<%-- 
    Document   : menus
    Created on : Apr 8, 2015, 12:21:16 PM
    Author     : Athinodoros
--%>

<%@page import="layer2.domain.interfaces.NamingConv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <%@page  import="layer2.domain.*"  %>
    <%@page  import="layer2.domain.bean.User"  %>
    <% User user = (User) session.getAttribute("user"); %>
    <% if (user.getRole().equals(NamingConv.ADMIN) && true)  { %>
    <ul class="nav nav-sidebar">
        <li class="active"><a href="#">Overview of all projects <span class="sr-only">(current)</span></a></li>
        <li><a href="#">View company budget</a></li>
        <li><a href="#">View pending projects</a></li>
        <li><a href="#">View approved projects</a></li>
    </ul>
    <% } else if (user.getRole().equals(NamingConv.RESELLER)) { %>
    <ul class="nav nav-sidebar ">
        <li class="active"><a href="#">Overview of projects <span class="sr-only">(current)</span></a></li>
        <li><a href="#">View company budget</a></li>
        <li><a href="#">Launch new project</a></li>
    </ul>
    <% }%>


</html>
