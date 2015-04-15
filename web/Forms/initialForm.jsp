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
    
        <div>
            <%@page import="layer2.domain.bean.User"  %>
            <%@page import="layer2.domain.bean.*" %>  

            <% User user = (User) session.getAttribute("user"); %>



            <%  if (user.getRole().equals(NamingConv.RESELLER)) {%>
            <form class="lead" action="StartProject">
                Title: <input type="text" name="Tiltle" value="Title" />
                <br>
                <br>
                Start Date: <input type="sdate" name="SDate" value=""/>
                <br>
                <br>
                End Date: <input type="edate" name="EDate" value=""/> 
                <br>
                <br>
                Approve : <input type="checkbox" name="Approve" value="ON" readonly="" disabled />

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
<<<<<<< HEAD:web/initialForm.jsp
    </body>
</html>
=======

>>>>>>> origin/master:web/Forms/initialForm.jsp
