<%-- 
    Document   : commentHandler
    Created on : Apr 22, 2015, 11:57:45 PM
    Author     : Athinodoros
--%>

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
                <!-- info strings here  -->
               <span style="color: #fff; background: rgba(255,255,255,0.2); border-radius: 20px; padding: 2px;"> Do you want to save the user?</span>  <br/>
                <jsp:useBean id="inComment" class="layer2.domain.bean.Comment" scope="session" />
                <jsp:setProperty name="inComment" property="*" />
                <%= inComment.getComment() %>
            </a>
                <div style="float: left">
                  <!-- First buttons here -->   
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                
               
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Save" />   
            </form>
                </div>
               
           
            <!-- buttons under here-->
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                
               
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Go Back" />   
            </form>
             </div>
    </body>
</html>


       
        


