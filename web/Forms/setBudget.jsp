<%-- 
    Document   : setBudget
    Created on : Apr 29, 2015, 1:38:38 PM
    Author     : Alex
--%>
<%@page import="layer2.domain.interfaces.NamingConv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="layer2.domain.bean.Budget" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Set the Budget</h1>
        <form class="lead" action="beanHandlers/budgetHandler.jsp" method="POST" >
            Year:
            <input type="number" name="year" required="">
            <br>
            Quarter:
            <input type="number" name="quarter" required="">
            <br>
            Budget:
            <input type="number" name="budget" required="">
            <br>
                
            
            
            
          <input name="command" value=<%=NamingConv.SET_BUDGET%> hidden/>
            <input type="submit" value="Set" />  
        </form>
    </body>
</html>
