<%-- 
    Document   : budgetHandler
    Created on : Apr 23, 2015, 12:21:00 AM
    Author     : Athinodoros
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="layer2.domain.interfaces.NamingConv" %>

<!DOCTYPE html>
<jsp:useBean id="newBudget" class="layer2.domain.bean.Budget" scope="session" />
<jsp:setProperty name="newBudget" property="*" />

Year:
${newBudget.getQbudget()}
${newBudget.getQuarter()}
${newBudget.getQyear()}
<br>






<a>The budget has been set </a><br/>

<a>Do you want to save it?</a>
<form style="lead" action="../UIServlet" method="POST">
    <input name="command" value=<%=NamingConv.SET_BUDGET%> hidden/>
    <input type="submit" value="Set"/>
</form>
<form style="lead" action="../UIServlet" method="POST">
    <input name="command" value="reloadMain" hidden/>
    <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.SET_BUDGET %>" hidden/>
    <input type="submit" value="Go Back" />
</form>

