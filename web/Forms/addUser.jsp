<%-- 
    Document   : addUser
    Created on : Apr 10, 2015, 12:15:40 PM
    Author     : Athinodoros

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
--%>
<link href="bootstrap.min.css" rel="stylesheet">


        <div class="form-user">
            <form class="lead"></br>
                <a>Name</a> <input type="text" name="Name" value="" /></br></br>
                
                <a>Last Name</a> <input type="text" name="LastName" value="" /></br></br>
                <a>Password</a> <input type="text" name="Password" value="" /></br></br>
                <a>e-mail</a> <input type="text" name="email" value="" /></br></br>
                <div>
                    </br>
                    </br>
                <a>Country <span style="margin:2px;">:</span>  </a> <select name="country">
                    <option>DK</option>
                    <option>IS</option>
                    <option>NO</option>
                    <option>SE</option>
                    <option>IS</option>
                    <option>ALL</option>
                </select>
                </div>
                </br>
                </br>
                <a>Role<span style="margin:2px;">:</span>   </a> <select name="role">
                    <option>Super User</option>
                    <option>Administrator</option>
                    <option>Re-Seller</option>
                    <option>Partner</option>
                </select>


            </form>
        </div>
    