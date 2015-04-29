<%-- 
    Document   : DashBoardPicker
    Created on : Apr 11, 2015, 8:16:06 PM
    Author     : Athinodoros
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
--%>

<%@page import="layer2.domain.interfaces.NamingConv"%>
<link href="bootstrap.min.css" rel="stylesheet">

<div class="col-md-5">
        <% String pagepoint = (String) request.getAttribute("mainArea");
            if (pagepoint == NamingConv.VIEW_BUDGET) { %>
        <jsp:include page="Forms/Budget.jsp"/>
        <%} else if (pagepoint == NamingConv.PROJECTLIST) { %>
        <jsp:include page="Forms/initialForm.jsp"/>
        <%} else if (pagepoint == NamingConv.CREATEPROJECT) { %>
        <jsp:include page="Forms/newProject.jsp"/>
        <% } else if (pagepoint == NamingConv.CREATECOMPANY) { %>
        <jsp:include page="Forms/createCompanyForm.jsp" />
        <% } else if (pagepoint == NamingConv.USERFORM) { %>
        <jsp:include page="Forms/addUser.jsp" />
        <% } else if (pagepoint == NamingConv.PROJECT_OVERVIEW) { %>
        <jsp:include page="Forms/ProjectOverview.jsp" />
        <%--<% } else if (pagepoint == NamingConv.NEWPROJECTBEAN) { %>
        <jsp:include page="beanHandlers/newProjectsHandler.jsp" /> --%>
        <% } else  { %>
        <p>no jsp was loaded </p>
        <% }%>

</div>