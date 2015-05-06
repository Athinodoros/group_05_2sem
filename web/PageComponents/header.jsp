<%-- 
    Document   : header
    Created on : Apr 15, 2015, 2:00:18 PM
    Author     : Athinodoros
--%>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <link href="../bootstrap.min.css" />
        
            <div class="col-md-4" style="float: left; font-size: 2em; color: #ccc">Powered by DELL</div>
            <div class="col-md-4" style="float: left; margin: auto;"></div>
            <div class="col-md-4" style="float: left; padding-left: 15%;">
                <form class="lead" action="UIServlet" >
                    <input type="hidden" name="command" value="log-out" />
                    <input class="MenuButtons" style="font-size: 1.2em; color: cyan; padding-top: 2px;" type="submit" value="Log out" />
                </form>
            </div>
        
</nav>