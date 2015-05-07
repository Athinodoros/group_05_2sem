<%-- 
    Document   : newProjectsHandler
    Created on : Apr 22, 2015, 9:55:46 PM
    Author     : Athinodoros
--%>

    <!DOCTYPE html>

<link href="../bootstrap.min.css" />
<html>
    <head>

        <%@page import="layer2.domain.interfaces.NamingConv" %>
        <link href="../bootstrap.min.css" type="text/css" />
        
        <jsp:useBean id="newProject" class="layer2.domain.bean.Project" scope="session" />
        <jsp:setProperty name="newProject" property="*" />
        
        
        <%  
            session.setAttribute(NamingConv.SDATE, request.getParameter("startdate"));
            session.setAttribute(NamingConv.FDATE, request.getParameter("findate"));
        %>
    </head>
    <body background="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/06/gear-wallpaper-14.jpg">
        <div class="col-md-5"></div>
        <div class="col-md-3" style="border-radius: 30px ; padding: 20px;background-color:rgba(255,255,255,0.5); margin-top: 20%; margin-bottom: 20%; margin-left: 30%; margin-right: 30%; ">
            <a style="font-size: 1.5em; font-weight: 600 ;">
                <!-- info strings here  -->
            

                Project Name: ${newProject.getTitle()} <br/>
                Description: ${newProject.description} <br/>
                Start date: <%=request.getParameter("startdate")%><br/>
                Final date: <%=request.getParameter("findate")%><br/>
                
            
               <span style="color: #fff; background: rgba(255,255,255,0.2); border-radius: 20px; padding: 2px;"> Do you want to save the Project?</span>  <br/>
            </a>
                <div style="float: left">
                 <form class="lead" style="margin:  4px;" action="../UIServlet">
                
                <input name="command" value=<%=NamingConv.CREATE_PROJECT%> hidden/>
               
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Save" />   
            </form>  
                </div>
               
           
            <form class="lead" style="margin:  4px;" action="../UIServlet">
                
                <input name="<%=NamingConv.COMMAND%>" value="<%=NamingConv.RELOAD_MAIN%>" hidden/>
                <input name="<%= NamingConv.MAINAREA %>" value="<%= NamingConv.CREATE_PROJECT %>" hidden/>
               
                <input style="padding: 18px; border-radius: 15px; border-color: rgba(200,200,200,0.8)" type="submit" value="Go Back" />   
            </form>
             </div>
    </body>
</html>





