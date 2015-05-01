<%-- 
    Document   : fileHandling
    Created on : May 1, 2015, 3:47:04 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%= request.getPart("file").toString() %>
    </body>
</html>
