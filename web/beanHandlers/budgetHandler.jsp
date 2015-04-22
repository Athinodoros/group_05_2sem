<%-- 
    Document   : budgetHandler
    Created on : Apr 23, 2015, 12:21:00 AM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="newBudget" class="layer2.domain.bean.Budget" scope="session" />
<jsp:setProperty name="newBudget" property="*" />

<%= newBudget.getQbudget() %>