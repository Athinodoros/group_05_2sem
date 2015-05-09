<%-- 
    Document   : newComment
    Created on : Apr 22, 2015, 11:35:47 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="../bootstrap.min.css" rel="stylesheet" />
<div style="line-height: 2em; padding: 10px;">
    <%@page import="layer2.domain.bean.Project" %>
    <form style="lead" action="beanHandlers/commentHandler.jsp" >
        <input type="text" name="commentID" value="" hidden/>
        <input type="text" name="thisProjectID" value="${ thisProject.projectID }"  hidden/>
        <input type="text" name="projectID" value="${thisProject.projectID}" hidden/>
        <input type="text" name="usedID" value="${UserInfo.userID}" hidden />
        <a> Add a comment :</a></br> <textarea name="comment" rows="4" cols="30"></textarea>
        </br> <input type="submit" value="Submit comment" /></br>
    </form>
</div>
