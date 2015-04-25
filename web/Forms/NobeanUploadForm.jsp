<%-- 
    Document   : NobeanUploadForm
    Created on : Apr 23, 2015, 11:21:36 PM
    Author     : user
--%>
<%@page import="java.io.*" %>"
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uploading</title>
    </head>
    <body>
        <h1>Upload!</h1>
        <form name="uploadForm" action="" method="POST" enctype="multipart/form-data">
         <%
             String saveToFile = new String();
             String contentType = request.getContentType();
             if((contentType != null) && (contentType.indexOf("multipart/form-data") >=0)){
             
             DataInputStream in = new DataInputStream(request.getInputStream());
             int formDatalength = request.getContentLength();
             }
             
             
            %>   
            
            <input type="file" name="file"> />
            <input type="submit" value="Submit" name="submit"> /> 
            
            
        </form>
    </body>
</html>
