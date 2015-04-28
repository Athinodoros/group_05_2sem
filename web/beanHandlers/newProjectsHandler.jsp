<%-- 
    Document   : newProjectsHandler
    Created on : Apr 22, 2015, 9:55:46 PM
    Author     : Athinodoros
--%>

<jsp:useBean id="newProject" class="layer2.domain.bean.Project" scope="session" />
<jsp:setProperty name="newProject" property="*" />
<%= newProject.getTitle()%>

<%= newProject.getSdate()%>
<%= newProject.getFdate()%>
<input name="command" value="reloadMain" hidden />
<input name="mainArea" value="createProject" hidden />

<jsp:include page="../Dashboard.jsp" />