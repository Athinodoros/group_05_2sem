

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
                <div class="col-md-2">
                <jsp:include page="PageComponents/header.jsp" /> fewfs
                </div>
                <div class="col-md-6">this </div>
                <div class="col-md-4">
                    Name:
                    Role:
                </div>
            </div>
                
            <%-- left menu item --%>
            <div style="height: 80vh;" class="col-md-2 sidebar">
                <a style="font-size: 2em; font-style: italic;   ">DashBoard</a> <jsp:include page="menus.jsp"/>
            </div>
            <%-- dashboard main area --%>
            <div style="height: 80vh;background-color: #f2dede; " class="col-md-10">
                  <jsp:include page="DashBoardPicker.jsp" /> 
            </div>
            <%-- footer area  --%>
            <div style="background-color: #ac2925; height: 20vh;" class="col-md-12 col-sm-12">
                <a style="font-size: 2em;color: #ffffff">CopyRight Group_05_2_semester </a>
            </div>
        </div>
    </body>
</html>
