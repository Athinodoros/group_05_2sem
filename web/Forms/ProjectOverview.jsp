<%-- 
    Document   : ProjectOverview
    Created on : Apr 27, 2015, 11:10:29 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Project overview</h1>
        <table>
            
            <c:forEach var="Project" items="${projects}">
            <tr>
                <td>Title</td>
                <td>Company Name</td>
                <td>Project Budget</td>
                <td>Start Date</td>
            </tr>
            <tr>
                <td>${Project.getTitle()}</td>
                <td>${Project.getPartner().getCompanyName()}</td>
                <td>${Project.getProjectBudget()}</td>
                <td>${Project.getSdate()}</td>
            </tr>
            
            
            </c:forEach>
            
            
            
            
        </table>
        
        
        
        
    </body>
</html>
