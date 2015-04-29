<%-- 
    Document   : ProjectOverview
    Created on : Apr 27, 2015, 11:10:29 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="layer2.domain.interfaces.NamingConv"%>
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
            <tr>
                <th>Title</th>
                <th>Company Name</th>
                <th>Project Budget</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Stage</th>
                <th> View More</th>

                
            </tr>
            <c:forEach var="Project" items="${projects}">
            <tr>
                <td>${Project.getTitle()}</td>
                <td>${Project.getPartner().getCompanyName()}</td>
                <td>${Project.getProjectBudget()}</td>
                <td>${Project.getSdate()}</td>
                <td>${Project.getFdate()}</td> 
                <td>${Project.getStage()}</td>
                <td><a href="#"><form action="UIServlet" method="POST">
                <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.VIEW_PROJECT_DETAILS %>"  hidden/>
                <input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">
                    View More</button></form></a></td>
                
            </tr>
            
            
            </c:forEach>
        </table>
            </div>
    </body>
</html>
