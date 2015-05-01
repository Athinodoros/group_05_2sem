<%-- 
    Document   : fileUploadForm
    Created on : Apr 23, 2015, 12:45:25 AM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page  import="layer2.domain.interfaces.NamingConv" %>
<div>
    <% session.setAttribute("command", NamingConv.UPLOAD); %>
    <% request.setAttribute(NamingConv.MAINAREA, NamingConv.SEE); %>
   
    <form action="UIServlet" method ="POST" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="submit" />
        <input type="email" name=""/>
    </form>
    
    
    
    
</div>
    