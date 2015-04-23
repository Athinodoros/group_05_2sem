<%-- 
    Document   : newProject
    Created on : Apr 22, 2015, 9:54:26 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="layer2.domain.interfaces.NamingConv"%>
<!DOCTYPE html>
<link href="../bootstrap.min.css" rel="stylesheet" />
<div style="line-height: 2em; padding: 10px;">
    <form style="lead" action="UIServlet" method="POST">
        <a>Title: </a></br><input type="text" name="title" value="" required=""/></br>
        <a>Description : </a><br><textarea name="description" rows="4" cols="20">
        </textarea></br>
        <input name="command" value="reloadMain" hidden=""/>
        <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.NEWPROJECTBEAN %>" hidden=""/>
        <a>Starting date : </a></br>  <input type="date" name="sdate" value="" /></br>
        <a>Finishing date : </a></br>  <input type="date" name="fdate" value="" /></br>                                                                         .
        <a>Budget request : </a></br><input type="number" name="title" value="" /></br>
        <input type="submit" value="Create Project" />
    </form>
    
</div>
