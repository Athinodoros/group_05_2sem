<%-- 
    Document   : createCompanyHandler
    Created on : Apr 28, 2015, 10:13:58 PM
    Author     : Алеx
--%>
<%@page import="layer2.domain.interfaces.NamingConv" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<jsp:useBean id="newCompany" class="layer2.domain.bean.Partner" scope="session" />
<jsp:setProperty name="<%=NamingConv.NEW_PARTNER%>" property="*" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Do you want to save this company</h1>
        Company Name: <%=newCompany.getCompanyName()%>
        
    <form style="lead" action="../UIServlet" method="POST">
    <input name="<%=NamingConv.COMMAND%>" value=<%=NamingConv.CREATE_COMPANY%> hidden/>
    <input type="submit" value="Save" />
    </form>
        
        
        
    </body>
</html>
