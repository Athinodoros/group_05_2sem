<%-- 
    Document   : newProject
    Created on : Apr 22, 2015, 9:54:26 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="../bootstrap.min.css" rel="stylesheet" />
<div style="line-height: 2em; padding: 10px;">
<<<<<<< HEAD
    <form style="lead" action="UIServlet" method="POST">
        <a>Title: </a></br><input type="text" name="title" value="" required=""/></br>
        <a>Description : </a><br><textarea name="description" rows="4" cols="20">
        </textarea></br>
        <input name="command" value="reloadMain" hidden=""/>
        <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.NEWPROJECTBEAN %>" hidden=""/>
        <a>Starting date : </a></br>  <input type="date" name="sdate" value="" /></br>
        <a>Finishing date : </a></br>  <input type="date" name="fdate" value="" /></br>                                                                         .
        <a>Budget request : </a></br><input type="number" name="title" value="" /></br>
=======
    <form style="" action="../beanHandlers/newProjectsHandler.jsp">
        <a>Title: </a></br><input type="text" name="title" value="" required=""/></br>
        <a>Description : </a><br><textarea name="description" rows="4" cols="20">
        </textarea></br>
        <a>Starting date : </a></br>  <input type="date" name="sdate" value="" required=""/></br>
        <a>Finishing date : </a></br>  <input type="date" name="fdate" value="" required=""/></br>                                                                         .
        <a>Budget request : </a></br><input type="number" name="title" value="" required=""/></br>
>>>>>>> parent of 128f3f2... trying to fix 404 error after submit
        <input type="submit" value="Create Project" />
    </form>
    
</div>
