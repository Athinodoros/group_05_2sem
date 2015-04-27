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
        <h1>Hello World!</h1>
        <table>
            
            <c:forEach var="Project" items="${list}">
            <tr>
                <td>Title:${Project.getTitle()}</td>
                <td>Company Name:${Project.getCompanyName}</td>
                <td>Project Budget:${Project.getProjectBudget}</td>
                
            </tr>
            
            
            </c:forEach>
            
            
            
            
        </table>
        
        
        
        
    </body>
</html>
