<%-- 
    Document   : addUser
    Created on : Apr 10, 2015, 12:15:40 PM
    Author     : Athinodoros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>

    <body>
        <div class="form-user">
            <form>
                <a>Name</a> <input type="text" name="Name" value="" />
                <a>Last Name</a> <input type="text" name="LastName" value="" />
                <a>Password</a> <input type="text" name="Password" value="" />
                <a>e-mail</a> <input type="text" name="email" value="" /></br>
                <div>
                    
                <a>Country </a> <select name="country">
                    <option>DK</option>
                    <option>IS</option>
                    <option>NO</option>
                    <option>SE</option>
                    <option>IS</option>
                    <option>ALL</option>
                </select>
                </div>
                </br>
                <a>Role </a> <select name="role">
                    <option>Super User</option>
                    <option>Administrator</option>
                    <option>Re-Seller</option>
                    <option>Partner</option>
                </select>


            </form>
        </div>
    </body>
</html>
