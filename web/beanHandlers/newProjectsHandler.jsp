<%-- 
    Document   : newProjectsHandler
    Created on : Apr 22, 2015, 9:55:46 PM
    Author     : Athinodoros
--%>

<jsp:useBean id="newProject" class="layer2.domain.bean.Project" />
<jsp:setProperty name="newProject" property="*" />

<%= newProject.getTitle() %>
<%= newProject.getSdate() %>