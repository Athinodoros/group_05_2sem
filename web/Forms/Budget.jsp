<%-- 
    Document   : Budget
    Created on : Apr 13, 2015, 3:30:50 PM
    Author     : Athinodoros
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link href="../bootstrap.min.css" rel="stylesheet"/>
<div style="line-height: 2em; padding: 10px;" >
    <form action="../beanHandlers/budgetHandler.jsp" style="lead">
    <a>Quarter</a></br> <select name="quarter">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
    </select></br>
    <a>Year:</a></br> <select name="quarter">
        <option>2014</option>
        <option>2015</option>
        <option>2016</option>
        <option>2017</option>
        <option>2018</option>
        <option>2019</option>
        <option>2020</option>
        <option>2021</option>
        <option>2022</option>
        <option>2023</option>
        <option>2024</option>
        <option>2025</option>
        <option>2026</option>
        <option>2027</option>
    </select></br>
    <a>Set the quarter Budget :</a></br><input name="qbudget" type="number" min="1" required=""/></br></br>
    <input type="submit" value="Submit Budget" />
    </form>
</div>