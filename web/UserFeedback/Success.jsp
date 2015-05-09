<%-- 
    Document   : Success
    Created on : Apr 29, 2015, 1:20:46 PM
    Author     : Bancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>New <%=request.getAttribute("type")%> saved successfully. </h1>
        <%  String file = (String)request.getAttribute("type") ;
if (file.equals("POE")) {%>
   <br/> Now you can go back and see your file. <br/>
   If in any case you can't see the file refresh the page :) 
 <%} %>
    </body>
</html>
