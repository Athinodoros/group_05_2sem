<%-- 
    Document   : DisplayProject
    Created on : May 1, 2015, 8:26:43 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@page import="layer2.domain.interfaces.NamingConv" %> 
        <%@page import="layer2.domain.bean.UserInfo" %> 
        <%@page import="layer2.domain.bean.Comment" %> 
        <%@page import="layer2.domain.bean.Partner" %> 
        <%@page import="layer2.domain.bean.Project" %> 
        <%@page import="java.util.ArrayList" %> 
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% Project pr = (Project) session.getAttribute("thisProject"); %>
        <% UserInfo ui = (UserInfo) session.getAttribute(NamingConv.USER); %>
        <% ArrayList<Comment> cmLi = (ArrayList<Comment>) session.getAttribute("commentList");%>
    </head>
    <body>
        <br/>
        <br/>
        <span style="font-size: 1.2em;">Project Title : </span>  <span style="background-color: rgba(255,255,255,0.5 );font-size: 1.1em;margin: 2px;padding: 2px;"><%= pr.getTitle()%></span><br/>
        <span style="font-size: 1.2em;">Created  By  : </span>  <span style="background-color: rgba(255,255,255,0.5 );font-size: 1.1em;margin: 2px;padding: 2px;"><%= pr.getPartner().getCompanyName() %></span><br/><br/>
        <div style="margin:3%;">
            <span style="font-size: 1.1em; border-style: dashed;border-color: rgba(255,255,255,0.5);padding: 4px;">Starting Date :<%= pr.getSdate().toString()%></span> <span style="font-weight: 900;font-size: 1.5em;">-</span>
            <span style="font-size: 1.1em; border-style: dashed;border-color: rgba(255,255,255,0.5);padding: 4px;">Finishing Date : <%= pr.getSdate().toString()%></span> <br/>
        </div>
        <span style="font-size: 1.2em; padding: 10px; margin-bottom:  20px;">Description : </span> <br/> <span style="background-color: rgba(255,255,255,0.5 );font-size: 1.1em; margin-top: 25px ;margin-left: 3%;padding: 3px; border-radius: 5px;border-style: solid;border-color: #31b0d5;"><%= pr.getDescription()%></span><br/>
        <div style="padding: 2%;">
        <span style="font-size: 1.2em;">The Budget Asked is : </span>  <span style="background-color: rgba(150,50,150,0.5 );font-size: 1.1em;margin: 2px;padding: 2px;"><%= String.valueOf(pr.getProjectBudget())%>DKK</span><br/>
        </div> <br/>
        <% if (ui.getUrole().equals("admin") && !pr.getStage().equals(NamingConv.APPROVED)) {%>
        <form>
            <input type="hidden" name="<%= NamingConv.COMMAND%>" value="<%= NamingConv.RELOAD_MAIN%>" />
            <input type="hidden" name="<%= NamingConv.MAINAREA%>" value="<%= NamingConv.SEE%>" />
            <input type="hidden" name="<%= NamingConv.APPROVED %>" value="<%= NamingConv.APPROVED %>" />
            
            <input type="hidden" name="thisProjectID" value="<%= pr.getProjectID()%>" />
            <input type="submit" value="Approve" />
        </form>
        <% } else {%>
        <span style="font-size: 2em;color: greenyellow; text-underline-position: auto">This Project has been approved </span>
        <%}%>
        <br/>
        <br/>
        <jsp:include page="/Forms/newComment.jsp" />
        <% if (cmLi != null) { %>
        <span style="font-size: 2em; font: sans-serif; color: #000 ">Comments</span>
        <% for (Comment cm : cmLi) {%>
        
        
        <% if (cm.getUser().getUrole().equals(NamingConv.ADMIN)) { %>
        <div style=" border-top: #000; margin: 5px;"> <span style="background-color: lightgreen ; padding: 1px; border-radius: 15px;"><span style="font-weight: 800; font-size: 1.2em;">user:</span> <%= cm.getUser().getFirstname()+" "+cm.getUser().getLastname() %></span></div>    <div style="margin: 10px;"> <span style="border-color: whitesmoke; background-color: rgba(255,255,255,0.6); border-radius: 40px; margin: 35px; padding: 10px;">Said : <%= cm.getComment() %></span> </div>
           <% } %>
        <% if (cm.getUser().getUrole().equals(NamingConv.PARTNER)) { %>
        <div style=" border-top: #000; margin: 5px;"> <span style="background-color: lightskyblue ; padding: 1px; border-radius: 15px;"><span style="font-weight: 800; font-size: 1.2em;">user:</span> <%= cm.getUser().getFirstname()+" "+cm.getUser().getLastname() %></span></div>    <div style="margin: 10px;"> <span style="border-color: whitesmoke; background-color: rgba(255,255,255,0.6); border-radius: 40px; margin: 35px; padding: 10px;">Said : <%= cm.getComment() %></span> </div>
           <% } %>
                <div style="background-color: rgba(100,100,100,0.4); border-top: #000;">  ${Scomment.getUser.getFirstname}</div> : ${Scomment.getComment} 
        

        <% } %>
        <% }%>


    </body>
</html>
