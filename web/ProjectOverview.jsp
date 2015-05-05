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
        
        <link  href="bootstrap.min.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        
    </head>
    <body class="col-md-12">
        <h1>Project overview</h1><br/>
        <div class="table-bordered table-responsive " style="padding:  auto; width: 220% ;">
            <table class="table-responsive table table-condensed table-striped " style="min-width: 900px ;">            
            <tr style="padding: auto ; width: auto">
                <th>Title</th>
                <th>Company Name</th>
                <th>Project Budget</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Stage</th>
                <th> View More</th>

                
            </tr>
            <c:if test="${projects != null}" >
            <c:forEach var="Project" items="${projects}">
            <tr>
                <td>${Project.getTitle()}</td>
                <td>${Project.getPartner().getCompanyName()}</td>
                <td>${Project.getProjectBudget()}</td>
                <td>${Project.getSdate()}</td>
                <td>${Project.getFdate()}</td> 
                <c:if test="${ Project.stage == 'pending' }" >
                    <td style="color: red;">${Project.getStage()}</td>
                </c:if>
                <c:if test="${ Project.stage == 'approved' }" >
                    <td style="color: blue;">${Project.getStage()}</td>
                </c:if>
                <c:if test="${ Project.stage == 'finished' }" >
                    <td style="color: chartreuse; font-weight: 900;">${Project.getStage()}</td>
                </c:if>
               
                <td><a href="#"><form action="UIServlet" method="POST">
                <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.VIEW_PROJECT_DETAILS %>"  hidden/>
                <input name="<%= NamingConv.COMMAND %>" value="<%= NamingConv.RELOAD_MAIN %>" hidden/><button class="MenuButtons" type="submit">
                    View More</button></form></a></td>
                
            </tr>
            
            
            </c:forEach>
            </c:if>
        </table>
            </div>
    </body>
</html>
