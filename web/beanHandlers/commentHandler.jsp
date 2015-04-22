<%-- 
    Document   : commentHandler
    Created on : Apr 22, 2015, 11:57:45 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="inComment" class="layer2.domain.bean.Comment" scope="session" />
<jsp:setProperty name="inComment" property="*" />

<%= inComment.getComment() %>