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
    </head>
    <body>
        <%-- Top area header etc. --%>
        <div class="col-md-12">
            <jsp:include page="header.jsp" />
        </div>
        <%-- left menu item --%>
        <div class="col-md-3">
            <jsp:include page="menus.jsp" />
        </div>
        <%-- dashboard main area --%>
        <div class="col-md-9">
            <jsp:include page="DashBoardPicker.jsp" />
        </div>
        <%-- footer area  --%>
        <div class="col-md-12">
            
        </div>
    </body>
</html>
