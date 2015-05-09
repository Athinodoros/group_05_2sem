<%-- 
    Document   : commentHandler
    Created on : Apr 22, 2015, 11:57:45 PM
    Author     : Athinodoros
--%>

<%@page import="layer2.domain.bean.UserInfo"%>
<%@page import="layer2.domain.bean.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<link href="../bootstrap.min.css" />
<html>
    <head>
    </head>
    <body background="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/06/gear-wallpaper-14.jpg">
        <div class="col-md-5"></div>
        <div class="col-md-3" style="border-radius: 30px ; padding: 20px;background-color:rgba(255,255,255,0.5); margin-top: 20%; margin-bottom: 20%; margin-left: 30%; margin-right: 30%; ">
            <a style="font-size: 1.5em; font-weight: 600 ;">
                <%@page import="layer2.domain.interfaces.NamingConv" %>
                <!-- info strings here  -->
               <span style="color: #fff; background: rgba(255,255,255,0.2); border-radius: 20px; padding: 2px;"> Do you want to save the user?</span>  <br/>
                <jsp:useBean id="inComment" class="layer2.domain.bean.Comment" scope="session" />
                <jsp:setProperty name="inComment" property="*" />
                <%= inComment.getComment() %><br/>
            </a>
                <div style="float: left">
            <!-- First buttons here -->   
            <% inComment.setProject((Project)session.getAttribute("thisProject")); %>
            <% inComment.setUser((UserInfo)session.getAttribute(NamingConv.USER)); %>
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                <input type="hidden" name="<%= NamingConv.COMMAND %>" value="<%= NamingConv.RELOAD_MAIN %>" />
                <input type="hidden" name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.SEE %>" />
                <input type="hidden" name="<%= NamingConv.NEWCOMMENT %>" value="<%= NamingConv.INCOMMENT %>" />
                <input type="hidden" name="thisProjectID" value="${param.projectID}" />
                
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Save" />   
            </form>
                </div>
           
            <!-- buttons under here-->
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                
               
                <input type="hidden" name="thisProjectID" value="${param.projectID}" />
                <input type="hidden" name="<%= NamingConv.COMMAND %>" value="<%= NamingConv.RELOAD_MAIN %>" />
                <input type="hidden" name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.SEE %>" />
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Go Back" />   
            </form>
             </div>
    </body>
</html>


       
        


