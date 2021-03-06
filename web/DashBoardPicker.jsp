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

<div class="col-md-10">
        <% String pagepoint = (String) request.getAttribute(NamingConv.MAINAREA);
        if (pagepoint == NamingConv.VIEW_BUDGET) { %>
        <jsp:include page="Forms/Budget.jsp"/>
        <%} else if (pagepoint == NamingConv.CREATE_PROJECT) { %>
        <jsp:include page="Forms/newProject.jsp"/>
        <% } else if (pagepoint == NamingConv.CREATE_COMPANY) { %>
        <jsp:include page="Forms/createCompanyForm.jsp" />
        <% } else if (pagepoint == NamingConv.CREATE_USER) { %>
        <jsp:include page="Forms/addUser.jsp" />
        <% } else if (pagepoint == NamingConv.PROJECT_OVERVIEW) { %>
        <jsp:include page="ProjectOverview.jsp" />
        <% } else if (pagepoint == NamingConv.SUCCESS) { %>
        <jsp:include page="UserFeedback/Success.jsp" />
        <% } else if (pagepoint == NamingConv.FAIL) { %>
        <jsp:include page="UserFeedback/Fail.jsp" />
        <% } else if (pagepoint == NamingConv.SEE) { %>
        <jsp:include page="Forms/DisplayProject.jsp" />
        <% } else  { %>
        <p>no jsp was loaded </p>
        <% }%>

</div>