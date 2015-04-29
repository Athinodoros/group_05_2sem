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
userinfo
<%= userInfo.getFirstname() %>
<%= userInfo.getLastname() %>
<%= userInfo.getUrole() %>
userauth
<%= newUserAth.getEmail() %>


<jsp:useBean id="newUserAth" class="layer2.domain.bean.UserAuthentication" scope="session" />
<jsp:setProperty name="newUserAth" property="*"  />

<form action="/UIServlet">
   Do you want to save the user? <input type="submit" value="Save" />   
</form>
