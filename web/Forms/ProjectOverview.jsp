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
        
        <table>            
            <c:forEach var="Project" items="${projects}">
            <tr>
                <title>Project overview</title>
                <th>TITLE</th>
                <br><br>
                <th>COMPANY NAME</th>
                <br><br>
                <th>PROJECT BUDGET</th>
                <br><br>
                <th>START DATE</th>
                <br><br>
                <th>END DATE</th>
                <br><br>
            </tr>
            <tr>
                <td>${Project.getTitle()}</td><br><br>
                <td>${Project.getPartner().getCompanyName()}</td><br><br>
                <td>${Project.getProjectBudget()}</td><br><br>
                <td>${Project.getSdate()}</td><br><br>
                <td>${Project.getFdate()}</td><br><br>                
            </tr>
            
            
            </c:forEach>
            
            
            
            
        </table>
        
        
        
        
    </body>
</html>
