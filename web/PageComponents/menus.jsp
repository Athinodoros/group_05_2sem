

<%@page import="java.io.PrintWriter"%>
<%@page import="layer2.domain.interfaces.NamingConv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .MenuButtons{
                padding: 1;
                border-radius: 8px;
                border: none;
                background: rgba(255,255,255,0.2);
            }
        </style>
    <style>li {background-color: 255,255,255,1 ; padding: 1px ; margin: 3%;}</style>
    </head>
    <body>
        <%@page  import="layer2.domain.*"  %>
        <%@page  import="layer2.domain.bean.UserInfo"  %>
        <% UserInfo user = (UserInfo) session.getAttribute("user"); %>
        <% if (user.getUrole().equals(NamingConv.ADMIN) && true) { %>
        <ul class="nav nav-sidebar">
            <li><a href><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.PROJECT_OVERVIEW %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Overview of all projects</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.PENDING_PROJECTS %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">View pending projects</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.APPROVED_PROJECTS %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">View approved projects</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.VIEW_BUDGET %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">View budget</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.SET_BUDGET %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Set Budget</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATE_USER %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Add User</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATE_COMPANY %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Add partner</button></form></a></li>
        </ul>
        <% } else if (user.getUrole().equals(NamingConv.PARTNER)) { %>
        <ul class="nav nav-sidebar ">
            <li><a href><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.PROJECT_OVERVIEW %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Overview of all projects</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATE_PROJECT %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Launch new project</button></form></a></li>
        </ul>
        <% } else if (user.getUrole().equals(NamingConv.PARTNER)) { %>
        <ul class="nav nav-sidebar ">
            <li class="active"><a href="#">Overview of projects <span class="sr-only">(current)</span></a></li>
            <li><a href="#">POE Inspect</a></li>
        </ul>    

        <%}%>
    </body>



</html>
