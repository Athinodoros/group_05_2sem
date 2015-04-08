<%-- 
    Document   : menus
    Created on : Apr 8, 2015, 12:21:16 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <%@page  import="layer2.domain.*"  %>
    <%@page  import="layer2.domain.bean.User"  %>
    <% User user = (User)session.getAttribute("user"); %>
    <c:if test="${param.user.role == NamingConv.ADMIN}">
        <ul class="nav nav-sidebar">
            <li>Project List</li>
            <li>Create Project</li>
            <li class="active"><a href="#">Overview of projects <span class="sr-only">(current)</span></a></li>
            <li>some Random thing</li>
        </ul>
    </c:if>
    <c:if test="${param.user.role == NamingConv.RESELLER}">
        <ul class="nav nav-sidebar">
            <li>Project List</li>
            <li>Create Project</li>
            <li class="active"><a href="#">Overview of projects <span class="sr-only">(current)</span></a></li>
            <li>some Random thing</li>
        </ul>
    </c:if>
    
    
</html>
