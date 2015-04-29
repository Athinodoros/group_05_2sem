<%-- 
    Document   : aka-form
    Created on : Apr 28, 2015, 2:18:39 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
        <jsp:useBean class="aka.PersonBean" id="person" scope="request" />
        <jsp:setProperty name="person" property="*" />
        
        <jsp:useBean class="aka.Page" id="xxx" scope="page" />
        <jsp:setProperty name="xxx" property="*"/>
        <c:if test="${xxx.reloaded}"><jsp:forward page="${xxx.target}"/></c:if>
    </head>
    <body>
        <h1>Hello World!</h1>
        <hr/>
        <form method="POST">
            <input type="hidden" name="target" value="Tester"/>
            Id: <input name="id" value="${person.id}"/><br/>
            Name: <input name="name" value="${person.name}"/><br/>
            <button>Press me</button>
        </form>
    </body>
</html>
