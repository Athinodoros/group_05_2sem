<%-- 
    Document   : POEHandler
    Created on : Apr 23, 2015, 1:00:50 AM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<jsp:useBean id="newFile" class="layer2.domain.bean.POE" scope="session" />
<jsp:setProperty name="newFile" property="*" />
<%= newFile.getFile().toString()%>
<jsp:include page="../Forms/fileUploadForm.jsp" />