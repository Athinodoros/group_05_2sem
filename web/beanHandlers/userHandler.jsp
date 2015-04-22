<%-- 
    Document   : userHandler
    Created on : Apr 15, 2015, 8:22:25 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="layer2.domain.bean.User" scope="session" />

<jsp:setProperty name="user" property="*" />


<jsp:include page="../DashBoardPicker.jsp" />
<jsp:getProperty name="user" property="*" />

<%= user.getName() %>
<%--  Shouldn't it be <jsp:getProperty etc. ??? --%>
