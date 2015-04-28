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
                <td>TITLE</td>
                <br>
                <td>COMPANY NAME</td>
                <br>
                <td>PROJECT BUDGET</td>
                <br>
                <td>START DATE</td>
                <br>
                <td>END DATE</td>
                <br>
            </tr>
            <tr>
                <td>${Project.getTitle()}</td><br>
                <td>${Project.getPartner().getCompanyName()}</td><br>
                <td>${Project.getProjectBudget()}</td><br>
                <td>${Project.getSdate()}</td><br>
                <td>${Project.getFdate()}</td><br>                
            </tr>
            
            
            </c:forEach>
            
            
            
            
        </table>
        
        
        
        
    </body>
</html>
