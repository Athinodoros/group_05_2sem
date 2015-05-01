<%-- 
    Document   : fileUploadForm
    Created on : Apr 23, 2015, 12:45:25 AM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div>
   
    <form action="UIServlet" method ="POST" enctype="multipart/form-data">
        <input type="hidden" name="command" value="upload" />
        <input type="file" name="file" />
        <input type="submit" value="submit" />
        <input type="email" name=""/>
    </form>
    
    
    
    
</div>
    