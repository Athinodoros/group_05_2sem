<%-- 
    Document   : initialForm
    Created on : Mar 31, 2015, 9:06:02 PM
    Author     : Athinodoros
--%>

<%@page import="layer2.domain.interfaces.NamingConv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <body>

        <link href="bootstrap.min.css" rel="stylesheet">

    
    <link href="bootstrap.min.css" rel="stylesheet">
    
    <div style="padding-top: 10px;">
            <%@page import="layer2.domain.bean.User"  %>
            <%@page import="layer2.domain.bean.*" %>  

            <% User user = (User) session.getAttribute("user"); %>



            <%  if (user.getRole().equals(NamingConv.RESELLER)) {%>
            <form class="lead" action="UIServlet">
                

                Company Name: <input type="text" name="companyName" value="" />
                <br>
                <br> 
                Title: <input type="text" name="title" value="" />
                <br>
                <br>
                Start Date: <input type="date" name="sDate" value=""/>
                <br>
                <br>
                End Date: <input type="date" name="fDate" value=""/> 
                <br>
                <br>
                Project Budget: <input type="number" name="budget" />
                <br>
                <br>
                              
               
                Description:
                <br>

                <textarea name="description" rows="6" cols="80" >
                </textarea>
                <br>
                <br>
                <input type="hidden" name="command" value="createProject">
                <input type="submit" value="Submit" />
            </form>
            <% } else if (user.getRole().equals("admin")) {%> 

            <form class="lead" action="StartProject">
                Title: <input type="text" name="Tiltle" value="Title" />
                <br>
                <br>
                Approve : <input type="checkbox" name="Approve" value="ON" readonly="" />

                <br>
                Old Comments:
                <br>
                <textarea name="PastComments" rows="6" cols="80" disabled>
                </textarea>
                <br>
                Comment input field:
                <br>

                <textarea name="PastComments" rows="6" cols="80" >
                </textarea>
                <br>
                <br>
                <input type="submit" value="Submit" />
            </form>

            <%} else if (user.getRole().equals("admin")) {%> 
            <form class="lead" action="StartProject">
                Title: <input type="text" name="Tiltle" value="Title" />
                <br>
                <br>
                Approve : <input type="checkbox" name="Approve" value="ON" readonly="" disabled checked />

                <br>
                Old Comments:
                <br>
                <textarea name="PastComments" rows="6" cols="80" disabled>
                </textarea>
                <br>
                Comment input field:
                <br>

                <textarea name="PastComments" rows="6" cols="80" >
                </textarea>
                <br>
                <br>
                <input type="submit" value="Submit" />
            </form>
            <%}%>

        </div>

    </body>
</html>
