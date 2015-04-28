<%-- 
    Document   : newProjectsHandler
    Created on : Apr 22, 2015, 9:55:46 PM
    Author     : Athinodoros
--%>

<jsp:useBean id="newProject" class="layer2.domain.bean.Project" scope="request" />
<jsp:setProperty name="newProject" property="*" />
<%= newProject.getTitle()%>
<%@page import="layer2.domain.interfaces.NamingConv" %>
<%= newProject.getSdate()%>
<%= newProject.getFdate()%>
<% 
    request.setAttribute("command", "reloadMain");
    request.setAttribute(NamingConv.MAINAREA, NamingConv.CREATEPROJECT);
    RequestDispatcher rd = request.getRequestDispatcher("UIServlet");
    rd.forward(request, response);%>