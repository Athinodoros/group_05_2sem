<%-- 
    Document   : DashBoardPicker
    Created on : Apr 11, 2015, 8:16:06 PM
    Author     : Athinodoros
--%>

<%@page import="layer2.domain.interfaces.NamingConv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <body>
       <!-- <%@ page import="" %>-->
        <% String pagepoint = (String)request.getAttribute("mainArea") ; 
    if (pagepoint == NamingConv.DASHBOARD) { %>
    <jsp:include page="dashBoard.jsp"/>
    <%} else if (pagepoint == NamingConv.PROJECTLIST ) { %>
    <jsp:include page="dashBoard.jsp"/>
       <% } %>
    %>
        %>
        
    </body>
</html>
