<%-- 
    Document   : newProjectsHandler
    Created on : Apr 22, 2015, 9:55:46 PM
    Author     : Athinodoros
--%>
<%@page import="layer2.domain.interfaces.NamingConv" %>
<link href="../bootstrap.min.css" type="text/css" />

<jsp:useBean id="newProject" class="layer2.domain.bean.Project" scope="session" />
<jsp:setProperty name="newProject" property="*" />

<%
    session.setAttribute("sdate", request.getParameter("startdate"));
    session.setAttribute("fdate", request.getParameter("findate"));
%>
Project Name: <%=newProject.getTitle()%><br/>
Start date: <%=request.getParameter("startdate")%><br/>
<a>Your project has been created </a><br/>

<a>Do you want to save it?</a>
<form style="lead" action="../UIServlet" method="POST">
    <input name="command" value=<%=NamingConv.CREATEPROJECT%> hidden/>
    <input type="submit" value="Save" />
</form>



