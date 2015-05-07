

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
        <div style="background-image: url('http://www.hdwallpapersimages.com/wp-content/uploads/2013/11/Dell-Computer-Wallpaper.jpg'); background-size: contain;" >
            <%-- Top area header etc. --%>
            <div class="col-md-12">
                <jsp:include page="PageComponents/header.jsp" /> 
            </div>
            <%-- left menu item --%>
            <div style="height: 85vh;" class="col-md-2 sidebar">
                <a style="font-size: 2em; font-style: italic;   ">DashBoard</a> <jsp:include page="menus.jsp"/>
            </div>
            <%-- dashboard main area --%>
            <div style="height: 85vh;" class="col-md-10">
                <jsp:include page="DashBoardPicker.jsp" /> 
            </div>
            <%-- footer area  --%>
            <div style="background-color: #31b0d5; height: 100px;" class="col-md-12 ">
            <a style="font-size: 2em;color: black;">CopyRight Group_05_2_semester </a>
            </div>
        </div>
    </body>
</html>
