<%-- 
    Document   : ProjectOverview
    Created on : Apr 27, 2015, 11:10:29 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="layer2.domain.interfaces.NamingConv" %>
<!DOCTYPE html>
<html>
    <head>
        <% request.removeAttribute("comment"); %>
        <% session.removeAttribute("comment"); %>
        <% session.removeValue("comment"); %>
    </head>
    
    <body>
        <h1>Project overview</h1><br/>
        <div class="" style="padding:  auto; width: 100% ;">
            <table class="table-responsive table table-condensed table-striped table-bordered" style="min-width: 900px ; background-color: rgba(255,255,255,0.5);">            
            <tr style=" width: auto;">
                <th>Title</th>
                <th>Company Name</th>
                <th style="padding-left: 10%;"><form action="UIServlet" method="POST"> <input  type="hidden" name="command" value="reloadMain" /><input type="hidden" name="order" value="BUDGET" /> <input type="hidden" name="<%= NamingConv.MAINAREA %>" value="<%= request.getAttribute(NamingConv.MAINAREA) %>" /> <input class="MenuButtons" type="submit" value="Project Budget" /> </form></th>
                <th>Start Date</th>
                <th>End Date</th>
                <th><form action="UIServlet" method="POST"> <input  type="hidden" name="command" value="reloadMain" /><input type="hidden" name="order" value="BUDGET" /> <input type="hidden" name="<%= NamingConv.MAINAREA %>" value="<%= request.getAttribute(NamingConv.MAINAREA) %>" /> <input class="MenuButtons" type="submit" value="Stage" /> </form></th>
                <th> View More</th>

                
            </tr>
            <c:if test="${projects != null}" >
            <c:forEach var="Project" items="${projects}">
            <tr>
                <td>${Project.getTitle()}</td>
                <td>${Project.getPartner().getCompanyName()}</td>
                <td style="text-align: right;"><div style="padding-right: 40%;">${Project.getProjectBudget()} DKK</div></td>
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
                <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.SEE %>"  hidden/>
                <input name="thisProjectID" value="${ Project.projectID }"  hidden/>
                <input name="<%= NamingConv.COMMAND %>" value="<%= NamingConv.RELOAD_MAIN %>" hidden/><button class="MenuButtons" type="submit">
                    View More</button></form></a></td>
                
            </tr>
            
            
            </c:forEach>
            </c:if>
        </table>
            </div>
    </body>
</html>
