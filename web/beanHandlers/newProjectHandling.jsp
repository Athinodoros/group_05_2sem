<%-- 
    Document   : newProjectrHandling
    Created on : Apr 23, 2015, 3:11:11 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>

        <jsp:useBean id="newProject" class="layer2.domain.bean.Project" scope="session" />
        <jsp:setProperty name="newProject" property="*" />
        <%= newProject.getTitle()%>
        <%= newProject.getSdate()%>
        <div>
            <jsp:include page="DashBoard.jsp"  />
        </div>

    </body>
</html>
