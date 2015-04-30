<%-- 
    Document   : userHandler
    Created on : Apr 15, 2015, 8:22:25 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="../bootstrap.min.css" />
<!DOCTYPE html>

<html>
    <head>
    </head>
    <body background="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/06/gear-wallpaper-14.jpg">
        <div class="col-md-5"></div>
        <%@page  import="layer2.domain.interfaces.NamingConv" %>
        <%@page  import="layer2.domain.bean.Partner" %>
        <%@page  import="layer2.domain.bean.UserInfo" %>
        <%@page  import="java.util.ArrayList" %>
        <jsp:useBean id="userInfo" class="layer2.domain.bean.UserInfo" scope="session" />
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
            <% userInfo.setCompany(newPartnerInTheBean); %>

            <!-- creating the userinfo bean-->
            <jsp:setProperty name="userInfo" property="*"  />
            <jsp:useBean id="newUserAth" class="layer2.domain.bean.UserAuthentication" scope="session" />
            <jsp:setProperty name="newUserAth" property="*"  />
            <% session.setAttribute("companyName", request.getParameter("company"));%>
            
            First Name : <%= userInfo.getFirstname()%><br/>
            Last Name :<%= userInfo.getLastname()%><br/>
            Country : <%= userInfo.getCountry()%><br/>
            Company : <%=  NAME%> <br/>
            User Role : <%= userInfo.getUrole()%><br/>
            
            UserName : <%= newUserAth.getUname()%><br/>
            Password : <%= newUserAth.getPassword()%><br/>
            e-mail : <%= newUserAth.getEmail()%><br/>
            <br/>
            <br/>
            <span style="color: #fff; background: rgba(255,255,255,0.2); border-radius: 20px; padding: 2px;"> Do you want to save the user?</span>  <br/>
            </a>
                <div style="float: left">
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                <% request.setAttribute("command", NamingConv.CREATEUSER); %>
               
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Save" />   
            </form>
                </div>
                <form class="lead" style="margin:  4px;  border-color: rgba(200,200,200,0.8)"  action="../UIServlet">
                <% request.setAttribute("command", "reloadMain"); %>
                <% request.setAttribute(NamingConv.MAINAREA, NamingConv.PROJECTLIST); %>
                <% request.setAttribute("userInfo", userInfo); %>
                <% request.setAttribute("userAth", newUserAth);%>
                <input style="padding: 18px; border-radius: 15px;" type="submit" value="GoBack" />   
            </form>
                
        </div>
    </body>
</html>





