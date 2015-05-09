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
        Title : <%= pr.getTitle()%><br/>
        Description : <%= pr.getDescription()%>
        Starting Date :<%= pr.getSdate().toString()%><br/>
        Finishing Date : <%= pr.getSdate().toString()%><br/>
        The Budget Asked is : <%= String.valueOf(pr.getProjectBudget())%><br/>
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
        <%= cm%>
        <c:forEach items="${cmLi}" var="Scomment" >
            <c:if test="${Scomment.user.urole ='admin' }" >
                <div style="background-color: #31b0d5; border-top: #000;"> user: ${Scomment.getUser.getFirstname}</div>    Said : ${Scomment.getComment} 
            </c:if>
            <c:if test="${Scomment.user.urole ='partner' }" >
                <div style="background-color: #31b0d5; border-top: #000;"> user: ${Scomment.getUser.getFirstname}</div>    Said : ${Scomment.getComment} 
                <div style="background-color: rgba(100,100,100,0.4); border-top: #000;">  ${Scomment.getUser.getFirstname}</div> : ${Scomment.getComment} 
            </c:if>
        </c:forEach>

        <% } %>
        <% }%>


    </body>
</html>
