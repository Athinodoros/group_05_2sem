<%-- 
    Document   : userHandler
    Created on : Apr 15, 2015, 8:22:25 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- creating the userinfo bean-->
<%@page  import="layer2.domain.interfaces.NamingConv" %>
<%@page  import="layer2.domain.bean.Partner" %>
<%@page  import="java.util.ArrayList" %>
i am here <%--
<% ArrayList<Partner> li = (ArrayList<Partner>)request.getAttribute("list"); %>
<% Partner newPartnerInTheBean = new Partner(); %>
<% for(Partner one: li){ 
    if (one.getCompanyName().equals(NAME)) {
         newPartnerInTheBean=one;  
         break;
        }
}%> 
<% userInfo.setCompany(newPartnerInTheBean); %>****
--%>

<% String NAME = (String)request.getParameter("companyName") ; %>
<jsp:useBean id="userInfo" class="layer2.domain.bean.UserInfo" scope="session" />
<jsp:setProperty name="userInfo" property="*"  />
<jsp:useBean id="newUserAth" class="layer2.domain.bean.UserAuthentication" scope="session" />
<jsp:setProperty name="newUserAth" property="*"  />
<% session.setAttribute("companyName", request.getParameter("company") ) ; %>
userinfo
<%= userInfo.getFirstname() %>
<%= userInfo.getLastname() %>
<%= userInfo.getCountry()%>
<%=  NAME %>
<%= userInfo.getUrole() %>
user auth info
<%= newUserAth.getUname()%>
<%= newUserAth.getPassword()%>
<%= newUserAth.getEmail() %>
<form action="../UIServlet">
    <% request.setAttribute("commant", NamingConv.USERFORM ); %>
   Do you want to save the user? <input type="submit" value="Save" />   
</form>
<form action="../UIServlet">
    <% request.setAttribute("command", "reloadMain" ); %>
    <% request.setAttribute(NamingConv.MAINAREA , NamingConv.PROJECTLIST ); %>
    <% request.setAttribute("userInfo", userInfo  ); %>
    <% request.setAttribute("userAth", newUserAth  ); %>
    <input type="submit" value="GoBack" />   
</form>




