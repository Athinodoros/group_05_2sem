<%-- 
    Document   : userHandler
    Created on : Apr 15, 2015, 8:22:25 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- creating the userinfo bean-->
i am here 
<jsp:useBean id="userInfo" class="layer2.domain.bean.UserInfo" scope="session" />
<jsp:setProperty name="userInfo" property="*"  />


<jsp:useBean id="newUserAth" class="layer2.domain.bean.UserAuthentication" scope="session" />
<jsp:setProperty name="newUserAth" property="*"  />
<%--
<jsp:include page="../DashBoardPicker.jsp" />
<jsp:getProperty name="user" property="*" />--%>
userinfo
<%= userInfo.getFirstname() %>
<%= userInfo.getLastname() %>
<%= userInfo.getUrole() %>
userauth
<%= newUserAth.getEmail() %>
<%--  Shouldn't it be <jsp:getProperty etc. ??? --%>
