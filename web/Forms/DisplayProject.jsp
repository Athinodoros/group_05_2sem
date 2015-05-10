<%-- 
    Document   : DisplayProject
    Created on : May 1, 2015, 8:26:43 PM
    Author     : Athinodoros
--%>

<%@page import="java.io.File"%>
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
        <%@page import="java.io.File" %> 
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% Project pr = (Project) session.getAttribute("thisProject"); %>
        <% UserInfo ui = (UserInfo) session.getAttribute(NamingConv.USER); %>
        <% ArrayList<Comment> cmLi = (ArrayList<Comment>) session.getAttribute("commentList");%>
    </head>
    <body>
        <br/>
        <br/>
        <span style="font-size: 1.2em;">Project Title : </span>  <span style="border-radius: 2px; background-color: rgba(255,255,255,0.5 );font-size: 1.1em;margin: 2px;padding: 2px;"><%= pr.getTitle()%></span><br/>
        <span style="font-size: 1.2em;">Created  By  : </span>  <span style="border-radius: 2px; background-color: rgba(255,255,255,0.5 );font-size: 1.1em;margin: 2px;padding: 2px;"><%= pr.getPartner().getCompanyName()%></span><br/><br/>
        <div style="margin:3%;">
            <span style="font-size: 1.1em; border-style: dashed;border-color: rgba(255,255,255,0.5);padding: 4px;">Starting Date :<%= pr.getSdate().toString()%></span> <span style="font-weight: 900;font-size: 1.5em;">-</span>
            <span style="font-size: 1.1em; border-style: dashed;border-color: rgba(255,255,255,0.5);padding: 4px;">Finishing Date : <%= pr.getSdate().toString()%></span> <br/>
        </div>
        <% if (ui.getUrole().equals(NamingConv.ADMIN)) {%>
        <span style="font-size: 1.2em; padding: 10px; margin-bottom:  20px;">Description : </span> <br/> <span style="background-color: rgba(255,255,255,0.5 );font-size: 1.1em; margin-top: 25px ;margin-left: 3%;padding: 3px; border-radius: 5px;border-style: solid;border-color: #31b0d5;"><%= pr.getDescription()%></span><br/>
        <%} else if (ui.getUrole().equals(NamingConv.PARTNER) && pr.getStage().equals(NamingConv.PENDING)) {%>
        <form action="UIServlet">
            <input type="hidden" name="<%= NamingConv.COMMAND%>" value="<%= NamingConv.RELOAD_MAIN%>" />
            <input type="hidden" name="<%= NamingConv.MAINAREA%>" value="<%= NamingConv.SEE%>" />
            <input type="hidden" name="<%= NamingConv.UPDATE_DESCR%>" value="<%= NamingConv.UPDATE_DESCR%>" />
            <textarea name="newDescription" rows="4" cols="20"><%= pr.getDescription()%>
            </textarea><br/>

            <input type="hidden" name="thisProjectID" value="<%= pr.getProjectID()%>" />
            <input type="submit" value="Update Description" />
        </form>
        <% } %>
            
            

        <% if (ui.getUrole().equals(NamingConv.PARTNER) && pr.getStage().equals("approved") ) {%> <br/><br/>
        <span style="font-size: 1.2em; margin: 30px;color: #101010; border-bottom: #000 dashed; padding: 1px; ">Upload a single file </span>
        <form class="lead" style="margin: 2%;" action="UIServlet" method ="POST" enctype="multipart/form-data">
            <input type="file" name="file" />
            <input type="hidden" name="ProjectPOE" value="<%= pr.getProjectID() %>" />
            <input type="hidden" name="command" value="<%= NamingConv.UPLOAD%>" />
            <span style="background-color: lightcoral">Please make sure that the uploaded file does not have <br/>
                   a name that matches the name of an already existing file <br/>
            </span>
            <input type="submit" value="submit" />
            <input type="email" name="" hidden=""/>
        </form>
        <% }%>
            
            <% File[] fli = (File[])session.getAttribute("fli"); %>
            <div class="col-md-12">
               <% if ( fli != null ) {                    %>
               <Span style="font-size: 2em; text-decoration: #000 solid blink;">Proof of execution :</Span><br/><br/>
        <%
                for(File sfile : fli ){ 
                    String filepathBuild = pr.getPartner().getCompanyName()+"\\"+pr.getProjectID()+"\\"+sfile.getName(); %>
                <div style="float: left; width: 220px; height: 220px;">
                <a href="<%= filepathBuild %>"><img src="<%= filepathBuild %>" style=" width: 150px; height: 150px; border-radius: 5px;" /></a><br/>
                <div style="text-align: center; width: 150px ;  border-radius: 5px; height: 1.3em; background-color: rgba(120,80,200,0.5);color: blue;">   <%= sfile.getName() %>  </div><br/>
                </div>
                <%}%>
               
                <%}%>
            </div>

        <br/>
        
        <div style="padding: 2%;">
            <span style="font-size: 1.2em;">The Budget Asked is : </span>  <span style="background-color: rgba(150,50,150,0.5 );font-size: 1.1em;margin: 2px;padding: 2px;"><%= String.valueOf(pr.getProjectBudget())%>DKK</span><br/>
        </div> <br/>
        <% if (ui.getUrole().equals("admin") && !pr.getStage().equals(NamingConv.APPROVED)) {%>
        <form>
            <input type="hidden" name="<%= NamingConv.COMMAND%>" value="<%= NamingConv.RELOAD_MAIN%>" />
            <input type="hidden" name="<%= NamingConv.MAINAREA%>" value="<%= NamingConv.SEE%>" />
            <input type="hidden" name="<%= NamingConv.APPROVED%>" value="<%= NamingConv.APPROVED%>" />

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
            

        <% if (cm.getUser().getUrole().equals(NamingConv.ADMIN)) {%>
        <div style=" border-top: #000; margin: 5px;"> <span style="background-color: lightgreen ; padding: 1px; border-radius: 15px;"><span style="font-weight: 800; font-size: 1.2em;">user:</span> <%= cm.getUser().getFirstname() + " " + cm.getUser().getLastname()%></span></div>    <div style="margin: 10px;"> <span style="border-color: whitesmoke; background-color: rgba(255,255,255,0.6); border-radius: 40px; margin: 35px; padding: 10px;">Said : <%= cm.getComment()%></span> </div>
        <% } %>
        <% if (cm.getUser().getUrole().equals(NamingConv.PARTNER)) {%>
        <div style=" border-top: #000; margin: 5px;"> <span style="background-color: lightskyblue ; padding: 1px; border-radius: 15px;"><span style="font-weight: 800; font-size: 1.2em;">user:</span> <%= cm.getUser().getFirstname() + " " + cm.getUser().getLastname()%></span></div>    <div style="margin: 10px;"> <span style="border-color: whitesmoke; background-color: rgba(255,255,255,0.6); border-radius: 40px; margin: 35px; padding: 10px;">Said : <%= cm.getComment()%></span> </div>
        <% } %>
        <div style="background-color: rgba(100,100,100,0.4); border-top: #000;">  ${Scomment.getUser.getFirstname}</div> : ${Scomment.getComment} 


        <% } %>
        <% }%>
        <div class="col-md-12" style="height: 250px"></div>
    </body>
</html>
