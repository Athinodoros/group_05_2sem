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
        <style>
    table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
    th, td {
    padding: 5px;
    text-align: left;
}
</style>
                
        
    </head>
    <body>
        <h1>Project overview</h1>
        <div class="scrollit">
        <table class="overview">            
            <c:forEach var="Project" items="${projects}">
            <tr>
                <th>TITLE</th>
                
                <th>COMPANY NAME</th>
                
                <th>PROJECT BUDGET</th>
                
                <th>START DATE</th>
                
                <th>END DATE</th>
                
            </tr>
            <tr>
                <td>${Project.getTitle()}</td>
                <td>${Project.getPartner().getCompanyName()}</td>
                <td>${Project.getProjectBudget()}</td>
                <td>${Project.getSdate()}</td>
                <td>${Project.getFdate()}</td>                
            </tr>
            
            
            </c:forEach>
        </table>
            </div>
    </body>
</html>
