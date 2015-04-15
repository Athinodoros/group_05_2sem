<%-- 
    Document   : nDashboard
    Created on : Apr 15, 2015, 1:52:54 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dell rules</title>
        <!-- Bootstrap core CSS -->
        <link href="bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="dashboard.css" rel="stylesheet">
    </head>
    <body>
        <div >
            <%-- Top area header etc. --%>
            <div class="col-md-12">
                <jsp:include page="PageComponents/header.jsp" />
            </div>
            <%-- left menu item --%>
            <div style="background-color: #31b0d5" class="col-md-3">
                Top area header etc.  <jsp:include page="menus.jsp"/>
            </div>
            <%-- dashboard main area --%>
            <div style="background-color: #bce8f1" class="col-md-9">
                Top area header etc.   <jsp:include page="DashBoardPicker.jsp" /> 
            </div>
            <%-- footer area  --%>
            <div class="col-md-12 col-sm-12">
                This is the footer area
            </div>
        </div>
    </body>
</html>
