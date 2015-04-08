<%-- 
    Document   : initialForm
    Created on : Mar 31, 2015, 9:06:02 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <% String role =  (String)request.getAttribute("role"); %>
            <% String on =  (String)request.getAttribute("ison"); %>
            <%  if (role.equals("reseller") && on.equals("")) {%>
            <form action="StartProject">
                Title: <input type="text" name="Tiltle" value="Title" />
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
            <% }else if(role.equals("admin") && on.equals("")){%> 
            
            <form action="StartProject">
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
            
            <%}else if(role.equals("admin") && on.equals("on")){%> 
                 <form action="StartProject">
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
