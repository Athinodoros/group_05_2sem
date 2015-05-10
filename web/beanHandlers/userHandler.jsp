<%-- 
    Document   : userHandler
    Created on : Apr 15, 2015, 8:22:25 PM
    Author     : Athinodoros
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="../bootstrap.min.css" />

<html>
    <head>
    </head>
    <body background="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/06/gear-wallpaper-14.jpg">
        <div class="col-md-5"></div>
        <%@page  import="layer2.domain.interfaces.NamingConv" %>
        <%@page  import="layer2.domain.bean.Partner" %>
        <%@page  import="layer2.domain.bean.UserInfo" %>
        <%@page  import="java.util.ArrayList" %>
        <jsp:useBean id="newUserInfo" class="layer2.domain.bean.UserInfo" scope="session" />
        <% String NAME = (String) request.getParameter("companyName"); %>
        <% ArrayList<Partner> li = (ArrayList<Partner>) session.getAttribute("list"); %>
        <div class="col-md-3" style="border-radius: 30px ; padding: 20px;background-color:rgba(255,255,255,0.5); margin-top: 20%; margin-bottom: 20%; margin-left: 30%; margin-right: 30%; ">
            <a style="font-size: 1.5em; font-weight: 600 ;">
            <% Partner newPartnerInTheBean = new Partner(); %>
            <% for (Partner one : li) {
                    if (one.getCompanyName().equals(NAME)) {
                        newPartnerInTheBean = one;
                        break;
                    }
                }%> 
            <% newUserInfo.setCompany(newPartnerInTheBean); %>

            <!-- creating the userinfo bean-->
            <jsp:setProperty name="newUserInfo" property="*"  />
            <jsp:useBean id="newUserAth" class="layer2.domain.bean.UserAuthentication" scope="session" />
            <jsp:setProperty name="newUserAth" property="*"  />
            <% session.setAttribute("companyName", request.getParameter("company"));%>
            
            First Name : <%= newUserInfo.getFirstname()%><br/>
            Last Name :<%= newUserInfo.getLastname()%><br/>
            Country : <%= newUserInfo.getCountry()%><br/>
            Company : <%=  NAME%> <br/>
            User Role : <%= newUserInfo.getUrole()%><br/>
            
            UserName : <%= newUserAth.getUname()%><br/>
            Password : <%= newUserAth.getPassword()%><br/>
            e-mail : <%= newUserAth.getEmail()%><br/>
            <br/>
            <br/>
            <span style="color: #fff; background: rgba(255,255,255,0.2); border-radius: 20px; padding: 2px;"> Do you want to save the user?</span>  <br/>
            </a>
                <div style="float: left">
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                    <input type="hidden" name="<%=NamingConv.MAINAREA%>" value="<%= NamingConv.CREATE_USER %>" />
                <% session.setAttribute(NamingConv.COMMAND, NamingConv.CREATE_USER); %>
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Save" />   
            </form>
                </div>
                <form class="lead" style="margin:  4px;  border-color: rgba(200,200,200,0.8)"  action="../UIServlet">
                <% session.setAttribute("userInfo", newUserInfo); %>
                <% session.setAttribute("userAth", newUserAth);%>
                <input type="hidden" name="<%= NamingConv.COMMAND %>" value="<%= NamingConv.RELOAD_MAIN %>" />
                <input type="hidden" name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATE_USER %>" />
                <input style="padding: 18px; border-radius: 15px;" type="submit" value="GoBack" />   
            </form>
                
        </div>
    </body>
</html>





