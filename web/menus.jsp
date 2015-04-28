

<%@page import="java.io.PrintWriter"%>
<%@page import="layer2.domain.interfaces.NamingConv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .MenuButtons{
                padding: 0;
                border: none;
                background: none;
            }
        </style>
    </head>

    <body>
        <%@page  import="layer2.domain.*"  %>
        <%@page  import="layer2.domain.bean.UserInfo"  %>
        <% UserInfo user = (UserInfo) session.getAttribute("user"); %>
        <% if (user.getUrole().equals(NamingConv.ADMIN) && true) { %>
        <ul class="nav nav-sidebar">
            <li><a href><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.PROJECT_OVERVIEW %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Overview of all projects</button></form></a></li>

            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATECOMPANY %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Add company</button></form></a></li>

            <li><a href="#">View company budget</a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.BUDGET %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Budget</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.USERFORM %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Add User</button></form></a></li>

            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.PROJECTLIST %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">View pending projects</button></form></a></li>
            <li><a href="#">View approved projects</a></li>
        </ul>
        <% } else if (user.getUrole().equals(NamingConv.PARTNER)) { %>
        <ul class="nav nav-sidebar ">
            <li><a href><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.PROJECT_OVERVIEW %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Overview of all projects</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.BUDGET %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Company Budget</button></form></a></li>
            <li><a href="#"><form action="UIServlet" method="POST"><input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATEPROJECT %>"  hidden/><input name="command" value="reloadMain" hidden/><button class="MenuButtons" type="submit">Launch new project</button></form></a></li>
        </ul>
        <% } else if (user.getUrole().equals(NamingConv.PARTNER)) { %>
        <ul class="nav nav-sidebar ">
            <li class="active"><a href="#">Overview of projects <span class="sr-only">(current)</span></a></li>
            <li><a href="#">POE Inspect</a></li>
        </ul>    

        <%}%>
    </body>



</html>
